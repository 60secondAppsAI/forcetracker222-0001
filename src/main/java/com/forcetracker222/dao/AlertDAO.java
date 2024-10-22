package com.forcetracker222.dao;

import java.util.List;

import com.forcetracker222.dao.GenericDAO;
import com.forcetracker222.domain.Alert;





public interface AlertDAO extends GenericDAO<Alert, Integer> {
  
	List<Alert> findAll();
	






}


