package com.backendprogramming.projectbuilding.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BuildingRepository extends CrudRepository<Building, Long>{

	List<Building> findByAddress(@Param("address") String address);
	
}
