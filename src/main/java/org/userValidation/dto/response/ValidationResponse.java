package org.userValidation.dto.response;

public class ValidationResponse {
    private StringBuilder message = new StringBuilder();

    public void addError(String error) {
        if (message.length() > 0) {
            message.append(", ");
        }
        message.append(error);
    }

    public boolean isValid() {
        return message.length() == 0;
    }

    public String getMessage() {
        return message.toString();
    }
}
