package com.example.blogapi.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    @Query(value = "SELECT u from \"user\" u WHERE u.is_deleted = false", nativeQuery = true)
    List<User> findAllNonDeletedUsers();

    @Query(value = "SELECT * from \"user\" WHERE id = ?1 AND is_deleted = false", nativeQuery = true)
    Optional<User> findNonDeletedUser(Long id);
}
