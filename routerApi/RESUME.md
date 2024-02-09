setSpacing - means the space between the components in the layout

------------------------------------------------------------------

All css can be set in layout like FlexLayout, VerticalLayout and HorizontalLayout

-----------------------------------------------------------------

FormLayout can have added components in two ways:

FormLayout formLayout = new FormLayout();

formLayout.add(test); or formLayout.addFormItem(test, "Test");

the first way the label will be on top of the component, the second will be at the left of the component

To add a lineBreak in form layout:

formLayout.getElement.appendChild(ElementFactory.createBr());