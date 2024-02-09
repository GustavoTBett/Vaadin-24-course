package com.vaadin.training.layouting.exercises.ex1;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.training.layouting.exercises.MainLayout;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Route(value = ApplicationLayout.ROUTE, layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class ApplicationLayout extends VerticalLayout {

	@Serial
	private static final long serialVersionUID = 1L;

	public static final String ROUTE = "ex1";
	public static final String TITLE = "Exercise 1";
        private final Div content = new Div();

	public ApplicationLayout() {
		setSizeFull();
		setPadding(false);
		setSpacing(false);

		final Div header = new Div();
		header.setText("This is the header. My height is 150 pixels");
		header.setClassName("header");
                header.setSizeFull();
                header.setHeight("150px");

                List<Div> divs = new ArrayList<>();
		final Div navigation = new Div();
		navigation.setClassName("navigation");
                Button btn = new Button("Add");
                btn.addClickListener(listener -> {
                    content.add(createBlock());
                });
		navigation.add(btn);
                navigation.setWidth("25%");
		
		content.setClassName("content");
		content.getStyle().set("display", "flex");
                content.getStyle().set("flex-direction", "row");
                content.getStyle().set("flex-wrap", "wrap");
                content.getStyle().set("flex-direction", "row");
                content.getStyle().set("align-content", "flex-start");
                content.getStyle().set("overflowY", "auto");
                content.setHeightFull();
                content.setWidth("75%");

		final Div footer = new Div();
		footer.setText("This is the footer area. My height is 100 pixels");
		footer.setClassName("footer");
                footer.setHeight("100px");
                footer.setWidth("100%");
                
                HorizontalLayout flexLayout = new HorizontalLayout(navigation, content);
                flexLayout.setWidth("100%");
                flexLayout.setFlexGrow(1, flexLayout);
                flexLayout.setHeight("100px");
                flexLayout.setSpacing(false);

		add(header, flexLayout, footer);
	}

	/**
	 * Ignore this method for now.
	 *
	 * @return
	 */
	private Div createBlock() {
		final Div button = new Div();
		button.setText("Block");
		button.getStyle().set("background", "white");
		button.setHeight("100px");
		button.setWidth("100px");
		button.getStyle().set("margin", "2px");
		return button;
	}

}