package br.com.guini.bpmspecification.spring.domain;

import java.math.BigDecimal;

public class PaymentRequest {

    private BigDecimal value;

    private String drawerName;

    private String drawerEmail;

    private String draweeName;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDrawerName() {
        return drawerName;
    }

    public void setDrawerName(String drawerName) {
        this.drawerName = drawerName;
    }

    public String getDrawerEmail() {
        return drawerEmail;
    }

    public void setDrawerEmail(String drawerEmail) {
        this.drawerEmail = drawerEmail;
    }

    public String getDraweeName() {
        return draweeName;
    }

    public void setDraweeName(String draweeName) {
        this.draweeName = draweeName;
    }
}
