package com.mystite.blog.repositories;

import com.mystite.blog.models.Post;
import com.mystite.blog.models.Subscription;
import com.mystite.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("SELECT p.user FROM Post p WHERE p = :post")
    User findUserByPost(Post post);
}
