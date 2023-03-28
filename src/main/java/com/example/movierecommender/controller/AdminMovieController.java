package com.example.movierecommender.controller;

import com.example.movierecommender.model.Movie;
import com.example.movierecommender.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminMovieController {
    @Autowired
    private MovieService movieService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin/dashboard") // 更改为 /admin/dashboard
    public String dashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/admin/movie/add")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "add-movie";
    }

    @PostMapping("/admin/movie/add")
    public String addMovie(Movie movie) {
        movieService.save(movie);
        return "redirect:/";
    }

    @GetMapping("/admin/movies")
    public String listMovies(Model model) {
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        return "admin-movies";
    }

    @GetMapping("/admin/movie/edit/{id}")
    public String showEditMovieForm(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        return "edit-movie";
    }

    @PostMapping("/admin/movie/edit/{id}")
    public String updateMovie(@PathVariable("id") Long id, Movie updatedMovie) {
        Movie movie = movieService.findById(id);
        movie.setTitle(updatedMovie.getTitle());
        movie.setImageUrl(updatedMovie.getImageUrl());
        movie.setDescription(updatedMovie.getDescription());
        movieService.save(movie);
        return "redirect:/admin/movies";
    }

    @GetMapping("/admin/movie/delete/{id}")
    public String deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteById(id);
        return "redirect:/admin/movies";
    }
}
