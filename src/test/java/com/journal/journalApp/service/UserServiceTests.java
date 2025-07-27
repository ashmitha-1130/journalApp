package com.journal.journalApp.service;

import com.journal.journalApp.Services.UserService;
import com.journal.journalApp.entity.User;
import com.journal.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

//    @Disabled
//    @Test
//    public void testByUsername(){
//       // assertEquals(4,2+2);
//        User user = userRepository.findByUserName("ashmitha");
//        assertTrue(!user.getJournalEntries().isEmpty());
//       // assertNotNull(userRepository.findByUserName("ashmitha"));
//    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings  ={
            "ram",
            "shyam",
            "ashmitha"
    })
    public void testByUsername(String name){
        assertNotNull(userRepository.findByUserName(name),"failed for : " + name);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}
