package com.movie.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.movie.app.repository.MovieDAO;
import com.movie.model.Movie;

@Service
public class MovieService 
{
	@Autowired
	private MovieDAO moviedao;
	
	@Transactional(readOnly=true)
	public List<Movie> getMoviedata() {
		return moviedao.getMoviedetails();
	}
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public int addMovieData(Movie movie) {
		return moviedao.addMovie(movie);
	}
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
	public int updateMovieData(Movie movie) {
		return moviedao.updateMovie(movie);
	}
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
	public int deleteMovieData(String movieID) {
		return moviedao.deleteeMovie(movieID);
	}
	
	@Transactional(readOnly = true)
	public Movie findById(String movieID) {
		return moviedao.findMovieById(movieID);
	}
	
	

}
