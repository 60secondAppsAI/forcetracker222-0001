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
import com.forcetracker222.dao.PersonnelDAO;
import com.forcetracker222.domain.Personnel;
import com.forcetracker222.dto.PersonnelDTO;
import com.forcetracker222.dto.PersonnelSearchDTO;
import com.forcetracker222.dto.PersonnelPageDTO;
import com.forcetracker222.dto.PersonnelConvertCriteriaDTO;
import com.forcetracker222.dto.common.RequestDTO;
import com.forcetracker222.dto.common.ResultDTO;
import com.forcetracker222.service.PersonnelService;
import com.forcetracker222.util.ControllerUtils;





@Service
public class PersonnelServiceImpl extends GenericServiceImpl<Personnel, Integer> implements PersonnelService {

    private final static Logger logger = LoggerFactory.getLogger(PersonnelServiceImpl.class);

	@Autowired
	PersonnelDAO personnelDao;

	


	@Override
	public GenericDAO<Personnel, Integer> getDAO() {
		return (GenericDAO<Personnel, Integer>) personnelDao;
	}
	
	public List<Personnel> findAll () {
		List<Personnel> personnels = personnelDao.findAll();
		
		return personnels;	
		
	}

	public ResultDTO addPersonnel(PersonnelDTO personnelDTO, RequestDTO requestDTO) {

		Personnel personnel = new Personnel();

		personnel.setPersonnelId(personnelDTO.getPersonnelId());


		personnel.setName(personnelDTO.getName());


		personnel.setRank(personnelDTO.getRank());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		personnel = personnelDao.save(personnel);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Personnel> getAllPersonnels(Pageable pageable) {
		return personnelDao.findAll(pageable);
	}

	public Page<Personnel> getAllPersonnels(Specification<Personnel> spec, Pageable pageable) {
		return personnelDao.findAll(spec, pageable);
	}

	public ResponseEntity<PersonnelPageDTO> getPersonnels(PersonnelSearchDTO personnelSearchDTO) {
	
			Integer personnelId = personnelSearchDTO.getPersonnelId(); 
 			String name = personnelSearchDTO.getName(); 
 			String rank = personnelSearchDTO.getRank(); 
 			String sortBy = personnelSearchDTO.getSortBy();
			String sortOrder = personnelSearchDTO.getSortOrder();
			String searchQuery = personnelSearchDTO.getSearchQuery();
			Integer page = personnelSearchDTO.getPage();
			Integer size = personnelSearchDTO.getSize();

	        Specification<Personnel> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, personnelId, "personnelId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, rank, "rank"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("rank")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Personnel> personnels = this.getAllPersonnels(spec, pageable);
		
		//System.out.println(String.valueOf(personnels.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(personnels.getTotalPages()));
		
		List<Personnel> personnelsList = personnels.getContent();
		
		PersonnelConvertCriteriaDTO convertCriteria = new PersonnelConvertCriteriaDTO();
		List<PersonnelDTO> personnelDTOs = this.convertPersonnelsToPersonnelDTOs(personnelsList,convertCriteria);
		
		PersonnelPageDTO personnelPageDTO = new PersonnelPageDTO();
		personnelPageDTO.setPersonnels(personnelDTOs);
		personnelPageDTO.setTotalElements(personnels.getTotalElements());
		return ResponseEntity.ok(personnelPageDTO);
	}

	public List<PersonnelDTO> convertPersonnelsToPersonnelDTOs(List<Personnel> personnels, PersonnelConvertCriteriaDTO convertCriteria) {
		
		List<PersonnelDTO> personnelDTOs = new ArrayList<PersonnelDTO>();
		
		for (Personnel personnel : personnels) {
			personnelDTOs.add(convertPersonnelToPersonnelDTO(personnel,convertCriteria));
		}
		
		return personnelDTOs;

	}
	
	public PersonnelDTO convertPersonnelToPersonnelDTO(Personnel personnel, PersonnelConvertCriteriaDTO convertCriteria) {
		
		PersonnelDTO personnelDTO = new PersonnelDTO();
		
		personnelDTO.setPersonnelId(personnel.getPersonnelId());

	
		personnelDTO.setName(personnel.getName());

	
		personnelDTO.setRank(personnel.getRank());

	

		
		return personnelDTO;
	}

	public ResultDTO updatePersonnel(PersonnelDTO personnelDTO, RequestDTO requestDTO) {
		
		Personnel personnel = personnelDao.getById(personnelDTO.getPersonnelId());

		personnel.setPersonnelId(ControllerUtils.setValue(personnel.getPersonnelId(), personnelDTO.getPersonnelId()));

		personnel.setName(ControllerUtils.setValue(personnel.getName(), personnelDTO.getName()));

		personnel.setRank(ControllerUtils.setValue(personnel.getRank(), personnelDTO.getRank()));



        personnel = personnelDao.save(personnel);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PersonnelDTO getPersonnelDTOById(Integer personnelId) {
	
		Personnel personnel = personnelDao.getById(personnelId);
			
		
		PersonnelConvertCriteriaDTO convertCriteria = new PersonnelConvertCriteriaDTO();
		return(this.convertPersonnelToPersonnelDTO(personnel,convertCriteria));
	}







}
