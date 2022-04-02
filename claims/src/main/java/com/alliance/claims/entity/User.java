
package com.alliance.claims.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "last_name", columnDefinition = "VARCHAR(30)")
    private String lastname;

    @Column(name = "first_name", columnDefinition = "VARCHAR(30)")
    private String firstname;

    @Column(name = "email", columnDefinition = "VARCHAR(30)")
    private String email;

    @Column(name = "password", columnDefinition = "VARCHAR(30)")
    private String pass;
}

