package com.journal.journalApp.Controller;

import com.journal.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    private Map<Long,JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry myEntries){
//        journalEntries.put(myEntries.getId(), myEntries);
//        return true;
//    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
        return journalEntries.remove(myId);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntryById(@PathVariable Long id, @RequestBody JournalEntry myEntries){
        return journalEntries.put(id,myEntries);
    }

}
