/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apethotel.dal.impl;

import com.apethotel.dal.DBContext;
import com.apethotel.dal.GenericDAO;
import com.apethotel.entity.BookingSummary;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Acer
 */
public class BookingSummaryDAO extends GenericDAO<BookingSummary> {

    @Override
    public List<BookingSummary> findAll() {
        return queryGenericDAO(BookingSummary.class);
    }

    @Override
    public int insert(BookingSummary t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public static void main(String[] args) {
//        BookingSummaryDAO bk = new BookingSummaryDAO();
//        for(BookingSummary b : bk.findAll()){
//            System.out.println(b);
//        }
//    }
    public void changeStatus(String bookingId) {
        String sql = "update Bookings\n"
                + "set IdStatus = IdStatus+1\n"
                + "where bookingId = ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("bookingId", bookingId);
        updateGenericDAO(sql, parameterMap);
    }

    public void Cancel(String bookingId) {
        String sql = "update Bookings\n"
                + "set IdStatus = 5 \n"
                + "where bookingId = ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("bookingId", bookingId);
        updateGenericDAO(sql, parameterMap);
    }

    public List<BookingSummary> findByProperty(String status) {
        String sql = "select * from BookingSummary where Status = ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("status", status);
        return queryGenericDAO(BookingSummary.class, sql, parameterMap);

    }

    public void updateBooking(BookingSummary bs) {
        String sql = "UPDATE [dbo].[Bookings]\n"
                + "   SET [cageId] = ? \n"
                + "      ,[startDate] = ?\n"
                + "      ,[endDate] = ?\n"
                + "      ,[petImage] = ?\n"
                + "      ,[IdStatus] = ?\n"
                + " WHERE  bookingId = ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("cageid", bs.getCageid());
        parameterMap.put("startDate", bs.getStartDate());
        parameterMap.put("endDate", bs.getEndDate());
        parameterMap.put("petImage", bs.getImage());
        parameterMap.put("IdStatus", bs.getStatus());
        updateGenericDAO(sql, parameterMap);
    }
}
