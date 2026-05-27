package com.thesis.automation.selenium.factory;

import com.thesis.automation.selenium.models.Customer;

public class CustomerFactory {

    public static Customer getProfile(String profileType) {
        switch (profileType.toLowerCase()) {
            case "validcustomer":
                return new Customer(
                        "Testare Andersson",
                        "testare_andersson@test.se",
                        "Sveavägen 5",
                        "Stockholm",
                        "11350",
                        "4345324532453242",
                        "345"
                );
            case "invalidcustomer":
                return new Customer(
                        "",
                        "testare_",
                        "Sveavägen",
                        "St",
                        "113",
                        "43453245324",
                        "34"
                );
            default:
                throw new IllegalArgumentException("❌ Test Data Error: Profile type '" + profileType + "' is not defined.");
        }
    }
}

