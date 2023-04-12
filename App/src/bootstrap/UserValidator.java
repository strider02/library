package bootstrap;

import java.util.HashMap;
import java.util.Map;

public class UserValidator {
    private static final HashMap<String, String> rules = new HashMap<>(
            Map.of(
                    "firstname", "required",
                    "lastname", "required",
                    "email", "required|email",
                    "username", "required",
                    "password", "required",
                    "passwordRepeat", "required|equals:password"
            )
    );


    public static Map<String, String> validate(Map<String, String> request) {

        Map<String, String> errors = new HashMap<>();

        UserValidator.rules.forEach((field, rule) -> {
            boolean result = false;

            // converting rule to array
            String[] rules = null;

            if (rule.contains("|")) {
                rules = rule.split("\\|");
            } else {
                rules = new String[]{rule};
            }

            for (String item : rules) {

                String[] ruleParts = item.split(":");
                String ruleName = ruleParts[0];
                String ruleValue = (ruleParts.length > 1) ? ruleParts[1] : "";

                switch (ruleName) {
                    case "required":
                        if (!request.containsKey(field) | request.get(field).isEmpty()) {
                            errors.put(field, "Field is required.");
                        }
                        break;
                    case "equals":
                        if (!request.containsKey(field) | !request.containsKey(ruleValue))
                            break;

                        String fieldValue = request.get(field);
                        String compareValue = request.get(ruleValue);
                        if (!fieldValue.equals(compareValue)) {
                            errors.put(field, "Not a match.");
                        }
                        break;
                    case "email":
                        if (request.containsKey(field)) {
                            String email = request.get(field);
                            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                                errors.put(field, "The " + field + " field must be a valid email address.");
                            }
                        }
                        break;
                }
            }


        });


        return errors;
    }

}
