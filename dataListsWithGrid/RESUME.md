the are a fill renderers that we can use in a grid like:

grid.addColumn(new LocalDateRenderer<>(person -> person.getbirthdat()));

---------------------------------------------------------------------------------

Grid as a field

SingleSelect<Grid<Person>, Person> selected = grid.asSingleSelect();
binder.forField(selected).bind(...);

MultiSelect<Grid<Person>, Person> selected = grid.asMultiSelect();
binder.forField(selected).bind(...);

---------------------------------------------------------------------------------

Context Menu (a little menu on row click)

GridContextMenu<Person> contextMenu = new GridContextMenu<>(grid);
contextMenu.addItem("Update", e -> {...});
contextMenu.addItem("Remove", e -> {...});

---------------------------------------------------------------------------------

Grid tooltips

grid.setTooltipGenerator(person -> {
    return "AKA: " + person.getNickname();
});

grid.getColumnByKey("birthday")
    .setTooltipGenerator(person -> "Age: " + getPersonAge(person));

----------------------------------------------------------------------------------

filtering

dataProvider.setFilter(person -> person.getEmail() != null);

-------------------------------------------------------------------------------

lazy loading grid

CallbackDataProvider<Person, AgeGroup> dataProvider = DataProvider.fromFilteringCallbacks(
    query -> service.getPersons(query.getOffset(), query.getLimit(), query.getFilter().orElse(null)), 
    query -> service.countPersons(query.getOffset(), query.getLimit(), query.getFilter().orElse(null)));

ConfigurableFilterDataProvider<Person, Void, AgeGroup> filterDataProvider = dataProvider.withConfigurableFilter();

grid.setItems(filterDataProvider);

filter.addValueChangeListener(listener -> {
    filterDataProvider.setFilter(listener.getValue());
});