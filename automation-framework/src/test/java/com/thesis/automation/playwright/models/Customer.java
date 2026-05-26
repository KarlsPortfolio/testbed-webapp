package com.thesis.automation.playwright.models;

public class Customer {
        private final String fullName;
        private final String address;
        private final String zipCode;
        private final String city;
        private String creditCard;
        private String cvv;

        public Customer(String fullName, String email, String address, String city, String zipCode, String creditCard, String cvv) {
            this.fullName = fullName;
            this.address = address;
            this.zipCode = zipCode;
            this.city = city;
            this.creditCard = creditCard;
            this.cvv = cvv;
        }

        // Getters
        public String getFullName() {
            return fullName;
        }

        public String getAddress() {
            return address;
        }

        public String getZipCode() {
            return zipCode;
        }

        public String getCity() {
            return city;
        }

        public String getCreditCard() {
            return creditCard;
        }

        public String getCvv() {
            return cvv;
        }

}
