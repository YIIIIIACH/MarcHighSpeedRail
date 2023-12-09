package com.myHighSpeedRail.marc.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private UserService uServ;
	@PostMapping("/requestMemberLogin")
	public @ResponseBody ResponseEntity<String> requestMemberLogin(HttpServletRequest req,  HttpServletResponse res,@RequestBody EmailPassword ep){
		LoginResponseModel loginRes = uServ.login(ep.email,ep.password);
		if(loginRes==null) {
			return new ResponseEntity<String> ("email or pwd  not valid",HttpStatus.UNAUTHORIZED);
		}
		res.addCookie(new Cookie("login-token", loginRes.getLogin_token().toString()));
		return new ResponseEntity<String> ("login-token already exitst"+ loginRes.getLogin_token(),HttpStatus.OK);
	}
	
	@PostMapping("/verifyLoginToken")
	public @ResponseBody ResponseEntity<String> addMemberTokenCookie( HttpServletRequest req, HttpServletResponse res){
		Cookie[] cks =  req.getCookies();
		String userUUID  = null;
		if(cks!=null) {
			for(Cookie c : cks) {
				if(c.getName().equals("login-token")) {
					// go for token login
					userUUID = uServ.tokenlogin( UUID.fromString(c.getValue())).getMember_id().toString();
					if( userUUID != null) {
						return new ResponseEntity<String> ("成功驗證登入 get User UUID:"+userUUID,HttpStatus.OK);						
					}else {
						return new ResponseEntity<String> ("非法user token :",HttpStatus.OK);				
					}
				}
			}
		}
		return new ResponseEntity<String> ("not found login-token",HttpStatus.UNAUTHORIZED);
	}
}
