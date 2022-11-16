Feature: My Playlists

  @myMusic
  Scenario: Verify Add to playlist
    Given I navigate to the homepage
    When I enter valid login credentials
    Then I should be able to login and land on Welcome page
    And I click on your music and create a playlist named "Study"
    And I add the following songs from the albums to my playlist
      | Artist           | Album     | Song      |
      | Nicky Jam        | Fenix     | El Amante |
      | Enrique Iglesias | Escape    | Hero      |
      | Enrique Iglesias | Escape    | Maybe     |
      | Maya Jane Coles  | Werk      | Whirls    |
      | Disclosure       | Ultimatum | Ultimatum |
    Then My playlist should contain the same songs
    And I delete the playlist
