package com.example.service;

import com.example.entity.SimDetails;
import com.example.repository.SimDetailsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SimDetailsService {
    private final SimDetailsRepository simRepository;

    public SimDetailsService(SimDetailsRepository simRepository) {
        this.simRepository = simRepository;
    }

    public List<SimDetails> getActiveSims() {
        return simRepository.findAll().stream()
                .filter(sim -> "Active".equalsIgnoreCase(sim.getStatus()))
                .collect(Collectors.toList());
    }

    public String getSimStatus(Long simNumber, Long serviceNumber) throws Exception {
        return simRepository.findAll().stream()
                .filter(sim -> sim.getSimNumber().equals(simNumber) && sim.getServiceNumber().equals(serviceNumber))
                .map(SimDetails::getStatus)
                .findFirst()
                .orElseThrow(() -> new Exception("No SIM details found for given service and SIM number"));
    }

    public void activateSim(Integer simId) throws Exception {
        SimDetails sim = simRepository.findById(simId);
        if (sim == null) {
            throw new Exception("No SIM details found for given Id");
        }
        sim.setStatus("Active");
        simRepository.update(sim);
    }
}
