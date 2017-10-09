Feature: Basic Search Functionality on Wikipedia

#  Target: https://www.wikipedia.org/
#  Test 1: Basic Search Bar Test
#  Step 1 - Go to https://www.wikipedia.org/
#  Step 2 - Type “Puppies” into the Search Bar
#  Step 3 - Click on the Search Button
#  Assert - You are on the wiki page for Puppies
#
#  Test 2 - Hide Contents Test
#  Step 1 - On the Puppies page, find the “Contents” panel.
#  Step 2 - Click the button to hide the panel
#  Assert - The Panel is hidden
#
#  Test 3 - Autocomplete Search Test
#  Step 1 - On the Puppies page, find the Search bar.
#  Step 2 - Type “Franc” into the Search bar.
#  Step 3 - The autocomplete should drop down, Click on “France”
#  Assert - You are taken to the page for France

  Scenario: Basic Search Bar Test
    Given Navigate to "wikipedia_url"
    Then Type “Puppies” into the Search Bar
    And Click on the Search Button
    Then Verify that "Puppies" page opened

  Scenario: Hide Contents Test
    Given Find “Contents” panel on the page
    Then Click on the


