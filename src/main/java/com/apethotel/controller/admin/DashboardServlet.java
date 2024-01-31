/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apethotel.controller.admin;

import com.apethotel.dal.impl.BookingSummaryDAO;
import com.apethotel.dal.impl.CagesDAO;
import com.apethotel.dal.impl.UsersDAO;
import com.apethotel.entity.BookingSummary;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Acer
 */
public class DashboardServlet extends HttpServlet {

    // Tạo ra đối tượng DAO
    UsersDAO userDAO;
    CagesDAO cagesDAO;
    BookingSummaryDAO bookingDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Tạo đối tượng DAO
        bookingDAO = new BookingSummaryDAO();
        //tim ve toan bo bookingsummary
        List<BookingSummary> listBookings;
        String action = request.getParameter("action") == null ? "default" : request.getParameter("action");
        listBookings = bookingDAO.findAll();
        //hiển thị thông báo bao nhiêu trong từng trạng thái
        int unconfirm = numberOf("unconfirm", listBookings);
        int confirm = numberOf("confirm", listBookings);
        int progress = numberOf("progress", listBookings);
        int complete = numberOf("complete", listBookings);
        int cancel = numberOf("cancel", listBookings);
        //set vao session
        HttpSession session = request.getSession();
        session.setAttribute("unconfirm", unconfirm);
        session.setAttribute("confirm", confirm);
        session.setAttribute("progress", progress);
        session.setAttribute("complete", complete);
        session.setAttribute("cancel", cancel);

        switch (action) {
            case "viewUnconfirm":
                listBookings = bookingDAO.findByProperty("Unconfirm");
                break;
            case "viewConfirm":
                listBookings = bookingDAO.findByProperty("Confirm");
                break;
            case "viewProgress":
                listBookings = bookingDAO.findByProperty("Progress");
                break;
            case "viewCancel":
                listBookings = bookingDAO.findByProperty("Cancel");
                break;
            case "viewComplete":
                listBookings = bookingDAO.findByProperty("Complete");
                break;
            case "default":
                listBookings = bookingDAO.findAll();
                break;
            default:
                throw new AssertionError();
        }

        // chuyển qua trang dashboard.jsp
        session.setAttribute("listBookings", listBookings);
        request.getRequestDispatcher("../views/admin/dashboard/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Tạo đối tượng DAO
        bookingDAO = new BookingSummaryDAO();
        //get ve action
        String action = request.getParameter("action");
        //sw
        switch (action) {
            case "changeStatus":
                changeStatusDoPost(request, response);
                break;
            case "cancel":
                changesStatusCancelDoPost(request, response);
                break;
                
            case"edit":
                edit(request);
                break;
            default:
                throw new AssertionError();
        }
        response.sendRedirect("dashboard");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void changeStatusDoPost(HttpServletRequest request, HttpServletResponse response) {
        //get ve bookingId
        String bookingId = request.getParameter("bookingId");
        // function change status
        bookingDAO = new BookingSummaryDAO();
        bookingDAO.changeStatus(bookingId);
    }

    private void changesStatusCancelDoPost(HttpServletRequest request, HttpServletResponse response) {
        //get ve bookingId
        String bookingId = request.getParameter("id");
        // function change status
        bookingDAO = new BookingSummaryDAO();
        bookingDAO.Cancel(bookingId);
    }

    private int numberOf(String status, List<BookingSummary> listBookings) {
        int count = 0;
        for (BookingSummary bk : listBookings) {
            if (bk.getStatus().equals(status)) {
                count++;
            }
        }
        return count;
    }

    private void edit(HttpServletRequest request) throws IOException {
        BookingSummary bs = new BookingSummary();

    int id = Integer.parseInt(request.getParameter("bookingId"));
    String username = request.getParameter("userName");
    String petName = request.getParameter("petName");
    int cageId = Integer.parseInt(request.getParameter("cageId"));
    
    // Giả sử startDate được gửi ở dạng chuỗi "yyyy-MM-dd"
    String startDateStr = request.getParameter("startDate");
    String endDateStr   = request.getParameter("endDate");
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(startDateStr);
        java.util.Date parsedDateEnd = dateFormat.parse(endDateStr);
        Timestamp startDate = new Timestamp(parsedDate.getTime());
        Timestamp endDate = new Timestamp(parsedDateEnd.getTime());
        // Set startDate vào đối tượng BookingSummary của bạn
        bs.setStartDate(startDate);
        bs.setEndDate(endDate);

    } catch (Exception e) { // ParseException
        // Xử lý lỗi ở đây
    }
    double price = Double.parseDouble(request.getParameter("totalCost")) ;
    String status = request.getParameter("status");
    String imagePath=null;
    try {
            //get image
            Part part = request.getPart("image");
            if (part == null || part.getSize() <= 0) {
                // su dung hinh anh hien tai va cap nhat duong dan(imagePath)
                String currentImage = request.getParameter("currentImage");
                bs.setImage(currentImage);
            } else {
                try {
                    String path = request.getServletContext().getRealPath("/image");
                    //tao ra duong dan den file anh
                    File dir = new File(path);
                    //khong co duong dan --> tự tạo ra
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    //get duoi anh
                    File image = new File(dir, part.getSubmittedFileName());
                    part.write(image.getAbsolutePath());
                    imagePath = "/APetHotel/image/" + image.getName();
                } catch (Exception e) {
                    System.out.println(e.getMessage() +"Error private void edit(HttpServletRequest request)");
                }
            }
        } catch (ServletException ex) {
            Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage() +"Error private void edit(HttpServletRequest request)");
        }
    
        //setter paramter
        bs.setBookingId(id);
        bs.setUserName(username);
        bs.setPetName(petName);
        bs.setCageid(cageId);
        bs.setImage(imagePath);
        bs.setTotalCost(price);
        bs.setStatus(status);
        BookingSummaryDAO dao= new BookingSummaryDAO();
        dao.updateBooking(bs);
    }

}
