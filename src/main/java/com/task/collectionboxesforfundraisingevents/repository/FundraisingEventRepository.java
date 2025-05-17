package com.task.collectionboxesforfundraisingevents.repository;

import com.task.collectionboxesforfundraisingevents.entity.FundraisingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundraisingEventRepository extends JpaRepository<FundraisingEvent, Integer> {

}
