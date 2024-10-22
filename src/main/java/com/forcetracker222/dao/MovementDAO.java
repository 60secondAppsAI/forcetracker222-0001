package com.forcetracker222.dao;

import java.util.List;

import com.forcetracker222.dao.GenericDAO;
import com.forcetracker222.domain.Movement;





public interface MovementDAO extends GenericDAO<Movement, Integer> {
  
	List<Movement> findAll();
	






}


