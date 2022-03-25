package com.emreblbl.springbootthymeleaf.demo.service;

import com.emreblbl.springbootthymeleaf.demo.dao.UserRepository;
import com.emreblbl.springbootthymeleaf.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImp implements UserService{
    private UserRepository userRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllByOrderByLastNameAsc() ;
    }

    @Override
    public User findById(int theId) {
        Optional<User> result = userRepository.findById(theId);
        User user;
        if(result.isPresent()){
            user =result.get();
        }else{
            throw new RuntimeException("Didn't find user id"+theId);
        }
        return user;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public List<User> searchForSurname(String surname) {
        return userRepository.findByLastName(surname);
    }

    @Override
    public List<User> searchWhereAny(String keyword) {
        if(keyword != null){
            userRepository.search(keyword);

        }
        return userRepository.findAll();
    }

    @Override
    public Boolean confirmEmail(String email) {
        User user = userRepository.confirmEmail(email);
        if(user ==null){
            return false;
        }
        return true;
    }
}
