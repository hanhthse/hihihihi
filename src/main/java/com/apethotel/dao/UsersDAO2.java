/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apethotel.dao;

import com.apethotel.entity.Cages;
import com.apethotel.entity.Users;
import com.bookstore.Constant.Constant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author Acer
 */
public class UsersDAO2 {
    // findAllUser

    public ArrayList<Users> findAll() {
        ArrayList<Users> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [userId]\n"
                        + "      ,[name]\n"
                        + "      ,[email]\n"
                        + "      ,[password]\n"
                        + "      ,[phoneNumber]\n"
                        + "      ,[roleId]\n"
                        + "  FROM [dbo].[Users]";
                Statement st = cn.createStatement();
                ResultSet table = st.executeQuery(sql);
                if (table != null) {
                    while (table.next()) {
                        int UserID = table.getInt("userId");
                        String Name = table.getString("name");
                        String Email = table.getString("email");
                        String password = table.getString("password");
                        String PhoneNumber = table.getString("phoneNumber");
                        int roleId = table.getInt("roleId");

                        list.add(new Users(UserID, Name, Email, password, PhoneNumber, roleId));
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

    // tìm kiếm tài khoản mật khẩu
    public Users findByemailPassword(String email, String password) {
        Users result = null;
        Connection cn = null;
        try {
            //make connection giua backend va sqlserver
            cn = DBUtils.makeConnection();
            if (cn != null) {
                //viet sql va exec cau sql
                String sql = "SELECT [userId]\n"
                        + "      ,[name]\n"
                        + "      ,[email]\n"
                        + "      ,[password]\n"
                        + "      ,[phoneNumber]\n"
                        + "      ,[roleId]\n"
                        + "  FROM [dbo].[Users]"
                        + "where email=? and password=? COLLATE SQL_Latin1_General_CP1_CI_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                //lay emai,password cua input params gan vao 2 cho ?
                pst.setString(1, email);
                pst.setString(2, password);
                //run cau sql
                ResultSet table = pst.executeQuery();
                //doc data trong table
                if (table != null && table.next()) {
                    int UserID = table.getInt("userId");
                    String Name = table.getString("name");
                    String Email = table.getString("email");
                    String m_password = table.getString("password");
                    String PhoneNumber = table.getString("phoneNumber");
                    int roleId = table.getInt("roleId");
                    result = new Users(UserID, Name, Email, m_password, PhoneNumber, roleId);
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
        return result;
    }

    // tìm kiếm có mail chưa
    public boolean findByEmail(String email) {
        Users result = null;
        Connection cn = null;
        try {
            //make connection giua backend va sqlserver
            cn = DBUtils.makeConnection();
            if (cn != null) {
                //viet sql va exec cau sql
                String sql = "SELECT [userId]\n"
                        + "      ,[name]\n"
                        + "      ,[email]\n"
                        + "      ,[password]\n"
                        + "      ,[phoneNumber]\n"
                        + "      ,[roleId]\n"
                        + "  FROM [dbo].[Users]"
                        + "where email=? COLLATE SQL_Latin1_General_CP1_CI_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                //lay emai,password cua input params gan vao 2 cho ?
                pst.setString(1, email);
                //run cau sql
                ResultSet table = pst.executeQuery();
                //doc data trong table
                if (table != null && table.next()) {
                    int UserID = table.getInt("userId");
                    String Name = table.getString("name");
                    String Email = table.getString("email");
                    String m_password = table.getString("password");
                    String PhoneNumber = table.getString("phoneNumber");
                    int roleId = table.getInt("roleId");
                    result = new Users(UserID, Name, Email, m_password, PhoneNumber, roleId);
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

        return result == null ? true : false;
    }


    public int insert(Users user) {
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[Users]\n"
                        + "           ([name]\n"
                        + "           ,[email]\n"
                        + "           ,[password]\n"
                        + "           ,[phoneNumber]\n"
                        + "           ,[roleId])\n"
                        + "     VALUES\n"
                        + "           (?,?,?,?,?)";

                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, user.getName());
                st.setString(2, user.getEmail());
                st.setString(3, user.getPassword());
                st.setString(4, user.getPhoneNumber());
                st.setInt(5, user.getRoleId());
                
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
    
//    
//        public static void main(String[] args) {
//        Users user = Users.builder()
//                .name("Test1")
//                .password("123")
//                .email("hihihiahah@gmail.com")
//                .roleId(Constant.ROLE_USER)
//                .build();
//        UsersDAO2 dao = new UsersDAO2();
//        System.out.println(dao.insert(user));
//
//    }
}
