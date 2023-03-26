package com.example.movierecommender.controller;

import com.example.movierecommender.entity.Comment;
import com.example.movierecommender.model.Movie;
import com.example.movierecommender.repository.MovieRepository;
import com.example.movierecommender.service.MovieService;
import com.example.movierecommender.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;
    private final MovieRepository movieRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public static String formatDescription(String description) {
        return description.replaceAll("(?m)^", "<p>").replaceAll("(?m)$", "</p>");
    }

    @GetMapping("/")
    public String getAllMovies(Model model) {
        List<Movie> movies = movieRepository.findAll();
        Collections.shuffle(movies);
        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("/search")
    public String searchMovies(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Movie> movies = movieService.searchMovies(keyword);
        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("/movie/{id}")
    public String getMovieDetails(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.findById(id);
        if (movie != null) {
            model.addAttribute("movie", movie);
            model.addAttribute("formattedDescription", formatDescription(movie.getDescription()));
        }
        return "movie-details";
    }

    @PostMapping("/movie/{id}/add-comment")
    public String addCommentToMovie(@PathVariable("id") Long id,
                                    @RequestParam("author") String author,
                                    @RequestParam("rating") Double rating,
                                    @RequestParam("content") String content) {
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setRating(rating);
        comment.setContent(content);
        movieService.addCommentToMovie(id, comment);
        return "redirect:/movie/{id}";
    }

}
