package com.service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.dao.book.BookDao;
import com.dao.book.CourseBooksDao;
import com.dao.book.CourseBooksDaoImpl;
import com.dao.subject.CourseSubjectsDao;
import com.dao.subject.CourseSubjectsDaoImpl;
import com.dao.subject.SubjectDao;

public class SubjectHandler implements CourseHandler {
	private static final String SPACE = " ";
	private CourseBooksDao courseBooksDao = new CourseBooksDaoImpl();
	private CourseSubjectsDao courseSubjectsDao= new CourseSubjectsDaoImpl();

	@Override
	public void add() {
		System.out.println("Selected option: Add Subject");
		System.out.println("Enter title:");
		String title = CourseApplication.sc.nextLine();
		System.out.println("Enter duration in hours:");
		int durationInHrs = CourseApplication.sc.nextInt();

		List<SubjectDao> existingSubjectList = courseSubjectsDao.getSubjectsDaoList();
		SubjectDao lastBook = existingSubjectList.get(existingSubjectList.size());
		SubjectDao newBook = new SubjectDao(lastBook.getSubjectId() + 1, title, durationInHrs);
		existingSubjectList.add(newBook);
		courseSubjectsDao.saveUpdatedSubjectList(existingSubjectList);
		System.out.println("Subject added successfully");
	}

	@Override
	public void delete() {
		System.out.println("Selected option: Delete Subject");

		List<SubjectDao> existingSubjectList = courseSubjectsDao.getSubjectsDaoList();

		System.out.println("Enter subject ID to be deleted:");
		long subjectIdToBeDeleted = CourseApplication.sc.nextLong();
		Iterator itr = existingSubjectList.iterator();
		while (itr.hasNext()) {
			SubjectDao x = (SubjectDao) itr.next();
			if (x.getSubjectId() == subjectIdToBeDeleted)
				itr.remove();
		}
		courseSubjectsDao.saveUpdatedSubjectList(existingSubjectList);
		System.out.println("Subject Deleted");

	}

	@Override
	public void search() {
		System.out.println("Selected option: Search Subject");

		List<SubjectDao> existingSubjectList = courseSubjectsDao.getSubjectsDaoList();
		List<BookDao> existingBookList = courseBooksDao.getBooksDaoList();
		System.out.println("Enter title to search:");
		String title = CourseApplication.sc.nextLine();
		List<SubjectDao> selectedBooksDao = existingSubjectList.stream()
				.filter(subject -> subject.getSubjectTitle().contains(title)).collect(Collectors.toList());
		if (Objects.nonNull(selectedBooksDao)) {
			for (SubjectDao subjectDao : existingSubjectList) {
				System.out.println("Subject ID: " + subjectDao.getSubjectId() + ", Subject Title: "
						+ subjectDao.getSubjectTitle() + "Associated Books: "
						+ displayBooks(getCourseBooks(subjectDao.getSubjectId(), existingBookList)) + "/n");
			}
		} else {
			System.out.println("No books found");
		}
	}

	private String displayBooks(Set<BookDao> books) {
		StringBuilder sb = new StringBuilder();
		for (BookDao book : books) {
			sb.append("/n");
			sb.append(book.getBookId());
			sb.append(SPACE);
			sb.append(book.getTitle());
		}
		return sb.toString();
	}

	private Set<BookDao> getCourseBooks(long subjectId, List<BookDao> existingBookList) {
		Set<BookDao> books = existingBookList.stream().filter(book -> book.getSubjectId() == subjectId)
				.collect(Collectors.toSet());
		return books;
	}

}
