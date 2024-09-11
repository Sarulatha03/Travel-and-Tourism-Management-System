package service;

import dao.FeedbackDAO;
import model.Feedback;

import java.sql.SQLException;
import java.util.List;

public class FeedbackService {

    private FeedbackDAO feedbackDAO = new FeedbackDAO();

    // Method to add feedback
    public void addFeedback(Feedback feedback) throws SQLException {
        feedbackDAO.addFeedback(feedback);
    }

    // Method to get feedback for a specific package
    public List<Feedback> getFeedbackByPackage(int packageId) throws SQLException {
        return feedbackDAO.getFeedbackByPackage(packageId);
    }
}
