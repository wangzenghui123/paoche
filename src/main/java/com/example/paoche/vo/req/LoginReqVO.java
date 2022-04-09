package com.example.paoche.vo.req;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqVO {
    
    private String username;

    private String password;
}
