package com.dao.book;

import java.util.List;

public interface CourseBooksDao {

	List<BookDao> getBooksDaoList();

	void saveUpdatedBookList(List<BookDao> updatedBookDaoList);
}
