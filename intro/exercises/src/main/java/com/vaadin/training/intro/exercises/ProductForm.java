package com.vaadin.training.intro.exercises;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author gustavo
 */
public class ProductForm extends VerticalLayout {

    public ProductForm() {
        TextField nameField = new TextField("Name");
        
        TextArea descriptionField = new TextArea("Description");
        
        NumberField priceField = new NumberField("Price");
        Span suffixComponent = new Span("€");
        priceField.setSuffixComponent(suffixComponent);
        priceField.setStep(0.01);
        
        DatePicker availableField = new DatePicker("Available");
        
        String[] categoryItens = {"A", "B", "C"};
        ComboBox<String> categoryField = new ComboBox<>("Category");
        categoryField.setItems(categoryItens);
        
        Button saveButton = new Button("Save");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button cancelButton = new Button("Cancel");
        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, cancelButton);
        
        add(nameField, descriptionField, priceField, availableField, categoryField, buttonsLayout);
    }
    
}
