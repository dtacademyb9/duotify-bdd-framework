@appHealthCheck
Feature: Basic features


  @smoke @positive
  Scenario: Navigate to homepage
    Given I navigate to the homepage
    Then The homepage url should be correct
    And The title should be Welcome to Duotify!

  @smoke
  Scenario: Navigate to sign up page
    Given I navigate to the homepage
    When I click on sign up link
    Then I should see Create your free account text
    And I should see sign up button


