package model;

public class Question {

    private int questionId;
    private int questionSetId;
    private String questionText;
    private String answerText;

    public Question() {
    }

    public Question(int questionId, int questionSetId, String questionText, String answerText) {
        this.questionId = questionId;
        this.questionSetId = questionSetId;
        this.questionText = questionText;
        this.answerText = answerText;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(int questionSetId) {
        this.questionSetId = questionSetId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
