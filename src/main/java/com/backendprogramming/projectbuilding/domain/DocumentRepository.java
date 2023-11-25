package com.backendprogramming.projectbuilding.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DocumentRepository extends CrudRepository<Document, Long> {

	List<Document> findByFileName(@Param("fileName") String fileName);

	List<Document> findByType(@Param("type") String type);

	List<Document> findByDocumentDate(@Param("docomentDate") String documentDate);

	List<Document> findByDocumentNumber(@Param("documentNumber") String documentNumber);
}
