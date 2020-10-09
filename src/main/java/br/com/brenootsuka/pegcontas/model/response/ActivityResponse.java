package br.com.brenootsuka.pegcontas.model.response;

import br.com.brenootsuka.pegcontas.model.Activity;

import java.util.List;

public class ActivityResponse {

    private List<Activity> activities;

    public ActivityResponse() {
    }

    public ActivityResponse(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
