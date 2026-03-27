package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String semester;
    private String branch;
    private String section;
    private String teacher;

    private int teaching;
    private int knowledge;
    private String token; 
    // getters & setters

    public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTeacher() { return teacher; }
    public void setTeacher(String teacher) { this.teacher = teacher; }

    public int getTeaching() { return teaching; }
    public void setTeaching(int teaching) { this.teaching = teaching; }

    public int getKnowledge() { return knowledge; }
    public void setKnowledge(int knowledge) { this.knowledge = knowledge; }
}