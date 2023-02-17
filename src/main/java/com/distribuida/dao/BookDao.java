package com.distribuida.dao;

import com.distribuida.db.Book;

import java.util.List;

public interface BookDao {

List<Book> findAll();
Book findOne(int id);
Book update(Book singer);
Book save(Book singer);
void delete(int id);
}
