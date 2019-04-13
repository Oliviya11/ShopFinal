/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopfinal.models;

import java.util.ArrayList;

public class Purveyance {
    public int id;
    public Provider provider;
    public ArrayList<Goods> goods = new ArrayList<Goods>();
    public double totalPrice;
    
    public Purveyance(int id, Provider provider, ArrayList<Goods> goods) {
        this.id = id;
        this.provider = provider;
        this.goods = goods;
        this.totalPrice = getTotalPrice();
    }
    
    private double getTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < goods.size(); ++i) {
            totalPrice = goods.get(i).totalPrice;
        }
        return totalPrice;
    }
}
