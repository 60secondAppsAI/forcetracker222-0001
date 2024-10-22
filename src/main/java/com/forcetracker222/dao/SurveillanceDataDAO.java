package com.forcetracker222.dao;

import java.util.List;

import com.forcetracker222.dao.GenericDAO;
import com.forcetracker222.domain.SurveillanceData;





public interface SurveillanceDataDAO extends GenericDAO<SurveillanceData, Integer> {
  
	List<SurveillanceData> findAll();
	






}


