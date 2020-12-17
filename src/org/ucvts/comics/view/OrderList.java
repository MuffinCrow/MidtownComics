package org.ucvts.comics.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.ucvts.comics.MidtownComics;
import org.ucvts.comics.controller.ViewManager;
import org.ucvts.comics.dao.OrderDAO;

public class OrderList extends JPanel implements ActionListener{

    private ViewManager manager;
    private JScrollPane scroll;
    private JButton viewCustomer;
    private JButton inventoryView;
    private JButton viewCart;

    public OrderList(ViewManager manager) throws SQLException {
        super(new BorderLayout());

        this.manager = manager;
        this.init();
    }

    private void init() throws SQLException {
        initHeader();
        initOrderList();
        initFooter();
    }

    private void initHeader() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Midtown Comics");
        label.setFont(new Font("DialogInput", Font.BOLD, 21));
        label.setBorder(new EmptyBorder(15,15,10,10));

        viewCustomer = new JButton("Customers");
        viewCustomer.addActionListener(this);

        inventoryView = new JButton("Inventory");
        inventoryView.addActionListener(this);

        panel.add(label, BorderLayout.CENTER);
        panel.add(viewCustomer,BorderLayout.WEST);
        panel.add(inventoryView,BorderLayout.EAST);
        this.add(panel,BorderLayout.NORTH);
    }

    private void initOrderList() throws SQLException {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(new EmptyBorder(15,15,15,15));

        for (int i = 0; i < OrderDAO.getOrders().size(); i++) {
            body.add(new OrderItemPanel(manager, OrderDAO.getOrders().get(i)));
        }

        scroll = new JScrollPane(body);
        this.add(scroll, BorderLayout.CENTER);
    }

    private void initFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10,15,15,15));

        viewCart = new JButton("Proceed to Cart");
        viewCart.putClientProperty("id", -1L);
        viewCart.addActionListener(this);

        panel.add(viewCart, BorderLayout.EAST);
        this.add(panel, BorderLayout.SOUTH);
    }

    public void refreshOrderList() throws SQLException {
        this.remove(scroll);

        initOrderList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.equals(viewCart)) {
            manager.switchTo(MidtownComics.CartView);
        } else if (source.equals(viewCustomer)) {
            manager.switchTo(MidtownComics.CustomerList);
        } else if (source.equals(inventoryView)) {
            manager.switchTo(MidtownComics.InventoryView);
        }
    }
}
