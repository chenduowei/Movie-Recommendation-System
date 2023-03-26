package com.example.movierecommender.service;

import com.example.movierecommender.entity.Comment;
import com.example.movierecommender.model.Movie;
import com.example.movierecommender.repository.CommentRepository;
import com.example.movierecommender.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> searchMovies(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return findAll();
        } else {
            return movieRepository.findByTitleContainingIgnoreCase(keyword);
        }
    }

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findCommentsByMovieId(Long movieId) {
        return commentRepository.findByMovieId(movieId);
    }

    public void addCommentToMovie(Long movieId, Comment comment) {
        Movie movie = findById(movieId);
        if (movie != null) {
            comment.setMovie(movie);
            commentRepository.save(comment);
        }
    }
}
