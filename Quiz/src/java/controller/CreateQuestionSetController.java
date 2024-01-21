package controller;

import dal.QuestionDAO;
import dal.QuestionSetDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateQuestionSetController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] questions = request.getParameterValues("question");
        String[] answers = request.getParameterValues("answer");
        String questionSetName = request.getParameter("questionSetName");

        QuestionDAO questionDAO = new QuestionDAO();
        QuestionSetDAO questionSetDAO = new QuestionSetDAO();
        
        int newQuestionSetId = questionSetDAO.addQuestionSet(2, questionSetName);

        for (int i = 0; i < questions.length; i++) {
            questionDAO.addQuestion(newQuestionSetId, questions[i], answers[i]);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
