package com.kseniyamargaretphotography.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

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
    @NotBlank(message = "{default.value.required}")
    @Size(min = 3, max = 50, message = "{default.invalid.length}")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "{default.value.required}")
    @Size(min = 3, max = 50, message = "{default.invalid.length}")
    private String lastName;

    @Size(min = 3, max = 50)
    @NotBlank(message = "{default.value.required}")
    @Column(name = "email", nullable = false, unique = true)
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "{default.value.invalid}")
    private String email;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "username_id")
    private UserName userName;

    @OneToMany(cascade = CascadeType.ALL, fetch = LAZY, orphanRemoval = true, mappedBy = "user")
    private Collection<Password> password;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "last_login_date")
    private Timestamp lastLoginDate;

    @JsonIgnore
    @Column(name = "roles", nullable = false)
    @ManyToMany(cascade = CascadeType.ALL, fetch = EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Collection<Role> roles;
}
