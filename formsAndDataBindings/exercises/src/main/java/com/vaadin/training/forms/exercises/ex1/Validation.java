package com.vaadin.training.forms.exercises.ex1;

import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.training.forms.exercises.MainLayout;

import java.io.Serial;
import org.hibernate.validator.internal.constraintvalidators.hv.LengthValidator;

@Route(value = Validation.ROUTE, layout = MainLayout.class)
@RouteAlias(value="", layout = MainLayout.class)
public class Validation extends VerticalLayout implements HasSize{

	@Serial
	private static final long serialVersionUID = 1L;

	public static final String ROUTE = "ex1";
	public static final String TITLE = "Validation";

	public Validation() {
                Binder<ProductEx1> binder = new Binder<>();
		// TODO Create a bean class to contain the data with fields, getters and
		// setters for each value needed to bind the three TextFields.

		// TODO Create a Binder typed for the bean class you just created

		// TODO Bind field and add validation to check that the input is a 
		// proper mail address.
		final TextField emailField = new TextField("Email validator"); 
                binder.forField(emailField).withValidator(new EmailValidator("Not a valid email"))
                        .bind(ProductEx1::getEmail, ProductEx1::setEmail);

		// TODO Bind field and add validation which accepts strings between 1
		// and 10 in length
		final TextField stringField = new TextField("String length validator");
                binder.forField(stringField).withValidator(new StringLengthValidator("The value must be between 1 character up to 10 characters", 1 , 10))
                        .bind(ProductEx1::getStringLength, ProductEx1::setStringLength);
                
		// TODO Bind field and add a custom Validator which only accepts
		// "Vaadin"
		final TextField vaadinField = new TextField("Vaadin validator");
                binder.forField(vaadinField).withValidator(x -> x.equals("Vaadin"), "The value must be 'Vaadin'")
                        .bind(ProductEx1::getVaadin, ProductEx1::setVaadin);

		add(emailField, stringField, vaadinField);

	}

}