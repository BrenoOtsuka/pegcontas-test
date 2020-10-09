package br.com.brenootsuka.pegcontas.model;

import br.com.brenootsuka.pegcontas.commons.enums.BillType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @Enumerated(EnumType.STRING)
    @NotNull private BillType billType;

    @NotNull private int numberOfPendencies;
    @NotNull private int numberOfOpenPendencies;

    @NotNull private float totalAmount;

    public Bill() {
    }

    public Bill(BillType billType, int numberOfPendencies, int numberOfOpenPendencies, float totalAmount) {
        this.billType = billType;
        this.numberOfPendencies = numberOfPendencies;
        this.numberOfOpenPendencies = numberOfOpenPendencies;
        this.totalAmount = totalAmount;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
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

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
}
