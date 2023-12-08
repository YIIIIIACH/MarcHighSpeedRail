package com.myHighSpeedRail.marc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.dto.EmailPassword;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BookingMemberLoginController {
	@PostMapping("/requestMemberLogin")
	public @ResponseBody ResponseEntity<String> requestMemberLogin(HttpServletRequest req,  HttpServletResponse res,@RequestBody EmailPassword ep){
		Cookie[] cks =  req.getCookies();
		for(Cookie c : cks) {
			if(c.getName().equals("login-token")) {
//				LoginResponseModel loginRes = uServ.tokenlogin( UUID.fromString(c.getValue()))
//				if(loginRes==null) {
//					return new ResponseEntity<String> ("login-token not valid"+c.getValue(),HttpStatus.OK);
//				}
//				res.addCookie(new Cookie("login-tooken", loginRes.login_token.toString()))
//				return new ResponseEntity<String> ("login-token already exitst"+c.getValue(),HttpStatus.OK);
				return new ResponseEntity<String> ("function not complete , is waiting for user service to merge",HttpStatus.BAD_REQUEST);
			}
		}
//		LoginResponseModel loginRes = uServ.login(ep.email,ep.password);
//		if(loginRes==null) {
//			return new ResponseEntity<String> ("email or pwd  not valid",HttpStatus.UNAUTHORIZED);
//		}
//		res.addCookie(new Cookie("login-token", loginRes.login_token.toString()));
		return new ResponseEntity<String> ("function not completed yet",HttpStatus.OK);
	}
	
	@GetMapping("/addMemberTokenCookie")
	public @ResponseBody ResponseEntity<String> addMemberTokenCookie( HttpServletRequest req, HttpServletResponse res){
		Cookie[] cks =  req.getCookies();
		if(cks!=null) {
			for(Cookie c : cks) {
				if(c.getName().equals("login-token")) {
					// go for token login
//				String userUUID = uServ.tokenlogin( UUID.fromString(c.getValue())).login_token.toString();
					return new ResponseEntity<String> ("功能尚未完成"+c.getValue(),HttpStatus.OK);
				}
			}
		}
		res.addCookie(new Cookie("login-token", "1234567890"));
		return new ResponseEntity<String> ("login-token added",HttpStatus.OK);
	}
}
