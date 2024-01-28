/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apethotel.dal.impl;

import com.apethotel.dal.DBContext;
import com.apethotel.dal.GenericDAO;
import com.apethotel.entity.Cages;
import java.util.List;

/**
 *
 * @author Acer
 */
public class CagesDAO extends GenericDAO<Cages>{

    @Override
    public List<Cages> findAll() {
        return queryGenericDAO(Cages.class);
    }
    

    @Override
    public int insert(Cages t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
