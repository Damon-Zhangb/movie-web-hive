package cn.bro;

import cn.bro.domain.Movie;
import cn.bro.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 脱缰
 * @date 2020/6/18 4:36 下午
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

    @Autowired
    MovieService movieService;

    @Test
    public void selectTest() {
        List<Movie> movieList = movieService.getMovieList();
        for (Movie movie : movieList) {
            System.out.println(movie.toString());
        }
    }

    @Test
    public void selectByName() {
        Movie movie = movieService.getMovieByName("test");
        System.out.println(movie);
    }
}
