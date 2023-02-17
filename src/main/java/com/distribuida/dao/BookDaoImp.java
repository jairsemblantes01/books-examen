package com.distribuida.dao;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class BookDaoImp implements BookDao {
@Inject
EntityManager em;
@Override
public List<Book> findAll() {
	return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
}

@Override
public Book findOne(int id) {
	return (Book) em.find(Book.class, id);
}

@Override
@Transactional
public Book update(Book book) {
	try {
		em.getTransaction().begin();
		em.merge(book);
		em.getTransaction().commit();
		em.close();
		return book;
	} catch (Exception e) {
		em.getTransaction().rollback();
		em.close();
	}
	return null;
}

@Override
@Transactional
public Book save(Book book) {
	try {
		em.getTransaction().begin();
		em.persist(book);
		em.getTransaction().commit();
		em.close();
		return book;
	} catch (Exception e) {
		em.getTransaction().rollback();
		em.close();
	}
	return null;
}

@Override

public void delete(int id) {
	final Object obj = findOne(id);
	if (obj != null) {
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			em.getTransaction().rollback();
			em.close();
		}
	}
}
}
