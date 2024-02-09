package com.vaadin.training.forms.exercises.ex2;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ProductViewer extends Composite<FormLayout> implements HasSize {

    private final Binder<Product> binder = new Binder<>();
    
    public ProductViewer(Product product) {
        final FormLayout layout = getContent();
        
//        binder.setBean(product);
        

        TextField nameField = new TextField("Name");
        nameField.setReadOnly(true);
        binder.forField(nameField).bind(Product::getName, Product::setName);
        TextField priceField = new TextField("Price");
        priceField.setReadOnly(true);
        binder.forField(priceField).withConverter(new CurrencyConverter()).bind(Product::getPrice, Product::setPrice);

        DatePicker datePicker = new DatePicker("Available");
        datePicker.setReadOnly(true);
        binder.forField(datePicker).bind(Product::getAvailable, Product::setAvailable);
        layout.add(nameField, priceField, datePicker);
        //TODO adding paragraphs to formlayout to show product info
        refresh(product);
    }

    void refresh(Product product) {
        // TODO Update the three paragraphs with values from the Product bean.
//        binder.addValueChangeListener(listener -> {
            binder.readBean(product);
//        });
    }
}
