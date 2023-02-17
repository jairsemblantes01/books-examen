package com.distribuida.servicios;

import com.distribuida.dao.BookDao;
import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepositoryImpl implements BookRepository {


@Inject
private BookDao bookDao;

@Override
public List<Book> findAll() {
    return bookDao.findAll();
}

@Override
public Book findOne(int id) {
    return bookDao.findOne(id);
}

@Override
public Book update(Book singer) {
    return bookDao.update(singer);
}

@Override
public Book save(Book singer) {
    return bookDao.save(singer);
}

@Override
public void delete(int id) {
    bookDao.delete(id);
}
}
