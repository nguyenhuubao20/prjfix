package controller;

import dal.UserDAO;
import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            String mess;
            UserDAO dao = new UserDAO();
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            int roleId;
            if(role.equals("student")){
                roleId = 1;
            } else{
                roleId = 3;
            }
            User user = new User(username, roleId, email, password, role);
            if(dao.getUserByEmail(email) != null){
                mess = "Email is already exist! Try other email";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }else{
                dao.addUser(user);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("Lỗi r cu");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}