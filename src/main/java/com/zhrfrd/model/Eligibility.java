package com.zhrfrd.model;

public class Eligibility {
    private boolean isEligible;
    private String reason;

    public Eligibility(boolean isEligible, String reason) {
        this.isEligible = isEligible;
        this.reason = reason;
    }

    public boolean isEligible() {
        return isEligible;
    }

    public String getReason() {
        return reason;
    }
}
