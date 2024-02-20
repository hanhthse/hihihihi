/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apethotel.dao;

import com.apethotel.entity.Cages;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mylib.DBUtils;

/**
 *
 * @author Acer
 */
public class CagesDAO2 {
    //lấy tất cả các cages có sẵn

    public ArrayList<Cages> findAllCages() {
        ArrayList<Cages> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [cageId]\n"
                        + "      ,[description]\n"
                        + "      ,[size]\n"
                        + "      ,[pricePerDay]\n"
                        + "  FROM [dbo].[Cages]";
                Statement st = cn.createStatement();
                ResultSet table = st.executeQuery(sql);
                if (table != null) {
                    while (table.next()) {
                        int m_cageId = table.getInt("cageId");
                        String m_des = table.getString("description");
                        String m_size = table.getString("size");
                        double m_pirce = table.getDouble("pricePerDay");
                        list.add(new Cages(m_cageId, m_des, m_size, m_pirce));
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

    public List<Cages> findByKeyWord(String keyword) {
        List<Cages> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select * from Cages \n"
                        + "where description like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int m_cageId = table.getInt("cageId");
                        String m_des = table.getString("description");
                        String m_size = table.getString("size");
                        double m_pirce = table.getDouble("pricePerDay");
                        list.add(new Cages(m_cageId, m_des, m_size, m_pirce));
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

//    public static void main(String[] args) {
//        CagesDAO2 dao = new CagesDAO2();
//        for (Cages b : dao.findByKeyWord("5kg")) {
//            System.out.println(b.toString());
//        }
//    }
}
