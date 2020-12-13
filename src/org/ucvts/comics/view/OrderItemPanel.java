package org.ucvts.comics.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.ucvts.comics.MidtownComics;
import org.ucvts.comics.controller.ViewManager;
import org.ucvts.comics.model.OrderItem;
import org.ucvts.comics.model.Product;
import org.ucvts.comics.model.Order;
import org.ucvts.comics.dao.OrderDAO;

public class OrderItemPanel extends JPanel implements ActionListener {

    private ViewManager manager;

    public OrderItemPanel(ViewManager manager, Order order) {
        super(new BorderLayout());

        this.manager = manager;
        this.init(order);
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
    }

    private void init(Order o) {
        JPanel content = getContentPanel(o);
        JPanel actions = getActionPanel(o);

        this.add(content, BorderLayout.CENTER);
        this.add(actions, BorderLayout.EAST);
        this.add(new JSeparator(), BorderLayout.SOUTH);
    }

    private JPanel getContentPanel(Order o) {
        JPanel panel = new JPanel(new GridLayout(0,1));

        JLabel title = new JLabel(o.getStatus() + " Order # " + o.getOrderId());
        JLabel desc = new JLabel("Ordered by " + o.getCustomer().getFirstName() + " " + o.getCustomer().getLastName() + " on " + convertDate(o.getOrderDate()));
        JLabel items = new JLabel("Number of items: " + o.getItems().size());

        title.setFont(new Font("DialogInput", Font.BOLD, 18));
        desc.setFont(new Font("DialogInput", Font.ITALIC, 12));
        items.setFont(new Font("DialogInput", Font.ITALIC, 12));

        panel.add(title);
        panel.add(desc);
        panel.add(items);

        return panel;
    }

    private JPanel getActionPanel(Order o) {
        JPanel panel = new JPanel(new GridLayout(0, 1));

        JButton edit = new JButton("Edit");
        edit.putClientProperty("id", o.getOrderId());
        edit.putClientProperty("type", "EDIT");
        edit.addActionListener(this);

        panel.add(edit);
        return panel;
    }

    private String convertDate(long date) {
        String dateStr = String.valueOf(date);

        int year = Integer.valueOf(dateStr.substring(0, 4));
        int month = Integer.valueOf(dateStr.substring(4, 6));
        int day = Integer.valueOf(dateStr.substring(6));

        return getMonth(month) + " " + day + ", " + year;
    }

    private String getMonth(int month) {
        switch (month) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        Long id = (Long) source.getClientProperty("id");
        String type = (String) source.getClientProperty("type");

        try {
            for (Order o : OrderDAO.getOrders()) {
                if (id.longValue() == o.getOrderId() && type.equals("EDIT")) {
                    manager.attachOrder(o);
                    manager.switchTo(MidtownComics.OrderEdit);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
