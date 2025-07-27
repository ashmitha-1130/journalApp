package com.journal.journalApp.Services;

import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.entity.User;
import com.journal.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntries(JournalEntry entry, String userName){
        try{
            User user = userService.findByUsername(userName);
            JournalEntry saved = journalEntryRepository.save(entry);
            user.getJournalEntries().add(saved);
//            user.setUserName(null);
             userService.saveUser(user);
        }catch (Exception e){
            log.error("Error : ", e);
            throw new RuntimeException("An error occurred while saving the entry",e);
        }
    }
    public void saveEntries(JournalEntry entry){
         journalEntryRepository.save(entry);

    }

    public List<JournalEntry> getAllEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
       try{
           User user = userService.findByUsername(userName);
           removed = user.getJournalEntries().removeIf(x->x.getId().equals(id));
           if(removed){
               userService.saveUser(user);
               journalEntryRepository.deleteById(id);
           }
       }catch(Exception e){
           log.error("Error : ",e);
           throw new RuntimeException("An error occurred while deleting an entity",e);
       }
        return removed;
    }


}
