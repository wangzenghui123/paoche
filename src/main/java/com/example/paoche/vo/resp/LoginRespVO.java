package com.example.paoche.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRespVO {

    private String username;

    private String access_token;

    private String refresh_token;

    private String id;

}
