package com.stefan.training.spring.security.repository;


import com.stefan.training.spring.security.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    private RowMapper<User> userRowMapper = new UserRowMapper();

    @Resource
    private JdbcTemplate jdbcTemplate;

    public void create(User user) {
        jdbcTemplate.update(
                "insert into users (username, password, encrypted_password, roles) values (?, ?, ?, ?)",
                user.getUsername(), user.getPassword(), user.getEncryptedPassword(), user.getRoles());
    }

    public void update(User user) {
        jdbcTemplate.update(
                "insert into users (id, username, password, encrypted_password, roles) values (?, ?, ?, ?, ?)",
                user.getId(), user.getUsername(), user.getPassword(), user.getEncryptedPassword(), user.getRoles());
    }

    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query("select * from users", userRowMapper);
        return users;
    }

    public boolean exists(User user) {
        return findUserByName(user.getUsername()) != null;
    }

    public User findUserByName(String username) {
        LOGGER.info("Find user by name: [{}]. JDBCTemplate={}", username, jdbcTemplate == null);
        List<User> users = jdbcTemplate.query("select * from users where username = ?", userRowMapper, username);
        LOGGER.info("Find user by name: [{}]. Found [{}].", username, users.size());
        return (users != null && !users.isEmpty()) ? users.get(0) : null;
    }

    public int delete(String userId) {
        return jdbcTemplate.update("DELETE FROM users WHERE id = ?", userId);
    }
}

class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEncryptedPassword(resultSet.getString("encrypted_password"));
        user.setRoles(resultSet.getString("roles"));

        return user;
    }
}
