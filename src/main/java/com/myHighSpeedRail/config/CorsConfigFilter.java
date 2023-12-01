package com.myHighSpeedRail.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(filterName = "CorsFilter", urlPatterns = "/*")
@Configuration
public class CorsConfigFilter implements Filter {

	@Value("${front.end.host}")
	private String front;

	@Value("${front.backstage.host}")
	private String backStage;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		//根據網址判斷來設定Access-Control-Allow-Origin，以達成可接納多來源的效果
		if (front.equals(request.getHeader("Origin"))) {
	        response.setHeader("Access-Control-Allow-Origin", front);
	    } else if (backStage.equals(request.getHeader("Origin"))) {
	        response.setHeader("Access-Control-Allow-Origin", backStage);
	    }
		
		// addAllowedOrigin 不能設定為* 因為與 allowCredential 衝突
//		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		chain.doFilter(req, res);
	}
}