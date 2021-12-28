package com.kseniyamargaretphotography.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author rkutsyy
 * @version 1.0
 * @since 2021/11/23
 */
@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @Size(min = 3, max = 50, message = "First Name length is invalid")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 3, max = 50, message = "First Name length is invalid")
    private String lastName;

    @Size(min = 3, max = 50)
    @Column(name = "email", nullable = false, unique = true)
    @Email(regexp = "Add validation later here", message = "Add additional validation message")
    private String email;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "username_id")
    private UserName userName;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "last_login_date")
    private Timestamp lastLoginDate;

    @JsonIgnore
    @Column(name = "roles", nullable = false)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles;
}
