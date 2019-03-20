package com.model;

import java.util.Set;

public class Subject {
	
	private long subjectId;
	private String subjectTitle;
	private int durationInHrs;
	private Set<Book> reference;
	
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectTitle() {
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	public int getDurationInHrs() {
		return durationInHrs;
	}
	public void setDurationInHrs(int durationInHrs) {
		this.durationInHrs = durationInHrs;
	}
	public Set<Book> getReference() {
		return reference;
	}
	public void setReference(Set<Book> reference) {
		this.reference = reference;
	}
	
	

}
