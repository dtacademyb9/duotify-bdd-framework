Feature: Rest Assured code BDD demo



  Scenario: Get Specific user details

    Given the base URI is initialized and header is "Accept" "application/json"
    When I send a GET request to "/users/dtacademyb9"
    Then the status code should be 200 and "login" value in the body should be  "dtacademyb9"