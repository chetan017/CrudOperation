
package com.movie.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.movie.app.service.MovieService;
import com.movie.model.Movie;
import com.movie.model.ResponseCode;

@RestController
public class MovieController {
	
	@Autowired
	public MovieService movieservice;
	
	@RequestMapping("/getmovieInfo")
	public List<Movie> getMoviedata() 
	{
		List<Movie> movies = null;
		try {
			 movies = movieservice.getMoviedata();		 
			return movies;
		} 
		catch (Exception e) 
		{
			return movies;
		}	
	}
	
	@PostMapping("/addmovieInfo")
	@ResponseBody
	public ResponseCode addMoviedata(@RequestBody Movie movie) {
		try {
			int status = movieservice.addMovieData(movie);		 
			ResponseCode rescode = new ResponseCode();
			if(status != -1)
			{
				rescode.setStatuDesc("sucess");
				rescode.setStatusCode("0");
				return rescode;	
			}
			else {
				rescode.setStatuDesc("fail");
				rescode.setStatusCode("-1");
				return rescode;	
			}
		} catch (Exception e) {
			ResponseCode respcode = new ResponseCode();
			respcode.setStatuDesc(e.getMessage());
			respcode.setStatusCode("-1");
			return respcode;
		}
	}
	
	@PostMapping("/updatemovieInfo")
	@ResponseBody
	public ResponseCode updateMoviedata(@RequestBody Movie movie) {
		try {
			int status = movieservice.updateMovieData(movie);		 
			ResponseCode rescode = new ResponseCode();
			if(status != -1)
			{
				rescode.setStatuDesc("sucess");
				rescode.setStatusCode("0");
				return rescode;	
			}
			else {
				rescode.setStatuDesc("fail");
				rescode.setStatusCode("-1");
				return rescode;	
			}
		} catch (Exception e) {
			ResponseCode respcode = new ResponseCode();
			respcode.setStatuDesc(e.getMessage());
			respcode.setStatusCode("-1");
			return respcode;
		}
	}
	
	@PostMapping("/deletemovieInfo")
	@ResponseBody
	public ResponseCode deletesMoviedata(@RequestParam("movieID") String movieID) {
		try {
			int status = movieservice.deleteMovieData(movieID);		 
			ResponseCode rescode = new ResponseCode();
			if(status != -1)
			{
				rescode.setStatuDesc("sucess");
				rescode.setStatusCode("0");
				return rescode;	
			}
			else {
				rescode.setStatuDesc("fail");
				rescode.setStatusCode("-1");
				return rescode;	
			}
		} catch (Exception e) {
			ResponseCode respcode = new ResponseCode();
			respcode.setStatuDesc(e.getMessage());
			respcode.setStatusCode("-1");
			return respcode;
		}
	}
	
	@RequestMapping("/getmovieInfobyid")
	public Movie  getMovieDetailsId(@RequestParam("movieID") String movieID) 
	{
		Movie movies = null;
		try {
			movies = movieservice.findById(movieID);	
			return movies;
		} 
		catch (Exception e) 
		{
			return movies;
		}
	}
}
	

//URL: http://localhost:8080/moviecrudapp/getcustInfo
//URL: http://localhost:8080/moviecrudapp/getmovieInfobyid?movieID=1