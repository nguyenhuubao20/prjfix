package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.QuestionSet;

public class QuestionSetDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(QuestionSetDAO.class.getName());
    private QuestionDAO questionDAO;

    public QuestionSetDAO() {
        questionDAO = new QuestionDAO();
    }

    public List<QuestionSet> getQuestionSets() throws SQLException {
        List<QuestionSet> questionSets = new ArrayList<>();
        String query = "SELECT QuestionSetID, UserID, QuestionSetName "
                + "FROM QuestionSets";
        try ( PreparedStatement ps = connection.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                QuestionSet questionSet = new QuestionSet();
                questionSet.setQuestionSetId(rs.getInt("QuestionSetID"));
                questionSet.setUserId(rs.getInt("UserID"));
                questionSet.setQuestionSetName(rs.getString("QuestionSetName"));
                questionSets.add(questionSet);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error executing query", ex);
        }
        return questionSets;
    }

    public QuestionSet getQuestionSetById(int id) {
        QuestionSet questionSet = null;
        String query = "SELECT QuestionSetID, UserID, QuestionSetName "
                + "FROM QuestionSets "
                + "WHERE QuestionSetID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    questionSet = new QuestionSet();
                    questionSet.setQuestionSetId(rs.getInt("QuestionSetID"));
                    questionSet.setUserId(rs.getInt("UserID"));
                    questionSet.setQuestionSetName(rs.getString("QuestionSetName"));
                    questionSet.setQuestions(questionDAO.getQuestionsByQuestionSetId(rs.getInt("QuestionSetID")));
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error executing query", ex);
        }
        return questionSet;
    }

    public List<QuestionSet> getQuestionSetsByName(String name) {
        List<QuestionSet> questionSets = new ArrayList<>();
        String query = "SELECT QuestionSetID, UserID, QuestionSetName "
                + "FROM QuestionSets "
                + "WHERE QuestionSetName LIKE ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    QuestionSet questionSet = new QuestionSet();
                    questionSet.setQuestionSetId(rs.getInt("QuestionSetID"));
                    questionSet.setUserId(rs.getInt("UserID"));
                    questionSet.setQuestionSetName(rs.getString("QuestionSetName"));
                    questionSets.add(questionSet);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error executing query", ex);
        }
        return questionSets;
    }

    public List<QuestionSet> getQuestionSetByUserId(int userId) {
        List<QuestionSet> questionSets = new ArrayList<>();
        String query = "SELECT QuestionSetID, UserID, QuestionSetName "
                + "FROM QuestionSets "
                + "WHERE QuestionSetID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    QuestionSet questionSet = new QuestionSet();
                    questionSet.setQuestionSetId(rs.getInt("QuestionSetID"));
                    questionSet.setUserId(rs.getInt("UserID"));
                    questionSet.setQuestionSetName(rs.getString("QuestionSetName"));
                    questionSets.add(questionSet);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error executing query", ex);
        }
        return questionSets;
    }

    public boolean updateQuestionSet(int id, String name) {
        String query = "UPDATE questionSets "
                + "SET QuestionSetName = ? "
                + "WHERE QuestionSetID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error executing query", ex);
            return false;
        }
        return true;
    }

    public int addQuestionSet(int userId, String name) {
        String query = "INSERT INTO questionSets (UserID, QuestionSetName) "
                + "VALUES (?, ?)";
        int generatedKey = 0;
        try ( PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
            ps.setString(2, name);
            ps.executeUpdate();
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    generatedKey = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error executing query", ex);
        }
        return generatedKey;
    }

    public boolean deleteQuestionSet(int id) {
        String query = "DELETE FROM questionSets WHERE QuestionSetID = ?";
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
