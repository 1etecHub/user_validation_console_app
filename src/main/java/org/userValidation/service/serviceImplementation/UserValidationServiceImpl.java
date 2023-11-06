package org.userValidation.service.serviceImplementation;

import org.userValidation.dto.response.ValidationResponse;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidationServiceImpl {
    public static ValidationResponse validateInputs(String username, String email, String password, String dob) {
        ValidationResponse result = new ValidationResponse();

        if (username.isEmpty() || username.length() < 4) {
            result.addError("Username: not empty, min 4 characters");
        }
        if (isValidEmail(email)) {
            System.out.println("true");
        } else {
            result.addError("Email: not empty, valid email address");
        }

        if (!Pattern.compile("^(?=.*[A-Z])(?=.*[!@#\\$%^&*])(?=.*\\d).{8,}$").matcher(password).matches()) {
            result.addError("Password: not empty, strong password requirements");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date currentDate = new Date();
            Date birthDate = dateFormat.parse(dob);
            if (currentDate.getYear() - birthDate.getYear() < 16) {
                result.addError("Date of Birth: not empty, should be 16 years or greater");
            }
        } catch (ParseException e) {
            result.addError("Date of Birth: invalid date format (use yyyy-MM-dd)");
        }

        return result;
    }

    public static boolean isValidEmail(String email) {
        // Define a regular expression pattern for a valid email address
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        // Check if the email matches the pattern
        return matcher.matches();
    }
}

