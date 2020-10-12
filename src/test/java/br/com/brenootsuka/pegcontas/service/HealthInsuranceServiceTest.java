package br.com.brenootsuka.pegcontas.service;

import br.com.brenootsuka.pegcontas.model.HealthInsurance;
import br.com.brenootsuka.pegcontas.repository.HealthInsuranceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HealthInsuranceServiceTest {

    @Autowired
    private HealthInsuranceService service;

    @MockBean
    private HealthInsuranceRepository repository;

    @Test
    public void findByNameSuccessTest() {
        String name = "Inter Plena";

        HealthInsurance healthInsurance = new HealthInsurance(name);

        when(repository.findByName(name)).thenReturn(healthInsurance);

        assertEquals(healthInsurance, service.findByName(name));
    }

    @Test
    public void findByNameFailTest() {
        String name = "Inter Plena";

        when(repository.findByName(name)).thenReturn(null);

        assertNull(service.findByName(name));
    }
}
