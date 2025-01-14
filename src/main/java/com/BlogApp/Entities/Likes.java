package com.BlogApp.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Likes")
public class Likes
{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer likeId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
    
    private LocalDateTime likedOn;

    @PrePersist
    private void likedOn()
    {
        this.likedOn=LocalDateTime.now();
    }

}
