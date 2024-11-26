package com.api.redis.dao;

import com.api.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private static final String KEY="USER18112001";

    //SAVE USER

    public User save(User user){

        redisTemplate.opsForHash().put(KEY, user.getUserId(),user);
        return user;
    }

    public User get(String userId){
       return (User) redisTemplate.opsForHash().get(KEY, userId);
    }

    //FIND ALL
    public Map<Object, Object> findAll(){
        return redisTemplate.opsForHash().entries(KEY);
    }

    //DELETE
    public void delete(String userId){
        redisTemplate.opsForHash().delete(KEY, userId);
    }

    //UPDATE
    public User update(User user) {
        redisTemplate.opsForHash().put(KEY, user.getUserId(), user);
        return user;
    }

}
