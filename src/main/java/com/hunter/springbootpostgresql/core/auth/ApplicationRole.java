package com.hunter.springbootpostgresql.core.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "application_roles")
@Data
@NoArgsConstructor
public class ApplicationRole {

    @Id
    @Column(name = "role_id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;

    @Column(name = "role")
    private String role;
}
