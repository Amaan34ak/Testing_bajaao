@Filter
Feature: Validating price by filter functionality

  Scenario Outline: Validating price by filter on search results
    Given user has launched the Bajaao website
    When user searches for an instrument
    And Range <range> is selected
    Then the price is validated successfully

  Examples: 
    | range |
    | "5000 - 10000" |