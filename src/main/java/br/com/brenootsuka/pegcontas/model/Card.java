package br.com.brenootsuka.pegcontas.model;

import br.com.brenootsuka.pegcontas.commons.enums.SlaStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Enumerated(EnumType.STRING)
    private SlaStatus slaStatus;

    private Long visitId;
    private int daysSinceCreated;

    private int numberOfChecklistItem;
    private int numberOfDoneChecklistItem;

    private int numberOfDocuments;
    private int numberOfNotReceivedDocuments;

    @OneToOne
    @JoinColumn(name = "billId")
    private Bill bill;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "healthInsuranceId")
    private HealthInsurance healthInsurance;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "activityId")
    @JsonIgnore
    private Activity activity;

    public Card() {
    }

    public Card(
            SlaStatus slaStatus,
            Long visitId,
            int daysSinceCreated,
            int numberOfChecklistItem,
            int numberOfDoneChecklistItem,
            int numberOfDocuments,
            int numberOfNotReceivedDocuments
    ) {
        this.slaStatus = slaStatus;
        this.visitId = visitId;
        this.daysSinceCreated = daysSinceCreated;
        this.numberOfChecklistItem = numberOfChecklistItem;
        this.numberOfDoneChecklistItem = numberOfDoneChecklistItem;
        this.numberOfDocuments = numberOfDocuments;
        this.numberOfNotReceivedDocuments = numberOfNotReceivedDocuments;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public SlaStatus getSlaStatus() {
        return slaStatus;
    }

    public void setSlaStatus(SlaStatus slaStatus) {
        this.slaStatus = slaStatus;
    }

    public Long getVisitId() {
        return visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public int getDaysSinceCreated() {
        return daysSinceCreated;
    }

    public void setDaysSinceCreated(int daysSinceCreated) {
        this.daysSinceCreated = daysSinceCreated;
    }

    public int getNumberOfChecklistItem() {
        return numberOfChecklistItem;
    }

    public void setNumberOfChecklistItem(int numberOfChecklistItem) {
        this.numberOfChecklistItem = numberOfChecklistItem;
    }

    public int getNumberOfDoneChecklistItem() {
        return numberOfDoneChecklistItem;
    }

    public void setNumberOfDoneChecklistItem(int numberOfDoneChecklistItem) {
        this.numberOfDoneChecklistItem = numberOfDoneChecklistItem;
    }

    public int getNumberOfDocuments() {
        return numberOfDocuments;
    }

    public void setNumberOfDocuments(int numberOfDocuments) {
        this.numberOfDocuments = numberOfDocuments;
    }

    public int getNumberOfNotReceivedDocuments() {
        return numberOfNotReceivedDocuments;
    }

    public void setNumberOfNotReceivedDocuments(int numberOfNotReceivedDocuments) {
        this.numberOfNotReceivedDocuments = numberOfNotReceivedDocuments;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public HealthInsurance getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(HealthInsurance healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
