package com.example.paoche.entity;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Permission implements Serializable {

    private String id;

    private String permission_name;

}
