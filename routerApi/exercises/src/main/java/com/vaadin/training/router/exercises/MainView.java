package com.vaadin.training.router.exercises;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

/**
 *
 * @author gustavo
 */
public class MainView extends Div implements RouterLayout, BeforeEnterObserver {
    
    private final Div childWrapper = new Div();

    public MainView() {
        VerticalLayout verticalLayout = new VerticalLayout(new H1("Header"));
        if (childWrapper != null) {
            verticalLayout.add(childWrapper);
        }
        
        verticalLayout.add(new H1("Footer"));
        add(new VerticalLayout(new RouterLink("Home", HomeView.class), new RouterLink("Lottery", LotteryView.class)), 
                verticalLayout);
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        childWrapper.getElement().appendChild(content.getElement());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent bee) {
        if (VaadinSession.getCurrent().getAttribute("userLoggedIn") == null) {
            VaadinSession.getCurrent().setAttribute("intendedPath", bee.getLocation().getPath());
            bee.forwardTo(LoginView.class);
        }
    }
    
    
    
}
