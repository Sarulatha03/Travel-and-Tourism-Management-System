package dao;

import model.Payment;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    // Method to add a new payment record
    public void addPayment(Payment payment) throws SQLException {
        String query = "INSERT INTO payments (user_id, booking_id, amount, payment_date, payment_status) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, payment.getUserId());
            ps.setInt(2, payment.getBookingId());
            ps.setDouble(3, payment.getAmount());
            ps.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
            ps.setString(5, payment.getPaymentStatus());
            ps.executeUpdate();
        }
    }

    // Method to retrieve all payments for a specific user
    public List<Payment> getPaymentsByUser(int userId) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments WHERE user_id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setUserId(rs.getInt("user_id"));
                payment.setBookingId(rs.getInt("booking_id"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentDate(rs.getDate("payment_date"));
                payment.setPaymentStatus(rs.getString("payment_status"));
                payments.add(payment);
            }
        }
        return payments;
    }

    // Method to update the payment status in the database
    public void updatePaymentStatus(int paymentId, String status) throws SQLException {
        String query = "UPDATE payments SET payment_status = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, paymentId);
            ps.executeUpdate();
        }
    }

    // Method to retrieve all payment records
    public List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setUserId(rs.getInt("user_id"));
                payment.setBookingId(rs.getInt("booking_id"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentDate(rs.getDate("payment_date"));
                payment.setPaymentStatus(rs.getString("payment_status"));
                payments.add(payment);
            }
        }
        return payments;
    }
}
