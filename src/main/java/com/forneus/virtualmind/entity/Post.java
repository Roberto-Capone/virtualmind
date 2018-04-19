package com.forneus.virtualmind.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
	
	public Post() {
		super();
	}
	
	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "topic_id")	
	private Topic topic;
	
	private String title;
	private String text;
	
	public Integer getId() {
		return id;
	}

	public Post setId(Integer id) {
		this.id = id;
		return this;
	}

	public Topic getTopic() {
		return topic;
	}
	public Post setTopic(Topic topic) {
		this.topic = topic;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public Post setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getText() {
		return text;
	}
	public Post setText(String text) {
		this.text = text;
		return this;
	}

}
