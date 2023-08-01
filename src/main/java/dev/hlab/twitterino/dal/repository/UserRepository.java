package dev.hlab.twitterino.dal.repository;

import dev.hlab.twitterino.dal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserByEmailAndPassword(String email, String password);
    public Optional<User> findByHandle(String handle);
}
