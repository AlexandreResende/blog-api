package com.example.blogapi.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT p from post u WHERE p.user.user_id = ?1 AND p.is_deleted = false", nativeQuery = true)
    List<Post> getNonDeletedPosts(Long userId);

    @Query(
        value = "SELECT p from post u WHERE p.user.user_id = ?1 AND p.post_id = ?2 AND p.is_deleted = false",
        nativeQuery = true
    )
    Optional<Post> getNonDeletedPostByUserIdAndPostId(Long userId, Long postId);
}
