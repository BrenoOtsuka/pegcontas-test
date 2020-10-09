package br.com.brenootsuka.pegcontas.repository;

import br.com.brenootsuka.pegcontas.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    public Bill findByBillId(Long value);
}
