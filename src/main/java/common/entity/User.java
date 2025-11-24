package common.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="users")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(nullable = false,  unique = true, length = 30)
    private String cellPhoneNumber;

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.cellPhoneNumber = user.getCellPhoneNumber();
    }
}
