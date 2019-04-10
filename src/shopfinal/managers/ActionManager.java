/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopfinal.managers;

import java.sql.Date;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import shopfinal.models.Purchase;
import shopfinal.models.PurchaseGoods;
import shopfinal.res.DbResources;
import shopfinal.res.StringResources;

public class ActionManager {

    private String userName;
    private String password;
    private String connectionStr;

    public enum Action {
        GET_PURCHASES_BY_DATE,
        GET_PURCHASES_FROM_TO_DATE,
        GET_PURCHASE_BY_ID,
        GET_THE_WHOLE_PURCHASE_LIST,
        GET_SALES_VOLUME_BY_NAME,
        GET_SALES_VOLUME_BY_NAME_DATE,
        GET_SALES_VOLUME,
        GET_SALES_VOLUME_BY_WEEK_DAY_MONTH_YEAR
    }

    public class Result {

        public Object[][][] data3;
        public Object[][] data2;
        public Object[] data1;
    }

    public static class ActionParams {

        public int intValue;
        public String strValue1;
        public String strValue2;
    }

    private DbAccessManager db = null;

    public ActionManager() throws ClassNotFoundException {
        db = new DbAccessManager();
    }

    public void setSettings(String userName, String password, String connectionStr) {
        this.userName = userName;
        this.password = password;

        if (connectionStr != null && !connectionStr.isEmpty()) {
            this.connectionStr = connectionStr;
        } else {
            this.connectionStr = "jdbc:mysql://localhost/" + DbResources.NameDB + "?useSSL=false";
        }
    }

    public Result performAction(Action action, ActionParams params) throws SQLException {
        Result result = new Result();

        if (db.connectionDb(connectionStr, userName, password)) {
            switch (action) {
                case GET_PURCHASES_BY_DATE:
                    if (params != null) {
                        result = getPurchasesByDate(params.strValue1);
                    }
                    break;
                case GET_PURCHASES_FROM_TO_DATE:
                    if (params != null) {
                        result = getPurchasesFromToDate(params.strValue1, params.strValue2);
                    }
                    break;
                case GET_PURCHASE_BY_ID:
                    if (params != null) {
                        result = getPurchaseById(params.intValue);
                    }
                    break;
                case GET_THE_WHOLE_PURCHASE_LIST:
                    result = getTheWholePurchaseList();
                    break;
                case GET_SALES_VOLUME_BY_NAME:
                    if (params != null) {
                        result = getSalesVolumeByName(params.strValue1);
                    }
                    break;
                case GET_SALES_VOLUME_BY_NAME_DATE:
                    if (params != null) {
                        result = getSalesVolumesByNameDate(params.strValue1, params.strValue2);
                    }
                    break;
                case GET_SALES_VOLUME:
                    result = getSalesVolume();
                    break;
                case GET_SALES_VOLUME_BY_WEEK_DAY_MONTH_YEAR:
                    if (params != null) {
                        result = getSalesVolumeByWeekDayMonthYear(params);
                    }
                default:
                    break;
            }

            db.disConnect();
        } else {
            System.out.println("No connection to DB " + DbResources.NameDB);
        }

        return result;
    }

    private Result getPurchasesByDate(String date) throws SQLException {
        Result result = new Result();
        ArrayList<Purchase> purchases = db.getPurchasesByDate(date);
        parsePurchaseList(purchases, result);
        return result;
    }

    private Result getPurchasesFromToDate(String dateFrom, String dateTo) throws SQLException {
        Result result = new Result();
        ArrayList<Purchase> purchases = db.getPurchasesFromToDate(dateFrom, dateTo);
        parsePurchaseList(purchases, result);
        return result;
    }

    private Result getPurchaseById(int id) throws SQLException {
        Result result = new Result();
        ArrayList<Purchase> purchases = db.getPurchaseById(id);
        parsePurchaseList(purchases, result);
        return result;
    }

    private Result getTheWholePurchaseList() throws SQLException {
        Result result = new Result();
        ArrayList<Purchase> purchases = db.getAllPurchases();
        parsePurchaseList(purchases, result);
        return result;
    }

    private void parsePurchaseList(ArrayList<Purchase> purchases, Result result) {
        result.data3 = new Object[purchases.size()][][];
        result.data2 = new Object[purchases.size()][];

        for (int i = 0; i < purchases.size(); ++i) {
            Purchase purchase = purchases.get(i);
            ArrayList<PurchaseGoods> goods = purchase.getGoods();
            result.data3[i] = new Object[goods.size()][];
            Object[] items3 = new Object[3];
            items3[0] = purchase.getId();
            items3[1] = purchase.getDate();
            items3[2] = purchase.getCost();
            result.data2[i] = items3;
            for (int j = 0; j < goods.size(); ++j) {
                Object[] items4 = new Object[4];
                PurchaseGoods g = goods.get(j);
                items4[0] = g.getName();
                items4[1] = g.getPrice();
                items4[2] = g.getNumber();
                items4[3] = g.getTotalPrice();
                result.data3[i][j] = items4;
            }
        }
    }

