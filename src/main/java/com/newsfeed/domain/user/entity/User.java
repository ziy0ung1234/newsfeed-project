package com.newsfeed.domain.user.entity;

import com.newsfeed.common.entity.BaseDateEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="users")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class User extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false,  unique = true, length = 30)
    private String cellPhoneNumber;

    public User(String username, String email, String password, String cellPhoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }
}
