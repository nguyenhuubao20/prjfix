package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Question;

public class QuestionDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(QuestionDAO.class.getName());

    public List<Question> getQuestionsByQuestionSetId(int questionSetId) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT QuestionID, QuestionSetID, QuestionText, AnswerText "
                + "FROM Questions";
        try ( PreparedStatement ps = connection.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Question question = new Question();
                question.setQuestionId(rs.getInt("QuestionID"));
                question.setQuestionSetId(rs.getInt("QuestionSetID"));
                question.setQuestionText(rs.getString("QuestionText"));
                question.setAnswerText(rs.getString("AnswerText"));
                questions.add(question);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error executing query", ex);
        }
        return questions;
    }

    public Question getQuestionById(int id) {
        Question question = null;
        String query = "SELECT QuestionID, QuestionSetID, QuestionText, AnswerText "
                + "FROM Questions "
                + "WHERE QuestionID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    question = new Question();
                    question.setQuestionId(rs.getInt("QuestionID"));
                    question.setQuestionSetId(rs.getInt("QuestionSetID"));
                    question.setQuestionText(rs.getString("QuestionText"));
                    question.setAnswerText(rs.getString("AnswerText"));
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error executing query", ex);
        }
        return question;
    }

    public boolean updateQuestion(int id, String questionText, String answerText) {
        String query = "UPDATE questions "
                + "SET questionText = ?, answerText = ? "
                + "WHERE QuestionID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, questionText);
            ps.setString(2, answerText);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error executing query", ex);
            return false;
        }
        return true;
    }

    public boolean addQuestion(int questionSetId, String questionText, String answerText) {
        String query = "INSERT INTO questions (QuestionSetID, QuestionText, AnswerText) "
                + "VALUES (?, ?, ?)";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, questionSetId);
            ps.setString(2, questionText);
            ps.setString(3, answerText);
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error executing query", ex);
            return false;
        }
        return true;
    }

    public boolean deleteQuestion(int id) {
        String query = "DELETE FROM questions WHERE QuestionID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error executing query", ex);
            return false;
        }
        return true;
    }

}
