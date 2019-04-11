/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopfinal.models;

public class Goods {

    public int id;
    public String name;
    public String providerName;
    public int number;
    public int minNumber;
    public int departmentId;

    public Goods(int id,
            String name,
            String providerName,
            int number,
            int minNumber,
            int departmentId) {

        this.id = id;
        this.name = name;
        this.providerName = providerName;
        this.number = number;
        this.minNumber = minNumber;
        this.departmentId = departmentId;
    }
}
