package com.forcetracker222.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker222.domain.Nation;
import com.forcetracker222.dto.NationDTO;
import com.forcetracker222.dto.NationSearchDTO;
import com.forcetracker222.dto.NationPageDTO;
import com.forcetracker222.dto.NationConvertCriteriaDTO;
import com.forcetracker222.service.GenericService;
import com.forcetracker222.dto.common.RequestDTO;
import com.forcetracker222.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface NationService extends GenericService<Nation, Integer> {

	List<Nation> findAll();

	ResultDTO addNation(NationDTO nationDTO, RequestDTO requestDTO);

	ResultDTO updateNation(NationDTO nationDTO, RequestDTO requestDTO);

    Page<Nation> getAllNations(Pageable pageable);

    Page<Nation> getAllNations(Specification<Nation> spec, Pageable pageable);

	ResponseEntity<NationPageDTO> getNations(NationSearchDTO nationSearchDTO);
	
	List<NationDTO> convertNationsToNationDTOs(List<Nation> nations, NationConvertCriteriaDTO convertCriteria);

	NationDTO getNationDTOById(Integer nationId);







}





