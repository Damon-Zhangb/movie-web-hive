package cn.bro.controller;

import cn.bro.domain.Movie;
import cn.bro.domain.Msg;
import cn.bro.service.MovieService;
import cn.bro.util.HBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 脱缰
 * @date 2020/6/22 7:57 下午
 */
@Controller
public class MovieContorller {

    @Autowired
    MovieService movieService;
    @Autowired
    HBaseUtil hBaseUtil;

    @RequestMapping("/movies")
    @ResponseBody
    public Msg getMovie() {
        List<Movie> movieList = movieService.getMovieList();
        return Msg.success().add("movie", movieList);
    }

    @RequestMapping("/save")
    @ResponseBody
    public Msg saveToHbase(@RequestParam("name") String name) {
        Movie movie = movieService.getMovieByName(name);
        hBaseUtil.writeRow(movie.getName(), movie.getAuthor(), movie.getCategory());
        return Msg.success();
    }
}
