package com.innowisegroup.messenger.repository;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.Message;
import com.innowisegroup.messenger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Component
public class UserRepositoryJdbcImpl implements UserRepository {
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("Select * from MESSENGER.users",
                new BeanPropertyRowMapper<>(User.class));

    }

    @Override
    public User addUser(User user) {
        jdbcTemplate.update("insert into users(user_name) values (?);", user.getUser_name());
        return null;
    }

    @Override
    public User getById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public boolean deleteUser(Long id) throws NotFoundException {
        return false;
    }

    @Override
    public void updateUser(Long id, User user) throws NotFoundException {
//jdbcTemplate.update("")
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }
}
