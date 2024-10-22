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

import com.forcetracker222.domain.SurveillanceData;
import com.forcetracker222.dto.SurveillanceDataDTO;
import com.forcetracker222.dto.SurveillanceDataSearchDTO;
import com.forcetracker222.dto.SurveillanceDataPageDTO;
import com.forcetracker222.service.SurveillanceDataService;
import com.forcetracker222.dto.common.RequestDTO;
import com.forcetracker222.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/surveillanceData")
@RestController
public class SurveillanceDataController {

	private final static Logger logger = LoggerFactory.getLogger(SurveillanceDataController.class);

	@Autowired
	SurveillanceDataService surveillanceDataService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SurveillanceData> getAll() {

		List<SurveillanceData> surveillanceDatas = surveillanceDataService.findAll();
		
		return surveillanceDatas;	
	}

	//@ReadAccess
	@GetMapping(value = "/{surveillanceDataId}")
	@ResponseBody
	public SurveillanceDataDTO getSurveillanceData(@PathVariable Integer surveillanceDataId) {
		
		return (surveillanceDataService.getSurveillanceDataDTOById(surveillanceDataId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addSurveillanceData", method = RequestMethod.POST)
	public ResponseEntity<?> addSurveillanceData(@RequestBody SurveillanceDataDTO surveillanceDataDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = surveillanceDataService.addSurveillanceData(surveillanceDataDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/surveillanceDatas")
	public ResponseEntity<SurveillanceDataPageDTO> getSurveillanceDatas(SurveillanceDataSearchDTO surveillanceDataSearchDTO) {
 
		return surveillanceDataService.getSurveillanceDatas(surveillanceDataSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateSurveillanceData", method = RequestMethod.POST)
	public ResponseEntity<?> updateSurveillanceData(@RequestBody SurveillanceDataDTO surveillanceDataDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = surveillanceDataService.updateSurveillanceData(surveillanceDataDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
