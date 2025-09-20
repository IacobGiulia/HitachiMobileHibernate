package com.example.repository;

import com.example.entity.SimDetails;
import java.util.List;

public interface SimDetailsRepository {
    List<SimDetails> findAll();
    SimDetails findById(Integer id);
    void save(SimDetails simDetails);
    void update(SimDetails simDetails);
    void delete(Integer id);
}
