package com.api.redis.Controller;

import com.api.redis.dao.UserDao;
import com.api.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    //CREATE USER
    @PostMapping
    public User createUser(@RequestBody User user){
        user.setUserId(UUID.randomUUID().toString());
        return userDao.save(user);
    }

    //GET SINGLE USER
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId){
        return userDao.get(userId);
    }


    //FINDALL
    @GetMapping
    public List<User> getAll(){
        Map<Object, Object> all = userDao.findAll();
        Collection<Object> values = all.values();
        List<User> collect = values.stream().map(value->(User)value).collect(Collectors.toList());
        return collect;
    }

    //DELETE USER
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String userId){
        userDao.delete(userId);
    }


    //Update
    @PutMapping("/{userId}")
    public User updateUser( @PathVariable("userId") String userId ,@RequestBody User user){
        user.setUserId(userId);
        return userDao.update(user);
    }

}
