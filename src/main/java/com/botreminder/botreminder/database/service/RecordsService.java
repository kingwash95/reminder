package com.botreminder.botreminder.database.service;

import com.botreminder.botreminder.database.entity.Records;
import com.botreminder.botreminder.database.repository.RecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordsService {
    @Autowired
    private final RecordsRepository recordsRepository;

    public RecordsService(RecordsRepository recordsRepository) {
        this.recordsRepository = recordsRepository;
    }

    public void createRecords (Records records){
        recordsRepository.save(records);
    }
    public void deleteRecords (Records records){
        recordsRepository.delete(records);
    }

}
