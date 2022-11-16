Feature: Scenario Outline demo

  @outline
  Scenario Outline: Demo 1
    Given  I navigate to "<page>"
    Then I click on "<productName>"
    Then The price should be <price>

    Examples:
      | page        | productName   | price |
      | homePage    | iphone 12     | 699   |
      | productPage | apple TV      | 299   |
      | productPage | samsung watch | 329   |
      | homepage    | PS 5          | 899   |
