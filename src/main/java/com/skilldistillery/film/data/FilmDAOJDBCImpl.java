package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.*;

public class FilmDAOJDBCImpl implements FilmDAO {

	private static String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private final String user = "student";
	private final String pass = "student";

	@Override
	public Film findById(int filmId) {
		// Method to retrieve film details by ID
		Film film = null;
		try {
			// Establish a database connection
			Connection conn = DriverManager.getConnection(url, user, pass);

			// SQL query to retrieve film information by ID
			String sql = "SELECT * FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			// Execute the query
			ResultSet filmResult = stmt.executeQuery();

			if (filmResult.next()) {
				// Retrieve film information from the result set
				String title = filmResult.getString("title");
				int releaseYear = filmResult.getInt("release_year");
				String rating = filmResult.getString("rating");
				String description = filmResult.getString("description");

				// Retrieve language information using findLanguageById method
				int languageId = filmResult.getInt("language_id");
				Language language = findLanguageById(languageId);
				
				Category category = findCategoryByFilmId(filmId);
				String categoryName = category.getCategory();

				// Retrieve actors information using findActorsByFilmId method
				List<Actor> actors = findActorsByFilmId(filmId);

				// Get the language name
				String name = language.getName();

				// Create a Film object
				film = new Film(filmId, title, releaseYear, rating, description, name, categoryName, actors);
			}
			
			// Close resources
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public List<Film> searchByKeyword(String keyword) {
		// Method to search films by keyword in title or description
		List<Film> films = new ArrayList<>();
		try {
			// Establish a database connection
			Connection conn = DriverManager.getConnection(url, user, pass);

			// SQL query to search films by keyword
			String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");

			// Execute the query
			ResultSet filmResult = stmt.executeQuery();
			while (filmResult.next()) {
				// Retrieve film information from the result set
				int filmId = filmResult.getInt("id");
				String title = filmResult.getString("title");
				int releaseYear = filmResult.getInt("release_year");
				String rating = filmResult.getString("rating");
				String description = filmResult.getString("description");
				int languageId = filmResult.getInt("language_id");

				// Retrieve language information using findLanguageById method
				Language language = findLanguageById(languageId);
				String name = language.getName();
				
				Category category = findCategoryByFilmId(filmId);
				String categoryName = category.getCategory();

				// Retrieve actors information using findActorsByFilmId method
				List<Actor> actors = findActorsByFilmId(filmId);

				// Create a Film object and add it to the list
				Film film = new Film(filmId, title, releaseYear, rating, description, name, categoryName, actors);
				films.add(film);
			}

			// Close resources
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public Language findLanguageById(int languageId) {
		// Method to retrieve language details by ID
		Language language = null;
		try {
			// Establish a database connection
			Connection conn = DriverManager.getConnection(url, user, pass);

			// SQL query to retrieve language information by ID
			String sql = "SELECT * FROM language WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, languageId);

			// Execute the query
			ResultSet languageResult = stmt.executeQuery();

			if (languageResult.next()) {
				// Retrieve language information from the result set
				int id = languageResult.getInt("id");
				String name = languageResult.getString("name");

				// Create a Language object
				language = new Language(id, name);
			}

			// Close resources
			languageResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return language;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// Method to retrieve actors for a specific film ID
		List<Actor> actors = new ArrayList<>();
		try {
			// Establish a database connection
			Connection conn = DriverManager.getConnection(url, user, pass);

			// SQL query to retrieve actors for a specific film ID
			String sql = "SELECT actor.* FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_actor.film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			// Execute the query
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// Retrieve actor information from the result set
				int actorId = rs.getInt("id");
				String fn = rs.getString("first_name");
				String ln = rs.getString("last_name");

				// Create an Actor object and add it to the list
				Actor actor = new Actor(actorId, fn, ln);
				actors.add(actor);
			}

			// Close resources
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	public Category findCategoryByFilmId(int filmId) {
		Category category = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			String sql = "SELECT category.id, category.name " + "FROM category "
					+ "JOIN film_category ON category.id = film_category.category_id "
					+ "WHERE film_category.film_id = ?";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, filmId);

				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						int categoryId = rs.getInt("id");
						String categoryName = rs.getString("name");

						category = new Category(categoryId, categoryName);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return category;
	}

	// Static block to load the MySQL Driver
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver");
			throw new RuntimeException("Unable to load MySQL Driver class");
		}
	}
}
