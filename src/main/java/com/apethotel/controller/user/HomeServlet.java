/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apethotel.controller.user;
import com.apethotel.dal.impl.UsersDAO;
import com.apethotel.dao.CagesDAO2;
import com.apethotel.entity.Cages;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Acer
 */
public class HomeServlet extends HttpServlet {

    UsersDAO usersDAO;
    CagesDAO2 cagesDAO2;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //set encoding UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        //tao ra session
        HttpSession session = request.getSession();
        //tao DAO
        cagesDAO2 = new CagesDAO2();
        List<Cages> listCages;

        //get du lieu tu database len
        //get ve action
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        switch (action) {
            case "search":
                listCages = findByKeyWord(request, response);
                break;
            default:
                listCages = cagesDAO2.findAllCages();
        }

        //set listBook vao session
        session.setAttribute("listCages", listCages);
        // go to homepage
        request.getRequestDispatcher("views/user/home-page/homePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private List<Cages> findByKeyWord(HttpServletRequest request, HttpServletResponse response) {
        //get ve keyword
        String keyword = request.getParameter("keyword");
        request.setAttribute("findKeyWord", keyword);
        // tao ra DAO
        cagesDAO2 = new CagesDAO2();
        return cagesDAO2.findByKeyWord(keyword);
    }

}
