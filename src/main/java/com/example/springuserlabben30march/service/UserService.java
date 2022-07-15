package com.example.springuserlabben30march.service;


import com.example.springuserlabben30march.dao.UserRepository;
import com.example.springuserlabben30march.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        Iterable<User> users = repository.findAll();
        return (List<User>) users;
    }


    public void save(User user) {
        repository.save(user);
    }

    public List<User> findByKeyword(String keyword) {
        return repository.findByKeyword(keyword);
    }

    public User get(Integer id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public void deleteById(Integer id) throws UserPrincipalNotFoundException {
        Integer count = repository.countById(id);
        if (count == null || count == 0){
            throw new UserPrincipalNotFoundException("Could not find any users with ID" + id);
        }
        repository.deleteById(id);

    }
}
