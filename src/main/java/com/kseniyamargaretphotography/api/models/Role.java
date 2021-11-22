package com.kseniyamargaretphotography.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long roleId;

    @Column(nullable = false, unique = true, length = 100)
    private String roleName;

    @Column(nullable = false)
    private boolean canManageUsers;

    @Column(nullable = false)
    private boolean canModifyMainPages;

    @Column(nullable = false)
    private boolean uploadFiles;

    @ManyToMany
    private List<User> users = new ArrayList<>();
}
