package br.com.brenootsuka.pegcontas.model.request;

import br.com.brenootsuka.pegcontas.commons.enums.BillType;
import br.com.brenootsuka.pegcontas.commons.enums.SlaStatus;

public class CardRequest {

    private int daysSinceCreated;
    private SlaStatus slaStatus;
    private Long visitId;
    private int numberOfDocuments;
    private int numberOfNotReceivedDocuments;
    private int numberOfChecklistItem;
    private int numberOfDoneChecklistItem;
    private BillType billType;
    private float totalAmount;
    private int numberOfPendencies;
    private int numberOfOpenPendencies;
    private String healthInsuranceName;
    private String patientName;

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

    public String getHealthInsuranceName() {
        return healthInsuranceName;
    }

    public void setHealthInsuranceName(String healthInsuranceName) {
        this.healthInsuranceName = healthInsuranceName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
