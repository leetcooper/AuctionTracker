Feature: Auction Tracker Fetures
  Scenario: User can bid for an aution item
    Given a registered user:
      | username | password | firstName | lastName |
      | lee      | XXXX     | Lee       | Cooper   |
    And an open aution item:
      | id  | name                    |
      | 1   | Sunflowers by Van Gogh  |
    When user "lee" logs in with
      | username | password |
      | lee      | XXXX     |
    And bids for auction item 1 with:
      | price | currency |
      | 10.50 | USD      |
    Then when user "lee" lists all the bids for an item the result contains a bid matching:
      | price | currency  | yourBid | currentHigestBid  |
      | 10.50 | USD       | true    | true              |
