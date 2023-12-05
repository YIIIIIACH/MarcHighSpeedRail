package com.myHighSpeedRail.yuhsin.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
public class TokenLoginRequestModel {
    private UUID login_token;
    private UUID member_id;
    private String device_name;
    private String data;
    private String created_at;

}
