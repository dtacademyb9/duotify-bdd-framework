Feature: Browse Music feature

  @temp @smoke
  Scenario: Verify song duration

    Given I navigate to the homepage
    When I enter valid login credentials
    Then I should be able to login and land on Welcome page
    And I click on "Werk" album
    # String parameter example
    And The song duration should be 2 minutes 56 seconds
    # int parameter example
    And The price of the song should be 1.03
    # double parameter example

   @albums
   Scenario: Verify default albums
     Given I navigate to the homepage
     When I enter valid login credentials
     Then I should be able to login and land on Welcome page
     And I should see the following albums
       | Escape              |
       | Fenix               |
       | Ultimatum           |
       | Oscillation         |
       | Cruel Summer        |
       | Werk                |
       | Clouds              |
       | Marisa              |
       | I Am...Sasha Fierce |
