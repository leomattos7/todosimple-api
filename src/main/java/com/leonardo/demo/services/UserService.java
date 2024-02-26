package com.leonardo.demo.services;

import com.leonardo.demo.models.User;
import com.leonardo.demo.repositories.TaskRepository;
import com.leonardo.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByID(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "User not found! Id: " + id + ", Type: " + User.class.getName()
        ));
    }

    @Transactional
    public User user(User obj) {
        obj.setId(null);
        return this.userRepository.save(obj);
    }

    @Transactional
    public User update(User obj) {
        User newObj = findByID(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findByID(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Could not delete user! Id: " + id + ", Type: " + User.class.getName());
        }
    }
}
