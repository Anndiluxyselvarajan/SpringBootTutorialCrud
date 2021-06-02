package com.example.anndiluxy.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.anndiluxy.model.Tutorial;
import com.example.anndiluxy.repository.TutorialRepository;

	

	@Service
	public class TutorialService {

	    @Autowired
	    TutorialRepository tutorialRepository;
	    
	    public ResponseEntity<Tutorial> createTutorial(Tutorial tutorial) {
		   	 try {
		   		Tutorial tt = tutorialRepository.save(tutorial);
		   	 	return new ResponseEntity<>(tt, HttpStatus.CREATED);
		   	 } catch (Exception e) {
		   	 	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		   	 }
		    }
	    
	    public ResponseEntity<List<Tutorial>> getTutorials(String title) {
	   	 try {
	   	 	List<Tutorial> tutorials = new ArrayList<Tutorial>();
	   	   tutorialRepository.findAll().forEach(tutorials::add);
	   	    if (tutorials.isEmpty()) {
 	   	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 	 	        }
 	   
 	 	        return new ResponseEntity<>(tutorials, HttpStatus.OK);
 	             } catch (Exception e) {
 	 	         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
 	             }
                 }
	   	 	
	   	 	 public ResponseEntity<Tutorial> getTutorialById(String id) {
	   	 	 try{
	   	        Optional<Tutorial> tt = tutorialRepository.findById(id);
               if (tt.isPresent()) {
	   		        return new ResponseEntity<>(tt.get(), HttpStatus.OK);
	   	       } else {
	   		     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   	       }
	   	 	   }
	   	       catch(Exception e){
	   		   return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	   	       }
	           }
	   	  public ResponseEntity<Tutorial> updateTutorial(String id, Tutorial tutorial) {
	 	   	 Optional<Tutorial> t = tutorialRepository.findById((id));

	 	   	 if (t.isPresent()) {
	 	   		 Tutorial _tutorial = t.get();
	 	   	 	_tutorial.setTitle(tutorial.getTitle());
	 	   	 	_tutorial.setDescription(tutorial.getDescription());
	 	   	 	_tutorial.setPublished(tutorial.isPublished());
	 	   	 	return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
	 	   	 } else {
	 	    	 	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 	    	 }
	 	     }

	    public ResponseEntity<List<Tutorial>> findByPublished() {
	   	 try {
	   	 	List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
	   	 	if (tutorials.isEmpty()) {
	   	   	       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   	 	}
	   	 	return new ResponseEntity<>(tutorials, HttpStatus.OK);
	   	 } catch (Exception e) {
	   	 	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   	 }
	    }

	   
	     public ResponseEntity<HttpStatus> deleteTutorial(String id) {
	    	 try {
	    		 tutorialRepository.deleteById(id);
	    	 	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	 } catch (Exception e) {
	    		 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    	 }
	     }

	     public ResponseEntity<HttpStatus> deleteAllTutorials() {
	    	 try {
	    		 tutorialRepository.deleteAll();
	    	 	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	 } catch (Exception e) {
	    	 	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    	 }

	   	 }


}
