/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apethotel.controller.user;

import com.apethotel.dal.impl.UsersDAO;
import com.apethotel.dao.UsersDAO2;
import com.apethotel.entity.Users;
import com.bookstore.Constant.Constant;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Acer
 */
public class AuthenServlet extends HttpServlet {

    UsersDAO2 userDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url;
        //get action
        String action = request.getParameter("action") == null
                ? "login"
                : request.getParameter("action");
        //sw action
        switch (action) {
            case "login":
//                login.jsp
                url = "views/user/home-page/login.jsp";
                break;
            case "register":
//                regiser.jsp
                url = "views/user/home-page/register.jsp";
                break;
            case "logout":
                logoutDoGet(request, response);
                url = "home";
                break;

            default:
                url = "views/user/home-page/login.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //tạo đối tượng user 
        Users user;
        //get action
        String action = request.getParameter("action") == null
                ? "login"
                : request.getParameter("action");
        switch (action) {
            case "login":
                //function thực hiện login
                loginDoPost(request, response);

                break;
            case "register":
                registerDoPost(request, response);
                break;

            default:

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void logoutDoGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constant.SESSION_ACCOUNT);
    }

    private void loginDoPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //get về thông tin
        userDAO = new UsersDAO2();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //kiem tra xem account co ton tai khong
        Users user = userDAO.findByemailPassword(email, password);
        //        neu account khong ton tai thi tai khoan or mat khau sai
        if (user == null) {
            request.setAttribute("error", "Username or password incorrect !");
            //chuyen lai trang login.jsp
            request.getRequestDispatcher("views/user/home-page/login.jsp").forward(request, response);
        } else {
//            set vao session
            HttpSession session = request.getSession();
            session.setAttribute(Constant.SESSION_ACCOUNT, user);
            //chuyen ve home
            response.sendRedirect("home");
        }
    }

    private void registerDoPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //tao doi tuong session, accountDAO
        HttpSession session = request.getSession();
        userDAO = new UsersDAO2();
        //get ve cac thong tin
        String fullname = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // tạo đối tượng từ dữ liệu đã được get về
        Users user = Users.builder()
                .name(fullname)
                .password(password)
                .email(email)
                .roleId(Constant.ROLE_USER)
                .build();
        //kiem tra email da ton tai trong database chua
        boolean isExist = userDAO.findByEmail(email);
// neu chua ton tai thi moi insert vao DB
        if (isExist) {
            userDAO.insert(user);
            //chuyen ve trang login cho nguoi ta login
            response.sendRedirect("authen");
        } else {
            request.setAttribute("error", "Email used already, please choose another one");
            request.getRequestDispatcher("views/user/home-page/register.jsp").forward(request, response);
        }
        //chuyen ve trang home
    }

}
