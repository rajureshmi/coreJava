package com.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.dao.book.BookDao;
import com.dao.book.CourseBooksDao;
import com.dao.book.CourseBooksDaoImpl;
import com.dao.subject.CourseSubjectsDao;
import com.dao.subject.CourseSubjectsDaoImpl;

public class BookHandler implements CourseHandler {
	private CourseBooksDao courseBooksDao = new CourseBooksDaoImpl();
	private CourseSubjectsDao courseSubjectsDao= new CourseSubjectsDaoImpl();

	@Override
	public void add() {
		System.out.println("Selected option: Add Book");
		System.out.println("Enter title:");
		String title = CourseApplication.sc.nextLine();
		System.out.println("Enter price:");
		double price = CourseApplication.sc.nextDouble();
		System.out.println("Enter volume:");
		int volume = CourseApplication.sc.nextInt();
		System.out.println("Enter published date(d/MM/yyyy):");
		String publishedDate = CourseApplication.sc.nextLine();
		LocalDate pubDate = LocalDate.parse(publishedDate, DateTimeFormatter.ofPattern("d/MM/yyyy"));
		System.out.println("Enter associated subject:");
		String subjectTitle = CourseApplication.sc.nextLine();

		List<BookDao> existingBookList = courseBooksDao.getBooksDaoList();
		long subjectId = courseSubjectsDao.getSubjectIdForName(subjectTitle);
		BookDao lastBook = existingBookList.get(existingBookList.size());
		BookDao newBook = new BookDao(lastBook.getBookId() + 1, title, price, volume, pubDate, subjectId);
		existingBookList.add(newBook);
		courseBooksDao.saveUpdatedBookList(existingBookList);
		System.out.println("Books added successfully");
	}

	@Override
	public void delete() {
		System.out.println("Selected option: Delete Book");
		List<BookDao> existingBookList = courseBooksDao.getBooksDaoList();

		System.out.println("Enter book ID to be deleted:");
		long bookIdToBeDeleted = CourseApplication.sc.nextLong();
		Iterator itr = existingBookList.iterator();
		while (itr.hasNext()) {
			BookDao x = (BookDao) itr.next();
			if (x.getBookId() == bookIdToBeDeleted)
				itr.remove();
		}
		courseBooksDao.saveUpdatedBookList(existingBookList);
		System.out.println("Book Deleted");
	}

	@Override
	public void search() {
		System.out.println("Selected option: Search Book");
		try {
			List<BookDao> existingBookList = courseBooksDao.getBooksDaoList();
			Map<Long, String> subjectIdTitleMapping = courseSubjectsDao.getSubjectIdTitleMapping();
			System.out.println("Enter title to search:");
			String title = CourseApplication.sc.nextLine();
			List<BookDao> selectedBooksDao = existingBookList.stream().filter(book -> book.getTitle().contains(title))
					.collect(Collectors.toList());
			if (Objects.nonNull(selectedBooksDao)) {
				selectedBooksDao.stream().forEach(bookDao -> {
					System.out.println("Book ID: " + bookDao.getBookId() + ", Book Title: " + bookDao.getTitle()
							+ " Associated Subject: " + subjectIdTitleMapping.get(bookDao.getSubjectId()));
				});
			} else {
				System.out.println("No books found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
