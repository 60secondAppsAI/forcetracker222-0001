package com.forcetracker222.dao;

import java.util.List;

import com.forcetracker222.dao.GenericDAO;
import com.forcetracker222.domain.Personnel;





public interface PersonnelDAO extends GenericDAO<Personnel, Integer> {
  
	List<Personnel> findAll();
	






}


