package com.citiustech.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citiustech.model.Diagnosis;

public interface DiagnosisDetailsRepo extends JpaRepository<Diagnosis, Integer> {

}
