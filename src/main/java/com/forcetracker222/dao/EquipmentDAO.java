package com.forcetracker222.dao;

import java.util.List;

import com.forcetracker222.dao.GenericDAO;
import com.forcetracker222.domain.Equipment;





public interface EquipmentDAO extends GenericDAO<Equipment, Integer> {
  
	List<Equipment> findAll();
	






}


