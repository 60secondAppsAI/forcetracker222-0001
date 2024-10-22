package com.forcetracker222.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker222.domain.SurveillanceData;
import com.forcetracker222.dto.SurveillanceDataDTO;
import com.forcetracker222.dto.SurveillanceDataSearchDTO;
import com.forcetracker222.dto.SurveillanceDataPageDTO;
import com.forcetracker222.dto.SurveillanceDataConvertCriteriaDTO;
import com.forcetracker222.service.GenericService;
import com.forcetracker222.dto.common.RequestDTO;
import com.forcetracker222.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SurveillanceDataService extends GenericService<SurveillanceData, Integer> {

	List<SurveillanceData> findAll();

	ResultDTO addSurveillanceData(SurveillanceDataDTO surveillanceDataDTO, RequestDTO requestDTO);

	ResultDTO updateSurveillanceData(SurveillanceDataDTO surveillanceDataDTO, RequestDTO requestDTO);

    Page<SurveillanceData> getAllSurveillanceDatas(Pageable pageable);

    Page<SurveillanceData> getAllSurveillanceDatas(Specification<SurveillanceData> spec, Pageable pageable);

	ResponseEntity<SurveillanceDataPageDTO> getSurveillanceDatas(SurveillanceDataSearchDTO surveillanceDataSearchDTO);
	
	List<SurveillanceDataDTO> convertSurveillanceDatasToSurveillanceDataDTOs(List<SurveillanceData> surveillanceDatas, SurveillanceDataConvertCriteriaDTO convertCriteria);

	SurveillanceDataDTO getSurveillanceDataDTOById(Integer surveillanceDataId);







}





