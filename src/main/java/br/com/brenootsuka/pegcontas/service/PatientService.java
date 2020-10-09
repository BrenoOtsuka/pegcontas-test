package br.com.brenootsuka.pegcontas.service;

import br.com.brenootsuka.pegcontas.model.Patient;
import br.com.brenootsuka.pegcontas.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }

    public Patient findByName(String name) {

        return patientRepository.findByName(name);
    }

    public Patient save(Patient patient) {

        return patientRepository.save(patient);
    }
}
