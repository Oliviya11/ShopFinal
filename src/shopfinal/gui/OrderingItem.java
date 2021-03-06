/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopfinal.gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import shopfinal.managers.ActionManager;
import shopfinal.models.Goods;
import shopfinal.models.Provider;

/**
 *
 * @author User
 */
public class OrderingItem extends javax.swing.JPanel {
    private DefaultTableModel  model;
    private ArrayList<Goods> goodsList = new  ArrayList<Goods>();
    private Provider provider;
    /**
     * Creates new form PurchaseItem
     */
    public OrderingItem() {
        initComponents();
        model = (DefaultTableModel) orderingGoods.getModel();
        model.setRowCount(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        status = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        employee = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        cost = new javax.swing.JLabel();
        weekDayLabel = new javax.swing.JLabel();
        weekDay = new javax.swing.JLabel();
        providerNameLabel1 = new javax.swing.JLabel();
        providerName = new javax.swing.JLabel();
        performOrdering = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderingGoods = new javax.swing.JTable();
        employeeLabel1 = new javax.swing.JLabel();
        totalCostLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(488, 460));
        setMinimumSize(new java.awt.Dimension(488, 460));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(488, 460));

        statusLabel.setText("Статус:");

        idLabel.setText("id:");

        title.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Замовлення");
        title.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        dateLabel.setText("Дата:");

        weekDayLabel.setText("День тижня:");

        providerNameLabel1.setText("Постачальник:");

        performOrdering.setText("Виконати замовлення");
        performOrdering.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performOrderingActionPerformed(evt);
            }
        });

        orderingGoods.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Назва товару", "Вартість за од", "Кількість", "Загальна вартість (грн)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(orderingGoods);

        employeeLabel1.setText("ПІБ працівника:");

        totalCostLabel1.setText("Загальна вартість:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(weekDayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(weekDay, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(providerNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(providerName, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(totalCostLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(employeeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(employee, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(performOrdering, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(weekDayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(weekDay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(providerNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(providerName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalCostLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(employeeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employee, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(performOrdering, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void performOrderingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_performOrderingActionPerformed
        setStatus(true);
        ActionManager.ActionParams params = new ActionManager.ActionParams();
        params.dataArr = new Object[2];
        params.dataArr[0] = getLabelId();
        params.dataArr[1] = provider.id;
        params.data = goodsList;
        try {
            ActionManager.getInstance().performAction(ActionManager.Action.ADD_PURVEYANCE, params);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrderingItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_performOrderingActionPerformed

    public void setLabelDate(String d) {
        date.setText(d);
    }
    
    public void setLabelId(String d) {
        id.setText(d);
    }
    
    public void setDayOfTheWeek(String day) {
        weekDay.setText(day);
    }

    public void createRowInTable(Goods goods) {
        model.addRow(new Object[] { goods.name, goods.price, goods.numberInSmth, goods.totalPriceInSmth});
        goods.number += goods.numberInSmth;
        goodsList.add(goods);
    }
    
    public void setEmployeeName(String n) {
        employee.setText(n);
    }
    
    /*
    public void setProviderName(String pn) {
        providerName.setText(pn);
    }
*/
    
    public void setTotalCost(String c) {
        cost.setText(c);
    }
    
    public void setStatus(boolean isPerformed) {
        if (isPerformed) {
            status.setText("Виконано");
            performOrdering.setVisible(false);
        } else {
            status.setText("Не виконано");
        }
    }
    
    public int getLabelId() {
        return Integer.parseInt(id.getText());
    }

    public void setProvider(Provider p) {
        provider = p;
        providerName.setText(p.name);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cost;
    private javax.swing.JLabel date;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel employee;
    private javax.swing.JLabel employeeLabel1;
    private javax.swing.JLabel id;
    private javax.swing.JLabel idLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable orderingGoods;
    private javax.swing.JButton performOrdering;
    private javax.swing.JLabel providerName;
    private javax.swing.JLabel providerNameLabel1;
    private javax.swing.JLabel status;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel title;
    private javax.swing.JLabel totalCostLabel1;
    private javax.swing.JLabel weekDay;
    private javax.swing.JLabel weekDayLabel;
    // End of variables declaration//GEN-END:variables
}
