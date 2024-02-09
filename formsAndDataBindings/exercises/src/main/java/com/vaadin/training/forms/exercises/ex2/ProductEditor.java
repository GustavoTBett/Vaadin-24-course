package com.vaadin.training.forms.exercises.ex2;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToDoubleConverter;

import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductEditor extends Composite<VerticalLayout> implements HasComponents, HasSize{
    public ProductEditor(Product product, Consumer<Product> productConsumer){
        // TODO Create a new class that extends a layout for editing the product
        Binder<Product> binder = new Binder<>(Product.class);
        
        
        TextField nameField = new TextField("Name");
        binder.forField(nameField).bind(Product::getName, Product::setName);

        // TODO Create a Binder and bind it together with the input fields
        // on the editor component you created. Note that after the bindings
        // have been defined, you should have the binder read the Product bean
        // given as a parameter.

        TextField priceField = new TextField("Price");
        binder.forField(priceField).withConverter(new CurrencyConverter()).bind(Product::getPrice, Product::setPrice);
        
        DatePicker datePicker = new DatePicker("Available");
        binder.forField(datePicker).bind(Product::getAvailable, Product::setAvailable);
        // TODO Create a Save button which will write the values from the binder
        // to the Product bean. A successful save should also refresh the
        // read-only view
        
        binder.readBean(product);

        Button saveButton = new Button("Save", click -> {
            try {
                binder.writeBean(product);
                productConsumer.accept(product);
            } catch (ValidationException ex) {
                Logger.getLogger(ProductEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        // TODO Create a Cancel button which will read the values from the
        // Product bean to the binder
        
        Button cancelButton = new Button("Cancel", click -> {
            binder.readBean(product);
        });
        
        add(nameField, priceField, datePicker, new HorizontalLayout(saveButton, cancelButton));
    }
}
