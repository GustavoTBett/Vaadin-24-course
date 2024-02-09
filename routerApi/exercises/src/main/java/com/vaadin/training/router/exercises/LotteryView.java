package com.vaadin.training.router.exercises;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import java.util.Random;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Route(value = "lottery", layout = MainView.class)
public class LotteryView extends Composite<VerticalLayout> implements HasComponents, HasUrlParameter<Integer>, HasDynamicTitle, BeforeLeaveObserver {

    private final Div lotteryResult = new Div();
    private final TextField numberInput = new TextField();
    private Boolean mudou = Boolean.FALSE;
    
    @Override
    public void setParameter(BeforeEvent be, @OptionalParameter Integer t) {
        if (t != null) {
            validate(t);
            numberInput.setValue(String.valueOf(t));
        }
    }
    
    public void lotyeryErrorView() {
        add(new Span("Ooops, seems it's an invalid number"));
    }

    public LotteryView() {
        add(new Span("Lottery View"));

        HorizontalLayout inputBar = new HorizontalLayout();
        inputBar.add(numberInput);
        numberInput.setPlaceholder("Input your number");
        Button button = new Button("Try my luck!", e -> {
            final String value = numberInput.getValue();
            if (isNotEmpty(value)) {
                final Integer number = Integer.parseInt(value);
                validate(number);
                updateContent(number);
                mudou = true;
            }
        });
        button.setEnabled(false);
        numberInput.addValueChangeListener(e -> button.setEnabled(isNotEmpty(e.getValue())));
        inputBar.add(button);
        add(inputBar);
        add(lotteryResult);
    }

    private void updateContent(Integer number) {
        if (number == null) {
            lotteryResult.setText("");
        } else {
            final int luckyNumber = new Random().nextInt(10) + 1;
            StringBuilder builder = new StringBuilder();
            if (number.equals(luckyNumber)) {
                builder.append("Congrats, you win! ");
            } else {
                builder.append("Sorry, better luck next time. ");
            }
            builder.append("Your number is: ").append(number).append(", the lucky number is:").append(luckyNumber);

            lotteryResult.setText(builder.toString());
        }
    }

    private void validate(Integer number) {
        if (number != null) {
            if (number < 1 || number > 10) {
                throw new InvalidValueException();
            }
        }
    }

    @Override
    public String getPageTitle() {
        return "Lottery View " + numberInput.getValue();
    }

    @Override
    public void beforeLeave(BeforeLeaveEvent ble) {
        if (numberInput != null && !numberInput.isEmpty() && mudou) {
            BeforeLeaveEvent.ContinueNavigationAction action = ble.postpone();
            Dialog confDialog = new Dialog();
            Button confButton = new Button("Confirm", e -> {
               action.proceed();
               confDialog.close();
            });
            confDialog.add(new Text("Are you sure you want to leave?"), confButton);
            confDialog.open();
        }
    }

}
