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
import org.ucvts.comics.dao.CustomerDAO;

public class CustomerList extends JPanel implements ActionListener{

    private ViewManager manager;
    private JScrollPane scroll;
    private JButton viewOrder;
    private JButton inventoryView;
    private JButton viewCart;

    public CustomerList(ViewManager manager) {
        super(new BorderLayout());

        this.manager = manager;
        this.init();
    }

    private void init() throws SQLException {
        initHeader();
        initCustomerList();
        initFooter();
    }

    private void initHeader() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Midtown Comics");
        label.setFont(new Font("DialogInput", Font.BOLD, 21));
        label.setBorder(new EmptyBorder(15,15,10,10));

        viewOrder = new JButton("Orders");
        viewOrder.addActionListener(this);

        inventoryView = new JButton("Inventory");
        inventoryView.addActionListener(this);

        panel.add(label, BorderLayout.CENTER);
        panel.add(inventoryView, BorderLayout.WEST);
        panel.add(viewOrder, BorderLayout.EAST);
        this.add(panel, BorderLayout.NORTH);
    }

    private void initCustomerList() throws SQLException {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(new EmptyBorder(15, 15, 15, 15));

        for (int i = 0; i < CustomerDAO.getCustomers().size(); i++) {
            body.add(new CustomerItemPanel(manager, CustomerDAO.getCustomers().get(i)));
        }

        scroll = new JScrollPane(body);
        this.add(scroll, BorderLayout.CENTER);
    }

    private void initFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 15, 15, 15));

        viewCart = new JButton("Proceed to Cart");
        viewCart.addActionListener(this);

        panel.add(viewCart, BorderLayout.EAST);
        this.add(panel, BorderLayout.SOUTH);
    }

    public void refreshCustomerList() throws SQLException {
        this.remove(scroll);

        initCustomerList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.equals(viewCart)) {
            manager.switchTo(MidtownComics.CartView);
        } else if (source.equals(viewOrder)) {
            manager.switchTo(MidtownComics.OrderList);
        } else if (source.equals(inventoryView)) {
            manager.switchTo(MidtownComics.InventoryView);
        }
    }
}