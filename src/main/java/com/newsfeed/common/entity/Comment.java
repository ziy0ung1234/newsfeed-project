package com.newsfeed.common.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="comments")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Comment extends BaseDateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private int depth;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id",  nullable = false)
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="newsfeed_id",  nullable = false)
    private Newsfeed newsfeedId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="parent_comment_id",  nullable = false)
    private Comment parentCommentId;

    public Comment(Comment comment) {
        this.content = comment.getContent();
        this.depth = comment.getDepth();
        this.userId = comment.getUserId();
        this.newsfeedId = comment.getNewsfeedId();
        this.parentCommentId = comment;

    }

}
