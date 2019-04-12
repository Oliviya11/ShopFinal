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
import shopfinal.managers.ActionManager;
import shopfinal.managers.ActionManager.Result;
import shopfinal.models.Goods;

public class AddPurchase extends javax.swing.JPanel {

    private final HashMap<String, Goods> goodsMap = new HashMap<String, Goods>();

    /**
     * Creates new form FindById
     */
    public AddPurchase() {
        initComponents();
        addAvailableGoods();
    }

    private void addAvailableGoods() {
        try {
            Result result = ActionManager.getInstance().performAction(ActionManager.Action.GET_ALL_GOODS, null);
            availableGoods.removeAllItems();
            ArrayList<Goods> goods = (ArrayList<Goods>) result.data;
            for (int i = 0; i < goods.size(); ++i) {
                goodsMap.put(goods.get(i).name, goods.get(i));
                availableGoods.addItem(goods.get(i).name);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddPurchase.class.getName()).log(Level.SEVERE, null, ex);
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textDate = new javax.swing.JTextField();
        scroll = new javax.swing.JScrollPane();
        content = new javax.swing.JPanel();
        GoodsNumber = new javax.swing.JLabel();
        availableGoods = new javax.swing.JComboBox<>();
        okButton = new javax.swing.JButton();
        performButton = new javax.swing.JButton();
        GoodsNumber1 = new javax.swing.JLabel();
        textNumber = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMaximumSize(new java.awt.Dimension(435, 600));
        setMinimumSize(new java.awt.Dimension(435, 600));
        setPreferredSize(new java.awt.Dimension(435, 600));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("Товар:");
        jLayeredPane1.add(jLabel1);
        jLabel1.setBounds(30, 40, 60, 20);
        jLabel1.getAccessibleContext().setAccessibleName("id");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Додати покупку");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLayeredPane1.add(jLabel2);
        jLabel2.setBounds(140, 10, 140, 20);

        textDate.setMinimumSize(new java.awt.Dimension(80, 20));
        textDate.setPreferredSize(new java.awt.Dimension(80, 20));
        textDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textDateActionPerformed(evt);
            }
        });
        jLayeredPane1.add(textDate);
        textDate.setBounds(100, 90, 100, 30);

        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        content.setLayout(new javax.swing.BoxLayout(content, javax.swing.BoxLayout.Y_AXIS));
        scroll.setViewportView(content);

        jLayeredPane1.add(scroll);
        scroll.setBounds(10, 130, 410, 340);

        GoodsNumber.setText("Дата:");
        jLayeredPane1.add(GoodsNumber);
        GoodsNumber.setBounds(30, 100, 70, 20);

        availableGoods.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLayeredPane1.add(availableGoods);
        availableGoods.setBounds(100, 40, 260, 20);

        okButton.setText("+");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        jLayeredPane1.add(okButton);
        okButton.setBounds(370, 50, 40, 30);

        performButton.setText("Виконати");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${alignmentY}"), performButton, org.jdesktop.beansbinding.BeanProperty.create("alignmentY"));
        bindingGroup.addBinding(binding);

        performButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performButtonActionPerformed(evt);
            }
        });
        jLayeredPane1.add(performButton);
        performButton.setBounds(130, 490, 180, 30);

        GoodsNumber1.setText("Кількість:");
        jLayeredPane1.add(GoodsNumber1);
        GoodsNumber1.setBounds(30, 70, 70, 20);

        textNumber.setMinimumSize(new java.awt.Dimension(80, 20));
        textNumber.setPreferredSize(new java.awt.Dimension(80, 20));
        textNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNumberActionPerformed(evt);
            }
        });
        jLayeredPane1.add(textNumber);
        textNumber.setBounds(100, 60, 100, 30);

        add(jLayeredPane1);

        getAccessibleContext().setAccessibleParent(this);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void textDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textDateActionPerformed

    private void performButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_performButtonActionPerformed
        ArrayList<Goods> goods = new ArrayList<Goods>();
        Component[] children = content.getComponents();
        for (int i = 0; i < children.length; ++i) {
            if (children[i].isVisible()) {
                GoodsItem item = (GoodsItem) children[i];
                if (item != null) {
                    Goods goodsItem = goodsMap.get(item.getName());
                    if (goodsItem.number > item.getNumber()) {
                        goodsItem.number -= item.getNumber();
                        goods.add(goodsItem);
                    }
                }
            }
        }
    }//GEN-LAST:event_performButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        String goodsName = String.valueOf(availableGoods.getSelectedItem());
        Goods goodsItem = goodsMap.get(goodsName);
        if (textDate.getText() != null && !"".equals(textDate.getText())
            && textNumber.getText() != null && !"".equals(textNumber.getText())) {
            GoodsItem item = new GoodsItem();
            item.setLabelName(goodsItem.name);
            item.setLabelNumber(textDate.getText());
            content.add(item);
            content.repaint();
            content.revalidate();
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void textNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNumberActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GoodsNumber;
    private javax.swing.JLabel GoodsNumber1;
    private javax.swing.JComboBox<String> availableGoods;
    private javax.swing.JPanel content;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JButton okButton;
    private javax.swing.JButton performButton;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField textDate;
    private javax.swing.JTextField textNumber;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
