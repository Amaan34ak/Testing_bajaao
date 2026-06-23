@Search
Feature: Bajaao Product Search Verification

  Scenario: Verify product search functionality
    Given user is on the Bajaao main screen
    When the user searches for "electric guitar"
    Then the search results should be displayed