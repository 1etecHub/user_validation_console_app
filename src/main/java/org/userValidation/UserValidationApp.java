package org.userValidation;

import org.userValidation.dto.request.RequestScanner;
import org.userValidation.dto.response.ValidationResponse;
import org.userValidation.service.serviceImplementation.UserValidationServiceImpl;
import org.userValidation.utils.TokenUtils;

import static org.userValidation.service.serviceImplementation.UserValidationServiceImpl.validateInputs;


public class UserValidationApp {

    public static void main(String[] args) {
        TokenUtils tokenUtils = new TokenUtils();
        UserValidationServiceImpl userValidationService = new UserValidationServiceImpl();


        RequestScanner inputScanner = new RequestScanner();
        String username = inputScanner.getUserInput("Username: ");
        String email = inputScanner.getUserInput("Email: ");
        String password = inputScanner.getUserInput("Password: ");
        String dob = inputScanner.getUserInput("Date of Birth (yyyy-MM-dd): ");

        ValidationResponse result = validateInputs(username, email, password, dob);

        if (result.isValid()) {
            String token = tokenUtils.generateToken(username, email);
            System.out.println("Token: " + token);
            boolean isVerified = tokenUtils.verifyToken(token);
            System.out.println("Verification: " + (isVerified ? "verification pass" : "verification fails"));
        } else {
            System.out.println("Validation failed:");
            System.out.println(result.getMessage());
        }
    }
}
