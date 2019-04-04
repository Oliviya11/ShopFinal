/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopfinal.models;

import java.sql.Date;
import java.util.ArrayList;
import shopfinal.managers.DbAccessManager;

public class Purchase {

    private int id;
    private Date date;
    private ArrayList<PurchaseGoods> goods = new ArrayList<PurchaseGoods>();
    private double cost;

    public Purchase(int id, Date date, ArrayList<PurchaseGoods> goods, double cost) {
        this.id = id;
        this.date = date;
        this.goods = goods;
        this.cost = cost;
    }

    public Purchase(DbAccessManager db, Date date) {
        this.date = date;
        //TODO: add to db
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<PurchaseGoods> getGoods() {
        return goods;
    }

    public double getCost() {
        return cost;
    }
}
