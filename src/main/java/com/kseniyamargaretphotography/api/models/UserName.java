package com.kseniyamargaretphotography.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.GenerationType.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserName {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;

    @OneToOne
    private User userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate createDate;

    private LocalDate endDate;
}
