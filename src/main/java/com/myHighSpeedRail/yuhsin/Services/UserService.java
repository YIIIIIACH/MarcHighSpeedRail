package com.myHighSpeedRail.yuhsin.Services;

import com.google.gson.Gson;
import com.myHighSpeedRail.yuhsin.Models.*;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {

    private String basic_Auth_user = "user";
    private String basic_Auth_password = "123";
    private String account_url = "https://yuhsin.ttmcloud.stream";

    public LoginResponseModel login(String email, String password) {
        LoginRequestModel request = new LoginRequestModel();
        request.setEmail(email);
        request.setPassword(password);
        Gson g1 = new Gson();
        String requestJson = g1.toJson(request);
        String callback = null;
        try {
            callback = post(account_url + "/signin", requestJson);
            if (callback != null) {
                var get = g1.fromJson(callback, LoginResponseModel.class);
                return get;
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    public LoginResponseModel tokenlogin(UUID token) {
        TokenLoginRequestModel request = new TokenLoginRequestModel();
        request.setLogin_token(token);
        Gson g1 = new Gson();
        String requestJson = g1.toJson(request);
        String callback = null;
        try {
            callback = post(account_url + "/tokenlogin", requestJson);
            if (callback != null) {
                var get = g1.fromJson(callback, LoginResponseModel.class);
                return get;
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    public TokenLogoutRequestModel signout(UUID token) {
        TokenLoginRequestModel request = new TokenLoginRequestModel();
        request.setLogin_token(token);
        Gson g1 = new Gson();
        String requestJson = g1.toJson(request);
        String callback = null;
        try {
            callback = post(account_url + "/signout", requestJson);
            if (callback != null) {
                var get = g1.fromJson(callback, TokenLogoutRequestModel.class);
                return get;
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    public RegisterResponseModel register(String name, String password, String email, String phone) {
        RegisterRequestModel request = new RegisterRequestModel();
        request.setMember_name(name);
        request.setMember_password(password);
        request.setMember_email(email);
        request.setMember_phone(phone);
        Gson g1 = new Gson();
        String requestJson = g1.toJson(request);
        String callback = null;
        try {
            callback = post(account_url + "/register", requestJson);
            if (callback != null) {
                var get = g1.fromJson(callback, RegisterResponseModel.class);
                return get;
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    public UpdateResponseModel edit(UUID id, String name, String password, String email, String phone) {
        UpdateRequestModel request = new UpdateRequestModel();
        request.setMember_id(id);
        request.setMember_name(name);
        request.setMember_password(password);
        request.setMember_email(email);
        request.setMember_phone(phone);
        Gson g1 = new Gson();
        String requestJson = g1.toJson(request);
        String callback = null;
        try {
            callback = post(account_url + "/edit", requestJson);
            if (callback != null) {
                var get = g1.fromJson(callback, UpdateResponseModel.class);
                return get;
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    public boolean removeInfo(UUID id) {
        UpdateRequestModel request = new UpdateRequestModel();
        request.setMember_id(id);

        Gson g1 = new Gson();
        String requestJson = g1.toJson(request);
        String callback = null;
        try {
            callback = post(account_url + "/removeUser", requestJson);
            if (callback.equals("200")) {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    private String post(String url, String jsonBody) throws IOException {
        String resultContent = null;
        final BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope("yuhsin.ttmcloud.stream", 443),
                new UsernamePasswordCredentials(basic_Auth_user, basic_Auth_password.toCharArray()));
        try (final CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build()) {
            final HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
            try (final CloseableHttpResponse response = httpclient.execute(httpPost)) {
                if (response.getCode() == 200) {
                    System.out.println(response.getReasonPhrase()); // OK
                    HttpEntity entity = response.getEntity();
                    // 獲取響應信息
                    resultContent = EntityUtils.toString(entity, "UTF-8");
                } else {
                    resultContent = null;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return resultContent;
    }
}
