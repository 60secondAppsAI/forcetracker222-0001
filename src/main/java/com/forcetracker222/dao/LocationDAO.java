package com.forcetracker222.dao;

import java.util.List;

import com.forcetracker222.dao.GenericDAO;
import com.forcetracker222.domain.Location;





public interface LocationDAO extends GenericDAO<Location, Integer> {
  
	List<Location> findAll();
	






}


