package br.com.brenootsuka.pegcontas.service;

import br.com.brenootsuka.pegcontas.model.HealthInsurance;
import br.com.brenootsuka.pegcontas.repository.HealthInsuranceRepository;
import org.springframework.stereotype.Service;

@Service
public class HealthInsuranceService {

    private final HealthInsuranceRepository healthInsuranceRepository;

    public HealthInsuranceService(HealthInsuranceRepository healthInsuranceRepository) {
        this.healthInsuranceRepository = healthInsuranceRepository;
    }

    public HealthInsurance findByName(String name) {
        return healthInsuranceRepository.findByName(name);
    }
}
