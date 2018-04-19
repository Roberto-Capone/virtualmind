package com.forneus.virtualmind.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import com.forneus.virtualmind.entity.Post;
import com.forneus.virtualmind.entity.dto.PostDTO;

public class PostService {

	private static int BENCHMARK_SIZE = 9973;

	public PostService(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@PersistenceContext
	private EntityManager entityManager;

	private static int PAGE_SIZE = 7; // In real life would be configurable

	//public List<PostDTO> listPostTitlesAndTopics(int pageNumber) {
	
	public long[] listPostTitlesAndTopics(int pageNumber) {
		
		long[] result = new long[2];
		 
		long start = System.currentTimeMillis();;
		
		
		List<Post> posts = (List<Post>) entityManager.createQuery("SELECT p FROM Post p")
				.setFirstResult(calculateOffset(pageNumber))
				.setMaxResults(PAGE_SIZE).getResultList();
		
		long queryTime = System.currentTimeMillis();
		
		result[0] = queryTime - start ;
		
		List<PostDTO> list = new ArrayList<PostDTO>(posts.size());

		for (Post post : posts) {
			PostDTO postDto = new PostDTO();
			postDto.setId(post.getId());
			postDto.setTitle(post.getTitle());
			postDto.setTopicName(post.getTopic().getName());
			list.add(postDto);
		}

		long dtoTime = System.currentTimeMillis();
		
		result[1] =  dtoTime - queryTime;

		return result;
	}

	public void benchMark() {
		int pageQty = 1 + (count()/PAGE_SIZE);
		long iterations = BENCHMARK_SIZE * pageQty;
		
		long[] totals = new long[3];
		
		for (int i = 0; i < BENCHMARK_SIZE; i++) {
			for (int j = 1; j <= pageQty; j++) {
				long[] times = listPostTitlesAndTopics(j);
				totals[0] += times[0];
				totals[1] += times[1];
				totals[2] += (times[0]+times[1]);
			}
			
		}
		System.out.println("Iteraciones : "+ iterations);
		System.out.println();
		System.out.println("__________________________________");
		System.out.println();
		System.out.println("Tiempo total: "+ totals[2]);
		System.out.println("Queries total: "+ totals[0]);
		System.out.println("DTOs total: "+ totals[1]);
		
		System.out.println("Promedios: "+ ((double)totals[2]/iterations)+" -- "+ ((double)totals[0]/iterations)+" -- "+ ((double)totals[1]/iterations));
		
		totals = null;
		totals = new long[3];
		
		for (int i = 0; i < BENCHMARK_SIZE; i++) {
			for (int j = 1; j <= pageQty; j++) {
				long[] times = listWithCriteria(j);
				totals[0] += times[0];
			}
			
		}

		System.out.println();
		System.out.println("__________________________________");
		System.out.println();

		System.out.println("Tiempo total: "+ totals[0]);		
		System.out.println("Promedios: "+ ((double)totals[0]/iterations));

	}
	//public List<PostDTO> listWithCriteria(int pageNumber) {

	public long[]  listWithCriteria(int pageNumber) {
		long[] result = new long[2];
		 
		long start = System.currentTimeMillis();;
		
		

		Session session = (Session) entityManager.getDelegate();

		List<PostDTO> list = (List<PostDTO>) session.createCriteria(Post.class)
				.setFirstResult(calculateOffset(pageNumber))
				.setMaxResults(PAGE_SIZE)
				.createAlias("topic", "t")
				.setProjection(Projections.projectionList()
						.add(Projections.property("t.name"), "topicName"))
				.setResultTransformer(Transformers.aliasToBean(PostDTO.class)).list();
		
		long queryTime = System.currentTimeMillis();
		
		result[0] = queryTime - start ;

		return result;
	}

	private int calculateOffset(int pageNumber) {
		return (PAGE_SIZE * (pageNumber - 1));
	}
	
	private int count() {
		Session session = (Session) entityManager.getDelegate();
		return ((Number) session.createCriteria(Post.class).setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	

}
