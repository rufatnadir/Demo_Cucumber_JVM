Feature: Basic Search Functionality on Wikipedia

  Background: Navigate to Wikipedia and search for "Puppies"
    Given Navigate to "wikipedia_url"
    Then Type "Puppies" into the Search Bar
    And Click on the Search Button
    And Verify that "Puppies" page opened

  Scenario: Basic Search Bar Test
    Then Switch language to "Deitsch"
    And Verify that "German Puppy" page opened
    Then Close browser

  Scenario: Hide Contents Test
    And Find "Contents" panel on the page
    And Hide "Contents" panel
    Then Close browser

  Scenario: Autocomplete Search Test
    Then Type "Franc" into the Search Bar
    And Select "France" in autocomplete dropdown
    Then Verify that "France" page opened
    And Close browser



