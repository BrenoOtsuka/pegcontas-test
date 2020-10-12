package br.com.brenootsuka.pegcontas.service;

import br.com.brenootsuka.pegcontas.commons.enums.BillType;
import br.com.brenootsuka.pegcontas.model.Activity;
import br.com.brenootsuka.pegcontas.model.Bill;
import br.com.brenootsuka.pegcontas.model.request.ActivityRequest;
import br.com.brenootsuka.pegcontas.repository.ActivityRepository;
import br.com.brenootsuka.pegcontas.repository.BillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BillServiceTest {

    @Autowired
    private BillService service;

    @MockBean
    private BillRepository repository;

    @Test
    public void findByBillIdSuccessTest() {
        long billId = (long) 1;

        Bill bill = new Bill(
                BillType.HOSPITALAR,
                0,
                0,
                (float) 8925.55
        );
        bill.setBillId(billId);

        when(repository.findByBillId(billId)).thenReturn(bill);

        assertEquals(bill, service.findByBillId(billId));
    }

    @Test
    public void findByActivityIdFailTest() {
        long billId = (long) 1;

        Bill bill = new Bill(
                BillType.HOSPITALAR,
                0,
                0,
                (float) 8925.55
        );
        bill.setBillId((long) 2);

        when(repository.findByBillId(billId)).thenReturn(null);

        assertNull(service.findByBillId(billId));
    }

    @Test
    public void saveSuccessTest() {
        Bill bill = new Bill(
                BillType.HOSPITALAR,
                0,
                0,
                (float) 8925.55
        );

        when(repository.save(bill)).thenReturn(bill);

        assertEquals(bill, service.save(bill));
    }

    @Test
    public void saveFailTest() {

        Bill bill = new Bill();
        bill.setBillType(BillType.HOSPITALAR);
        bill.setNumberOfPendencies(0);
        bill.setNumberOfOpenPendencies(0);

        when(repository.save(bill))
                .thenThrow(ConstraintViolationException.class);

        try {
            service.save(bill);

            fail("activityService.save(activity) does not throw Constraint Violation");
        } catch (ConstraintViolationException ex) {
            assertTrue(true);
        }
    }
}
