package com.kseniyamargaretphotography.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

/**
 * @author rkutsyy
 * @version 1.0
 * @since 2021/11/23
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long userId;

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 100, nullable = false)
    private String lastName;

    @OneToOne
    private UserName userName;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate registrationDate;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();
}
