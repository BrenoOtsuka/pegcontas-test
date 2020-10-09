package br.com.brenootsuka.pegcontas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HealthInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long healthInsuranceId;

    private String name;

    public  HealthInsurance() {
    }

    public HealthInsurance(String name) {
        this.name = name;
    }

    public Long getHealthInsuranceId() {
        return healthInsuranceId;
    }

    public void setHealthInsuranceId(Long healthInsuranceId) {
        this.healthInsuranceId = healthInsuranceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
