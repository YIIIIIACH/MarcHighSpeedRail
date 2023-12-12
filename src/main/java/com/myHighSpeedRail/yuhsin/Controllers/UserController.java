package com.myHighSpeedRail.yuhsin.Controllers;

import com.myHighSpeedRail.yuhsin.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.UUID;

@Controller
public class UserController {

    private UserService _userService;

    public UserController(UserService userService) {
        _userService = userService;
    }

    @GetMapping("/yuhsin/test/signin")
    public String signin() {
        return "yuhsin/test/SignIn";
    }

    @PostMapping("/yuhsin/test/signin")
    public String post_signin(Model model,
                              @RequestParam("user") String user,
                              @RequestParam("password") String password,
                              HttpServletResponse response) {

        if (user.isEmpty() || password.isEmpty()) {
            //無帳號密碼
            model.addAttribute("msg", "帳號密碼不得為空");
            return "yuhsin/test/SignIn";
        }
        var get_user_info = _userService.login(user, password);
        if (get_user_info != null) {
            Cookie cookie = new Cookie("logintoken", get_user_info.getLogin_token().toString());
            cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
            //add cookie to response
            response.addCookie(cookie);
            return "redirect:/yuhsin/test/profile";
        } else {
            model.addAttribute("msg", "帳號或密碼錯誤");
            return "yuhsin/test/SignIn";
        }
    }

    @GetMapping("/yuhsin/test/profile")
    public String profile(Model model,
                          @CookieValue(value = "logintoken", defaultValue = "") String token) throws IOException {
        if (token.isEmpty()) {
            return "redirect:/yuhsin/test/signin";
        }

        var get_user_info = _userService.tokenlogin(UUID.fromString(token));
        if (get_user_info == null) {
            return "redirect:/yuhsin/test/signin";
        }
        model.addAttribute("username", get_user_info.getMember_name());
        model.addAttribute("email", get_user_info.getMember_email());
        model.addAttribute("phone", get_user_info.getMember_phone());

        return "yuhsin/test/profile";


    }

    @PostMapping("/yuhsin/test/signout")
    public String signout(Model model,
                          @CookieValue(value = "logintoken", defaultValue = "") String token,
                          HttpServletResponse response) {
        if (token.isEmpty()) {
            Cookie cookie = new Cookie("logintoken", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return "redirect:/yuhsin/test/signin";
        }

        var get_user_info = _userService.tokenlogin(UUID.fromString(token));
        if (get_user_info != null) {
            Cookie cookie = new Cookie("logintoken", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            _userService.signout(UUID.fromString(token));
            return "redirect:/yuhsin/test/signin";
        } else {
            Cookie cookie = new Cookie("logintoken", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return "redirect:/yuhsin/test/signin";
        }

    }

    @GetMapping("/yuhsin/test/register")
    public String register() {
        return "yuhsin/test/register";
    }

    @PostMapping("/yuhsin/test/register")
    public String register(Model model,
                           @CookieValue(value = "logintoken", defaultValue = "") String token,
                           @RequestParam("user") String user,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("phone") String phone,
                           HttpServletResponse response) throws IOException {

        if (user.isEmpty()) {
            //無帳號密碼
            model.addAttribute("msg", "帳號不得為空");
            return "yuhsin/test/register";
        }
        if (password.isEmpty()) {
            //無帳號密碼
            model.addAttribute("msg", "密碼不得為空");
            return "yuhsin/test/register";
        }
        if (email.isEmpty()) {
            //無帳號密碼
            model.addAttribute("msg", "信箱不得為空");
            return "yuhsin/test/register";
        }
        if (phone.isEmpty()) {
            //無帳號密碼
            model.addAttribute("msg", "電話不得為空");
            return "yuhsin/test/register";
        }
        var get_user_info = _userService.register(user, password, email, phone);
        if (get_user_info != null) {
            return "yuhsin/test/welcome";
        } else {
            model.addAttribute("msg", "信箱已使用過");
            return "yuhsin/test/register";
        }
    }


    @PostMapping("/yuhsin/test/updateInfo")
    public String updateinfo(Model model,
                             @CookieValue(value = "logintoken", defaultValue = "") String token,
                             @RequestParam("user") String user,
                             @RequestParam("password") String password,
                             @RequestParam("email") String email,
                             @RequestParam("phone") String phone,
                             HttpServletResponse response) throws IOException {

        if (user.isEmpty()) {
            //無帳號密碼
            model.addAttribute("msg0", "帳號不得為空");
            return "/yuhsin/test/profile";
        }
        if (password.isEmpty()) {
            //無帳號密碼
            model.addAttribute("msg1", "密碼不得為空");
            return "/yuhsin/test/profile";
        }
        if (email.isEmpty()) {
            //無帳號密碼
            model.addAttribute("msg2", "信箱不得為空");
            return "/yuhsin/test/profile";
        }
        if (phone.isEmpty()) {
            //無帳號密碼
            model.addAttribute("msg3", "電話不得為空");
            return "/yuhsin/test/profile";
        }
        var getmemberid = _userService.tokenlogin(UUID.fromString(token));
        if (getmemberid == null) {
            return "redirect:/yuhsin/test/signin";
        }
        var get_user_info = _userService.edit(getmemberid.getMember_id(), user, password, email, phone);
        if (get_user_info != null) {
            model.addAttribute("msg4", "更新成功");
            model.addAttribute("username", get_user_info.getMember_name());
            model.addAttribute("email", get_user_info.getMember_email());
            model.addAttribute("phone", get_user_info.getMember_phone());
            return "yuhsin/test/profile";
        } else {
            model.addAttribute("mag4", "資料輸入錯誤");
            return "yuhsin/test/profile";
        }
    }

    @PostMapping("/yuhsin/test/deleteInfo")
    public String deleteInfo(Model model,
                             @CookieValue(value = "logintoken", defaultValue = "") String token,
                             HttpServletResponse response) {
        if (token.isEmpty()) {
            return "redirect/yuhsin/test/signin";
        }
        var getmemberid = _userService.tokenlogin(UUID.fromString(token));
        if (getmemberid == null) {
            Cookie cookie = new Cookie("logintoken", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return "redirect:/yuhsin/test/sighin";
        }
        var get_user_info = _userService.removeInfo(getmemberid.getMember_id());
        if (get_user_info) {
            Cookie cookie = new Cookie("logintoken", "");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            model.addAttribute("msg5", "刪除成功");
            return "yuhsin/test/SignIn";
        } else {
            Cookie cookie = new Cookie("logintoken", "");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            model.addAttribute("mag5", "系統錯誤");
            return "redirect:/yuhsin/test/profile";
        }

    }
}
