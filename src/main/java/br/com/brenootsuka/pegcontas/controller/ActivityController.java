package br.com.brenootsuka.pegcontas.controller;

import br.com.brenootsuka.pegcontas.model.Activity;
import br.com.brenootsuka.pegcontas.model.request.ActivityRequest;
import br.com.brenootsuka.pegcontas.model.response.ActivityResponse;
import br.com.brenootsuka.pegcontas.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/activity")
public class ActivityController {

    private ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    @RequestMapping("/all")
    public ResponseEntity<ActivityResponse> findAll() {

        List<Activity> activities = activityService.findAll();

        ActivityResponse response = new ActivityResponse(activities);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ActivityRequest request) {

        Activity response = activityService.save(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
