<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Signup</title>
        <style>
            body {
                background-image:url('assets/img/Login-backgroud.jpg');
                background-size: 100% 100%;
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f0f0f0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
                width: 300px;
                text-align: center;
            }

            input[type="email"],
            input[type="password"],
            input[type="text"]{
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border-radius: 5px;
                border: 1px solid #ddd;
                box-sizing: border-box;
            }

            input[type="checkbox"] {
                margin-right: 5px;
                margin-bottom: 15px;
            }

            input[type="submit"] {
                width: 100%;
                padding: 10px;
                border-radius: 5px;
                border: none;
                color: #fff;
                background-color: #000;
                color: #fff;
                cursor: pointer;
                transition: background-color 0.3s, color 0.3s, border-color 0.3s;
            }

            input[type="submit"]:hover {
                background-color: #9B68FF;
            }

            h2 {
                margin-bottom: 20px;
                color: #333;
            }

            h5 {
                margin-bottom: 0;
                padding-top: 10px;
                color: #000;
                cursor: pointer;
                margin-top: 10px;

            }
        </style>
    </head>
    <body>

        <form action="signup" method="post">
            <h2>Sign-up</h2>

            <div>
                <input type="text" id="username" name="username" placeholder="Display Name" required>
            </div>

            <div>
                <input type="email" id="email" name="email" placeholder="Email Address" required>
            </div>
            <div>
                <input placeholder="Password" type="password" id="password" name="password" required>
            </div>

            <div style="justify-content: space-around; padding: auto; margin-bottom: 10px">
                <input type="radio" name="role" value="student" > Student
                <input type="radio" name="role" value="teacher" style="margin-left: 30px"> Teacher
            </div>

            <div>
                <input type="submit" value="SignUp">
            </div>
            <div>
                <h5>Back to <a href="/quiz/login" class="text-decoration-none">Login</a></h5>
            </div>

            <c:if test="${mess!=null}">
                <div class="message">
                    <h4 style="color: red;">${mess}</h4>
                </div>
            </c:if>
        </form>

    </body>
</html>