package org.ucvts.comics.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.ucvts.comics.MidtownComics;
import org.ucvts.comics.controller.ViewManager;
import org.ucvts.comics.model.Customer;
import org.ucvts.comics.dao.CustomerDAO;

@SuppressWarnings("serial")
public class CustomerForm extends JPanel{

    private ViewManager manager;
    private JTextField customerIdField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField postalField;

    public CustomerForm() { this(null); }

    public CustomerForm(Customer c) { this.init(c); }

    public void updateFields(Customer c) {
        if (c == null) {
            clearFields();

            return;
        }

        customerIdField.setText(String.valueOf(c.getCustomerId()));
        nameField.setText(c.getFirstName() + " " + c.getLastName());
        phoneField.setText(String.valueOf(c.getPhone()));
        emailField.setText(c.getEmail());
        streetField.setText(c.getStreetAddress());
        cityField.setText(c.getCity());
        stateField.setText(c.getState());
        postalField.setText(c.getPostalCode());
    }

    public Customer getCustomerFromFields() {
        if (customerIdField.getText().trim().isEmpty()) {
            return new Customer(
                    getCustomerName()[0],
                    getCustomerName()[2],
                    Integer.parseInt(phoneField.getText()),
                    emailField.getText(),
                    streetField.getText(),
                    cityField.getText(),
                    stateField.getText(),
                    postalField.getText()
            );
        } else {
            return new Customer(
                    Long.parseLong(customerIdField.getText()),
                    getCustomerName()[0],
                    getCustomerName()[2],
                    Integer.parseInt(phoneField.getText()),
                    emailField.getText(),
                    streetField.getText(),
                    cityField.getText(),
                    stateField.getText(),
                    postalField.getText()
            );
        }
    }

    private void init(Customer c) {
        this.setLayout(null);

        initCustomerId(c);
        initName(c);
        initPhone(c);
        initEmail(c);
        initStreet(c);
        initCity(c);
        initState(c);
        initPostal(c);
    }

    private void initCustomerId(Customer c) {
        JLabel label = new JLabel("Customer ID");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(customerIdField);

        customerIdField = new JTextField(10);
        customerIdField.setBounds(20,45,710,40);
        customerIdField.setEditable(false);

        this.add(label);
        this.add(customerIdField);
    }

    private void initName(Customer c) {
        JLabel label = new JLabel("First And Last Name");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(nameField);

        nameField = new JTextField(10);
        nameField.setBounds(20,45,710,40);

        this.add(label);
        this.add(nameField);
    }

    private void initPhone(Customer c) {
        JLabel label = new JLabel("Phone Number");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(phoneField);

        phoneField = new JTextField(10);
        phoneField.setBounds(20,45,710,40);

        this.add(label);
        this.add(phoneField);
    }

    private void initEmail(Customer c) {
        JLabel label = new JLabel("Email");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(emailField);

        emailField = new JTextField(10);
        emailField.setBounds(20,45,710,40);

        this.add(label);
        this.add(emailField);
    }

    private void initStreet(Customer c) {
        JLabel label = new JLabel("Street Address");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(streetField);

        streetField = new JTextField(10);
        streetField.setBounds(20,45,710,40);

        this.add(label);
        this.add(streetField);
    }

    private void initCity(Customer c) {
        JLabel label = new JLabel("City");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(cityField);

        cityField = new JTextField(10);
        cityField.setBounds(20,45,710,40);

        this.add(label);
        this.add(cityField);
    }

    private void  initState(Customer c) {
        JLabel label = new JLabel("State");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(stateField);

        stateField = new JTextField(10);
        stateField.setBounds(20,45,710,40);

        this.add(label);
        this.add(stateField);
    }

    private void initPostal(Customer c) {
        JLabel label = new JLabel("Postal Code");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(postalField);

        postalField = new JTextField(10);
        postalField.setBounds(20,45,710,40);

        this.add(label);
        this.add(postalField);
    }

    private void clearFields() {
        customerIdField.setText("");
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        streetField.setText("");
        cityField.setText("");
        stateField.setText("");
        postalField.setText("");
    }

    private String[] getCustomerName() {
        String temp = nameField.getText().trim();
        String[] tempA = temp.split(" ");
        for (String s : tempA) {
            s.replaceAll(" ", "");
        }
        return tempA;
    }

    public String getCustomerId() { return this.customerIdField.getText().trim(); }
}