    private Result getSalesVolumesByNameDate(String name, String date) throws SQLException {
        Result result = new Result();
        result.data2 = new Object[1][3];
        result.data2[0][0] = name;
        result.data2[0][1] = date;
        result.data2[0][2] = db.getSalesVolumeByNameDate(name, date);
        return result;
    }

    private Result getSalesVolumeByName(String name) throws SQLException {
        Result result = new Result();
        result.data2 = new Object[1][3];
        result.data2[0][0] = name;
        java.util.Date utilDate = new java.util.Date();
        Date date = new Date(utilDate.getTime());
        result.data2[0][1] = date.toString();
        result.data2[0][2] = db.getSalesVolumeByName(name);
        return result;
    }

    private Result getSalesVolume() throws SQLException {
        Result result = new Result();

        ArrayList<String> names = db.getDepartmentsNames();

        result.data2 = new Object[names.size() + 1][3];
        java.util.Date utilDate = new java.util.Date();
        Date date = new Date(utilDate.getTime());
        String dateStr = date.toString();
        double totalVolume = 0;

        for (int i = 0; i < names.size(); ++i) {
            String name = names.get(i);
            result.data2[i][0] = name;
            result.data2[i][1] = dateStr;
            double res = db.getSalesVolumeByName(name);
            result.data2[i][2] = res;
            totalVolume += res;
        }

        result.data2[names.size()][0] = StringResources.allDepartments;
        result.data2[names.size()][1] = dateStr;
        result.data2[names.size()][2] = totalVolume;

        return result;
    }

    private Result getSalesVolumeByWeekDayMonthYear(ActionParams params) throws SQLException {
        Result result = new Result();
        int weekDay = getWeekDayNumber(params.strValue1);
        int month = getMonthNumber(params.strValue2);
        int year = params.intValue;
        String datesRes = getDatesByWeekDayMonthYear(weekDay, month, year, DbResources.CashFlowDate);
        result.data2 = new Object[1][3];
        result.data2[0][0] = StringResources.allDepartments;
        result.data2[0][1] = year + ", " + month + ", " + weekDay;
        result.data2[0][2] = db.getSalesVolumeByDates(datesRes);
        return result;
    }

    private int getWeekDayNumber(String day) {
        // Saturday = 1, Monday = 2, ... Sunday = 7
        switch (day) {
            case StringResources.Saturday:
                return 1;
            case StringResources.Monday:
                return 2;
            case StringResources.Tuesday:
                return 3;
            case StringResources.Wednesday:
                return 4;
            case StringResources.Thursday:
                return 5;
            case StringResources.Friday:
                return 6;
            case StringResources.Sunday:
                return 7;
            default:
                return 0;
        }
    }

    private int getMonthNumber(String month) {
        switch (month) {
            case StringResources.January:
                return 1;
            case StringResources.February:
                return 2;
            case StringResources.March:
                return 3;
            case StringResources.April:
                return 4;
            case StringResources.May:
                return 5;
            case StringResources.June:
                return 6;
            case StringResources.July:
                return 7;
            case StringResources.August:
                return 8;
            case StringResources.September:
                return 9;
            case StringResources.October:
                return 10;
            case StringResources.November:
                return 11;
            case StringResources.December:
                return 12;
            default:
                return 0;
        }
    }

    private String getDatesByWeekDayMonthYear(int day, int month, int year, String date) {
        StringBuilder dates = new StringBuilder("");
        Calendar c = Calendar.getInstance();
        java.util.Date utilDate;
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        for (int i = 1; i <= daysInMonth; ++i) {
            String monthStr = month + "";
            if (month < 10) {
                monthStr = "0" + month;
            }

            String dayStr = i + "";
            if (i < 10) {
                dayStr = "0" + i;
            }

            Calendar dateC = Calendar.getInstance();
            dateC.set(year, month - 1, i);
            utilDate = dateC.getTime();
            c.setTime(utilDate);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

            if (dayOfWeek == day) {
                String sqlDate = year + "-" + monthStr + "-" + dayStr;
                dates.append(date + "='" + sqlDate + "' or ");
            }

        }

        if (dates.length() > 4) {
            dates.setCharAt(dates.length() - 1, ' ');
            dates.setCharAt(dates.length() - 2, ' ');
            dates.setCharAt(dates.length() - 3, ' ');
            dates.setCharAt(dates.length() - 4, ')');

        }

        return "(" + dates.toString();
    }
    
    private static ActionManager instance;
	
    public static synchronized ActionManager getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new ActionManager();
	}
	return instance;
    }
    
}
