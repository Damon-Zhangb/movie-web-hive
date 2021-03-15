package cn.bro.service;

import cn.bro.domain.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 脱缰
 * @date 2020/6/18 4:30 下午
 */

public interface MovieService {

    /**
     * 获取电影列表
     *
     * @return
     */
    List<Movie> getMovieList();

    /**
     * 通过名称获取电影信息
     *
     * @return
     */
    Movie getMovieByName(String name);


}
