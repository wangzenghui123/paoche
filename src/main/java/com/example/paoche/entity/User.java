package com.example.paoche.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private String id;
    private String username;
    private String password;
    private String salt;
    private String status;
    private Integer age;
    private String sex;
    private String phoneNumber;
    private String address;
}
