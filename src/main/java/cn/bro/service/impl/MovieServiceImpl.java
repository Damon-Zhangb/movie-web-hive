package cn.bro.service.impl;

import cn.bro.dao.MovieMapper;
import cn.bro.domain.Movie;
import cn.bro.domain.MovieExample;
import cn.bro.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 脱缰
 * @date 2020/6/18 4:32 下午
 */
@Service("movieServiceImpl")
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> getMovieList() {
        return movieMapper.selectByExample(null);
    }

    @Override
    public Movie getMovieByName(String name) {
        Movie movie = new Movie();
        MovieExample movieExample = new MovieExample();
        movieExample.createCriteria().andNameEqualTo(name);
        List<Movie> movies = movieMapper.selectByExample(movieExample);
        if (movies.size() != 0) {
            movie = movies.get(0);
        } else {
            movie = null;
        }
        return movie;
    }
}
