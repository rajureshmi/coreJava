package com.dao.book;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.model.Book;

public class CourseBooksDaoImpl implements CourseBooksDao {

	@Override
	public List<BookDao> getBooksDaoList() {
		List<BookDao> booksDaoList = new ArrayList<>();
		try (FileInputStream fileIn = new FileInputStream("books.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);) {
			booksDaoList = (ArrayList<BookDao>) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return booksDaoList;
	}

	@Override
	public void saveUpdatedBookList(List<BookDao> updatedBookDaoList) {
		try (FileOutputStream file = new FileOutputStream("books.ser");
				ObjectOutputStream out = new ObjectOutputStream(file);) {
			out.writeObject(updatedBookDaoList);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private List<Book> convertToBooks(List<BookDao> booksDaoList) {
		Set<Book> booksList = new HashSet<>();
		if (!booksDaoList.isEmpty()) {
			for (BookDao bookDao : booksDaoList) {
				Book book = new Book();
				book.setBookId(bookDao.getBookId());
				book.setTitle(bookDao.getTitle());
				book.setPrice(bookDao.getPrice());
				book.setVolume(bookDao.getVolume());
				book.setPublishedDate(bookDao.getPublishedDate());
				booksList.add(book);
			}
		}
		return new ArrayList<>(booksList);
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
