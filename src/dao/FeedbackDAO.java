package dao;

import model.Feedback;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    // Method to add a new feedback
    public void addFeedback(Feedback feedback) throws SQLException {
        String query = "INSERT INTO feedback (user_id, package_id, feedback, rating) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, feedback.getUserId());
            ps.setInt(2, feedback.getPackageId());
            ps.setString(3, feedback.getFeedback());
            ps.setInt(4, feedback.getRating());
            ps.executeUpdate();
        }
    }

    // Method to get all feedbacks for a specific package
    public List<Feedback> getFeedbackByPackage(int packageId) throws SQLException {
        List<Feedback> feedbacks = new ArrayList<>();
        String query = "SELECT * FROM feedback WHERE package_id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, packageId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt("id"));
                feedback.setUserId(rs.getInt("user_id"));
                feedback.setPackageId(rs.getInt("package_id"));
                feedback.setFeedback(rs.getString("feedback"));
                feedback.setRating(rs.getInt("rating"));
                feedbacks.add(feedback);
            }
        }
        return feedbacks;
    }
}
