package common.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="newsfeeds")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Newsfeed extends BaseDateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id",  nullable=false)
    private User userId;

    public Newsfeed(Newsfeed newsfeed,  User userId) {
        this.title = newsfeed.getTitle();
        this.content = newsfeed.getContent();
        this.userId = userId;
    }
}
