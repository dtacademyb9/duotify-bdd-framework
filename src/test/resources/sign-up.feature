Feature: Sign Up feature


  @smoke @positive
  Scenario: Sign up with random valid info
    Given I navigate to the homepage
    And I click on sign up link
    When I enter valid random info to sign up
    Then I should be able to login and land on Welcome page
    And  I should be able to see the same full name that I signed up with


  Scenario: Sign up with invalid info
    Given I navigate to the homepage
    And I click on sign up link
    When I enter invalid random info to sign up
    Then I should not be able to login


  @test @noCredentials
  Scenario: Sign up no info
    Given I navigate to the homepage
    And I click on sign up link
    When I enter no info to sign up
    Then I should not be able to login