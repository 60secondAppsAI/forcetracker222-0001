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

import com.forcetracker222.domain.MilitaryUnit;
import com.forcetracker222.dto.MilitaryUnitDTO;
import com.forcetracker222.dto.MilitaryUnitSearchDTO;
import com.forcetracker222.dto.MilitaryUnitPageDTO;
import com.forcetracker222.service.MilitaryUnitService;
import com.forcetracker222.dto.common.RequestDTO;
import com.forcetracker222.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/militaryUnit")
@RestController
public class MilitaryUnitController {

	private final static Logger logger = LoggerFactory.getLogger(MilitaryUnitController.class);

	@Autowired
	MilitaryUnitService militaryUnitService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<MilitaryUnit> getAll() {

		List<MilitaryUnit> militaryUnits = militaryUnitService.findAll();
		
		return militaryUnits;	
	}

	//@ReadAccess
	@GetMapping(value = "/{militaryUnitId}")
	@ResponseBody
	public MilitaryUnitDTO getMilitaryUnit(@PathVariable Integer militaryUnitId) {
		
		return (militaryUnitService.getMilitaryUnitDTOById(militaryUnitId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addMilitaryUnit", method = RequestMethod.POST)
	public ResponseEntity<?> addMilitaryUnit(@RequestBody MilitaryUnitDTO militaryUnitDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = militaryUnitService.addMilitaryUnit(militaryUnitDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/militaryUnits")
	public ResponseEntity<MilitaryUnitPageDTO> getMilitaryUnits(MilitaryUnitSearchDTO militaryUnitSearchDTO) {
 
		return militaryUnitService.getMilitaryUnits(militaryUnitSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateMilitaryUnit", method = RequestMethod.POST)
	public ResponseEntity<?> updateMilitaryUnit(@RequestBody MilitaryUnitDTO militaryUnitDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = militaryUnitService.updateMilitaryUnit(militaryUnitDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
