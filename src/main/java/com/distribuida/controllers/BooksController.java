package com.distribuida.controllers;

import com.distribuida.db.Book;

import java.util.List;

public interface BooksController {
	List<Book> findAll();
	Book findOne(int id);
	Book update(int id, Book singer);
	Book save(Book singer);
	void delete(int id);
}
