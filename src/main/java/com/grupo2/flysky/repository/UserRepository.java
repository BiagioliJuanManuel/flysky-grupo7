//package com.grupo2.flysky.repository;
//
//import com.grupo2.flysky.entity.User;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class UserRepository implements UserRepositoryInterface{
//
//    private List<User> database = new ArrayList<>();
//
//    @Override
//    public User save(User user) {
//        user.setId((long) (database.size() + 1));
//        database.add(user);
//
//        return user;
//    }
//
//    @Override
//    public List<User> findAll() {
//        return database;
//    }
//
//    @Override
//    public User findById(int id) {
//        return database.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
//    }
//}
