package br.com.brenootsuka.pegcontas.service;

import br.com.brenootsuka.pegcontas.model.Activity;
import br.com.brenootsuka.pegcontas.model.Bill;
import br.com.brenootsuka.pegcontas.repository.BillRepository;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill save(Bill bill) {

        return billRepository.save(bill);
    }

    public Bill findByBillId(Long value) {
        return billRepository.findByBillId(value);
    }
}
