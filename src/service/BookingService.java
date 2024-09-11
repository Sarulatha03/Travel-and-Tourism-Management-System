package service;

import dao.BookingDAO;
import model.Booking;

import java.sql.SQLException;
import java.util.List;

public class BookingService {

    private BookingDAO bookingDAO = new BookingDAO();

    // Method to add a new booking
    public void addBooking(Booking booking) throws SQLException {
        bookingDAO.addBooking(booking);
    }

    // Method to get bookings for a specific user
    public List<Booking> getBookingsByUser(int userId) throws SQLException {
        return bookingDAO.getBookingsByUser(userId);
    }
    public List<Booking> getAllBookings() throws SQLException {
        return bookingDAO.getAllBookings();
    }
}
