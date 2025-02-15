package com.yasin.e_commerce.core;

import org.springframework.security.web.util.matcher.RequestMatcher;

import jakarta.servlet.http.HttpServletRequest;

public class EndsWithRequestMatcher implements RequestMatcher {
    private final String suffix;

    public EndsWithRequestMatcher(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return request.getRequestURI().endsWith(suffix);
    }
}

