package com.company.dao;

import com.company.bean.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAOClass<User> {

    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void create(User entity){
        String sql="insert into Users(Login, Password, Email) Values('"+entity.getLogin()+"',"+entity.getPassword()+",'"+entity.getEmail()+"')";
        template.update(sql);
    }
    public void update(User entity){
        String sql="update users set login='"+entity.getLogin()+"', password='"+entity.getPassword()+"',email='"+entity.getEmail()+"' where id="+entity.getId()+"";
        template.update(sql);
    }
    public void delete(long id){
        String sql="delete from Users where id="+id+"";
        template.update(sql);
    }
    public User getById(long id){
        String sql="select * from Users where id=?";
        return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<User>(User.class));
    }
    public List<User> getAll(){
        return template.query("select * from Users",new RowMapper<User>(){
            public User mapRow(ResultSet rs, int row) throws SQLException {
                User e=new User();
                e.setId(rs.getInt(1));
                e.setLogin(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setEmail(rs.getString(4));
                return e;
            }
        });
    }

    public User getByLogin(String login)
    {
        String sql="select * from Users where login=?";


        try {
            return template.queryForObject(sql, new Object[]{login},new BeanPropertyRowMapper<User>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
