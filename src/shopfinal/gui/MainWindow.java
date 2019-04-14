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
import javax.swing.JPanel;
import shopfinal.ButtonActionHolder;
import shopfinal.Utils;
import shopfinal.managers.ActionManager;
import shopfinal.managers.ActionManager.Result;
import shopfinal.models.*;

/**
 *
 * @author User
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    private JPanel leftPanel;
    private ContentPanel rightPanel;

    public MainWindow() {
        initComponents();
        setUpOnFirstLaunch();
    }

    private void setUpOnFirstLaunch() {
       setResizable(false);
       setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        Goods = new javax.swing.JMenu();
        FindGoodsById = new javax.swing.JMenuItem();
        AddGoods = new javax.swing.JMenuItem();
        ShowAllGoods = new javax.swing.JMenuItem();
        Employee = new javax.swing.JMenu();
        AddEmployee = new javax.swing.JMenuItem();
        ShowAllEmployees = new javax.swing.JMenuItem();
        Purchase = new javax.swing.JMenu();
        FindPurchaseById = new javax.swing.JMenuItem();
        AddPurchase = new javax.swing.JMenuItem();
        ShowAllPurchases = new javax.swing.JMenuItem();
        Ordering = new javax.swing.JMenu();
        AddOrdering = new javax.swing.JMenuItem();
        ShowAllOrderings = new javax.swing.JMenuItem();
        Provider = new javax.swing.JMenu();
        AddProvider = new javax.swing.JMenuItem();
        ShowAllProviders = new javax.swing.JMenuItem();
        Purveyance = new javax.swing.JMenu();
        ShowAllPurveyances = new javax.swing.JMenuItem();
        Department = new javax.swing.JMenu();
        AddDepartment = new javax.swing.JMenuItem();
        ShowAllDepartments = new javax.swing.JMenuItem();
        Settings = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(950, 600));
        setMinimumSize(new java.awt.Dimension(950, 600));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        Goods.setText("Товар");

        FindGoodsById.setText("Знайти за id");
        FindGoodsById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindGoodsByIdActionPerformed(evt);
            }
        });
        Goods.add(FindGoodsById);

        AddGoods.setText("Додати");
        AddGoods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGoodsActionPerformed(evt);
            }
        });
        Goods.add(AddGoods);

        ShowAllGoods.setText("Показати всі");
        ShowAllGoods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllGoodsActionPerformed(evt);
            }
        });
        Goods.add(ShowAllGoods);

        jMenuBar1.add(Goods);

        Employee.setText("Працівник");

        AddEmployee.setText("Додати");
        AddEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddEmployeeActionPerformed(evt);
            }
        });
        Employee.add(AddEmployee);

        ShowAllEmployees.setText("Показати всі");
        ShowAllEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllEmployeesActionPerformed(evt);
            }
        });
        Employee.add(ShowAllEmployees);

        jMenuBar1.add(Employee);

        Purchase.setText("Покупка");

        FindPurchaseById.setText("Знайти за id");
        FindPurchaseById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindPurchaseByIdActionPerformed(evt);
            }
        });
        Purchase.add(FindPurchaseById);

        AddPurchase.setText("Додати");
        AddPurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPurchaseActionPerformed(evt);
            }
        });
        Purchase.add(AddPurchase);

        ShowAllPurchases.setText("Показати всі");
        ShowAllPurchases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllPurchasesActionPerformed(evt);
            }
        });
        Purchase.add(ShowAllPurchases);

        jMenuBar1.add(Purchase);

        Ordering.setText("Замовлення");

        AddOrdering.setText("Додати");
        AddOrdering.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddOrderingActionPerformed(evt);
            }
        });
        Ordering.add(AddOrdering);

        ShowAllOrderings.setText("Показати всі");
        ShowAllOrderings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllOrderingsActionPerformed(evt);
            }
        });
        Ordering.add(ShowAllOrderings);

        jMenuBar1.add(Ordering);

        Provider.setText("Постачальник");

        AddProvider.setText("Додати");
        AddProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProviderActionPerformed(evt);
            }
        });
        Provider.add(AddProvider);

        ShowAllProviders.setText("Показати всіх");
        ShowAllProviders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllProvidersActionPerformed(evt);
            }
        });
        Provider.add(ShowAllProviders);

        jMenuBar1.add(Provider);

        Purveyance.setText("Поставка");

        ShowAllPurveyances.setText("Показати всі");
        ShowAllPurveyances.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllPurveyancesActionPerformed(evt);
            }
        });
        Purveyance.add(ShowAllPurveyances);

        jMenuBar1.add(Purveyance);

        Department.setText("Відділ");
        Department.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentActionPerformed(evt);
            }
        });

        AddDepartment.setText("Додати");
        AddDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDepartmentActionPerformed(evt);
            }
        });
        Department.add(AddDepartment);

        ShowAllDepartments.setText("Показати всі");
        ShowAllDepartments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllDepartmentsActionPerformed(evt);
            }
        });
        Department.add(ShowAllDepartments);

        jMenuBar1.add(Department);

        Settings.setText("Налаштування");
        Settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SettingsMouseClicked(evt);
            }
        });
        Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsActionPerformed(evt);
            }
        });
        jMenuBar1.add(Settings);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FindGoodsByIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindGoodsByIdActionPerformed
        
    }//GEN-LAST:event_FindGoodsByIdActionPerformed

    private void ShowAllPurchasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllPurchasesActionPerformed
        removeLeftPanel();
        ShowAll showAllPurchases = new ShowAll();
        class RealButtonActionHolder extends ButtonActionHolder {

            @Override
            public void performAction() {
                try {
                    Result result = ActionManager.getInstance().performAction(ActionManager.Action.GET_ALL_PURCHASES, null);
                    ArrayList<Purchase> purchases = (ArrayList<Purchase>) result.data;
                    rightPanel.clearPanel();
                    addPurchasesItems(purchases);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        RealButtonActionHolder actionHolder = new RealButtonActionHolder();
        showAllPurchases.actionHolder = actionHolder;
        leftPanel = showAllPurchases;
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }//GEN-LAST:event_ShowAllPurchasesActionPerformed

    private void FindPurchaseByIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindPurchaseByIdActionPerformed
        addFindByIdPurchasePanel();
    }//GEN-LAST:event_FindPurchaseByIdActionPerformed

    private void ShowAllGoodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllGoodsActionPerformed
        removeLeftPanel();
        ShowAll showAllEmployees = new ShowAll();
        class RealButtonActionHolder extends ButtonActionHolder {

            @Override
            public void performAction() {
                try {
                    ActionManager.ActionParams params = new ActionManager.ActionParams();
                    params.data = Utils.getNow().toString();
                    Result result = ActionManager.getInstance().performAction(ActionManager.Action.GET_ALL_GOODS, params);
                    ArrayList<Goods> goods = (ArrayList<Goods>) result.data;
                    rightPanel.clearPanel();
                    addGoodsItems(goods);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        RealButtonActionHolder actionHolder = new RealButtonActionHolder();
        showAllEmployees.actionHolder = actionHolder;
        leftPanel = showAllEmployees;
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }//GEN-LAST:event_ShowAllGoodsActionPerformed

    private void SettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SettingsMouseClicked
        removeLeftPanel();
        leftPanel = new Settings();
        add(leftPanel);
        removeRightPanel();
        refresh();
    }//GEN-LAST:event_SettingsMouseClicked

    private void AddPurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPurchaseActionPerformed
        removeLeftPanel();
        leftPanel = new AddPurchase();
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }//GEN-LAST:event_AddPurchaseActionPerformed

    private void AddOrderingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddOrderingActionPerformed
        removeLeftPanel();
        leftPanel = new AddOrdering();
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }//GEN-LAST:event_AddOrderingActionPerformed

    private void ShowAllOrderingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllOrderingsActionPerformed
        removeLeftPanel();
        ShowAll showAll = new ShowAll();
        class RealButtonActionHolder extends ButtonActionHolder {

            @Override
            public void performAction() {
                try {
                    Result result = ActionManager.getInstance().performAction(ActionManager.Action.GET_ALL_ORDERINGS, null);
                    ArrayList<Ordering> orderings = (ArrayList<Ordering>) result.data;
                    rightPanel.clearPanel();
                    addOrderingsItem(orderings);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        RealButtonActionHolder actionHolder = new RealButtonActionHolder();
        showAll.actionHolder = actionHolder;
        leftPanel = showAll;
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }//GEN-LAST:event_ShowAllOrderingsActionPerformed

    private void AddProviderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProviderActionPerformed
        removeLeftPanel();
        AddProvider addProvider = new AddProvider();
        class RealButtonActionHolder extends ButtonActionHolder {

            @Override
            public void performAction() {
                try {
                    ActionManager.ActionParams params = new ActionManager.ActionParams();
                    params.strValue1 = addProvider.getLabelName();
                    rightPanel.clearPanel();
                    ActionManager.getInstance().performAction(ActionManager.Action.ADD_PROVIDER, params);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        RealButtonActionHolder actionHolder = new RealButtonActionHolder();
        addProvider.actionHolder = actionHolder;
        leftPanel = addProvider;
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }//GEN-LAST:event_AddProviderActionPerformed

    private void ShowAllProvidersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllProvidersActionPerformed
        removeLeftPanel();
        ShowAll showAllProviders = new ShowAll();
        class RealButtonActionHolder extends ButtonActionHolder {

            @Override
            public void performAction() {
                try {
                    Result result = ActionManager.getInstance().performAction(ActionManager.Action.GET_ALL_PROVIDERS, null);
                    ArrayList<Provider> providers = (ArrayList<Provider>) result.data;
                    rightPanel.clearPanel();
                    addProviderItems(providers);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        RealButtonActionHolder actionHolder = new RealButtonActionHolder();
        showAllProviders.actionHolder = actionHolder;
        leftPanel = showAllProviders;
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }//GEN-LAST:event_ShowAllProvidersActionPerformed

    private void ShowAllPurveyancesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllPurveyancesActionPerformed
        removeLeftPanel();
        ShowAll showAllPurveyances = new ShowAll();
        class RealButtonActionHolder extends ButtonActionHolder {

            @Override
            public void performAction() {
                try {
                    Result result = ActionManager.getInstance().performAction(ActionManager.Action.GET_ALL_PURVEYANCES, null);
                    ArrayList<Purveyance> purveyances = (ArrayList<Purveyance>) result.data;
                    rightPanel.clearPanel();
                    addPurveyanceItems(purveyances);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        RealButtonActionHolder actionHolder = new RealButtonActionHolder();
        showAllPurveyances.actionHolder = actionHolder;
        leftPanel = showAllPurveyances;
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }//GEN-LAST:event_ShowAllPurveyancesActionPerformed

    private void SettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SettingsActionPerformed

    private void ShowAllEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllEmployeesActionPerformed
        removeLeftPanel();
        ShowAll showAllEmployees = new ShowAll();
        class RealButtonActionHolder extends ButtonActionHolder {

            @Override
            public void performAction() {
                try {
                    Result result = ActionManager.getInstance().performAction(ActionManager.Action.GET_ALL_EMPLOYEES, null);
                    ArrayList<Employee> employees = (ArrayList<Employee>) result.data;
                    rightPanel.clearPanel();
                    addEmployeeItems(employees);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        RealButtonActionHolder actionHolder = new RealButtonActionHolder();
        showAllEmployees.actionHolder = actionHolder;
        leftPanel = showAllEmployees;
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }//GEN-LAST:event_ShowAllEmployeesActionPerformed

    private void AddEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddEmployeeActionPerformed
        removeLeftPanel();
        leftPanel = new AddEmployee();
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }//GEN-LAST:event_AddEmployeeActionPerformed

    private void AddGoodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddGoodsActionPerformed
        removeLeftPanel();
        leftPanel = new AddGoods();
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }//GEN-LAST:event_AddGoodsActionPerformed

    private void ShowAllDepartmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllDepartmentsActionPerformed
        removeLeftPanel();
        ShowAll showAllDepartments = new ShowAll();
        class RealButtonActionHolder extends ButtonActionHolder {

            @Override
            public void performAction() {
                try {
                    Result result = ActionManager.getInstance().performAction(ActionManager.Action.GET_ALL_DEPARTMENTS, null);
                    ArrayList<Department> departments = (ArrayList<Department>) result.data;
                    rightPanel.clearPanel();
                    addDepartmentsItems(departments);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        RealButtonActionHolder actionHolder = new RealButtonActionHolder();
        showAllDepartments.actionHolder = actionHolder;
        leftPanel = showAllDepartments;
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }//GEN-LAST:event_ShowAllDepartmentsActionPerformed

    private void AddDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddDepartmentActionPerformed
        removeLeftPanel();
        leftPanel = new AddDepartment();
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }//GEN-LAST:event_AddDepartmentActionPerformed

    private void DepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DepartmentActionPerformed

    private void addFindByIdPurchasePanel() {
        removeLeftPanel();
        FindById p = new FindById();
        class RealButtonActionHolder extends ButtonActionHolder {

            @Override
            public void performAction() {
                try {
                    ActionManager.ActionParams params = new ActionManager.ActionParams();
                    params.intValue = p.getId();
                    Result result = ActionManager.getInstance().performAction(ActionManager.Action.GET_PURCHASE_BY_ID, params);
                    ArrayList<Purchase> purchases = (ArrayList<Purchase>) result.data;
                    rightPanel.clearPanel();
                    addPurchasesItems(purchases);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        RealButtonActionHolder actionHolder = new RealButtonActionHolder();
        p.actionHolder = actionHolder;
        leftPanel = p;
        add(leftPanel);
        adjsutRightPanel();
        refresh();
    }
    
    private void addPurchaseItem(Purchase purchase) {
        PurchaseItem purchaseItem = new PurchaseItem();
        purchaseItem.setLabelDate(purchase.date.toString());
        purchaseItem.setDayOfTheWeek(purchase.dayOfTheWeek);
        purchaseItem.setLabelId(purchase.id+"");
        purchaseItem.setPurchasePrice(purchase.getTotalPrice());
        for (int i = 0; i < purchase.goods.size(); ++i) {
            Goods goods = purchase.goods.get(i);
            purchaseItem.createRowInTable(goods.name, goods.price, goods.number, goods.totalPrice);
        }
        rightPanel.addPanel(purchaseItem);
    }
    
    private void addPurchasesItems(ArrayList<Purchase> purchases) {
        for (int i = 0; i < purchases.size(); ++i) {
          addPurchaseItem(purchases.get(i));
        }
        refresh();
    }
    
    private void addDepartmentItem(Department d) {
        DepartmentItem item = new DepartmentItem();
        item.setLabelId(d.id+"");
        item.setLabelName(d.name);
        rightPanel.addPanel(item);
    }
    
    private void addDepartmentsItems(ArrayList<Department> departments) {
        for (int i = 0; i < departments.size(); ++i) {
            addDepartmentItem(departments.get(i));
        }
        
        refresh();
    }
    
    private void addPurveyanceItem(Purveyance purveyance) {
       PurveyanceItem purveyanceItem = new PurveyanceItem();
       purveyanceItem.setLabelId(purveyance.id+"");
       purveyanceItem.setTotalPrice(purveyance.totalPrice);
       for (int i = 0; i < purveyance.goods.size(); ++i) {
            Goods goods = purveyance.goods.get(i);
            purveyanceItem.createRowInTable(goods);
        }
       rightPanel.addPanel(purveyanceItem);
    }
    
    private void addPurveyanceItems(ArrayList<Purveyance> purveyances) {
        for (int i = 0; i < purveyances.size(); ++i) {
            addPurveyanceItem(purveyances.get(i));
        }
        
        refresh();
    }
    
    private void addEmployeeItem(Employee employee) {
        EmployeeItem item = new EmployeeItem();
        item.setLabelId(employee.id + "");
        item.setLabelPIB(employee.pib);
        item.setLabelCost(employee.cost + "(грн)");
        item.setLabelDepartment(employee.department.name);
        rightPanel.addPanel(item);
    }
    
    private void addEmployeeItems(ArrayList<Employee> employees) {
        for (int i = 0; i < employees.size(); ++i) {
            addEmployeeItem(employees.get(i));
        }
        
        refresh();
    }
    
    private void addGoodsItem(Goods goods) {
        GoodsItem item = new GoodsItem();
        item.setLabelId(goods.id+"");
        item.setLabelName(goods.name);
        item.setLabelNumber(goods.number+"(од)");
        item.setLabelProvider(goods.providerName);
        item.setLabelActualPrice(goods.price + "(грн)");
        rightPanel.addPanel(item);
    }
    
    
    private void addGoodsItems(ArrayList<Goods> goods) {
        for (int i = 0; i < goods.size(); ++i) {
            addGoodsItem(goods.get(i));
        }
        
        refresh();
    }
    
    private void addOrderingItem(Ordering ordering) {
        OrderingItem orderingItem = new OrderingItem();
        orderingItem.setLabelId(ordering.id+"");
        orderingItem.setDayOfTheWeek(ordering.dayOfTheWeek);
        orderingItem.setLabelDate(ordering.date + "");
        orderingItem.setProviderName(ordering.provider.name);
        orderingItem.setEmployeeName(ordering.employee.pib);
        orderingItem.setStatus(ordering.pruveyanceId != 0);
        orderingItem.setTotalCost(ordering.totalCost + "(грн)");
        for (int i = 0; i < ordering.goods.size(); ++i) {
            Goods goods = ordering.goods.get(i);
            orderingItem.createRowInTable(goods);
        }
        rightPanel.addPanel(orderingItem);
    }
    
    private void addOrderingsItem(ArrayList<Ordering> orderings) {
        for (int i = 0; i < orderings.size(); ++i) {
            addOrderingItem(orderings.get(i));
        }
        refresh();
    }
    
    private void addProviderItem(Provider p) {
        ProviderItem providerItem = new ProviderItem();
        providerItem.setLabelId(p.id + "");
        providerItem.setLabelName(p.name);
        rightPanel.addPanel(providerItem);
    }
    
    private void addProviderItems(ArrayList<Provider> providers) {
        for (int i = 0; i < providers.size(); ++i) {
            addProviderItem(providers.get(i));
        }
        refresh();
    }
    
    private void adjsutRightPanel() {
        removeRightPanel();
        addRightPanel();
    }
    
    private void addRightPanel() {
        rightPanel = new ContentPanel();
        add(rightPanel);
    }
    
    private void removeRightPanel() {
        if (rightPanel != null) {
            remove(rightPanel);
        }
    }

    private void removeLeftPanel() {
        if (leftPanel != null) {
            remove(leftPanel);
        }
    }

    public void refresh() {
        revalidate();
        repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AddDepartment;
    private javax.swing.JMenuItem AddEmployee;
    private javax.swing.JMenuItem AddGoods;
    private javax.swing.JMenuItem AddOrdering;
    private javax.swing.JMenuItem AddProvider;
    private javax.swing.JMenuItem AddPurchase;
    private javax.swing.JMenu Department;
    private javax.swing.JMenu Employee;
    private javax.swing.JMenuItem FindGoodsById;
    private javax.swing.JMenuItem FindPurchaseById;
    private javax.swing.JMenu Goods;
    private javax.swing.JMenu Ordering;
    private javax.swing.JMenu Provider;
    private javax.swing.JMenu Purchase;
    private javax.swing.JMenu Purveyance;
    private javax.swing.JMenu Settings;
    private javax.swing.JMenuItem ShowAllDepartments;
    private javax.swing.JMenuItem ShowAllEmployees;
    private javax.swing.JMenuItem ShowAllGoods;
    private javax.swing.JMenuItem ShowAllOrderings;
    private javax.swing.JMenuItem ShowAllProviders;
    private javax.swing.JMenuItem ShowAllPurchases;
    private javax.swing.JMenuItem ShowAllPurveyances;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
