package com.harish.model;

import javax.persistence.*;

@Entity
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;
	private String body;
	private String author;

	public Blog() {
	}

	public Blog(String title, String body, String author) {
		this.title = title;
		this.body = body;
		this.author = author;
	}

	public Blog(int id, String title, String body, String author) {

		this.id = id;
		this.title = title;
		this.body = body;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getbody() {
		return body;
	}

	public void setbody(String body) {
		this.body = body;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Blog [title=" + title + ", body=" + body + ", author=" + author + "]";
	}

}
