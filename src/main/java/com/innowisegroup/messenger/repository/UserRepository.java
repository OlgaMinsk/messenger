package com.innowisegroup.messenger.repository;

import com.innowisegroup.messenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);
    User findByUserName(String userName);
}
