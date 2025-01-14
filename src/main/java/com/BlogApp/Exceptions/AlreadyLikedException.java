package com.BlogApp.Exceptions;

public class AlreadyLikedException extends RuntimeException 
{
    String postId;
    String message;

    public AlreadyLikedException(String postId,String message)
    {
        super(String.format("%s with postID: %s",message,postId));
        this.postId=postId;
        this.message=message;
    }
    
}
