package com.vaadin.training.grid.exercises.ex2;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.training.grid.exercises.MainLayout;

import java.time.LocalDate;

@Route(value = FilteringDataProvider.ROUTE, layout = MainLayout.class)
public class FilteringDataProvider extends Composite<VerticalLayout>{

	public static final String ROUTE = "ex2";
	public static final String TITLE = "Exercise 2";

	private final ListDataProvider<Product> dataProvider;

	public FilteringDataProvider() {
		final VerticalLayout layout = getContent();
		layout.setWidth("100%");

		dataProvider = DataProviderHelper.createProductDataProvider();

                DatePicker datePickerStart = new DatePicker("Start");
                DatePicker datePickerEnd = new DatePicker("End");
                Button filterButton = new Button("Filter");
                FormLayout formLayout = new FormLayout(datePickerStart, datePickerEnd, filterButton);
		// TODO create layout for DateFields
                Grid<Product> grid = new Grid(Product.class);
                grid.setItems(dataProvider);
                filterButton.addClickListener(listener -> {
                   dataProvider.setFilter(product -> filterProduct(product, datePickerStart.getValue(), datePickerEnd.getValue()));
                   dataProvider.refreshAll();
                });
		// TODO create and populate Grid
                layout.add(formLayout, grid);
	}

	private boolean filterProduct(Product product, LocalDate start, LocalDate end) {

		// TODO implement filtering logic here.
                if (start != null && end != null && (product.getAvailable().isAfter(start) || product.getAvailable().isEqual(start))  
                        && (product.getAvailable().isBefore(end) || product.getAvailable().isEqual(end))) {
                    return true;
                } else if (start != null && (product.getAvailable().isAfter(start) || product.getAvailable().isEqual(start)) && end == null) {
                    return true;
                } else if (end != null && (product.getAvailable().isBefore(end) || product.getAvailable().isEqual(end)) && start == null) {
                    return true;
                } else if (start == null && end == null) {
                    return true;
                }
		return false;
	}

}
