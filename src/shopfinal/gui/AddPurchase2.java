/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopfinal.gui;

import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import shopfinal.ButtonActionHolder;
import shopfinal.managers.ActionManager;
import shopfinal.managers.ActionManager.ActionParams;
import shopfinal.managers.ActionManager.Result;
import shopfinal.models.Department;
import shopfinal.models.Goods;
import shopfinal.models.Provider;

public class AddPurchase2 extends javax.swing.JPanel {

    public ButtonActionHolder actionHolder = null;
    private final HashMap<String, Goods> goodsMap = new HashMap<String, Goods>();

    public AddPurchase2() {
        initComponents();
        addAvailableGoods();
    }
    
    private void addAvailableGoods() {
        try {
            ActionManager.ActionParams params = new ActionParams();
            params.data = date.toString();
            Result result = ActionManager.getInstance().performAction(ActionManager.Action.GET_ALL_GOODS, params);
            availableGoods.removeAllItems();
            ArrayList<Goods> goods = (ArrayList<Goods>) result.data;
            for (int i = 0; i < goods.size(); ++i) {
                Goods g = goods.get(i);
                goodsMap.put(g.name, g);
                availableGoods.addItem(g.name);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddPurchase2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        departmentLabel = new javax.swing.JLabel();
        performButton = new javax.swing.JButton();
        goodsLabel = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        dateLabel = new javax.swing.JLabel();
        numberLabel = new javax.swing.JLabel();
        availableGoods = new javax.swing.JComboBox<>();
        number = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        content = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(435, 600));
        setMinimumSize(new java.awt.Dimension(435, 600));
        setPreferredSize(new java.awt.Dimension(435, 600));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Додати покупку");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        departmentLabel.setText("(од)");

        performButton.setText("Виконати");
        performButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performButtonActionPerformed(evt);
            }
        });

        goodsLabel.setText("Товар:");

        date.setMinimumSize(new java.awt.Dimension(80, 20));
        date.setPreferredSize(new java.awt.Dimension(80, 20));

        dateLabel.setText("Дата:");

        numberLabel.setText("Кількість:");

        availableGoods.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        number.setMinimumSize(new java.awt.Dimension(80, 20));
        number.setPreferredSize(new java.awt.Dimension(80, 20));

        okButton.setText("+");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        content.setLayout(new javax.swing.BoxLayout(content, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(content);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(goodsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(availableGoods, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(numberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(departmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(performButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(goodsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(availableGoods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(departmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(performButton))
        );

        departmentLabel.getAccessibleContext().setAccessibleName("id");

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void performButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_performButtonActionPerformed
       ArrayList<Goods> goods = new ArrayList<Goods>();
        Component[] children = content.getComponents();
        for (int i = 0; i < children.length; ++i) {
            if (children[i].isVisible()) {
                GoodsPreviewItem item = (GoodsPreviewItem) children[i];
                if (item != null) {
                    Goods goodsItem = goodsMap.get(item.getLabelName());
                    if (goodsItem.number > item.getLabelNumber()) {
                        goodsItem.number -= item.getLabelNumber();
                        goodsItem.numberInSmth = item.getLabelNumber();
                        goods.add(goodsItem);
                    }
                }
            }
        }
        
        ActionManager.ActionParams params = new ActionManager.ActionParams();
        params.dataArr = new Object[2];
        params.dataArr[0] = date.getText();
        params.dataArr[1] = goods;
        try {
            ActionManager.getInstance().performAction(ActionManager.Action.ADD_PURCHASE, params);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddPurchase2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_performButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        String goodsName = String.valueOf(availableGoods.getSelectedItem());
        Goods goodsItem = goodsMap.get(goodsName);
        if (date.getText() != null && !"".equals(date.getText())
            && number.getText() != null && !"".equals(number.getText())) {
            GoodsPreviewItem item = new GoodsPreviewItem();
            item.setLabelName(goodsItem.name);
            item.setLabelNumber(number.getText());
            item.setLabelPrice(goodsItem.price+"");
            content.add(item);
            content.repaint();
            content.revalidate();
        }
    }//GEN-LAST:event_okButtonActionPerformed

    public String getLabelName() {
        return null;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> availableGoods;
    private javax.swing.JPanel content;
    private javax.swing.JTextField date;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel departmentLabel;
    private javax.swing.JLabel goodsLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField number;
    private javax.swing.JLabel numberLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JButton performButton;
    // End of variables declaration//GEN-END:variables
}
