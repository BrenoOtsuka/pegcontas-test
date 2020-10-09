package br.com.brenootsuka.pegcontas.repository;

import br.com.brenootsuka.pegcontas.model.HealthInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthInsuranceRepository extends JpaRepository<HealthInsurance, Long> {
    public HealthInsurance findByName(String name);
}
