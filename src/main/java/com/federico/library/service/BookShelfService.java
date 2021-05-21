package com.federico.library.service;

import java.util.List;

import com.federico.library.entity.BookShelf;
import com.federico.library.exception.BookShelfException;

public interface BookShelfService {

	public BookShelf addBookShelf(BookShelf bookShelf) throws BookShelfException;
	public BookShelf getBookShelfById(Long bookShelfId) throws BookShelfException;
	public BookShelf getBookShelf(BookShelf bookShelf) throws BookShelfException;
	public List<BookShelf> getAllBookShelf();
	public BookShelf editBookShelf(BookShelf bookShelf) throws BookShelfException;
	public boolean removeBookShelf(BookShelf bookShelf) throws BookShelfException;

}
