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
import org.ucvts.comics.dao.OrderDAO;
import org.ucvts.comics.model.Customer;
import org.ucvts.comics.view.CustomerList;
import org.ucvts.comics.dao.CustomerDAO;

import static java.lang.Integer.MAX_VALUE;

public class CustomerItemPanel extends JPanel implements ActionListener {

    private ViewManager manager;

    public CustomerItemPanel(ViewManager manager, Customer customer) {
        super(new BorderLayout());

        this.manager = manager;
        this.init(customer);
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
    }

    private void init(Customer c) {
        JPanel content = getContentPanel(c);
        JPanel actions = getActionPanel(c);

        this.add(content, BorderLayout.CENTER);
        this.add(actions, BorderLayout.EAST);
        this.add(new JSeparator(), BorderLayout.SOUTH);
    }

    private JPanel getContentPanel(Customer c) {
        JPanel panel = new JPanel(new GridLayout(0, 1));

        JLabel name = new JLabel(c.getFirstName() + " " + c.getLastName() + " " + c.getCustomerId());
        JLabel postal = new JLabel(c.getPostalCode());
        JLabel email = new JLabel(c.getEmail());

        name.setFont(new Font("DialogInput", Font.BOLD, 18));
        postal.setFont(new Font("DialogInput", Font.ITALIC, 12));
        email.setFont(new Font("DialogInput", Font.ITALIC, 12));

        panel.add(name);
        panel.add(postal);
        panel.add(email);

        return panel;
    }

    private JPanel getActionPanel(Customer c) {
        JPanel panel = new JPanel(new GridLayout(0, 1));

        JButton edit = new JButton("Edit");
        edit.putClientProperty("id", c.getCustomerId());
        edit.putClientProperty("type", "EDIT");
        edit.addActionListener(this);

        panel.add(edit);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        Long id = (Long) source.getClientProperty("id");
        String type = (String) source.getClientProperty("type");

        try {
            for (Customer c : CustomerDAO.getCustomers()) {
                if (id.longValue() == c.getCustomerId() && type.equals("EDIT")) {
                    manager.attachCustomer(c);
                    manager.switchTo(MidtownComics.CustomerEdit);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
