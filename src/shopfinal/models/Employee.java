/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopfinal.models;

public class Employee {
    public int id;
    public String pib;
    public double cost;
    
    public Employee(int id, String pib, double cost) {
        this.id = id;
        this.pib = pib;
        this.cost = cost;
    }
}
