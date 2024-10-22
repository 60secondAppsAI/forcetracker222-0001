package com.forcetracker222.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonnelPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<PersonnelDTO> personnels;
}




