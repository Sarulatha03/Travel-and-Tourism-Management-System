package service;

import dao.PaymentDAO;
import model.Payment;

import java.sql.SQLException;
import java.util.List;

public class PaymentService {

    private PaymentDAO paymentDAO = new PaymentDAO();

    // Method to add a payment record
    public void addPayment(Payment payment) throws SQLException {
        paymentDAO.addPayment(payment);
    }

    // Method to get all payments for a specific user
    public List<Payment> getPaymentsByUser(int userId) throws SQLException {
        return paymentDAO.getPaymentsByUser(userId);
    }

    // Method to update payment status
    public void updatePaymentStatus(int paymentId, String status) throws SQLException {
        paymentDAO.updatePaymentStatus(paymentId, status);
    }

    // Method to get all payment records
    public List<Payment> getAllPayments() throws SQLException {
        return paymentDAO.getAllPayments();
    }
    
}
