package com.forneus.virtualmind.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "topic")
public class Topic {
	
	public Topic() {
		super();
	}

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	
	@OneToMany(mappedBy = "topic")
	private List<Post> posts;

	public Integer getId() {
		return id;
	}
	
	public Topic setId(Integer id) {
		this.id = id;
		return this;
	}
	/*
 * Getters and setters
 */
	public String getName() {
		return name;
	}

	public Topic setName(String name) {
		this.name = name;
		return this;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public Topic setPosts(List<Post> posts) {
		this.posts = posts;
		return this;
	}

}
