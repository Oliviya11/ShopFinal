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

    private ArrayList<Goods> getGoodsIdsFromGoodsPurchases(int id, Date date) throws SQLException {
        ArrayList<Goods> listGoods = new ArrayList<Goods>();
        String sql = "select * from " + DbResources.GoodsPurchases + " where " + DbResources.PurchaseId + "=" + id;
        ResultSet rs = getResultSet(sql);
        getGoodsItemFromSet(rs, listGoods, date);

        return listGoods;
    }
    
    private ArrayList<Goods> getGoodsIdsFromGoodsOrderings(int id) throws SQLException {
        ArrayList<Goods> listGoods = new ArrayList<Goods>();
        String sql = "select * from " + DbResources.GoodsOrderings + " where " + DbResources.OrderingId + "=" + id;
        ResultSet rs = getResultSet(sql);
        getGoodsItemFromSet(rs, listGoods, null);

        return listGoods;
    }
    
    private ArrayList<Goods> getGoodsIdsFromGoodsPurveyances(int id) throws SQLException {
        ArrayList<Goods> listGoods = new ArrayList<Goods>();
        String sql = "select * from " + DbResources.GoodsPurveyances + " where " + DbResources.PurveyanceId + "=" + id;
        ResultSet rs = getResultSet(sql);
        getPurveyancesGoodsItemFromSet(rs, listGoods);
        return listGoods;
    }
    
    private void getPurveyancesGoodsItemFromSet(ResultSet rs, ArrayList<Goods> listGoods) throws SQLException {
        while ((rs != null) && (rs.next())) {
            int goodsId = rs.getInt(DbResources.GoodsId);
            int number = rs.getInt(DbResources.Number);
            double price = rs.getDouble(DbResources.Price);
            String name = getGoodsNameById(goodsId);
            Goods goods = new Goods(goodsId, name, number, price);
            listGoods.add(goods);
        }
    }
    
    private void getGoodsItemFromSet(ResultSet rs, ArrayList<Goods> listGoods, Date date) throws SQLException {
        while ((rs != null) && (rs.next())) {
            int goodsId = rs.getInt(DbResources.GoodsId);
            int number = rs.getInt(DbResources.Number);
            double price = 0;
            if (date != null) {
                price = getActualGoodsPriceByDateAndId(date, goodsId);
            }
            String name = getGoodsNameById(goodsId);
            Goods goods = new Goods(goodsId, name, number, price);
            listGoods.add(goods);
        }
    }
    
    public ArrayList<Purveyance> getAllPurveyances() throws SQLException {
        ArrayList<Purveyance> purveyances = new ArrayList<Purveyance>();
        String sql = "select * from " + DbResources.Purveyances;
        ResultSet rs = getResultSet(sql);
        getPurveyancesFromSet(rs, purveyances);
        return purveyances;
    }
    
    public ArrayList<Ordering> getAllOrderings() throws SQLException {
        ArrayList<Ordering> orderings = new ArrayList<Ordering>();
        String sql = "select * from " + DbResources.Orderins;
        ResultSet rs = getResultSet(sql);
        getOrderingFromSet(rs, orderings);
        return orderings;
    }
    
    private void getOrderingFromSet(ResultSet rs, ArrayList<Ordering> orderings) throws SQLException {
        while ((rs != null) && (rs.next())) {
            Date date = rs.getDate(DbResources.OrderingDate);
            int id = rs.getInt(DbResources.OrderingId);
            ArrayList<Goods> goods = getGoodsIdsFromGoodsOrderings(id);
            int providerId = rs.getInt(DbResources.ProviderId);
            Provider provider = getProviderById(providerId);
            int employeeId = rs.getInt(DbResources.EmployeeId);
            Employee employee = getEmployeeById(employeeId);
            int pruvId = rs.getInt(DbResources.PurveyanceId);
            Ordering ordering = new Ordering(id, date, provider,pruvId, employee, goods);
            orderings.add(ordering);
        }
    }

    private void getPurveyancesFromSet(ResultSet rs, ArrayList<Purveyance> purveyances) throws SQLException {
         while ((rs != null) && (rs.next())) {
            int id = rs.getInt(DbResources.PurveyanceId);
            ArrayList<Goods> goods = getGoodsIdsFromGoodsPurveyances(id);
            int providerId = rs.getInt(DbResources.ProviderId);
            Provider provider = getProviderById(providerId);
            Purveyance purveyance = new Purveyance(id, provider, goods);
            purveyances.add(purveyance);
        }
    }

    public ArrayList<Purchase> getAllPurchases() throws SQLException {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        String sql = "select * from " + DbResources.Purchases;
        ResultSet rs = getResultSet(sql);
        getPurchaseFromSet(rs, purchases);

        return purchases;
    }

    public ArrayList<Purchase> getPurchaseById(int purchaseId) throws SQLException {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        String sql = "select * from " + DbResources.Purchases + " where " + DbResources.PurchaseId + "=" + purchaseId;
        ResultSet rs = getResultSet(sql);
        getPurchaseFromSet(rs, purchases);

        return purchases;
    }
    
    public Provider getProviderById(int providerId) throws SQLException {
        Provider provider = null;
        String sql = "select * from " + DbResources.Providers + " where " + DbResources.ProviderId + "=" + providerId;
        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            int id = rs.getInt(DbResources.ProviderId);
            String name = rs.getString(DbResources.ProviderName);
            provider = new Provider(id, name);
        }

        return provider;
    }
    
    public Employee getEmployeeById(int id) throws SQLException {
        Employee employee = null;
        String sql = "select * from " + DbResources.Employees + " where " + DbResources.EmployeeId + "=" + id;
        ResultSet rs = getResultSet(sql);
        while ((rs != null) && (rs.next())) {
            int eid = rs.getInt(DbResources.EmployeeId);
            String pib = rs.getString(DbResources.PIB);
            double cost = rs.getDouble(DbResources.Cost);
            employee = new Employee(eid, pib, cost);
        }

        return employee;
    }

    public ArrayList<Purchase> getPurchasesByDate(String date) throws SQLException {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        String sql = "select * from " + DbResources.Purchases + " where " + DbResources.PurchaseDate + "=" + "'" + date
                + "'";
        ResultSet rs = getResultSet(sql);
        getPurchaseFromSet(rs, purchases);

        return purchases;
    }

    public ArrayList<Purchase> getPurchasesFromToDate(String dateFrom, String dateTo) throws SQLException {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        String sql = "select * from " + DbResources.Purchases + " where " + DbResources.PurchaseDate + ">=" + "'"
                + dateFrom + "'" + " and " + DbResources.PurchaseDate + "<=" + "'" + dateTo + "'";
        ResultSet rs = getResultSet(sql);
        getPurchaseFromSet(rs, purchases);

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

    private void getPurchaseFromSet(ResultSet rs, ArrayList<Purchase> purchases) throws SQLException {
        while ((rs != null) && (rs.next())) {
            Date date = rs.getDate(DbResources.PurchaseDate);
            int id = rs.getInt(DbResources.PurchaseId);
            ArrayList<Goods> goods = getGoodsIdsFromGoodsPurchases(id, date);
            Purchase purchase = new Purchase(id, date, goods);
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
    
    public void addProvider(String name) throws SQLException {
        String sql = "insert into " + DbResources.Providers + " (" + DbResources.ProviderName + ") "
                +" values ('" + name + "')";
        executSql(sql);
    }
    
    public void createPurchase(ArrayList<Goods> goods) throws SQLException {
        updateGoods(goods);
    }
}
