package com.forneus.virtualmind.rest.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.forneus.virtualmind.entity.dto.TopicDTO;
import com.forneus.virtualmind.service.TopicService;

@RestController
public class TopicController {
	

	@Autowired
	private TopicService topicService;
	
	@PutMapping("/topic/{id}")	// HTTP method: PUT http://www.virtualmind.io/interview/topic/13
	public ResponseEntity update(@PathVariable Integer id, @RequestBody TopicDTO topicDTO) {

		try {
			topicService.updateTopic(id, topicDTO);
		} catch (EntityNotFoundException enfe) {

			return new ResponseEntity("No hay un topic con ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(topicDTO, HttpStatus.OK);
	}


}
