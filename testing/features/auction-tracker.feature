#Basic Requirments
  #Record a users bid on an item
  #Get the current winning bid for an item
  #Get all the bids for an item
  #Get all the items on which a user has bid

#Business Analysis resulting in BDD test
  # Business terminology derived from - https://www.liveauctioneers.com/terminology.html
    #Auction:
      # A method of selling property in a public forum through open and competitive bidding. Also referred to as: public auction, auction sale or sale.

    #Bid:
      # A prospective buyer's indication or offer of a price he/she will pay to purchase property at auction.
      # Bids are usually in standardized increments established by the auctioneer.

    #Bid History:
      # A historical list of all the bids made on a particular auction during or after the auction.

    #Bidder Paddle Number:
      # The number issued to each person who registers at an auction.

    #Catalog or Brochure:
      # A publication advertising and describing the property(ies) available for sale at public auction, often including photographs, property descriptions and the terms and conditions of the sale.

    #Lot:
      #An item or set of items for sale in an auction, lots are normally denoted by a "lot" number.

Feature: Auction Tracker Fetures
  Scenario: Bidder can bid for an aution lot
    Given a "sothebys" auction with a catelogue list:
      | lotNumber         | description             |
      | 1                 | Sunflowers by Van Gogh  |
    And a bidder "lee" registers at the "sothebys" auction and recieves paddle 42:
    When bidding on lot 1 opens:
    And bidder "lee" bids 10.50 for lot 1 using paddle 42:
    Then if the bidder "lee" looks up lot 1 bid history the following matching bid is listed:
      | price     | highestCurrentBid  | paddle  |
      | 10.50     | true               | 42      |
    And if the bidder "lee" looks up all lots they have placed at the "sothebys" auction the following matching lot is listed:
      | lot         |
      | 1           |
