package com.up.usersauth.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_user_role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String name;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "is_active")
    private Boolean active;
}
