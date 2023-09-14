package com.mystite.blog.repositories;

import com.mystite.blog.models.Post;
import com.mystite.blog.models.Subscription;
import com.mystite.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Long countSubscribersByUser(User user);
    @Query("SELECT p.user FROM Post p WHERE p.postId = :postId")
    User findUserByPostId(long postId);
    Subscription findBySubscriberAndUser(User subscriber, User user);
}
