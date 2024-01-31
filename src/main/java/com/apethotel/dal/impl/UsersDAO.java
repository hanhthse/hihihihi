/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apethotel.dal.impl;

import com.apethotel.dal.DBContext;
import com.apethotel.dal.GenericDAO;
import com.apethotel.entity.Users;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Acer
 */
public class UsersDAO extends GenericDAO<Users> {

    @Override
    public List<Users> findAll() {
        return queryGenericDAO(Users.class);
    }

    @Override
    public int insert(Users t) {
        return insertGenericDAO(t);
    }

    public Users findByemailPassword(String email, String password) {
        String sql = "select * from users where email = ? and password = ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("email", email);
        parameterMap.put("password", password);
        return queryGenericDAO(Users.class, sql, parameterMap).isEmpty()
                ? null
                : queryGenericDAO(Users.class, sql, parameterMap).get(0);
    }

    public boolean findByEmail(String email) {
        String sql = "select * from users where email = ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("email", email);
        return queryGenericDAO(Users.class, sql, parameterMap).isEmpty()
                ? true : false;
    }

}
