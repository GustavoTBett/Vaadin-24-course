package com.vaadin.training.router.exercises;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.Route;

/**
 *
 * @author gustavo
 */
@Route("error")
public class ErrorView extends Div implements HasErrorParameter<InvalidValueException> {

    @Override
    public int setErrorParameter(BeforeEnterEvent bee, ErrorParameter<InvalidValueException> ep) {
        setText("Ooops, seems it's an invalid number");
        return 500;
    }
    
}
