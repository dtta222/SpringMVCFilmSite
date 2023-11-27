package com.skilldistillery.film.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Category;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.Language;

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
		assertEquals("ACADEMY DINOSAUR", film.getTitle());
	}

	@Test
	void test_findById_returns_null_for_invalid_id() {
		Film film = dao.findById(1234567);
		assertNull(film);
	}

	@Test
	void test_searchByKeyword_returns_films() {
		List<Film> films = dao.searchByKeyword("comedy");
		assertNotNull(films);
		assertFalse(films.isEmpty());
	}

	@Test
	void test_searchByKeyword_returns_empty_list_for_invalid_keyword() {
		List<Film> films = dao.searchByKeyword("invalidKeyword123");
		assertNotNull(films);
		assertTrue(films.isEmpty());
	}

	@Test
	void test_findLanguageById_returns_language() {
		Language language = dao.findLanguageById(1);
		assertNotNull(language);
		assertEquals("English", language.getName());
	}

	@Test
	void test_findLanguageById_returns_null_for_invalid_language_id() {
		Language language = dao.findLanguageById(1234567);
		assertNull(language);
	}

	@Test
	void test_findActorsByFilmId_returns_actors() {
		List<Actor> actors = dao.findActorsByFilmId(1);
		assertNotNull(actors);
		assertFalse(actors.isEmpty());
	}

	@Test
	void test_findActorsByFilmId_returns_empty_list_for_invalid_film_id() {
		List<Actor> actors = dao.findActorsByFilmId(1234567);
		assertNotNull(actors);
		assertTrue(actors.isEmpty());
	}
	
	@Test
	void test_category_constructor_and_getters() {
		int id = 1;
		String categoryName = "Action";

		Category category = new Category(id, categoryName);

		assertEquals(id, category.getId());
		assertEquals(categoryName, category.getCategory());
	}

	@Test
	void test_category_equals() {
		Category category1 = new Category(1, "Action");
		Category category2 = new Category(1, "Action");
		Category category3 = new Category(7, "Drama");

		assertTrue(category1.equals(category2));
		assertFalse(category1.equals(category3));
		assertFalse(category2.equals(category3));
	}
}
