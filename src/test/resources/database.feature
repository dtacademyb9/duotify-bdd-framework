Feature: Example database scenarios


  @db
  Scenario: Verify Default albums with the database


    Given I navigate to the homepage
    When I enter valid login credentials
    Then I should be able to login and land on Welcome page
    And The albums on the welcome page should match the albums on the database table albums
