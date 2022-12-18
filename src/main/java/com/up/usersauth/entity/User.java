package com.up.usersauth.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;

    private String password;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "is_active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
}
