package com.newsfeed.common.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="follows")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Follow extends BaseDateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="follower_id")
    private User followerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="following_id")
    private User followingId;

    public Follow(Follow follow) {
        this.followerId = follow.getFollowerId();
        this.followingId = follow.getFollowingId();
    }
}
