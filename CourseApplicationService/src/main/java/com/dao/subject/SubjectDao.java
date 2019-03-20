package com.dao.subject;

import java.io.Serializable;

public class SubjectDao implements Serializable {

	private static final long serialVersionUID = 2848039592981832389L;

	private long subjectId;
	private String subjectTitle;
	private int durationInHrs;

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

	public SubjectDao(long subjectId, String subjectTitle, int durationInHrs) {
		super();
		this.subjectId = subjectId;
		this.subjectTitle = subjectTitle;
		this.durationInHrs = durationInHrs;
	}
	
	

}
