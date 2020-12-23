package org.ucvts.comics.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.ucvts.comics.MidtownComics;
import org.ucvts.comics.controller.ViewManager;
import org.ucvts.comics.dao.OrderDAO;
import org.ucvts.comics.model.Product;
import org.ucvts.comics.model.Order;

public class OrderEdit extends JPanel implements ActionListener {

    private ViewManager manager;
    private OrderForm orderForm;
    private Order order;
    private JButton save;
    private JButton remove;
    private JButton cancel;

    public OrderEdit(ViewManager manager) {
        super(new BorderLayout());

        this.manager = manager;
        this.orderForm = new OrderForm();
        this.init();
    }

    public void init() {
        initHeader();
        initOrderForm();
        initFooter();
    }

    public void setOrder(Order order) throws SQLException {
        this.order = order;

        remove.setEnabled(true);
        orderForm.updateFields(order);
    }

    public void initHeader() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Midtown Comics");
        label.setFont(new Font("DialogInput", Font.BOLD, 21));
        label.setBorder(new EmptyBorder(15, 15, 10, 0));

        panel.add(label, BorderLayout.WEST);
        this.add(panel, BorderLayout.NORTH);
    }

    public void initOrderForm() {
        this.add(new JScrollPane(orderForm), BorderLayout.CENTER);
    }

    public void initFooter() {
        JPanel panel = new JPanel(new GridLayout(1, 0));
        panel.setBorder(new EmptyBorder(10, 15, 15, 15));

        cancel = new JButton("Cancel");
        cancel.addActionListener(this);

        remove = new JButton("Remove");
        remove.setEnabled(false);
        remove.addActionListener(this);

        save = new JButton("Save");
        save.addActionListener(this);

        panel.add(cancel);
        panel.add(remove);
        panel.add(save);
        this.add(panel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.equals(save)) {
            manager.modifyOrder(orderForm.getOrderFromFields());
        } else if (source.equals(remove)) {
            try {
                OrderDAO.deleteOrder(order);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (source.equals(cancel)) {
            try {
                manager.detachOrder();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            manager.switchTo(MidtownComics.OrderList);
        }
    }
}
