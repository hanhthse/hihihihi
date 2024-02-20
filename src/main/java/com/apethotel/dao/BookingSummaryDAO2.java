/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apethotel.dao;

import com.apethotel.entity.BookingSummary;
import com.apethotel.entity.Cages;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import mylib.DBUtils;

/**
 *
 * @author Acer
 */
public class BookingSummaryDAO2 {

    public ArrayList<BookingSummary> findAll() {
        ArrayList<BookingSummary> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [bookingId]"
                        + "      ,[UserName]"
                        + "      ,[PetName]"
                        + "      ,[cageId]"
                        + "      ,[startDate]"
                        + "      ,[endDate]"
                        + "      ,[totalCost]"
                        + "      ,[Status]"
                        + "      ,[image]"
                        + "  FROM [dbo].[BookingSummary]";
                Statement st = cn.createStatement();
                ResultSet table = st.executeQuery(sql);
                if (table != null) {
                    while (table.next()) {
                        int bookingId = table.getInt("bookingId");
                        String username = table.getString("UserName");
                        String PetName = table.getString("PetName");
                        int cageId = table.getInt("cageId");
                        Timestamp startDate = table.getTimestamp("startDate");
                        Timestamp endDate = table.getTimestamp("endDate");
                        double totalCost = table.getDouble("totalCost");
                        String image = table.getString("image");
                        String status = table.getString("Status");
                        list.add(new BookingSummary(bookingId, username, PetName, cageId, startDate, endDate, totalCost, image, status));
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;

    }

    public ArrayList<BookingSummary> findByProperty(String status) {
        ArrayList<BookingSummary> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [bookingId]"
                        + "      ,[UserName]"
                        + "      ,[PetName]"
                        + "      ,[cageId]"
                        + "      ,[startDate]"
                        + "      ,[endDate]"
                        + "      ,[totalCost]"
                        + "      ,[Status]"
                        + "      ,[image]"
                        + "  FROM [dbo].[BookingSummary] where Status = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, status);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int bookingId = table.getInt("bookingId");
                        String username = table.getString("UserName");
                        String PetName = table.getString("PetName");
                        int cageId = table.getInt("cageId");
                        Timestamp startDate = table.getTimestamp("startDate");
                        Timestamp endDate = table.getTimestamp("endDate");
                        double totalCost = table.getDouble("totalCost");
                        String image = table.getString("image");
                        String m_status = table.getString("Status");
                        list.add(new BookingSummary(bookingId, username, PetName, cageId, startDate, endDate, totalCost, image, m_status));
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;

    }

    public int changeStatus(int bookingId) {
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Bookings\n"
                        + "set IdStatus = IdStatus+1\n"
                        + "where bookingId = ?";

                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, bookingId);
                result = st.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    public int Cancel(int bookingId) {
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Bookings\n"
                        + "set IdStatus = 5 \n"
                        + "where bookingId = ?";

                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, bookingId);
                result = st.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    public int updateBooking(BookingSummary bs) {
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[Bookings]\n"
                        + "   SET [cageId] = ? \n"
                        + "      ,[startDate] = ?\n"
                        + "      ,[endDate] = ?\n"
                        + "      ,[petImage] = ?\n"
                        + "      ,[IdStatus] = ?\n"
                        + " WHERE  bookingId = ?";

                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, bs.getCageid());
                st.setTimestamp(2, bs.getStartDate());
                st.setTimestamp(3, bs.getEndDate());
                st.setString(4, bs.getImage());
                st.setString(5, bs.getStatus());
                st.setInt(6, bs.getBookingId());
                result = st.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;

    }

//    public static void main(String[] args) {
//        BookingSummaryDAO2 dao = new BookingSummaryDAO2();
//        for (BookingSummary b : dao.findByProperty("complete")) {
//            System.out.println(b);
//        }
//    }
}
