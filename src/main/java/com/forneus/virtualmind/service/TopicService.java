package com.forneus.virtualmind.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.forneus.virtualmind.entity.Topic;
import com.forneus.virtualmind.entity.dto.TopicDTO;

public class TopicService {
	
    public TopicService(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}


	@PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public void updateTopic(int topicId, TopicDTO updated) {
    	
        Topic topic = entityManager.find(Topic.class, topicId);
        if (topic != null) {
             topic.setName(updated.getName());
        } else {
             throw new EntityNotFoundException("["+Topic.class.getName()+"] id=" + topicId);
        }
    }


}
