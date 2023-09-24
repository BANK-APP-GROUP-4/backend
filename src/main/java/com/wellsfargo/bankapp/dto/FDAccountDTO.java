package com.wellsfargo.bankapp.dto;

import org.springframework.stereotype.Component;

@Component
public class FDAccountDTO {
    private Long id;
    private double principalAmount;
    private int maturityPeriod;

    public FDAccountDTO() {
    }

    public FDAccountDTO(Long id, double principalAmount, int maturityPeriod) {
        this.id = id;
        this.principalAmount = principalAmount;
        this.maturityPeriod = maturityPeriod;
    }

    public Long getId() {
        return id;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public int getMaturityPeriod() {
        return maturityPeriod;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    public void setMaturityPeriod(int maturityPeriod) {
        this.maturityPeriod = maturityPeriod;
    }
}
