package com.backendprogramming.projectbuilding;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.backendprogramming.projectbuilding.domain.Building;
import com.backendprogramming.projectbuilding.domain.BuildingRepository;
import com.backendprogramming.projectbuilding.domain.Document;
import com.backendprogramming.projectbuilding.domain.DocumentRepository;

@DataJpaTest
public class DocumentRepositoryTest {
	@Autowired
	private DocumentRepository drepository;
	@Autowired
	private BuildingRepository brepository;

	@Test
	public void createNewDocumentTest() {
		Building building = new Building("11 Downing Street", 2, 6);
		brepository.save(building);
		Document document = new Document("File Name", "PDF", "Description", "2023-01-01", "D01", building);
		drepository.save(document);
		assertThat(document.getId()).isNotNull();
		assertThat(drepository.findById(document.getId())).isNotNull();
	}

	@Test
	public void findByFileNameTest() {
		Building building = new Building("11 Downing Street", 2, 6);
		brepository.save(building);
		Document document = new Document("File Name", "TYPE", "Description", "2023-01-01", "D01", building);
		drepository.save(document);
		List<Document> documents = drepository.findByFileName("File Name");
		assertThat(documents).hasSize(1);
	}
	
	@Test
	public void findByTypeTest() {
		Building building = new Building("11 Downing Street", 2, 6);
		brepository.save(building);
		Document document = new Document("File Name", "TYPE", "Description", "2023-01-01", "D01", building);
		drepository.save(document);
		List<Document> documents = drepository.findByType("TYPE");
		assertThat(documents).hasSize(1);
	}
	
	@Test
	public void findByDocumentDateTest() {
		Building building = new Building("11 Downing Street", 2, 6);
		brepository.save(building);
		Document document = new Document("File Name", "TYPE", "Description", "1920-01-01", "D01", building);
		drepository.save(document);
		List<Document> documents = drepository.findByDocumentDate("1920-01-01");
		assertThat(documents).hasSize(1);
	}
	
	@Test
	public void findByDocumentNumberTest() {
		Building building = new Building("11 Downing Street", 2, 6);
		brepository.save(building);
		Document document = new Document("File Name", "TYPE", "Description", "1920-01-01", "D999", building);
		drepository.save(document);
		List<Document> documents = drepository.findByDocumentNumber("D999");
		assertThat(documents).hasSize(1);
	}
	
	@Test
	public void deleteNewDocument() {
		Building building = new Building("11 Downing Street", 2, 6);
		brepository.save(building);
		Document document = new Document("File Name", "TYPE", "Description", "1920-01-01", "D999", building);
		drepository.save(document);
		List<Document> documents = drepository.findByFileName("File Name");
		assertThat(documents).hasSize(1);
		drepository.delete(document);
		List<Document> newDocuments = drepository.findByFileName("File Name");
		assertThat(newDocuments).hasSize(0);
	}
}
