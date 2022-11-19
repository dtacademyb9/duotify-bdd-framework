Feature: Test Parallel execution on DB 1

  Scenario: Create User UI To DB Data Mapping
    Given I navigate to the homepage
    And I click on sign up link
    When I enter the following info to sign up
      | username     | first | last   | email                 | password |
      | danny.devito | Danny | Devito | dannydevito@gmail.com | danny123 |
    Then I should be able to login and land on Welcome page
    And  I should be able to see the same full name that I signed up with
    Then I retrieve the following information from the database and verify the data mapping
      | username     | first | last   | email                 | password |
      | danny.devito | Danny | Devito | dannydevito@gmail.com | danny123 |