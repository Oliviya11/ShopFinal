/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopfinal.models;

public class Employee {
    public int id;
    public String pib;
    public int cost;
    public Department department;
    
    public Employee(int id, String pib, int cost, Department department) {
        this.id = id;
        this.pib = pib;
        this.cost = cost;
        this.department = department;
    }
}
