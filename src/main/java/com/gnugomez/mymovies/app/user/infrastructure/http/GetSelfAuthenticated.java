package com.gnugomez.mymovies.app.user.infrastructure.http;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class GetSelfAuthenticated {

        @RequestMapping("/@me")
        public Principal getSelfAuthenticated(Principal principal) {
            System.out.printf("Principal: %s", principal);
                return principal;
        }
}
