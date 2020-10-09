package br.com.brenootsuka.pegcontas.service;

import br.com.brenootsuka.pegcontas.model.Activity;
import br.com.brenootsuka.pegcontas.model.request.ActivityRequest;
import br.com.brenootsuka.pegcontas.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {

        this.activityRepository = activityRepository;
    }

    public List<Activity> findAll() {

        return activityRepository.findAll();
    }

    public Activity save(ActivityRequest request) {

        Activity activity = new Activity(
                request.getTitle(),
                request.getSubtitle(),
                request.getSla()
        );

        try {
            activityRepository.save(activity);

            return activity;
        } catch (ConstraintViolationException exception) { throw exception; }
    }

    public Activity findByActivityId(Long activityId) {

        return activityRepository.findByActivityId(activityId);
    }
}
