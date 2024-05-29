package com.citiustech.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citiustech.model.Nurse;

public interface NurseDetailsRepo extends JpaRepository<Nurse, Integer> {

}
