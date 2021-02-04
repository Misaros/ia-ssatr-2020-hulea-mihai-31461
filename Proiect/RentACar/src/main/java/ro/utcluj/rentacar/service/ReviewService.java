package ro.utcluj.rentacar.service;

import ro.utcluj.rentacar.connection.DbAccess;
import ro.utcluj.rentacar.model.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReviewService {

    DbAccess dbAccess;

    public ReviewService() throws ClassNotFoundException, SQLException {
        this.dbAccess = DbAccess.getInstance();
    }

    public String addANewReview(Review review) throws SQLException {
        Random random = new Random();
        int id = random.nextInt(999999999);
        try (Statement s = dbAccess.getConnection().createStatement()) {
            s.executeUpdate("INSERT INTO REVIEWS (id, IDCARS, IDUSERS, NOTA) VALUES ("+id+"," + review.getCarId()+ "," + review.getClientId()+ "," + review.getStars() + ")");
        }
        return "Successfully inserted new car: " + review.toString();
    }

    public List<Review> readCars() throws SQLException {
        try (Statement s = dbAccess.getConnection().createStatement()) {
            ResultSet resultSet = s.executeQuery("SELECT * FROM REVIEWS");
            if (resultSet.next()) {
                Review review = createReviewFromResultSet(resultSet);

                List<Review> reviews = new ArrayList<>();
                reviews.add(review);
                while (resultSet.next()) {
                    review = new Review();
                    setFields(resultSet, review);
                    reviews.add(review);
                }
                return reviews;
            } else {
                return new ArrayList<>(0);
            }
        }
    }

    public String deleteReviewById(Integer reviewId) throws SQLException {
        try (Statement s = dbAccess.getConnection().createStatement()) {
            Review review = findReviewById(reviewId);
            if (review != null) {
                s.executeUpdate("DELETE FROM REVIEWS WHERE ID = " + reviewId);
                return "Car with id = " + reviewId + " deleted successfully";
            } else {
                return "Delete failed, review with id = " + reviewId + " not found";
            }
        }
    }

    public Review findReviewById(Integer reviewId) throws SQLException {
        try (Statement s = dbAccess.getConnection().createStatement()) {
            ResultSet resultSet = s.executeQuery("SELECT * FROM REVIEWS WHERE ID = " + reviewId);
            if (resultSet.next()) {
                return createReviewFromResultSet(resultSet);
            } else {
                return null;
            }
        }
    }

    private void setFields(ResultSet resultSet, Review review) throws SQLException {
        review.setId(resultSet.getLong("id"));
        review.setCarId(resultSet.getLong("car_id"));
        review.setClientId(resultSet.getLong("client_id"));
        review.setStars(resultSet.getInt("stars"));
        review.setReview(resultSet.getString("review"));
    }

    private Review createReviewFromResultSet(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        setFields(resultSet, review);
        return review;
    }
}
