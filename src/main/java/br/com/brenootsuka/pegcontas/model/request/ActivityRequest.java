package br.com.brenootsuka.pegcontas.model.request;

public class ActivityRequest {

    private String title;
    private String subtitle;
    private int sla;

    public ActivityRequest() {
    }

    public ActivityRequest(String title, String subtitle, int sla) {
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
