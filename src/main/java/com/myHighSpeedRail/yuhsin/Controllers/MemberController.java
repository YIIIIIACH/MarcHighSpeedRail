package com.myHighSpeedRail.yuhsin.Controllers;

import com.google.gson.Gson;
import com.myHighSpeedRail.yuhsin.Models.*;
import com.myHighSpeedRail.yuhsin.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class MemberController {

    Logger _logger = LoggerFactory.getLogger(MemberController.class);
    private Gson g1 = new Gson();
    private UserService _userService;

    public MemberController(UserService userService) {
        _userService = userService;

    }

    @RequestMapping(value = "/member/signin", method = RequestMethod.POST)
    public String Login(@RequestBody String body_input,
                        HttpServletResponse httpServletResponse
                        ) {
        _logger.info("on signin");
        LoginRequestModel request;
        //處理請求反序列化
        try {
            _logger.debug("signin," + body_input);
            request = g1.fromJson(body_input, LoginRequestModel.class);

        } catch (Exception ex) {
            _logger.warn(String.format("signin,parse fail,%s", ex.toString()));
            return null;
        }
        //業務邏輯
        try {
           var get_data=_userService.login(request.getEmail(), request.getPassword());
           return g1.toJson(get_data);
        } catch (Exception ex) {
            _logger.warn(String.format("signin,findByUserName fail,%s", ex.toString()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/member/signout", method = RequestMethod.POST)
    public String logout2(@RequestBody String body_input,
                          HttpServletResponse httpServletResponse) {
        _logger.info("on signout");
        TokenLoginRequestModel request;
        //處理請求反序列化
        try {
            _logger.debug("signout," + body_input);
            request = g1.fromJson(body_input, TokenLoginRequestModel.class);

        } catch (Exception ex) {
            _logger.warn(String.format("signout input fail,%s", ex.toString()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        //業務邏輯
        try {
            var get_data=_userService.signout(request.getLogin_token());
            return g1.toJson(get_data);
        } catch (Exception ex) {
            _logger.warn(String.format("signout output fail,%s", ex.toString()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @PostMapping("/member/register")
    public String Register(@RequestBody String body_input,
                           HttpServletResponse httpServletResponse) {
        _logger.info("on register");
        RegisterRequestModel request;
        try {
            _logger.debug("register," + body_input);
            request = g1.fromJson(body_input, RegisterRequestModel.class);

        } catch (Exception ex) {
            _logger.warn(String.format("register input fail,%s", ex.toString()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        try {
            if (request.getMember_name().isEmpty()) {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }
            if (request.getMember_password().isEmpty()) {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }
            if (request.getMember_email().isEmpty()) {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }
            if (request.getMember_phone().isEmpty()) {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }

            var get_data = _userService.register(request.getMember_name(), request.getMember_password(), request.getMember_email(), request.getMember_phone());
            return g1.toJson(get_data);
        } catch (Exception ex) {
            _logger.warn(String.format("register,process fail,%s", ex.toString()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
//
//
    @PostMapping("/member/tokenlogin")
    public String tokenlogin(@RequestBody String body_input,
                             HttpServletResponse httpServletResponse) {
        _logger.info("on tokenlogin");
        TokenLoginRequestModel request;
        try {
            _logger.debug("tokenlogin," + body_input);
            request = g1.fromJson(body_input, TokenLoginRequestModel.class);
        } catch (Exception ex) {
            _logger.warn(String.format("tokenlogin input fail,%s", ex.toString()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        try {
            var get_data=_userService.tokenlogin(request.getLogin_token());
            return g1.toJson(get_data);
        } catch (Exception ex) {
            _logger.warn(String.format("tokenlogin,process fail,%s", ex.toString()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @PostMapping("/member/edit")
    public String edit(@RequestBody String body_input,
                       HttpServletResponse httpServletResponse){
        _logger.info("on edit");
        UpdateRequestModel request;
        UpdateResponseModel result;
        try {
            _logger.debug("edit," + body_input);
            request = g1.fromJson(body_input, UpdateRequestModel.class);
        } catch (Exception ex) {
            _logger.warn(String.format("edit input fail,%s", ex.toString()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        try {
            var get_data=_userService.edit(
                    request.getMember_id(),
                    request.getMember_name(),
                    request.getMember_password(),
                    request.getMember_email(),
                    request.getMember_phone());
            return g1.toJson(get_data);
        } catch (Exception ex) {
            _logger.warn(String.format("edit,output fail,%s", ex.toString()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }


    @PostMapping("/member/removeUser")
    public String delete(@RequestBody String body_input,
                         HttpServletResponse httpServletResponse) {
        _logger.info("on removeUser");
        Gson g1 = new Gson();
        UpdateRequestModel request;
        try {
            _logger.debug("removeUser," + body_input);
            request = g1.fromJson(body_input, UpdateRequestModel.class);
        } catch (Exception ex) {
            _logger.warn(String.format("removeUser input fail,%s", ex.toString()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        try {
            var get_data=_userService.removeInfo(request.getMember_id());
            return g1.toJson(get_data);
        } catch (Exception ex) {
            _logger.warn(String.format("removeUser,process fail,%s", ex.toString()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}
