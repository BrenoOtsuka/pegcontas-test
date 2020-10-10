package br.com.brenootsuka.pegcontas.model.request;

import br.com.brenootsuka.pegcontas.commons.enums.BillType;
import br.com.brenootsuka.pegcontas.commons.enums.SlaStatus;
import br.com.brenootsuka.pegcontas.model.Bill;
import br.com.brenootsuka.pegcontas.model.Card;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CardRequest {

    @NotNull private Long activityId;
    @NotNull private int daysSinceCreated;
    @NotNull private SlaStatus slaStatus;
    @NotNull private Long visitId;
    @NotNull private int numberOfDocuments;
    @NotNull private int numberOfNotReceivedDocuments;
    @NotNull private int numberOfChecklistItem;
    @NotNull private int numberOfDoneChecklistItem;
    @NotNull private BillType billType;
    @NotNull private float totalAmount;
    @NotNull private int numberOfPendencies;
    @NotNull private int numberOfOpenPendencies;
    @NotBlank private String healthInsurance;
    @NotBlank private String patient;

    public Card getCard() {
        return new Card(
                this.slaStatus,
                this.visitId,
                this.daysSinceCreated,
                this.numberOfChecklistItem,
                this.numberOfDoneChecklistItem,
                this.numberOfDocuments,
                this.numberOfNotReceivedDocuments
        );
    }

    public Bill getBill() {
        return new Bill(
                this.billType,
                this.numberOfPendencies,
                this.numberOfOpenPendencies,
                this.totalAmount
        );
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public int getDaysSinceCreated() {
        return daysSinceCreated;
    }

    public void setDaysSinceCreated(int daysSinceCreated) {

        this.daysSinceCreated = daysSinceCreated;
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

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getNumberOfPendencies() {
        return numberOfPendencies;
    }

    public void setNumberOfPendencies(int numberOfPendencies) {

        this.numberOfPendencies = numberOfPendencies;
    }

    public int getNumberOfOpenPendencies() {
        return numberOfOpenPendencies;
    }

    public void setNumberOfOpenPendencies(int numberOfOpenPendencies) {
        this.numberOfOpenPendencies = numberOfOpenPendencies;
    }

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsuranceName(String healthInsurance) {

        this.healthInsurance = healthInsurance;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatientName(String patient) {
        this.patient = patient;
    }
}
