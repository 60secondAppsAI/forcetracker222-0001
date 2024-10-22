package com.forcetracker222.dao;

import java.util.List;

import com.forcetracker222.dao.GenericDAO;
import com.forcetracker222.domain.MilitaryUnit;





public interface MilitaryUnitDAO extends GenericDAO<MilitaryUnit, Integer> {
  
	List<MilitaryUnit> findAll();
	






}


