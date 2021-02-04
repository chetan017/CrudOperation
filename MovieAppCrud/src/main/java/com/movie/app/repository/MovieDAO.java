
package com.movie.app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.movie.model.Movie;

@Repository
public class MovieDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final String SELECT_QUERY = "SELECT movie_id, title,release_date,director FROM egl_ivr_db.movie_details";
	final String INSERT_QUERY = "insert into egl_ivr_db.movie_details (movie_id, title,release_date,director) values (?, ?,?,?)";
	final String UPDATE_QUERY = "update egl_ivr_db.movie_details set title = ? , release_date = ? ,director = ? where movie_id = ?";
    final String DELETE_QUERY = "delete from egl_ivr_db.movie_details where movie_id = ?";
    final String SELECT_QUERY_ID = "SELECT title,release_date,director FROM egl_ivr_db.movie_details WHERE movie_id = ?";


	public List<Movie> getMoviedetails() {

		List<Movie> movies = new ArrayList<Movie>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_QUERY);
		
		for (Map<String, Object> row : rows) {
			Movie movie = new Movie();
			movie.setMovie_id(String.valueOf(row.get("movie_id")));
			movie.setTitle((String)row.get("title"));
			movie.setRelease_date((String)row.get("release_date"));
			movie.setDirector((String)row.get("director"));
			
			movies.add(movie);
		}

		return movies;
		
	}
	
	public int addMovie(Movie movie) 
	{ 
	     int status = jdbcTemplate.update(INSERT_QUERY, movie.getMovie_id(),movie.getTitle(),movie.getRelease_date(),movie.getDirector());   
	     if(status != 0){
	         return 0;
	     }
	     else{
	         return -1;
	       }    
	  }
	
	public int updateMovie(Movie movie) {
	    int status = jdbcTemplate.update(UPDATE_QUERY,movie.getTitle(),movie.getRelease_date(),movie.getDirector(), movie.getMovie_id()); 
	    if(status != 0){
	      return 0;
	    }else{
	      return -1;
	    }
	  }
	
	public int deleteeMovie(String movieID) {
	    int status = jdbcTemplate.update(DELETE_QUERY,movieID); 
	    if(status != 0){
	      return 0;
	    }else{
	      return -1;
	    }
	  }
	
	public Movie findMovieById(String movieID) {
		  RowMapper<Movie> rowMapper = new BeanPropertyRowMapper<Movie>(Movie.class);
		  Movie moviedata = jdbcTemplate.queryForObject(SELECT_QUERY_ID, rowMapper, movieID);
		  return moviedata;


	  }
}