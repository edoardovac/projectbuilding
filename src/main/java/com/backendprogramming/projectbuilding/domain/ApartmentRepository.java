package com.backendprogramming.projectbuilding.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
	
    List<Apartment> findByOwnerSurname(@Param("ownerSurname") String ownerSurname);

    List<Apartment> findByAptNumber(@Param("aptNumber") int aptNumber);

}
