package com.citiustech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.model.PatientDemographicDetails;
import com.citiustech.model.TreatmentDetails;
import com.citiustech.service.TreatmentDetailsService;

@RestController
@RequestMapping(path = "/data")
public class HospitalController {

	@Autowired
	private TreatmentDetailsService detailsService;
	
	@GetMapping(path = "/treatment/{id}")
	public List<TreatmentDetails> getTreatmentById(@PathVariable("id") int patientId) {
		System.out.println(patientId);

		List<TreatmentDetails> treatmentById = detailsService.getTreatmentById(patientId);
		return treatmentById;
	}

}
