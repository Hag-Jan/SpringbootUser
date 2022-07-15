package com.example.springuserlabben30march.dao;

import com.example.springuserlabben30march.model.User;

import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testAddUser() {
        User user = new User();

        user.setFirstName("Limas");
        user.setLastName("Miaa");
        user.setPassword("12324");
        user.setEmail("limaa@gmail.com");

        User savedUser = repository.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();

        user1.setFirstName("Filo");
        user1.setLastName("Finaa");
        user1.setPassword("1234");
        user1.setEmail("finao@gmail.com");

        repository.save(user1);

        User user2 = new User();

        user2.setFirstName("Siloa");
        user2.setLastName("Silaa");
        user2.setPassword("5678");
        user1.setEmail("sils@gmail.com");

        repository.save(user2);

        Iterable<User> users = repository.findAll();
        int listSize = IterableUtil.sizeOf(users);

        assertThat(listSize).isEqualTo(2);

    }

    @Test
    public void testupdate() {
        User user1 = new User();

        user1.setFirstName("talia");
        user1.setLastName("tals");
        user1.setPassword("123");
        user1.setEmail("tala@gmail.com");

        User savedUser1 = repository.save(user1);

        Integer userId = savedUser1.getId();

        Optional<User> optionalUser2 = repository.findById(userId);

        User user2 = optionalUser2.get();

        user2.setFirstName("Sandy");

        repository.save(user2);

        User updateUser = repository.findById(userId).get();

        assertThat(updateUser.getFirstName()).isEqualTo("Sandy");


    }

    @Test
    public void testListAll(){
        Iterable<User> users = repository.findAll();
        assertThat(users).hasSizeGreaterThan(0);

        for(User user : users){
            System.out.println(user);
        }
    }

}