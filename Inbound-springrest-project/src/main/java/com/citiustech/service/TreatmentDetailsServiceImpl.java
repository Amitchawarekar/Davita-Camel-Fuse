package com.citiustech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.dao.PatientDetailsRepo;
import com.citiustech.dao.TreatmentDetailsRepo;
import com.citiustech.model.TreatmentDetails;

@Service
public class TreatmentDetailsServiceImpl implements TreatmentDetailsService {

	@Autowired
	private PatientDetailsRepo detailsRepo;
	
	@Autowired
	private TreatmentDetailsRepo treatmentRepo;

	
	@Override
	public List<TreatmentDetails> getTreatmentById(int id) {

		System.out.println(id);

		List<TreatmentDetails> treatmentById = treatmentRepo.treatmentByPatientId(id);
		System.out.println(treatmentById);

		// TODO Auto-generated method stub
		return treatmentById;
	}

}
