Value change event - All methods that hasValue method has a method in addValueChangeListener to know if the change in the value is from
the client or not;

binder.forField(name).bind(person -> person.getTitle(),
                (person, newTitle) -> person.setTitle(newTitle));

for read and write in bean reaspectavaly:

allMethods first then:
binder.readBean(person);

try {
    binder.writeBean(person);
} catch (ValidationException err) {
    throw err;
}

for binder read only:

binder.forField(name).bindReadOnly(Person::getName);
or
binder.forField(name).bind(Person::getName, null);

------------------------------------------------------------------------------------

public class MyValidator implements Validator<String> {
    
    @Override
    public ValidationResult apply(String value, ValueContext context) {
        if (value == null || value.length() < 3) {
            return ValidationResult.error("String is too short");
        } else {
            return ValidationResult.ok();
        }
    }
}

-----------------------------------------------------------------------------------

public class MyStringToDoubleConverter implements Converter<String, Double> {

    @Override
    public String convertToPresentation(Double value, ValueContext context) {
        return String.format(context.getLocale().get(), "%1$.2f", value);
    }

    @Override
    public Result<Double> convertToModel(String value, ValueContext context) {
        try {
            return Result.ok(Double.parseDouble(value));
        } catch (NumberFormatException ex) {
            return Result.error(ex.getMessage());
        }
    }
}
            