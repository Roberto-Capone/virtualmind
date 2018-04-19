package com.forneus.virtualmind.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.forneus.virtualmind.entity.Post;
import com.forneus.virtualmind.entity.dto.PostDTO;

public class PostService {

	public PostService(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@PersistenceContext
	private EntityManager entityManager;

	private int pageSize = 25; // In real life would be configurable

	public List<PostDTO> listPostTitlesAndTopics(int pageNumber) {
		List<Post> posts = entityManager
				.createQuery("SELECT p FROM Post p")
				.setFirstResult((pageNumber - 1) * pageSize)
				.setMaxResults(pageSize)
				.getResultList();
	
		List<PostDTO> result = new ArrayList<PostDTO>(posts.size());

		for (Post post : posts) {
			PostDTO postDto = new PostDTO();
			postDto.setId(post.getId());
			postDto.setTitle(post.getTitle());
			postDto.setTopicName(post.getTopic().getName());
			result.add(postDto);
		}

		return result;
	}

}
