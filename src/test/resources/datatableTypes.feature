Feature: Datatable types


  Scenario:Datatable demo 1
    Given I navigate to the homepage
    When I enter valid login credentials
    Then I should see the following info

      | Annie M. G. | Schmidt  | 1911-03-20 |
      | Roald       | Dahl     | 1916-09-13 |
      | Astrid      | Lindgren | 1907-11-14 |


  Scenario:Datatable demo 2
    Given I navigate to the homepage
    When I enter valid login credentials
    Then I should see the following info as List of Maps
      | firstName   | lastName | birthDate  |
      | Annie M. G. | Schmidt  | 1911-03-20 |
      | Roald       | Dahl     | 1916-09-13 |
      | Astrid      | Lindgren | 1907-11-14 |



  Scenario:Datatable demo 3
    Given I navigate to the homepage
    When I enter valid login credentials
    Then I should see the following info as Map
      | KMSY | Louis Armstrong New Orleans International Airport |
      | KSFO | San Francisco International Airport               |
      | KSEA | Seattle–Tacoma International Airport              |
      | KJFK | John F. Kennedy International Airport             |



#  @datatable
  Scenario:Datatable demo 4
    Given I navigate to the homepage
    When I enter valid login credentials
    Then I should see the following info as Map of String as key and List as value

      | KMSY | 29.993333 |  -90.258056 |
      | KSFO | 37.618889 | -122.375000 |
      | KSEA | 47.448889 | -122.309444 |
      | KJFK | 40.639722 |  -73.778889 |
