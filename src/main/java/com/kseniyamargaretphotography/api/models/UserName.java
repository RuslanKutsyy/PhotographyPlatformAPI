package com.kseniyamargaretphotography.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usernames")
public class UserName {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "username_id")
    private Long userNameId;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "{default.value.required}")
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message = "{default.value.invalid}")
    private String userName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "create_date")
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    public UserName(String userName, User user) {
        this.userName = userName;
        this.user = user;
    }

}
