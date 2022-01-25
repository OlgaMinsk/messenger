package com.innowisegroup.messenger.repository;

import com.innowisegroup.messenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    default User findUserById(Long id) {
//        return findById(id)
//                .orElseThrow(() -> {
//                            return new ResponseStatusException(HttpStatus.NOT_FOUND, "nu&");
//                        }
//                );
//    }
}
