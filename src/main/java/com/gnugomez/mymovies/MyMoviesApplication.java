package com.gnugomez.mymovies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MyMoviesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyMoviesApplication.class, args);
    }

}
