package com.forcetracker222.dao;

import java.util.List;

import com.forcetracker222.dao.GenericDAO;
import com.forcetracker222.domain.Nation;





public interface NationDAO extends GenericDAO<Nation, Integer> {
  
	List<Nation> findAll();
	






}


