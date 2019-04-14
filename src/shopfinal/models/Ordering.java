/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopfinal.models;

import java.sql.Date;
import java.util.ArrayList;
import shopfinal.Utils;

public class Ordering {
    public int id;
    public Date date;
    public Provider provider;
    public int pruveyanceId;
    public Employee employee;
    public ArrayList<Goods> goods;
    public String dayOfTheWeek;
    public double totalCost;
    
    public Ordering(int id, 
                    Date date, 
                    Provider provider,
                    int pruveyanceId,
                    Employee employee,
                    ArrayList<Goods> goods) {
        this.id = id;
        this.date = date;
        this.provider = provider;
        this.pruveyanceId = pruveyanceId;
        this.employee = employee;
        this.goods = goods;
        this.dayOfTheWeek = Utils.getDayOfTheWeek(date);
        this.totalCost = getTotalCost();
    }
    
    private double getTotalCost() {
        double totalCost = 0;
        for (int i = 0; i < goods.size(); ++i) {
            totalCost += (goods.get(i).totalPrice);
        }
        return totalCost;
    }
}
