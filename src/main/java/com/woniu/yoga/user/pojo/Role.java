package com.woniu.yoga.user.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer roleId;
    @Column(name = "role_name")
    private String roleName;
}