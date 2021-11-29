package com.kseniyamargaretphotography.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long roleId;

    @Column(nullable = false, unique = true, length = 100)
    private String roleName;

    @Column(nullable = false)
    private boolean canManageUsers;

    @Column(nullable = false)
    private boolean canModifyMainPages;

    @Column(nullable = false)
    private boolean uploadFiles;

    @ManyToOne(fetch = LAZY)
    private User user;
}
