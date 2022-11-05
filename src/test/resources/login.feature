Feature: Login feature

  @login
  Scenario: Login with valid credentials
    Given I navigate to the homepage
    When I enter valid login credentials
    Then I should be able to login and land on Welcome page


  Scenario: Login with invalid credentials
    Given I navigate to the homepage
    When I enter invalid login credentials
    Then I should not be able to login

  @test
  Scenario: Login with no credentials
    Given I navigate to the homepage
    When I enter no login credentials
    Then I should not be able to login