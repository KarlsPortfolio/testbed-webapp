package com.thesis.automation.selenium.factory;

import com.thesis.automation.selenium.models.Customer;

public class CustomerFactory {

    public static Customer createValidCustomer() {
        return new Customer(
                "Testare Andersson",
                "testare_andersson@test.se",
                "Sveavägen 5",
                "Stockholm",
                "11350",
                "4345324532453242",
                "345"
        );
    }

    /**
     * 🟢 MODULE 2: The Dynamic Invalidation Engine
     * Responsible strictly for taking a field keyword and surgically mutating the profile.
     */
    public static Customer createInvalidatedProfile(String missingFieldKeyword) {
        String target = missingFieldKeyword.toLowerCase();

        // 💡 DELEGATION: Harvest the baseline data cleanly from Module 1
        Customer valid = createValidCustomer();

        String name = valid.getFullName(); // Assuming your model holds full name
        String email = valid.getEmail();
        String address = valid.getAddress();
        String zip = valid.getZipCode();
        String city = valid.getCity();
        String card = valid.getCreditCard();
        String cvv = valid.getCvv();

        // The Switch strictly handles the mutation logic
        switch (target) {
            case "full-name":   name = ""; break;
            case "email":       email = ""; break;
            case "address":     address = ""; break;
            case "zip-code":    zip = ""; break;
            case "city":        city = ""; break;
            case "credit-card": card = ""; break;
            case "cvv":         cvv = ""; break;
        }

        return new Customer(name, email, address,city,zip, card, cvv);
    }

}

