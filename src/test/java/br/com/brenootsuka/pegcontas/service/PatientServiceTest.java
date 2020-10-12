package br.com.brenootsuka.pegcontas.service;

import br.com.brenootsuka.pegcontas.model.Patient;
import br.com.brenootsuka.pegcontas.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService service;

    @MockBean
    private PatientRepository repository;

    @Test
    public void findByNameSuccessTest() {
        String name = "Fernando Leite Serrano";

        Patient patient = new Patient(name);

        when(repository.findByName(name)).thenReturn(patient);

        assertEquals(patient, service.findByName(name));
    }

    @Test
    public void findByNameFailTest() {
        String name = "Fernando Leite Serrano";

        when(repository.findByName(name)).thenReturn(null);

        assertNull(service.findByName(name));
    }
}
