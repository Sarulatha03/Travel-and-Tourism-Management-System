package dao;

import model.Booking;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // Method to add a new booking
    public void addBooking(Booking booking) throws SQLException {
        String query = "INSERT INTO bookings (user_id, package_id, booking_date) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getPackageId());
            ps.setDate(3, new java.sql.Date(booking.getBookingDate().getTime()));
            ps.executeUpdate();
        }
    }

    // Method to get bookings for a specific user
    public List<Booking> getBookingsByUser(int userId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE user_id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setPackageId(rs.getInt("package_id"));
                booking.setBookingDate(rs.getDate("booking_date"));
                bookings.add(booking);
            }
        }
        return bookings;
    }
    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setId(resultSet.getInt("id"));
                booking.setUserId(resultSet.getInt("user_id"));
                booking.setPackageId(resultSet.getInt("package_id"));
                booking.setBookingDate(resultSet.getDate("booking_date"));
                bookings.add(booking);
            }
        }
        return bookings;
    }
}
