/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopfinal.managers;

import java.sql.*;
import java.util.ArrayList;
import shopfinal.models.*;
import shopfinal.res.DbResources;

public class DbAccessManager {

    private Connection conn = null;

    public DbAccessManager() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    public boolean connectionDb(String connectionString, String user, String passwd) throws SQLException {
        conn = DriverManager.getConnection(connectionString, user, passwd);
        return true;
    }

    public void disConnect() throws SQLException {
        conn.close();
        conn = null;
    }

    public double getSalesVolumeByDates(String dates) throws SQLException {
        String purchaseBenefitsIds = getCashFlowsIdsFromPurchaseBenefits();
        double purchaseBenefits = getCashFlowsSumByIdsAndDates(purchaseBenefitsIds, dates);

        String purveyancesCostsIds = getCashFlowsIdsFromPurveyancesCosts();
        double purveyancesCosts = getCashFlowsSumByIdsAndDates(purveyancesCostsIds, dates);

        return purchaseBenefits - purveyancesCosts;
    }

    private String getCashFlowsIdsFromPurchaseBenefits() throws SQLException {
        return getCashFlowsIdsFromTable(DbResources.PurchaseBenefits);
    }

    private String getCashFlowsIdsFromPurveyancesCosts() throws SQLException {
        return getCashFlowsIdsFromTable(DbResources.PurveyancesCosts);
    }

    private String getCashFlowsIdsFromTable(String name) throws SQLException {
        StringBuilder ids = new StringBuilder("(");

        String sql = "select " + DbResources.CashFlowId + " from " + name;

        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            int id = rs.getInt(DbResources.CashFlowId);
            ids.append(id + ", ");
        }
        ids.setCharAt(ids.length() - 2, ')');

