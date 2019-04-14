/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopfinal.managers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import shopfinal.models.Goods;
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
        ADD_EMPLOYEE,
        ADD_GOODS,
        ADD_PURVEYANCE,
        ADD_DEPARTMENT,
        GET_GOODS_BY_NAME,
        UPDATE_GOODS,
        GET_NOT_PERFORMED_ORDERINGS,
        GET_PERFORMED_ORDERINGS
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
                    result = getAllGoods(params);
                    break;
                case GET_ALL_PROVIDERS:
                    result = getAllProviders();
                    break;
                case ADD_PURCHASE:
                    if (params != null) {
                        addPurchase(params);
                    }
                    break;
                case GET_SELECTED_PROVIDER_GOODS:
                    if (params != null) {
                        result = getSelectedProviderGoods(params);
                    }
                    break;
                case ADD_PROVIDER:
                    if (params != null) {
                        addProvider(params);
                    }
                    break;
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
                    }
                    break;
                case ADD_GOODS:
                    if (params != null) {
                        addGoods(params);
                    }
                    break;
                case ADD_ORDERING:
                    if (params != null) {
                        addOrdering(params);
                    }
                    break;
                case ADD_PURVEYANCE:
                    if (params != null) {
                        addPurveyance(params);
                    }
                    break;
                case ADD_DEPARTMENT:
                    if (params != null) {
                        addDepartment(params);
                    }
                    break;
                case GET_GOODS_BY_NAME:
                    if (params != null) {
                        result = getGoodsByName(params);
                    }
                    break;
                case UPDATE_GOODS:
                    if (params != null) {
                        updateGoods(params);
                    }
                    break;
                case GET_NOT_PERFORMED_ORDERINGS:
                    result = getNotPerformedOrderings();
                    break;
                case GET_PERFORMED_ORDERINGS:
                    result = getPerformedOrderings();
                    break;
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

    private Result getAllGoods(ActionParams params) throws SQLException {
        Result result = new Result();
        if (params == null) {
            result.data = db.getAllGoods(null);
        } else {
            String date = (String) params.data;
            result.data = db.getAllGoods(date);
        }
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
    
    private Result getNotPerformedOrderings() throws SQLException {
        Result result = new Result();
        result.data = db.getNotPerformedOrderings();
        return result;
    }
    
    private Result getPerformedOrderings() throws SQLException {
        Result result = new Result();
        result.data = db.getPerformedOrderings();
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

    private void addGoods(ActionParams params) throws SQLException {
        String name = (String) params.dataArr[0];
        String provider = (String) params.dataArr[1];
        int number = (int) params.dataArr[2];
        int depId = (int) params.dataArr[3];
        int price = (int) params.dataArr[4];
        String date = (String) params.dataArr[5];
        int id = db.addGoods(name, provider, number, depId);
        db.addToGoodsPrices(id, date, price);
    }

    private void addOrdering(ActionParams params) throws SQLException {
        int providerId = (int) params.dataArr[0];
        int employeeId = (int) params.dataArr[1];
        String date = (String) params.dataArr[2];
        ArrayList<Goods> goods = (ArrayList<Goods>) params.data;
        int orderingId = db.addOrdering(date, providerId, employeeId);
        db.addItemsToGoodsOrderings(goods, orderingId);
    }

    private void addPurveyance(ActionParams params) throws SQLException {
        int orderingId = (int) params.dataArr[0];
        int providerId = (int) params.dataArr[1];
        ArrayList<Goods> goods = (ArrayList<Goods>) params.data;
        int purveyanceId = db.addPurveyances(providerId);
        db.addItemsToGoodsPurveyances(goods, purveyanceId);
        db.updateGoodsNumber(goods);
        db.updateOrderingPurvId(orderingId, purveyanceId);
    }

    private void addDepartment(ActionParams params) throws SQLException {
        String name = (String) params.data;
        db.addDepartment(name);
    }

    private void addPurchase(ActionParams params) throws SQLException {
        String date = (String) params.dataArr[0];
        ArrayList<Goods> goods = (ArrayList<Goods>) params.dataArr[1];
        int id = db.addPurchase(date);
        db.addItemsToPurchasesGoods(goods, id);
        db.updateGoodsNumber(goods);
    }
    
    private Result getGoodsByName(ActionParams params) throws SQLException {
        String name = (String) params.dataArr[0];
        String date = (String) params.dataArr[1];
        Result result = new Result();
        result.data = db.getGoodsByName(name, date);
        return result;
    }
    
    private void updateGoods(ActionParams params) throws SQLException {
        int id = (int) params.dataArr[0];
        String date = (String) params.dataArr[1];
        int price = (int) params.dataArr[2];
        db.updateGoodsPrice(id, date, price);
    }

    private static ActionManager instance;

    public static synchronized ActionManager getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new ActionManager();
        }
        return instance;
    }

}
