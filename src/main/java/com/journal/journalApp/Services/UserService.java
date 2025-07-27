package com.journal.journalApp.Services;

import com.journal.journalApp.entity.User;
import com.journal.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    public void saveUser(User user){
        userRepository.save(user);
    }

    public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        }catch (Exception e){
//            log.error("Error for {} : ",user.getUserName(),e);
            log.info("hahahahaah...");
            log.warn("hahaha");
            log.debug("haahaaha");
            log.error("hahaaha");
            log.trace("hahahahaa");

            return false;
        }
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }


   public List<User> getAllUser(){
        return userRepository.findAll();
   }

   public Optional<User> getUserById(ObjectId id){
        return userRepository.findById(id);
   }

   public void deleteUser(ObjectId id){
        userRepository.deleteById(id);
   }

   public User findByUsername(String username){
        return userRepository.findByUserName(username);
   }
}
