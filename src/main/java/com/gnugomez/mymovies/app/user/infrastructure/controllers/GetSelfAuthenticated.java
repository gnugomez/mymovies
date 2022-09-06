package com.gnugomez.mymovies.app.user.infrastructure.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class GetSelfAuthenticated {

        @RequestMapping("/me")
        public String getSelfAuthenticated(Principal principal) {
            return principal.getName();
        }
}
