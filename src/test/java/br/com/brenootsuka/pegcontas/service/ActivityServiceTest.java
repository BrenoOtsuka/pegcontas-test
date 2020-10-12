package br.com.brenootsuka.pegcontas.service;

import br.com.brenootsuka.pegcontas.model.Activity;
import br.com.brenootsuka.pegcontas.model.request.ActivityRequest;
import br.com.brenootsuka.pegcontas.repository.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ActivityServiceTest {

    @Autowired
    private ActivityService service;

    @MockBean
    private ActivityRepository repository;

    @Test
    public void findAllTest() {
        when(repository.findAll()).thenReturn(
                Stream.of(
                        new Activity("OPME", "Finalizar conta", 5),
                        new Activity("Auditoria", "Limpar conta", 3),
                        new Activity("Berçario", "Organizar prontuário", 2)
                ).collect(Collectors.toList())
        );
        assertEquals(3, service.findAll().size());
    }

    @Test
    public void findByActivityIdSuccessTest() {
        long activityId = (long) 1;

        Activity activity;
        activity = new Activity("OPME", "Finalizar conta", 5);
        activity.setActivityId(activityId);

        when(repository.findByActivityId(activityId)).thenReturn(activity);

        assertEquals(activity, service.findByActivityId(activityId));
    }

    @Test
    public void findByActivityIdFailTest() {
        long activityId = (long) 1;

        Activity activity;
        activity = new Activity("OPME", "Finalizar conta", 5);
        activity.setActivityId((long) 2);

        when(repository.findByActivityId(activityId)).thenReturn(null);

        assertNull(service.findByActivityId(activityId));
    }

    @Test
    public void saveSuccessTest() {
        ActivityRequest request;
        request = new ActivityRequest("OPME", "Finalizar conta", 5);

        Activity activity = new Activity(
                request.getTitle(),
                request.getSubtitle(),
                request.getSla()
        );

        when(repository.save(any(Activity.class))).thenReturn(activity);

        assertEquals(activity, service.save(request));
    }

    @Test
    public void saveFailTest() {

        ActivityRequest request = new ActivityRequest();
        request.setTitle("");
        request.setSla(5);

        when(repository.save(any(Activity.class)))
                .thenThrow(ConstraintViolationException.class);

        try {
            service.save(request);

            fail("activityService.save(activity) does not throw Constraint Violation");
        } catch (ConstraintViolationException ex) {
            assertTrue(true);
        }
    }
}
