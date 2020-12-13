package org.ucvts.comics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ucvts.comics.model.Order;

public class OrderDAO {

    public static Order getOrder(long orderId) throws SQLException {
        Order order = null;

        Connection conn = DAO.getConnection();

        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM orders WHERE orderId = ?");

        pstmt.setLong(1, orderId);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            order = new Order();

            order.setOrderId(rs.getLong(1));
            order.setOrderDate(rs.getLong(2));
            order.setStatus(rs.getString(3));
            order.setTotal(rs.getDouble(4));
            order.setCustomerId(rs.getLong(5));
        }

        rs.close();
        pstmt.close();
        conn.close();

        return order;
    }

    public static List<Order> getOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        Connection conn = DAO.getConnection();

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM orders");

        while (rs.next()) {
            Order order = new Order();

            order.setOrderId(rs.getLong(1));
            order.setOrderDate(rs.getLong(2));
            order.setStatus(rs.getString(3));
            order.setTotal(rs.getDouble(4));
            order.setCustomerId(rs.getLong(5));

            orders.add(order);
        }

        rs.close();
        stmt.close();
        conn.close();

        return orders;
    }

    public static void insertOrder(Order order) throws SQLException {
        Connection conn = DAO.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO orders (" +
                        "   orderdate, " +
                        "   status, " +
                        "   total, " +
                        "   customerid, " +
                        ") VALUES (?, ?, ?, ?)"
        );

        pstmt.setLong(1, order.getOrderDate());
        pstmt.setString(2, order.getStatus());
        pstmt.setDouble(3, order.getTotal());
        pstmt.setLong(4, order.getCustomerId());

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public static void updateOrder(Order order) throws SQLException {
        Connection conn = DAO.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE orders SET " +
                        "   orderdate = ?, " +
                        "   status = ?, " +
                        "   total = ?, " +
                        "   customerid = ?, " +
                        "WHERE id = ?"
        );

        pstmt.setLong(1, order.getOrderDate());
        pstmt.setString(2, order.getStatus());
        pstmt.setDouble(3, order.getTotal());
        pstmt.setLong(4, order.getCustomerId());

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public static void deleteOrder(Order order) throws SQLException {
        Connection conn = DAO.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM orders WHERE id = ?");

        pstmt.setLong(1, order.getOrderId());

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}
