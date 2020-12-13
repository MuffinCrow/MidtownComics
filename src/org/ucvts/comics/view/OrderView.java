package org.ucvts.comics.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.ucvts.comics.controller.ViewManager;
import org.ucvts.comics.view.OrderList;

@SuppressWarnings("serial")
public class OrderView extends JPanel implements ActionListener {

    private ViewManager manager;
    private PaymentForm form;
    private JLabel total;
    private JButton submit;
    private JScrollPane scroll;

    public OrderView(ViewManager manager) {
        super(new BorderLayout());

        this.manager = manager;
        this.init();
    }

    /**
     * Updates the order total label.
     *
     * @param total the new total
     */

    public void updateOrderTotal(double total) {
        this.total.setText("Order Total: $" + total);
    }

    /**
     * Clears all fields.
     */

    public void clearOrder() {
        total.setText("");
        form.clearFields();
    }

    /*
     * Initializes all UI components.
     */

    private void init() {
        submit = new JButton("Submit");
        submit.addActionListener(this);

        total = new JLabel("Order Total: $", SwingConstants.RIGHT);
        total.setFont(new Font("DialogInput", Font.BOLD, 16));
        total.setBorder(new EmptyBorder(10, 10, 10, 10));

        form = new PaymentForm();

        this.add(total, BorderLayout.NORTH);
        this.add(form, BorderLayout.CENTER);
        this.add(submit, BorderLayout.SOUTH);
    }

    /*
     * Handles button clicks in this view.
     *
     * @param e the event that triggered this action
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.equals(submit)) {
            try {
                manager.submitOrder();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}