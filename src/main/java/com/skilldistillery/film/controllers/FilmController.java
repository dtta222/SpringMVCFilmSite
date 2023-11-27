package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDAO;

	// Handler method for the home page
	@RequestMapping(path = { "home.do", "/" })
	public String goToHome() {
		// Return the path to the home page view
		return "WEB-INF/home.jsp";
	}

	// Handler method to retrieve film data by ID
	@RequestMapping(path = "GetFilmData.do", method = RequestMethod.GET, params = "id")
	public String getFilmByID(String id, Model model) {
		// Retrieve film information by ID from the DAO
		Film film = filmDAO.findById(Integer.valueOf(id));

		// Add film information to the model for the view
		model.addAttribute("film", film);

		// Return the path to the result view
		return "WEB-INF/singleFilm.jsp";
	}

	// Handler method to retrieve films data by keyword
	@RequestMapping(path = "GetFilmsData.do", method = RequestMethod.GET, params = "keyword")
	public String getFilmByKeyword(String keyword, Model model) {
		// Search for films by keyword from the DAO
		List<Film> films = filmDAO.searchByKeyword(keyword);

		// Add list of films to the model for the view
		model.addAttribute("films", films);

		// Return the path to the result2 view
		return "WEB-INF/films.jsp";
	}
}
