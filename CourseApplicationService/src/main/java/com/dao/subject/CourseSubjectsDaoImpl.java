package com.dao.subject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dao.book.BookDao;
import com.dao.book.CourseBooksDao;
import com.model.Book;
import com.model.Subject;

public class CourseSubjectsDaoImpl implements CourseSubjectsDao {

	private CourseBooksDao courseBooksDao;

	@Override
	public List<SubjectDao> getSubjectsDaoList() {
		List<SubjectDao> subjectDaoList = new ArrayList<>();
		try (FileInputStream fileIn = new FileInputStream("subjects.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);) {
			subjectDaoList = (ArrayList<SubjectDao>) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subjectDaoList;
	}

	@Override
	public void saveUpdatedSubjectList(List<SubjectDao> updatedSubjectDaoList) {
		try (FileOutputStream file = new FileOutputStream("subjects.ser");
				ObjectOutputStream out = new ObjectOutputStream(file);) {
			out.writeObject(updatedSubjectDaoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Subject> getSubjectList() {
		List<SubjectDao> subjectsDaoList = getSubjectListDao();
		List<Subject> subjectsList = convertToSubjects(subjectsDaoList);
		return subjectsList;
	}

	@Override
	public Map<Long, String> getSubjectIdTitleMapping() {
		Map<Long, String> subjectIdTitleMapping = new HashMap<>();
		List<SubjectDao> subjectsDaoList = getSubjectListDao();
		for (SubjectDao subDao : subjectsDaoList) {
			subjectIdTitleMapping.put(subDao.getSubjectId(), subDao.getSubjectTitle());
		}
		return subjectIdTitleMapping;
	}

	private List<SubjectDao> getSubjectListDao() {
		List<SubjectDao> subjectsDaoList = new ArrayList<>();
		try (FileInputStream fileIn = new FileInputStream("subjects.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);) {
			subjectsDaoList = (ArrayList<SubjectDao>) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subjectsDaoList;
	}

	@Override
	public long getSubjectIdForName(String courseName) {
		long subjectId = 0;
		List<SubjectDao> subjectsDaoList = getSubjectListDao();
		for (SubjectDao subj : subjectsDaoList) {
			if (subj.getSubjectTitle().equals(courseName)) {
				subjectId = subj.getSubjectId();
			}
		}
		return subjectId;
	}

	private List<Subject> convertToSubjects(List<SubjectDao> subjectsDaoList) {
		Set<Subject> subjectsList = new HashSet<>();
		List<BookDao> booksList = courseBooksDao.getBooksDaoList();
		if (!subjectsDaoList.isEmpty()) {
			for (SubjectDao subjectDao : subjectsDaoList) {
				Subject subject = new Subject();
				subject.setSubjectId(subjectDao.getSubjectId());
				subject.setSubjectTitle(subjectDao.getSubjectTitle());
				subject.setReference(getBooks(subjectDao.getSubjectId(), booksList));
				subjectsList.add(subject);
			}
		}
		return new ArrayList<>(subjectsList);
	}

	private Set<Book> getBooks(long subjectId, List<BookDao> booksList) {
		Set<Book> references = new HashSet<>();
		for (BookDao bookDao : booksList) {
			if (subjectId == bookDao.getSubjectId()) {
				Book book = new Book();
				book.setBookId(bookDao.getBookId());
				book.setTitle(bookDao.getTitle());
				references.add(book);
			}
		}
		return references;
	}

}
