package com.citiustech.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citiustech.model.Equipment;

public interface EquipmentDetailsRepo extends JpaRepository<Equipment, Integer> {

}
