package br.com.brenootsuka.pegcontas.repository;

import br.com.brenootsuka.pegcontas.model.Bill;
import br.com.brenootsuka.pegcontas.model.Card;
import br.com.brenootsuka.pegcontas.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    public List<Card> findByVisitId(Long value);

    public List<Card> findByBill(Bill bill);

    public List<Card> findByPatient(Patient patient);
}
