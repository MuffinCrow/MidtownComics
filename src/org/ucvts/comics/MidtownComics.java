package org.ucvts.comics;

import java.awt.CardLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.ucvts.comics.controller.ViewManager;
import org.ucvts.comics.view.CartView;
import org.ucvts.comics.view.InventoryView;
import org.ucvts.comics.view.ProductView;
import org.ucvts.comics.view.OrderView;
import org.ucvts.comics.view.OrderList;
import org.ucvts.comics.view.CustomerList;
import org.ucvts.comics.view.OrderEdit;

@SuppressWarnings("serial")
public class MidtownComics extends JFrame {

    public static final int InventoryViewIndex = 0;
    public static final int ProductViewIndex = 1;
    public static final int CartViewIndex = 2;
    public static final int OrderViewIndex = 3;
    public static final int OrderEditIndex = 4;

    public static final String InventoryView = "InventoryView";
    public static final String ProductView = "ProductView";
    public static final String CartView = "CartView";
    public static final String OrderView = "OrderView";
    public static final String OrderList = "OrderList";
    public static final String CustomerList = "CustomerList";
    public static final String OrderEdit = "OrderEdit";

    /**
     * Initializes the application views and frame.
     */

    public void init() throws SQLException {
        JPanel views = new JPanel(new CardLayout());
        ViewManager manager = ViewManager.getInstance(views);

        // add child views to the parent container

        views.add(new InventoryView(manager), InventoryView);
        views.add(new ProductView(manager), ProductView);
        views.add(new CartView(manager), CartView);
        views.add(new OrderView(manager), OrderView);
        views.add(new OrderList(manager), OrderList);
        views.add(new CustomerList(manager), CustomerList);
        views.add(new OrderEdit(manager), OrderEdit);

        // configure application frame

        this.getContentPane().add(views);
        this.setBounds(0, 0, 750, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    ////////// MAIN METHOD /////////////////////////////////////////////////////////

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MidtownComics().init();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}