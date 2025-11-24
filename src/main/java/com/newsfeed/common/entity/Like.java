package com.newsfeed.common.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="likes")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Like extends BaseDateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="newsfeed_id" )
    private Newsfeed newsfeedId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="comment_id")
    private Comment commentId;

    public Like(Like like) {
        this.userId = like.getUserId();
        this.newsfeedId = like.getNewsfeedId();
        this.commentId = like.getCommentId();
    }
}
