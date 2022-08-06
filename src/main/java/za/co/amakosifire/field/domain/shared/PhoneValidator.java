package za.co.amakosifire.field.domain.shared;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class PhoneValidator implements Predicate<String> {

    @Override
    public boolean test(String number) {
        var isMatch = Pattern.compile("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
                .matcher(number)
                .matches();
        return isMatch && number.length() == 10;
    }
}
