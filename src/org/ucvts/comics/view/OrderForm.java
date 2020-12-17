package org.ucvts.comics.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.ucvts.comics.controller.ViewManager;
import org.ucvts.comics.model.Order;

@SuppressWarnings("serial")
public class OrderForm extends JPanel implements ActionListener {

    private ViewManager manager;
    private JTextField orderIdField;
    private JTextField customerIdField;
    private JComboBox<String> monthDropdown;
    private JComboBox<String> dayDropdown;
    private JComboBox<String> yearDropdown;
    private JTextField status;
    private JTextField total;

    public OrderForm() { this(null); }

    public OrderForm(Order order) { this.init(order); }

    public void updateFields(Order order) {
        if (order == null) {
            clearFields();

            return;
        }

        orderIdField.setText(String.valueOf(order.getOrderId()));
        customerIdField.setText(String.valueOf(order.getCustomerId()));
        String date = String.valueOf(order.getOrderDate());
        String year = date.substring(0, 4);
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6));

        monthDropdown.setSelectedIndex(month);
        dayDropdown.setSelectedIndex(day);
        yearDropdown.setSelectedItem(year);
        status.setText(String.valueOf(order.getStatus()));
        total.setText(String.valueOf(order.getTotal()));

        this.remove(scroll);

        initItems();
    }

    private void init(Order order) {
        this.setLayout(null);

        initOrderId(order);
        initCustomerId(order);
        initOrderDate(order);
        initStatus(order);
        initTotal(order);
        initItems(order);
    }

    private void initOrderId(Order order) {
        JLabel label = new JLabel("Order ID");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(orderIdField);

        orderIdField = new JTextField(10);
        orderIdField.setBounds(20, 45, 710, 40);;
        orderIdField.setEditable(false);

        this.add(label);
        this.add(orderIdField);
    }

    private void initCustomerId(Order order) {
        JLabel label = new JLabel("Customer ID");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(customerIdField);

        customerIdField = new JTextField(10);
        customerIdField.setBounds(20, 45, 710, 40);;
        customerIdField.setEditable(false);

        this.add(label);
        this.add(customerIdField);
    }

    private void initOrderDate(Order order) {
        JLabel label = new JLabel("Order Date");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 225, 100, 40);
        label.setLabelFor(monthDropdown);

        monthDropdown = new JComboBox<>(getMonths());
        monthDropdown.setBounds(20, 255, 320, 40);

        dayDropdown = new JComboBox<>(getDays());
        dayDropdown.setBounds(350, 255, 160, 40);

        yearDropdown = new JComboBox<>(getYears());
        yearDropdown.setBounds(520, 255, 210, 40);

        this.add(label);
        this.add(monthDropdown);
        this.add(dayDropdown);
        this.add(yearDropdown);
    }

    private void initStatus(Order order) {
        JLabel label = new JLabel("Order Status");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(status);

        status = new JTextField(10);
        status.setBounds(20, 45, 710, 40);;
        status.setEditable(false);

        this.add(label);
        this.add(status);
    }

    private void initTotal(Order order) {
        JLabel label = new JLabel("Order Total");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(total);

        total = new JTextField(10);
        total.setBounds(20, 45, 710, 40);;
        total.setEditable(false);

        this.add(label);
        this.add(total);
    }

    private void initItems(Order order) {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(new EmptyBorder(15, 15, 15, 15));

        for (int i = 0; i < order.getItems().size(); i++) {
            body.add(new CartItemPanel(manager, order.getItems().get(i).getProduct()));
        }

        this.add(body);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
