Feature: Navigation to sign in page after adding a product to cart

  Scenario: Check navigation from home page to products listing page through search, then on to sign in section on adding to cart
    Given I view the home page
    And I search for Summer Dresses
    And the search results page is displayed
    And I view the overlay for product listing 1 on the page
    And I add the product to cart
    When I proceed to checkout
    And the shopping cart summary page must be displayed
    And I proceed to checkout from shopping cart summary page
    Then the login page is displayed


