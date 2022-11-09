@signUp
Feature: Sign Up feature


  Background: Common steps for all scenarios
    Given I navigate to the homepage
    And I click on sign up link


  @smoke @positive
  Scenario: Sign up with random valid info
    When I enter valid random info to sign up
    Then I should be able to login and land on Welcome page
    And  I should be able to see the same full name that I signed up with

 @db
  Scenario: Sign up with invalid info
    When I enter invalid random info to sign up
    Then I should not be able to login


  @test @noCredentials @smoke
  Scenario: Sign up no info
    When I enter no info to sign up
    Then I should not be able to login


