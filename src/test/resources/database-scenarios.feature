Feature: Database scenarios



  Scenario: Verify Default albums with the database


    Given I navigate to the homepage
    When I enter valid login credentials
    Then I should be able to login and land on Welcome page
    And The albums on the welcome page should match the albums on the database table albums

    @createUser
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






  Scenario Outline: Create User UI To DB Data Mapping Verify Transaction Isolation
    Given I navigate to the homepage
    And I click on sign up link
    When I enter the following info to sign up
      | username   | first   | last   | email   | password   |
      | <username> | <first> | <last> | <email> | <password> |
    Then I should be able to login and land on Welcome page
    And  I should be able to see the same full name that I signed up with
    Then I retrieve the following information from the database and verify the data mapping
      | username   | first   | last   | email   | password   |
      | <username> | <first> | <last> | <email> | <password> |

    Examples:
      | username     | first | last   | email                 | password    |
      | danny.devito | Danny | Devito | dannydevito@gmail.com | danny123    |
      | danny.dog    | Danny | Dog    | dannydog@gmail.com    | dannydog123 |
      | peppa.pig    | Peppa | Pig    | peppapig@gmail.com    | peppa123    |
      | suzy.sheep   | Suzy  | Sheep  | suzy@gmail.com        | suzy123     |





  Scenario: Playlist Creation UI to DB Data mapping
    Given I navigate to the homepage
    When I enter login credentials as "duotechb9" and "tester"
    Then I should be able to login and land on Welcome page
    And I click on your music and create the following playlists
      | Study   |
      | Calm    |
      | Energy  |
      | Relax   |
      | Workout |
    Then The database table should contain the same playlist names for user "duotechb9"


  Scenario: Create User DB to UI Data Mapping

    Given I create a new user with the following information in the database
      | username   | first | last   | email               | password |
      | tom.cruise | Tom   | Cruise | tomcruise@gmail.com | tommy123 |
    Given I navigate to the homepage
    When I enter login credentials as "tom.cruise" and "tommy123"
    Then I should be able to login and land on Welcome page



  Scenario: Update User email UI to DB Data Mapping
    Given I navigate to the homepage
    When I enter login credentials as "duotechb9" and "tester"
    Then I should be able to login and land on Welcome page
    When I update the user email to "duotechNew@gmail.com"
    Then The user email for "duotechb9" in the database should also be updated to "duotechNew@gmail.com"


   @db_only
  Scenario: Verify database table column names

        When I send a query to retrieve column names for users table
        Then The column names should be the following
          | id         |
          | username   |
          | firstName  |
          | lastName   |
          | email      |
          | password   |
          | signUpDate |
          | profilePic |


  @db_only
  Scenario: Verify business rule related to genres

    When I send a query to retrieve genres from genres table
    Then The expected list of genres should be the following
      | rap        |
      | pop        |
      | techno     |
      | rnb        |
      | house      |
      | classical  |
      | jazz       |
      | electronic |
      | dance      |
      | reggae     |
      | reggaeton  |


  @db_only
  Scenario: Verify business rule related to user table username field
    When I send a request to retrieve all usernames
    Then The usernames column should not contain any duplicates

