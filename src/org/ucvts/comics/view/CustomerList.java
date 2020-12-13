package org.ucvts.comics.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.ucvts.comics.MidtownComics;
import org.ucvts.comics.controller.ViewManager;

public class CustomerList extends JPanel implements ActionListener{

    private ViewManager manager;

    public CustomerList(ViewManager manager) {
        super(new BorderLayout());

        this.manager = manager;
        this.init();
    }

    private void init() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}