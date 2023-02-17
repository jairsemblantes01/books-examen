package com.distribuida.servicios;

import com.distribuida.db.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

List<Book> findAll();
Book findOne(int id);
Book update(Book book);
Book save(Book book);
void delete(int id);
}
