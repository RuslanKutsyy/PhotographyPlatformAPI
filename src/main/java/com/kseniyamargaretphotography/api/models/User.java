package com.kseniyamargaretphotography.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.LazyCollectionOption.*;

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

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_name_id")
    private UserName userName;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "last_login_date")
    private Timestamp lastLoginDate;

    @Column(name = "roles", nullable = false)
    @LazyCollection(FALSE)
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Role> role = new ArrayList<>();
}
