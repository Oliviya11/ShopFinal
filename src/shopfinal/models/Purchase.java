/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopfinal.models;

import java.sql.Date;
import java.util.ArrayList;
import shopfinal.Utils;
import shopfinal.managers.DbAccessManager;

public class Purchase {

    public int id;
    public Date date;
    public ArrayList<Goods> goods = new ArrayList<Goods>();
    public String dayOfTheWeek;

    public Purchase(int id, Date date, ArrayList<Goods> goods) {
        this.id = id;
        this.date = date;
        this.goods = goods;
        this.dayOfTheWeek = Utils.getDayOfTheWeek(date);
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Goods> getGoods() {
        return goods;
    }
    
    public double getTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < goods.size(); ++i) {
            totalPrice = goods.get(i).totalPrice;
        }
        return totalPrice;
    }
}
