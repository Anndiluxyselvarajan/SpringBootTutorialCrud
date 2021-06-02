package com.example.anndiluxy.repository;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.anndiluxy.model.Tutorial;

@Repository
public interface TutorialRepository extends MongoRepository<Tutorial, String> {
	List<Tutorial> findByPublished(boolean published);
    List<Tutorial>findByTitleContaining(String title);

	}