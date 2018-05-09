Feature: Basic Search Functionality on Wikipedia

  Scenario: Basic Search Bar Test
    Given Navigate to wikipedia home
    Then Type "Puppies" into the Search Bar
    And Click on the Search Button
    And Verify that "Puppies" page opened
    Then Close browser

  Scenario: Hide Contents Test
    Given Navigate to wikipedia home
    Then Type "Puppies" into the Search Bar
    And Click on the Search Button
    Then Verify that "Puppies" page opened
    And Find "Contents" panel on the page
    And Hide "Contents" panel
    Then Close browser

  Scenario: Autocomplete Search Test
    Given Navigate to wikipedia home
    Then Type "Puppies" into the Search Bar
    And Click on the Search Button
    And Verify that "Puppies" page opened
    Then Type "Franc" into the Search Bar
    And Select "France" in autocomplete dropdown
    Then Verify that "France" page opened
    And Close browser



