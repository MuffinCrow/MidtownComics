package org.ucvts.comics.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.ucvts.comics.MidtownComics;
import org.ucvts.comics.controller.ViewManager;
import org.ucvts.comics.model.OrderItem;
import org.ucvts.comics.model.Product;
import org.ucvts.comics.model.Order;
import org.ucvts.comics.dao.OrderDAO;

public class OrderFormItemPanel extends JPanel implements ActionListener{

    private ViewManager manager;
    private Order order;
    private OrderItem orderItem;

    public OrderFormItemPanel (OrderItem item) {
        super(new BorderLayout());

        this.manager = manager;
        this.init(item);
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
    }

    private void init(OrderItem item) {
        JPanel content = getContentPanel(item);
        JPanel actions = getActionPanel(item);

        this.add(content, BorderLayout.CENTER);
        this.add(actions, BorderLayout.EAST);
        this.add(new JSeparator(), BorderLayout.SOUTH);
    }

    private JPanel getContentPanel(OrderItem i) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel(i.getProduct().getTitle() + " #" + i.getProduct().getIssue());
        JLabel quantity = new JLabel("Quantity in order: " + i.getQuantity());

        title.setFont(new Font("DialogInput", Font.BOLD, 18));
        quantity.setFont(new Font("DialogInput", Font.ITALIC, 12));

        panel.add(title, BorderLayout.NORTH);
        panel.add(quantity, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel getActionPanel(OrderItem i) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel price = new JLabel("$" + i.getPrice());
        price.setFont(new Font("DialogInput", Font.BOLD, 15));

        JButton remove = new JButton("Remove");
        remove.putClientProperty("id", i.getItemId());
        remove.putClientProperty("type", "Rem");
        remove.addActionListener(this);

        panel.add(price, BorderLayout.NORTH);
        panel.add(remove, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        Long id = (Long) source.getClientProperty("id");
        int temp = 0;

        for (OrderItem i : order.getItems()) {
            if (id.longValue() == i.getItemId() && (temp + 1) == order.getItems().size()) {
                if (temp == 0) {
                    order.setItems(new ArrayList<>());
                } else {
                    order.setItems(order.getItems().subList(0, temp));
                }
            } else if (id.longValue() == i.getItemId() && temp == 0) {
                if (order.getItems().size() > 1) {
                    order.setItems(order.getItems().subList(1, order.getItems().size() + 1));
                } else {
                    order.setItems(new ArrayList<>());
                }
            } else {
                List<OrderItem> tempI = order.getItems();
                tempI.remove(temp);
                order.setItems(tempI);
            }
            temp++;
        }
    }
}
