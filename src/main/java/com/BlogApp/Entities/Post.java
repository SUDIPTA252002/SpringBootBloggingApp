package com.BlogApp.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Posts")
public class Post 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "Title",nullable = false,length=100)
    private String title;

    @Column(name="Content",nullable = false,length = 10000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    
    @ManyToOne
    private User user;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String mediaUrl;

    @PrePersist
    protected void onCreate() 
    {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() 
    {
        updatedAt = LocalDateTime.now();
    }
    
}
