package com.myHighSpeedRail.marc.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.dto.EmailPassword;
import com.myHighSpeedRail.yuhsin.Models.LoginResponseModel;
import com.myHighSpeedRail.yuhsin.Services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BookingMemberLoginController {
	@Value("${server.baseurl}")
	private String SERVER_BASE_URL;
	@Value("${front.end.host}")
	private String FRONT_SERVER_URL;
	@Autowired
	private UserService uServ;
	@PostMapping("/requestMemberLogin")
	public @ResponseBody ResponseEntity<LoginResponseModel> requestMemberLogin(HttpServletRequest req,  HttpServletResponse res,@RequestBody EmailPassword ep){
//		Cookie[] cks = req.getCookies();
//		if(cks==null) {
//			System.out.print("not cookie found");
//		}else {
//			for( Cookie c: cks) {
//				if(c.getName().equals("login-token")) {
//					ep.loginToken=c.getValue();
//				}
//			}
//		}
		LoginResponseModel loginRes = uServ.login(ep.email,ep.password);
		if(loginRes==null) {
			System.out.println( " user not found");
			return new ResponseEntity<LoginResponseModel> (new LoginResponseModel(),HttpStatus.UNAUTHORIZED);
		}
		System.out.println( loginRes.getMember_id().toString());
		Cookie c = new Cookie("login-token",loginRes.getLogin_token().toString());
		res.addCookie(c);
		Cookie c2 = new Cookie("member-name",loginRes.getMember_name());
		res.addCookie(c);
		res.addCookie(c2);
		return new ResponseEntity<LoginResponseModel> (loginRes,HttpStatus.OK);
	}
	
	@PostMapping("/verifyLoginToken")
	public @ResponseBody ResponseEntity<String> addMemberTokenCookie( HttpServletRequest req, HttpServletResponse res){
		String token = null;
		Cookie[] cks = req.getCookies();
		if(cks==null) {
			System.out.print("not cookie found");
		}else {
			for( Cookie c: cks) {
				if(c.getName().equals("login-token")) {
					token = c.getValue();
				}
			}
		}
		String userUUID  = null;
		if(token !=null) {	
			LoginResponseModel userDetail = uServ.tokenlogin(UUID.fromString(token));
			if( userDetail != null) {
				return new ResponseEntity<String> (userDetail.getMember_name(),HttpStatus.OK);						
			}else {
				return new ResponseEntity<String> ("failed",HttpStatus.OK);				
			}
		}
		return new ResponseEntity<String> ("failed",HttpStatus.UNAUTHORIZED);
	}
}
