package com.example.wonderq.dao;

import com.example.wonderq.objects.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by shallinris on 08/03/2019.
 */
public interface MessageRepository extends PagingAndSortingRepository<Message, Integer> {

    List<Message> findAll();

    Message findById(int id);

    @Query(value = "SELECT * FROM MESSAGES WHERE TAKEN = ?1", nativeQuery = true)
    List<Message> findByWasProcessed(boolean wasProcessed);
}
