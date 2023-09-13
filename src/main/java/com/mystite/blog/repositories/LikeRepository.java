package com.mystite.blog.repositories;

import com.mystite.blog.models.Like;
import com.mystite.blog.models.Post;
import com.mystite.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Like FindByPostAndUser(Post post, User user);
}
