package com.forcetracker222.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker222.domain.Personnel;
import com.forcetracker222.dto.PersonnelDTO;
import com.forcetracker222.dto.PersonnelSearchDTO;
import com.forcetracker222.dto.PersonnelPageDTO;
import com.forcetracker222.dto.PersonnelConvertCriteriaDTO;
import com.forcetracker222.service.GenericService;
import com.forcetracker222.dto.common.RequestDTO;
import com.forcetracker222.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PersonnelService extends GenericService<Personnel, Integer> {

	List<Personnel> findAll();

	ResultDTO addPersonnel(PersonnelDTO personnelDTO, RequestDTO requestDTO);

	ResultDTO updatePersonnel(PersonnelDTO personnelDTO, RequestDTO requestDTO);

    Page<Personnel> getAllPersonnels(Pageable pageable);

    Page<Personnel> getAllPersonnels(Specification<Personnel> spec, Pageable pageable);

	ResponseEntity<PersonnelPageDTO> getPersonnels(PersonnelSearchDTO personnelSearchDTO);
	
	List<PersonnelDTO> convertPersonnelsToPersonnelDTOs(List<Personnel> personnels, PersonnelConvertCriteriaDTO convertCriteria);

	PersonnelDTO getPersonnelDTOById(Integer personnelId);







}





