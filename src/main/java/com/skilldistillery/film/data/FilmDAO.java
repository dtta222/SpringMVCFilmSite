package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.*;
/*import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.Language;*/

public interface FilmDAO {
	
	public Film findById(int filmId);
	public Language findLanguageById(int languageId);
	public List<Actor> findActorsByFilmId(int filmId);
	public List<Film> searchByKeyword(String keyword);

}
