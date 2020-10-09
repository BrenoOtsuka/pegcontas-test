package br.com.brenootsuka.pegcontas.repository;

import br.com.brenootsuka.pegcontas.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    public  Patient findByName(String name);
}
