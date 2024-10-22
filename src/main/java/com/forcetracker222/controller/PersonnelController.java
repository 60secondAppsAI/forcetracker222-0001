package com.forcetracker222.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.forcetracker222.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.forcetracker222.domain.Personnel;
import com.forcetracker222.dto.PersonnelDTO;
import com.forcetracker222.dto.PersonnelSearchDTO;
import com.forcetracker222.dto.PersonnelPageDTO;
import com.forcetracker222.service.PersonnelService;
import com.forcetracker222.dto.common.RequestDTO;
import com.forcetracker222.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/personnel")
@RestController
public class PersonnelController {

	private final static Logger logger = LoggerFactory.getLogger(PersonnelController.class);

	@Autowired
	PersonnelService personnelService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Personnel> getAll() {

		List<Personnel> personnels = personnelService.findAll();
		
		return personnels;	
	}

	//@ReadAccess
	@GetMapping(value = "/{personnelId}")
	@ResponseBody
	public PersonnelDTO getPersonnel(@PathVariable Integer personnelId) {
		
		return (personnelService.getPersonnelDTOById(personnelId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addPersonnel", method = RequestMethod.POST)
	public ResponseEntity<?> addPersonnel(@RequestBody PersonnelDTO personnelDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = personnelService.addPersonnel(personnelDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/personnels")
	public ResponseEntity<PersonnelPageDTO> getPersonnels(PersonnelSearchDTO personnelSearchDTO) {
 
		return personnelService.getPersonnels(personnelSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updatePersonnel", method = RequestMethod.POST)
	public ResponseEntity<?> updatePersonnel(@RequestBody PersonnelDTO personnelDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = personnelService.updatePersonnel(personnelDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}