package com.vaadin.training.forms.exercises.ex1;

/**
 *
 * @author gustavo
 */
public class ProductEx1 {
    private String email;
    private String stringLength;
    private String vaadin;

    public ProductEx1(String email, String stringLength, String vaadin) {
        this.email = email;
        this.stringLength = stringLength;
        this.vaadin = vaadin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStringLength() {
        return stringLength;
    }

    public void setStringLength(String stringLength) {
        this.stringLength = stringLength;
    }

    public String getVaadin() {
        return vaadin;
    }

    public void setVaadin(String vaadin) {
        this.vaadin = vaadin;
    }
    
    
}
