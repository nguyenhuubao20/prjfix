package model;

import java.util.List;

public class QuestionSet {

    private int questionSetId;
    private int userId;
    private String questionSetName;
    private List<Question> questions;

    public QuestionSet() {
    }

    public QuestionSet(int questionSetId, int userId, String questionSetName, List<Question> questions) {
        this.questionSetId = questionSetId;
        this.userId = userId;
        this.questionSetName = questionSetName;
        this.questions = questions;
    }

    public String getQuestionSetName() {
        return questionSetName;
    }

    public void setQuestionSetName(String questionSetName) {
        this.questionSetName = questionSetName;
    }

    public int getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(int questionSetId) {
        this.questionSetId = questionSetId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
