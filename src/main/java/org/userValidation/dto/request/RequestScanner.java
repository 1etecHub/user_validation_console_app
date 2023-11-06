package org.userValidation.dto.request;

import java.util.Scanner;


public class RequestScanner {
    private Scanner scanner;

    public RequestScanner() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }


}
