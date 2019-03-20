package com.dao.subject;

import java.util.List;
import java.util.Map;

public interface CourseSubjectsDao {

	long getSubjectIdForName(String courseName);

	Map<Long, String> getSubjectIdTitleMapping();

	List<SubjectDao> getSubjectsDaoList();
	
	void saveUpdatedSubjectList(List<SubjectDao> updatedSubjectDaoList);

}
