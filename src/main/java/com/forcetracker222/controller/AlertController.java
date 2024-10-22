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

import com.forcetracker222.domain.Alert;
import com.forcetracker222.dto.AlertDTO;
import com.forcetracker222.dto.AlertSearchDTO;
import com.forcetracker222.dto.AlertPageDTO;
import com.forcetracker222.service.AlertService;
import com.forcetracker222.dto.common.RequestDTO;
import com.forcetracker222.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/alert")
@RestController
public class AlertController {

	private final static Logger logger = LoggerFactory.getLogger(AlertController.class);

	@Autowired
	AlertService alertService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Alert> getAll() {

		List<Alert> alerts = alertService.findAll();
		
		return alerts;	
	}

	//@ReadAccess
	@GetMapping(value = "/{alertId}")
	@ResponseBody
	public AlertDTO getAlert(@PathVariable Integer alertId) {
		
		return (alertService.getAlertDTOById(alertId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addAlert", method = RequestMethod.POST)
	public ResponseEntity<?> addAlert(@RequestBody AlertDTO alertDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = alertService.addAlert(alertDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/alerts")
	public ResponseEntity<AlertPageDTO> getAlerts(AlertSearchDTO alertSearchDTO) {
 
		return alertService.getAlerts(alertSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateAlert", method = RequestMethod.POST)
	public ResponseEntity<?> updateAlert(@RequestBody AlertDTO alertDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = alertService.updateAlert(alertDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
