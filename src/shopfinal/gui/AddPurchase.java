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
import shopfinal.managers.ActionManager.ActionParams;
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
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        goodsLabel = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        scroll = new javax.swing.JScrollPane();
        content = new javax.swing.JPanel();
        dateLabel = new javax.swing.JLabel();
        availableGoods = new javax.swing.JComboBox<>();
        okButton = new javax.swing.JButton();
        performButton = new javax.swing.JButton();
        numberLabel = new javax.swing.JLabel();
        number = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(435, 600));
        setMinimumSize(new java.awt.Dimension(435, 600));
        setPreferredSize(new java.awt.Dimension(435, 600));
        setLayout(new java.awt.GridBagLayout());

        goodsLabel.setText("Товар:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 32, 0, 0);
        add(goodsLabel, gridBagConstraints);
        goodsLabel.getAccessibleContext().setAccessibleName("id");

        title.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Додати покупку");
        title.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(45, 40, 0, 0);
        add(title, gridBagConstraints);

        date.setMinimumSize(new java.awt.Dimension(80, 20));
        date.setPreferredSize(new java.awt.Dimension(80, 20));
        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        add(date, gridBagConstraints);

        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        content.setLayout(new javax.swing.BoxLayout(content, javax.swing.BoxLayout.Y_AXIS));
        scroll.setViewportView(content);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 387;
        gridBagConstraints.ipady = 314;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 12, 0, 13);
        add(scroll, gridBagConstraints);

        dateLabel.setText("Дата:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 32, 0, 0);
        add(dateLabel, gridBagConstraints);

        availableGoods.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 204;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        add(availableGoods, gridBagConstraints);

        okButton.setText("+");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 13);
        add(okButton, gridBagConstraints);

        performButton.setText("Виконати");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${alignmentY}"), performButton, org.jdesktop.beansbinding.BeanProperty.create("alignmentY"));
        bindingGroup.addBinding(binding);

        performButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 99;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 45, 0);
        add(performButton, gridBagConstraints);

        numberLabel.setText("Кількість:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 32, 0, 0);
        add(numberLabel, gridBagConstraints);

        number.setMinimumSize(new java.awt.Dimension(80, 20));
        number.setPreferredSize(new java.awt.Dimension(80, 20));
        number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        add(number, gridBagConstraints);

        getAccessibleContext().setAccessibleParent(this);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateActionPerformed

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
            Logger.getLogger(AddPurchase.class.getName()).log(Level.SEVERE, null, ex);
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

    private void numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> availableGoods;
    private javax.swing.JPanel content;
    private javax.swing.JTextField date;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel goodsLabel;
    private javax.swing.JTextField number;
    private javax.swing.JLabel numberLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JButton performButton;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JLabel title;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
