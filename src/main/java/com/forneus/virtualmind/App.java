package com.forneus.virtualmind;

import javax.persistence.EntityManager;

import com.forneus.virtualmind.helper.PersistenceManager;
import com.forneus.virtualmind.service.PostService;


public class App {
	public static void main(String[] args) {
		
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		PostService service = new PostService(em);
		
		service.benchMark();
		
//		int topicId = 1;
//		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//		
//		TopicService service = new TopicService(em);
//
//		TopicDTO dto = new TopicDTO().setName("TRAGAME");
//		service.updateTopic(topicId, dto);
		


//		Test 1
//		Topic topic = new Topic();
//		topic.setName("Topi");
//		
//				
//		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//
//		em.getTransaction().begin();
//		em.persist(topic);
//		em.getTransaction().commit();	
//		em.close();
//		
//		PersistenceManager.INSTANCE.close();

	}
	
}
