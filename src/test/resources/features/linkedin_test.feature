Feature: Test Linkedin Account
  Scenario: Login to Linkedin and search for "PatientPay"
    Given Navigate to Linkedin login page
    Then Login to "Linkedin" as "Rufat Nadir"
    And Perform search for "PatientPay" in search field
    Then Click on "COMPANIES" button
    And Click on "Patient Pay" search result
    And "Patient Pay" company page will open
