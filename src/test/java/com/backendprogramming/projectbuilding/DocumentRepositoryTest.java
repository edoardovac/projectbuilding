package com.backendprogramming.projectbuilding;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendprogramming.projectbuilding.domain.Building;
import com.backendprogramming.projectbuilding.domain.BuildingRepository;
import com.backendprogramming.projectbuilding.domain.Document;
import com.backendprogramming.projectbuilding.domain.DocumentRepository;

@SpringBootTest
public class DocumentRepositoryTest {
	@Autowired
	private DocumentRepository drepository;
	@Autowired
	private BuildingRepository brepository;

	private Building building = new Building("11 Downing Street", 2, 6);
	private Document document = new Document("File Name", "TYPE", "Description", "1920-12-25", "D999", building);

	@Test
	public void createNewDocumentTest() {
		brepository.save(building);
		drepository.save(document);
		assertThat(document.getId()).isNotNull();
		assertThat(drepository.findById(document.getId())).isNotNull();
		drepository.delete(document);
		brepository.delete(building);
	}

	@Test
	public void findByFileNameTest() {
		brepository.save(building);
		drepository.save(document);
		List<Document> documents = drepository.findByFileName("File Name");
		assertThat(documents).hasSize(1);
		drepository.delete(document);
		brepository.delete(building);
	}

	@Test
	public void findByTypeTest() {
		brepository.save(building);
		drepository.save(document);
		List<Document> documents = drepository.findByType("TYPE");
		assertThat(documents).hasSize(1);
		drepository.delete(document);
		brepository.delete(building);
	}

	@Test
	public void findByDocumentDateTest() {
		brepository.save(building);
		drepository.save(document);
		List<Document> documents = drepository.findByDocumentDate("1920-12-25");
		assertThat(documents).hasSize(1);
		drepository.delete(document);
		brepository.delete(building);
	}

	@Test
	public void findByDocumentNumberTest() {
		brepository.save(building);
		drepository.save(document);
		List<Document> documents = drepository.findByDocumentNumber("D999");
		assertThat(documents).hasSize(1);
		drepository.delete(document);
		brepository.delete(building);
	}

	@Test
	public void deleteNewDocument() {
		brepository.save(building);
		drepository.save(document);
		List<Document> documents = drepository.findByFileName("File Name");
		assertThat(documents).hasSize(1);
		Document newDocument = documents.get(0);
		drepository.delete(newDocument);
		List<Document> newDocuments = drepository.findByFileName("File Name");
		assertThat(newDocuments).hasSize(0);
		Building building = brepository.findByAddress("11 Downing Street").get(0);
		brepository.delete(building);
	}
}
