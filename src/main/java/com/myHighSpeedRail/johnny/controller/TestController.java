package com.myHighSpeedRail.johnny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.service.StationService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class TestController {
	
	@Autowired
	private StationService ss;
	
	@GetMapping("/testJohnny")
	public @ResponseBody List<Station> testJohny(){
		return ss.getAllStation();
	}
	
//	@PostMapping("/logout")
//	public ResponseEntity<String> logout(HttpServletRequest req, HttpServletResponse res){
//		Cookie[] cks = req.getCookies();
//		if(cks != null) {
//			for(Cookie ck : cks) {
//				if("login-token".equals(ck.getName())) {
//					ck.setValue("");
////					ck.setPath("/");
//					ck.setHttpOnly(false);
//					ck.setMaxAge(0);
//					System.out.println(ck);
//					res.addCookie(ck);
//                    break;
//				}
//			}
//		}
//		return ResponseEntity.ok("登出成功");
//	}
	
}
