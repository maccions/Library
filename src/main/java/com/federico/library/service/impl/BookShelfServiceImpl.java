package com.federico.library.service.impl;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.federico.library.entity.BookShelf;
import com.federico.library.entity.Copy;
import com.federico.library.enumeration.ErrorType;
import com.federico.library.exception.BookShelfException;
import com.federico.library.exception.CopyException;
import com.federico.library.repository.BookShelfRepository;
import com.federico.library.service.BookShelfService;
import com.federico.library.service.CopyService;

@Service
public class BookShelfServiceImpl implements BookShelfService {

	@Autowired
	private BookShelfRepository bookShelfRepository;

	@Autowired
	private CopyService copyService;

	@Override
	public BookShelf addBookShelf(BookShelf bookShelf) throws BookShelfException {
		if (bookShelf == null) {
			throw new BookShelfException("Invalid book shelf", ErrorType.INVALID_PARAMETER);
		}
		String room = bookShelf.getRoom();
		String col = bookShelf.getShelfColumn();
		String row = bookShelf.getShelfRow();
		if ((Strings.isBlank(room) && Strings.isBlank(col) && Strings.isBlank(row))) {
			throw new BookShelfException("Missing bookshelf data", ErrorType.MISSING_PARAMETER);
		}
		checkBookShelfDataExistence(room, col, row);
		return bookShelfRepository.save(bookShelf);
	}

	@Override
	public boolean removeBookShelf(BookShelf bookShelf) throws BookShelfException {
		bookShelf = getBookShelf(bookShelf);
		boolean emptyBookShelf = false;
		try {
			emptyBookShelf = copyService.getCopyByBookShelf(Copy.builder().bookShelf(bookShelf).build()).isEmpty();
		} catch (BookShelfException | CopyException e) {
			// data already tested
		}
		if(!emptyBookShelf) {
			throw new BookShelfException("Book shelf not empty", ErrorType.INVALID_OPERATION);
		}
		bookShelfRepository.deleteById(bookShelf.getId());
		return true;
	}

	@Override
	public BookShelf editBookShelf(BookShelf bookShelf) throws BookShelfException {
		BookShelf oldBookShelf = getBookShelf(bookShelf);
		if (oldBookShelf == null) {
			throw new BookShelfException("Wrong bookshelf data", ErrorType.INVALID_PARAMETER);
		}
		String room = bookShelf.getRoom();
		String col = bookShelf.getShelfColumn();
		String row = bookShelf.getShelfRow();
		if ((Strings.isBlank(room) && Strings.isBlank(col) && Strings.isBlank(row))) {
			return oldBookShelf;
		}
		checkBookShelfDataExistence(room, col, row);
		if (Strings.isNotBlank(room) && !oldBookShelf.getRoom().equalsIgnoreCase(row)) {
			oldBookShelf.setRoom(room);
		}
		if (Strings.isNotBlank(row) && !oldBookShelf.getShelfRow().equalsIgnoreCase(row)) {
			oldBookShelf.setShelfRow(row);
		}
		if (Strings.isNotBlank(col) && !oldBookShelf.getShelfColumn().equalsIgnoreCase(col)) {
			oldBookShelf.setShelfColumn(col);
		}
		return bookShelfRepository.save(oldBookShelf);
	}

	@Override
	public List<BookShelf> getAllBookShelf() {
		return bookShelfRepository.findAll();
	}

	@Override
	public BookShelf getBookShelf(BookShelf bookShelf) throws BookShelfException {
		if (bookShelf == null) {
			throw new BookShelfException("Missing parameter", ErrorType.MISSING_PARAMETER);
		}
		return getBookShelfById(bookShelf.getId());
	}

	@Override
	public BookShelf getBookShelfById(Long bookShelfId) throws BookShelfException {
		if (bookShelfId == null) {
			throw new BookShelfException("Missing parameter", ErrorType.MISSING_PARAMETER);
		}
		return bookShelfRepository.findById(bookShelfId).orElse(null);
	}

	private void checkBookShelfDataExistence(String room, String col, String row) throws BookShelfException {
		BookShelf tmp = BookShelf.builder().room(room).shelfColumn(col).shelfRow(row).build();
		if (bookShelfRepository.exists(Example.of(tmp))) {
			throw new BookShelfException("a book shelf with same data already exist", ErrorType.INVALID_OPERATION);
		}
	}

}
