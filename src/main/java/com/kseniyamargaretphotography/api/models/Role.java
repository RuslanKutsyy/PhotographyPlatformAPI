package com.kseniyamargaretphotography.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String roleName;

    @Column(nullable = false)
    private boolean canManageUsers;

    @Column(nullable = false)
    private boolean canModifyMainPages;

    @Column(nullable = false)
    private boolean uploadFiles;

    @ManyToMany
    private List<User> user;
}
