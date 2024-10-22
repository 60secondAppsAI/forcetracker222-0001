package com.forcetracker222.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.forcetracker222.dao.GenericDAO;
import com.forcetracker222.service.GenericService;
import com.forcetracker222.service.impl.GenericServiceImpl;
import com.forcetracker222.dao.SurveillanceDataDAO;
import com.forcetracker222.domain.SurveillanceData;
import com.forcetracker222.dto.SurveillanceDataDTO;
import com.forcetracker222.dto.SurveillanceDataSearchDTO;
import com.forcetracker222.dto.SurveillanceDataPageDTO;
import com.forcetracker222.dto.SurveillanceDataConvertCriteriaDTO;
import com.forcetracker222.dto.common.RequestDTO;
import com.forcetracker222.dto.common.ResultDTO;
import com.forcetracker222.service.SurveillanceDataService;
import com.forcetracker222.util.ControllerUtils;





@Service
public class SurveillanceDataServiceImpl extends GenericServiceImpl<SurveillanceData, Integer> implements SurveillanceDataService {

    private final static Logger logger = LoggerFactory.getLogger(SurveillanceDataServiceImpl.class);

	@Autowired
	SurveillanceDataDAO surveillanceDataDao;

	


	@Override
	public GenericDAO<SurveillanceData, Integer> getDAO() {
		return (GenericDAO<SurveillanceData, Integer>) surveillanceDataDao;
	}
	
	public List<SurveillanceData> findAll () {
		List<SurveillanceData> surveillanceDatas = surveillanceDataDao.findAll();
		
		return surveillanceDatas;	
		
	}

	public ResultDTO addSurveillanceData(SurveillanceDataDTO surveillanceDataDTO, RequestDTO requestDTO) {

		SurveillanceData surveillanceData = new SurveillanceData();

		surveillanceData.setSurveillanceDataId(surveillanceDataDTO.getSurveillanceDataId());


		surveillanceData.setObservedActivity(surveillanceDataDTO.getObservedActivity());


		surveillanceData.setTimestamp(surveillanceDataDTO.getTimestamp());


		surveillanceData.setReportedBy(surveillanceDataDTO.getReportedBy());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		surveillanceData = surveillanceDataDao.save(surveillanceData);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SurveillanceData> getAllSurveillanceDatas(Pageable pageable) {
		return surveillanceDataDao.findAll(pageable);
	}

	public Page<SurveillanceData> getAllSurveillanceDatas(Specification<SurveillanceData> spec, Pageable pageable) {
		return surveillanceDataDao.findAll(spec, pageable);
	}

	public ResponseEntity<SurveillanceDataPageDTO> getSurveillanceDatas(SurveillanceDataSearchDTO surveillanceDataSearchDTO) {
	
			Integer surveillanceDataId = surveillanceDataSearchDTO.getSurveillanceDataId(); 
 			String observedActivity = surveillanceDataSearchDTO.getObservedActivity(); 
   			String reportedBy = surveillanceDataSearchDTO.getReportedBy(); 
 			String sortBy = surveillanceDataSearchDTO.getSortBy();
			String sortOrder = surveillanceDataSearchDTO.getSortOrder();
			String searchQuery = surveillanceDataSearchDTO.getSearchQuery();
			Integer page = surveillanceDataSearchDTO.getPage();
			Integer size = surveillanceDataSearchDTO.getSize();

	        Specification<SurveillanceData> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, surveillanceDataId, "surveillanceDataId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, observedActivity, "observedActivity"); 
			
 			
			spec = ControllerUtils.andIfNecessary(spec, reportedBy, "reportedBy"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("observedActivity")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("reportedBy")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<SurveillanceData> surveillanceDatas = this.getAllSurveillanceDatas(spec, pageable);
		
		//System.out.println(String.valueOf(surveillanceDatas.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(surveillanceDatas.getTotalPages()));
		
		List<SurveillanceData> surveillanceDatasList = surveillanceDatas.getContent();
		
		SurveillanceDataConvertCriteriaDTO convertCriteria = new SurveillanceDataConvertCriteriaDTO();
		List<SurveillanceDataDTO> surveillanceDataDTOs = this.convertSurveillanceDatasToSurveillanceDataDTOs(surveillanceDatasList,convertCriteria);
		
		SurveillanceDataPageDTO surveillanceDataPageDTO = new SurveillanceDataPageDTO();
		surveillanceDataPageDTO.setSurveillanceDatas(surveillanceDataDTOs);
		surveillanceDataPageDTO.setTotalElements(surveillanceDatas.getTotalElements());
		return ResponseEntity.ok(surveillanceDataPageDTO);
	}

	public List<SurveillanceDataDTO> convertSurveillanceDatasToSurveillanceDataDTOs(List<SurveillanceData> surveillanceDatas, SurveillanceDataConvertCriteriaDTO convertCriteria) {
		
		List<SurveillanceDataDTO> surveillanceDataDTOs = new ArrayList<SurveillanceDataDTO>();
		
		for (SurveillanceData surveillanceData : surveillanceDatas) {
			surveillanceDataDTOs.add(convertSurveillanceDataToSurveillanceDataDTO(surveillanceData,convertCriteria));
		}
		
		return surveillanceDataDTOs;

	}
	
	public SurveillanceDataDTO convertSurveillanceDataToSurveillanceDataDTO(SurveillanceData surveillanceData, SurveillanceDataConvertCriteriaDTO convertCriteria) {
		
		SurveillanceDataDTO surveillanceDataDTO = new SurveillanceDataDTO();
		
		surveillanceDataDTO.setSurveillanceDataId(surveillanceData.getSurveillanceDataId());

	
		surveillanceDataDTO.setObservedActivity(surveillanceData.getObservedActivity());

	
		surveillanceDataDTO.setTimestamp(surveillanceData.getTimestamp());

	
		surveillanceDataDTO.setReportedBy(surveillanceData.getReportedBy());

	

		
		return surveillanceDataDTO;
	}

	public ResultDTO updateSurveillanceData(SurveillanceDataDTO surveillanceDataDTO, RequestDTO requestDTO) {
		
		SurveillanceData surveillanceData = surveillanceDataDao.getById(surveillanceDataDTO.getSurveillanceDataId());

		surveillanceData.setSurveillanceDataId(ControllerUtils.setValue(surveillanceData.getSurveillanceDataId(), surveillanceDataDTO.getSurveillanceDataId()));

		surveillanceData.setObservedActivity(ControllerUtils.setValue(surveillanceData.getObservedActivity(), surveillanceDataDTO.getObservedActivity()));

		surveillanceData.setTimestamp(ControllerUtils.setValue(surveillanceData.getTimestamp(), surveillanceDataDTO.getTimestamp()));

		surveillanceData.setReportedBy(ControllerUtils.setValue(surveillanceData.getReportedBy(), surveillanceDataDTO.getReportedBy()));



        surveillanceData = surveillanceDataDao.save(surveillanceData);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SurveillanceDataDTO getSurveillanceDataDTOById(Integer surveillanceDataId) {
	
		SurveillanceData surveillanceData = surveillanceDataDao.getById(surveillanceDataId);
			
		
		SurveillanceDataConvertCriteriaDTO convertCriteria = new SurveillanceDataConvertCriteriaDTO();
		return(this.convertSurveillanceDataToSurveillanceDataDTO(surveillanceData,convertCriteria));
	}







}
