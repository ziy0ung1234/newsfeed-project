package com.newsfeed.domain.like.entity;

import com.newsfeed.common.entity.*;
import com.newsfeed.domain.comment.entity.Comment;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="likes")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Like extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="newsfeed_id" )
    private Newsfeed newsfeed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="comment_id")
    private Comment comment;

    public Like(User user, Newsfeed newsfeed, Comment comment) {
        this.user = user;
        this.newsfeed = newsfeed;
        this.comment = comment;
    }
}