        return ids.toString();
    }

    public ArrayList<String> getDepartmentsNames() throws SQLException {
        ArrayList<String> names = new ArrayList<String>();
        String sql = "select " + DbResources.DepartmentName + " from " + DbResources.Departments;
        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            String name = rs.getString(DbResources.DepartmentName);
            names.add(name);
        }
        return names;
    }

    public double getSalesVolumeByName(String name) throws SQLException {
        int departmentId = getDepartmentIdByName(name);

        String goodsIdsStr = getGoodsIdsByDepartmentId(departmentId);

        double costs = getCostsByGoodsIds(goodsIdsStr);
        double benefit = getBenefitByGoodsIds(goodsIdsStr);

        return benefit - costs;
    }

    public double getSalesVolumeByNameDate(String name, String date) throws SQLException {
        int departmentId = getDepartmentIdByName(name);

        String goodsIdsStr = getGoodsIdsByDepartmentId(departmentId);

        double costs = getCostsByGoodsIdsDate(goodsIdsStr, date);
        double benefits = getBenefitsByGoodsIdsDate(goodsIdsStr, date);

        return benefits - costs;
    }

    double getBenefitByGoodsIds(String goodsIdsStr) throws SQLException {
        double benefit = 0;
        String sql = "select * from " + DbResources.GoodsPurchases + " where " + DbResources.GoodsId + " in "
                + goodsIdsStr;

        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            int purchaseId = rs.getInt(DbResources.PurchaseId);
            int goodsId = rs.getInt(DbResources.GoodsId);
            int number = rs.getInt(DbResources.Number);
            int cashFlowId = getCashFlowIdByPurchaseId(purchaseId);
            Date date = getDateByCashFlowId(cashFlowId);
            double price = getActualGoodsPriceByDateAndId(date, goodsId);
            benefit += (number * price);
        }

        return benefit;
    }

    Date getDateByCashFlowId(int cashFlowId) throws SQLException {
        Date date = null;
        String sql = "select " + DbResources.CashFlowDate + " from " + DbResources.CashFlows + " where "
                + DbResources.CashFlowId + "=" + cashFlowId;
        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            date = rs.getDate(DbResources.CashFlowDate);
        }
        return date;
    }

    double getCostsByGoodsIds(String goodsIdsStr) throws SQLException {
        double costs = 0;
        String purveyancesIdsStr = getTableIdsByGoodsIds(goodsIdsStr, DbResources.GoodsPurveyances,
                DbResources.PurveyanceId);
        String cashFlowsIdsStr = getCashFlowsIdsByIds(purveyancesIdsStr, DbResources.PurveyancesCosts,
                DbResources.PurveyanceId);
        costs = getCashFlowsSumByIds(cashFlowsIdsStr);
        return costs;
    }

    double getCostsByGoodsIdsDate(String goodsIdsStr, String date) throws SQLException {
        double costs = 0;
        String purveyancesIdsStr = getTableIdsByGoodsIds(goodsIdsStr, DbResources.GoodsPurveyances,
                DbResources.PurveyanceId);
        String cashFlowsIdsStr = getCashFlowsIdsByIds(purveyancesIdsStr, DbResources.PurveyancesCosts,
                DbResources.PurveyanceId);
        costs = getCashFlowsSumByIdsDate(cashFlowsIdsStr, date);
        return costs;
    }

    double getBenefitsByGoodsIdsDate(String goodsIdsStr, String date) throws SQLException {
        double benefits = 0;
        String purchasesIdsStr = getTableIdsByGoodsIds(goodsIdsStr, DbResources.GoodsPurchases, DbResources.PurchaseId);
        String cashFlowsIdsStr = getCashFlowsIdsByIds(purchasesIdsStr, DbResources.PurchaseBenefits,
                DbResources.PurchaseId);
        benefits = getCashFlowsSumByIdsDate(cashFlowsIdsStr, date);
        return benefits;
    }

    private double getCashFlowsSumByIds(String flowsIds) throws SQLException {
        double sum = 0;
        String sql = "select sum(" + DbResources.Cost + ") from " + DbResources.CashFlows + " where "
                + DbResources.CashFlowId + " in " + flowsIds;
        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            String s = rs.getString(1);
            sum = Double.parseDouble(s);
        }

        return sum;
    }

    private double getCashFlowsSumByIdsDate(String flowsIds, String date) throws SQLException {
        double sum = 0;
        String sql = "select sum(" + DbResources.Cost + ") from " + DbResources.CashFlows + " where "
                + DbResources.CashFlowId + " in " + flowsIds + " and " + DbResources.CashFlowDate + "=" + "'" + date
                + "'";
        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            String s = rs.getString(1);
            if (s != null) {
                sum = Double.parseDouble(s);
            }
        }

        return sum;
    }

    private double getCashFlowsSumByIdsAndDates(String flowsIds, String dates) throws SQLException {
        double sum = 0;
        String sql = "select sum(" + DbResources.Cost + ") from " + DbResources.CashFlows + " where "
                + DbResources.CashFlowId + " in " + flowsIds + "and " + dates;
        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            String s = rs.getString(1);
            sum = Double.parseDouble(s);
        }

        return sum;
    }

    private String getCashFlowsIdsByIds(String purIds, String table, String field) throws SQLException {
        StringBuilder flowsIds = new StringBuilder("(");
        String sql = "select " + DbResources.CashFlowId + " from " + table + " where " + field + " in " + purIds;

        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            int flowId = rs.getInt(DbResources.CashFlowId);
            flowsIds.append(flowId + ", ");
        }

        flowsIds.setCharAt(flowsIds.length() - 2, ')');

        return flowsIds.toString();
    }

    private double getCostByCashFlowId(int cashFlowId) throws SQLException {
        double cost = 0.0;
        String sql = "select " + DbResources.Cost + " from " + DbResources.CashFlows + " where "
                + DbResources.CashFlowId + "=" + cashFlowId;
        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            cost = rs.getDouble(DbResources.Cost);
        }

        return cost;
    }

    private String getTableIdsByGoodsIds(String goodsIds, String tableName, String field) throws SQLException {
        StringBuilder purvIds = new StringBuilder("(");
        String sql = "select " + field + " from " + tableName + " where " + DbResources.GoodsId + " in " + goodsIds;

        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            int id = rs.getInt(field);
            purvIds.append(id + ", ");
        }

        purvIds.setCharAt(purvIds.length() - 2, ')');

        return purvIds.toString();
    }

    private int getDepartmentIdByName(String name) throws SQLException {
        int id = 0;
        String sql = "select " + DbResources.DepartmentId + " from " + DbResources.Departments + " where "
                + DbResources.DepartmentName + "=" + "'" + name + "'";

        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            id = rs.getInt(DbResources.DepartmentId);
        }

        return id;
    }

    private String getGoodsIdsByDepartmentId(int id) throws SQLException {
        StringBuilder ids = new StringBuilder("(");
        String sql = "select " + DbResources.GoodsId + " from " + DbResources.Goods + " where "
                + DbResources.DepartmentId + "=" + id;

        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            id = rs.getInt(DbResources.GoodsId);
            ids.append(id + ", ");
        }

        ids.setCharAt(ids.length() - 2, ')');

        return ids.toString();
    }

    private int getCashFlowIdByPurchaseId(int purchaseId) throws SQLException {
        int cashFlowId = 0;
        String sql = "select " + DbResources.CashFlowId + " from " + DbResources.PurchaseBenefits + " where "
                + DbResources.PurchaseId + "=" + purchaseId;

        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            cashFlowId = rs.getInt(DbResources.CashFlowId);
        }

        return cashFlowId;
    }

    private String getGoodsNameById(int goodsId) throws SQLException {
        String name = "";
        String sql = "select " + DbResources.GoodsName + " from " + DbResources.Goods + " where " + DbResources.GoodsId
                + "=" + goodsId;
        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            name = rs.getString(DbResources.GoodsName);
        }

        return name;
    }

    private double getActualGoodsPriceByDateAndId(Date date, int goodsId) throws SQLException {
        Double price = 0.0;
        Date prevDate = null;
        String sql = "select " + DbResources.Price + ", " + DbResources.GoodsPricesDate + " from "
                + DbResources.GoodsPrices + " where " + DbResources.GoodsPricesDate + "<=" + "'" + date + "'" + " and "
                + DbResources.GoodsId + "=" + goodsId;

        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            Date d = rs.getDate(DbResources.GoodsPricesDate);
            if (prevDate == null || d.after(prevDate)) {
                prevDate = d;
                price = rs.getDouble(DbResources.Price);
            }
        }

        return price;
    }

    private ArrayList<PurchaseGoods> getGoodsIds(int id, Date date) throws SQLException {
        ArrayList<PurchaseGoods> purchasesGoods = new ArrayList<PurchaseGoods>();
        String sql = "select * from " + DbResources.GoodsPurchases + " where " + DbResources.PurchaseId + "=" + id;
        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            int goodsId = rs.getInt(DbResources.GoodsId);
            int number = rs.getInt(DbResources.Number);
            double price = getActualGoodsPriceByDateAndId(date, goodsId);
            String name = getGoodsNameById(goodsId);
            PurchaseGoods purchaseGoods = new PurchaseGoods(goodsId, name, price, number);
            purchasesGoods.add(purchaseGoods);
        }

        return purchasesGoods;
    }

    public ArrayList<Purchase> getAllPurchases() throws SQLException {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        String sql = "select * from " + DbResources.Purchases;
        ResultSet rs = getResultSet(sql);
        getPurchase(rs, purchases);

        return purchases;
    }

    public ArrayList<Purchase> getPurchaseById(int purchaseId) throws SQLException {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        String sql = "select * from " + DbResources.Purchases + " where " + DbResources.PurchaseId + "=" + purchaseId;
        ResultSet rs = getResultSet(sql);
        getPurchase(rs, purchases);

        return purchases;
    }

    public ArrayList<Purchase> getPurchasesByDate(String date) throws SQLException {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        String sql = "select * from " + DbResources.Purchases + " where " + DbResources.PurchaseDate + "=" + "'" + date
                + "'";
        ResultSet rs = getResultSet(sql);
        getPurchase(rs, purchases);

        return purchases;
    }

    public ArrayList<Purchase> getPurchasesFromToDate(String dateFrom, String dateTo) throws SQLException {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        String sql = "select * from " + DbResources.Purchases + " where " + DbResources.PurchaseDate + ">=" + "'"
                + dateFrom + "'" + " and " + DbResources.PurchaseDate + "<=" + "'" + dateTo + "'";
        ResultSet rs = getResultSet(sql);
        getPurchase(rs, purchases);

        return purchases;
    }

    private ResultSet getResultSet(String sql) throws SQLException {
        Statement s = conn.createStatement();
        s.execute(sql);
        ResultSet rs = s.getResultSet();
        return rs;
    }
    
    private void executSql(String sql) throws SQLException {
        Statement s = conn.createStatement();
        s.execute(sql);
    }

    private void getPurchase(ResultSet rs, ArrayList<Purchase> purchases) throws SQLException {
        while ((rs != null) && (rs.next())) {
            Date date = rs.getDate(DbResources.PurchaseDate);
            int id = rs.getInt(DbResources.PurchaseId);
            ArrayList<PurchaseGoods> goods = getGoodsIds(id, date);
            int cashFlowId = getCashFlowIdByPurchaseId(id);
            double cost = getCostByCashFlowId(cashFlowId);
            Purchase purchase = new Purchase(id, date, goods, cost);
            purchases.add(purchase);
        }
    }
    
    public ArrayList getAllGoods() throws SQLException {
        ArrayList goods = new ArrayList();
        String sql = "select * from " + DbResources.Goods;
        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            Goods goodsItem = new Goods(
                    rs.getInt(DbResources.GoodsId),
                    rs.getString(DbResources.GoodsName),
                    rs.getString(DbResources.Provider),
                    rs.getInt(DbResources.Number),
                    rs.getInt(DbResources.Minimum),
                    rs.getInt(DbResources.DepartmentId)
            );
            goods.add(goodsItem);
        }
        return goods;
    }
    
    public ArrayList getSelectedProviderGoods(String providerName) throws SQLException {
        ArrayList goods = new ArrayList();
        String sql = "select * from " + DbResources.Goods + " where "
                + DbResources.Provider + "='"+providerName+"'";
        ResultSet rs = getResultSet(sql);
        System.out.println("sql: " + sql);
        while ((rs != null) && (rs.next())) {
            Goods goodsItem = new Goods(
                    rs.getInt(DbResources.GoodsId),
                    rs.getString(DbResources.GoodsName),
                    rs.getString(DbResources.Provider),
                    rs.getInt(DbResources.Number),
                    rs.getInt(DbResources.Minimum),
                    rs.getInt(DbResources.DepartmentId)
            );
            goods.add(goodsItem);
        }
        return goods;
    }
  
    public ArrayList getAllProviders() throws SQLException {
        ArrayList providers = new ArrayList();
        String sql = "select * from " + DbResources.Providers;
        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            Provider providerItem = new Provider(
                    rs.getInt(DbResources.ProviderId),
                    rs.getString(DbResources.ProviderName)
            );
            providers.add(providerItem);
        }
        return providers;
    }
    
    private void updateGoods(ArrayList<Goods> goods) throws SQLException {
        for (int i = 0; i < goods.size(); ++i) {
            String sql = "update " + DbResources.Goods + " set " 
            + DbResources.Number + " = '" + goods.get(i).number + "' where " +
             DbResources.GoodsId + " = '" + goods.get(i).id + "'";
            executSql(sql);
        }
    }
    
    public void createPurchase(ArrayList<Goods> goods) throws SQLException {
        updateGoods(goods);
    }
}
