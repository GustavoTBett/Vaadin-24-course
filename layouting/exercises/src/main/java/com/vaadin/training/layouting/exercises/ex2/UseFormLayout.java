package com.vaadin.training.layouting.exercises.ex2;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.Route;
import com.vaadin.training.layouting.exercises.MainLayout;

@Route(value = UseFormLayout.ROUTE, layout = MainLayout.class)
public class UseFormLayout extends VerticalLayout {
    public static final String ROUTE = "ex2";
    public static final String TITLE = "Exercise 2";

    public UseFormLayout(){
        setSizeFull();
        
        TextField nameField = new TextField();
        nameField.setWidth("100%");
        TextField lastNameField = new TextField();
        lastNameField.setWidth("100%");
        TextField emailField = new TextField();
        emailField.setWidth("100%");
        TextField phoneField = new TextField();
        Checkbox doNotCallCheck = new Checkbox("Do not call");
        HorizontalLayout phoneLayout = new HorizontalLayout(phoneField, doNotCallCheck);
        phoneLayout.setWidthFull();
        phoneLayout.expand(phoneField);
        phoneLayout.setAlignItems(Alignment.CENTER);
        PasswordField passwordField = new PasswordField();
        passwordField.setWidth("100%");
        PasswordField repeatPasswordField = new PasswordField();
        repeatPasswordField.setWidth("100%");
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(nameField, "First Name");
        formLayout.addFormItem(lastNameField, "Last Name");
        formLayout.addFormItem(emailField, "Email").getElement().setAttribute("colspan", "2");
        formLayout.addFormItem(phoneLayout, "Phone").getElement().setAttribute("colspan", "2");
        formLayout.addFormItem(passwordField, "Password");
        formLayout.getElement().appendChild(ElementFactory.createBr());
        formLayout.addFormItem(repeatPasswordField, "Repeat password");
        
//        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
        add(formLayout);
    }
}
