package com.kseniyamargaretphotography.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.time.LocalDate;

import static javax.persistence.GenerationType.*;

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
    @CreatedDate
    private LocalDate createDate;

    @Column(name = "end_date")
    @Future
    private LocalDate endDate;
}
