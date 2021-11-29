package com.kseniyamargaretphotography.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserName implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userNameId;

    @Column(nullable = false)
    private String userName;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "end_date")
    private Timestamp endDate;
}
