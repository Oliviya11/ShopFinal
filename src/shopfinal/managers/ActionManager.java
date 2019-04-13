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
        GET_ALL_PURCHASES,
        GET_ALL_PROVIDERS,
        GET_ALL_GOODS,
        ADD_PURCHASE,
        ADD_ORDERING,
        GET_SELECTED_PROVIDER_GOODS,
        ADD_PROVIDER,
        GET_ALL_ORDERINGS,
        GET_ALL_PURVEYANCES,
        GET_ALL_EMPLOYEES,
        GET_ALL_DEPARTMENTS,
        ADD_EMPLOYEE
    }

    public class Result {

        public Object[][][] data3;
        public Object[][] data2;
        public Object[] data1;
        public Object data;
    }

    public static class ActionParams {

        public int intValue;
        public String strValue1;
        public String strValue2;
        public Object data;
        public Object[] dataArr;
    }

    private DbAccessManager db = null;

    public ActionManager() throws ClassNotFoundException {
        db = new DbAccessManager();
        setSettings("Admin", "1111", null);
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
                case GET_ALL_PURCHASES:
                    result = getAllPurchases();
                    break;
                case GET_ALL_GOODS:
                    result = getAllGoods();
                    break;
                case GET_ALL_PROVIDERS:
                    result = getAllProviders();
                    break;
                case ADD_PURCHASE:
                    if (params != null) {
                        break;
                    }
                case GET_SELECTED_PROVIDER_GOODS:
                    if (params != null) {
                        result = getSelectedProviderGoods(params);
                        break;
                    }
                case ADD_PROVIDER:
                    if (params != null) {
                        addProvider(params);
                        break;
                    }
                case GET_ALL_ORDERINGS:
                    result = getAllOrderings();
                    break;
                case GET_ALL_PURVEYANCES:
                    result = getAllPurveyances();
                    break;
                case GET_ALL_EMPLOYEES:
                    result = getAllEmployees();
                    break;
                case GET_ALL_DEPARTMENTS:
                    result = getAllDepartments();
                    break;
                case ADD_EMPLOYEE:
                    if (params != null) {
                       addEmployee(params);
                       break;
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
    
    private void addProvider(ActionParams params) throws SQLException {
        db.addProvider(params.strValue1);
    }

    private Result getPurchasesByDate(String date) throws SQLException {
        Result result = new Result();
        ArrayList<Purchase> purchases = db.getPurchasesByDate(date);
        // parsePurchaseList(purchases, result);
        return result;
    }

    private Result getPurchasesFromToDate(String dateFrom, String dateTo) throws SQLException {
        Result result = new Result();
        ArrayList<Purchase> purchases = db.getPurchasesFromToDate(dateFrom, dateTo);
        result.data = purchases;
        return result;
    }

    private Result getPurchaseById(int id) throws SQLException {
        Result result = new Result();
        ArrayList<Purchase> purchases = db.getPurchaseById(id);
        result.data = purchases;
        return result;
    }

    private Result getAllPurchases() throws SQLException {
        Result result = new Result();
        ArrayList<Purchase> purchases = db.getAllPurchases();
        result.data = purchases;
        return result;
    }
    
    private Result getAllGoods() throws SQLException {
        Result result = new Result();
        result.data = db.getAllGoods();
        return result;
    }

    private Result getSelectedProviderGoods(ActionParams params) throws SQLException {
        Result result = new Result();
        result.data = db.getSelectedProviderGoods(params.strValue1);
        return result;
    }

    private Result getAllProviders() throws SQLException {
        Result result = new Result();
        result.data = db.getAllProviders();
        return result;
    }

    private Result getAllOrderings() throws SQLException {
        Result result = new Result();
        result.data = db.getAllOrderings();
        return result;
    }
    
    private Result getAllPurveyances() throws SQLException {
        Result result = new Result();
        result.data = db.getAllPurveyances();
        return result;
    }
    
    private Result getAllEmployees() throws SQLException {
        Result result = new Result();
        result.data = db.getAllEmployees();
        return result;
    }
    
    private Result getAllDepartments() throws SQLException {
        Result result = new Result();
        result.data = db.getAllDepartments();
        return result;
    }
    
    private void addEmployee(ActionParams params) throws SQLException {
        String pib = (String) params.dataArr[0];
        int cost = (int) params.dataArr[1];
        int depId = (int) params.dataArr[2];
        db.addEmployee(pib, cost, depId);
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

    private static ActionManager instance;

    public static synchronized ActionManager getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new ActionManager();
        }
        return instance;
    }

}
