package com.example.demo.repositories;

import com.example.demo.models.TodoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class TodoRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class TodoMapper implements RowMapper<TodoModel>{
        @Override
        public TodoModel mapRow(ResultSet resultSet, int rawNum) throws SQLException{
            TodoModel todo = new TodoModel();
            todo.setId(resultSet.getInt("id"));
            todo.setTitle(resultSet.getString("title"));
            todo.setDescription(resultSet.getString("description"));
            todo.setCompleted(resultSet.getBoolean("completed"));
            return todo;
        }
    }
    public int save(TodoModel todoModel) {
        String sql = "INSERT INTO " + "Todos" + " (title, description, completed) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, todoModel.getTitle());
            ps.setString(2, todoModel.getDescription());
            ps.setBoolean(3, todoModel.getCompleted());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public TodoModel findById(int id) {
        String sql = "SELECT * FROM Todos WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new TodoMapper());
    }

    public List<TodoModel> findAll() {
        String sql = "SELECT * FROM Todos";
        return jdbcTemplate.query(sql, new TodoMapper());
    }

//    public int update(TodoModel todoModel) {
//        String sql = "UPDATE Todos SET name = ?, email = ? WHERE id = ?";
//        return jdbcTemplate.update(sql, todoModel.getName(), todoModel.getEmail(), todoModel.getId());
//    }

    public int deleteById(int id) {
        String sql = "DELETE FROM Todos WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}


