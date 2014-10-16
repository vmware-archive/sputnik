package com.sputnik.home;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String loadIndex(HttpServletRequest request, HttpServletResponse response) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        response.setHeader("X-CSRF-HEADER", token.getHeaderName());
        response.setHeader("X-CSRF-PARAM", token.getParameterName());
        response.setHeader("X-CSRF-TOKEN", token.getToken());

        return "index";
    }

    @RequestMapping("/signin")
    public String signIn(Model model, HttpServletRequest request) {

        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        model.addAttribute("_csrf", token);

        return "signin";
    }
}
