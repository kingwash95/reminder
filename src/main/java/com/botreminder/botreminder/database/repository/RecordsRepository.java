package com.botreminder.botreminder.database.repository;

import com.botreminder.botreminder.database.entity.Records;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordsRepository extends JpaRepository <Records, Long> {

}
