/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apethotel.dal.impl;

import com.apethotel.dal.GenericDAO;
import com.apethotel.entity.Users;
import java.util.List;

/**
 *
 * @author Acer
 */
public class UsersDAO extends GenericDAO<Users>{

    @Override
    public List<Users> findAll() {
        return queryGenericDAO(Users.class);
    }

    @Override
    public int insert(Users t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
