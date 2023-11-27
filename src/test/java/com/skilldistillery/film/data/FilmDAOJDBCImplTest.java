package com.skilldistillery.film.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.film.entities.Film;

class FilmDAOJDBCImplTest {

	 private FilmDAOJDBCImpl dao;

	 @BeforeEach
	 void setUp() throws Exception {
	 	dao = new FilmDAOJDBCImpl();
	 }

	 @AfterEach
	 void tearDown() throws Exception {
	 	dao = null;
	 }

	 @Test
	 void test_findById_returns_film() {
	 	Film film = dao.findById(1);
	 	assertNotNull(film);
	 	//assertEquals("ACADEMY DINOSAUR", film.getTitle());
	 }
	 
	 @Test
	 void test_findById_returns_null_for_invalid_id() {
	 	Film film = dao.findById(1234567);
	 	assertNull(film);
	 }
	 
	 

}
