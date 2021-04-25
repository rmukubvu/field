package za.co.amakosifire.field.domain.shared;

public class PhoneNumber {
    public static String getFormat(String msisdn, String countryCode) {
        if (msisdn.startsWith("0"))
            return countryCode + msisdn.substring(1);
        return msisdn;
    }
}
