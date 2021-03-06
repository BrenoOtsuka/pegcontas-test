package br.com.brenootsuka.pegcontas.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ActivityRequest {

    @NotBlank private String title;

    @NotBlank private String subtitle;

    @NotNull  private int sla;

    public ActivityRequest() {
    }

    public ActivityRequest(
            @NotBlank String title,
            @NotBlank String subtitle,
            @NotNull int sla
    ) {
        this.title = title;
        this.subtitle = subtitle;
        this.sla = sla;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getSla() {
        return sla;
    }

    public void setSla(int sla) {
        this.sla = sla;
    }
}
