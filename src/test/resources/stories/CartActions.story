Feature: Add and remove dishes from a cart

Scenario: Add several different dishes to a cart

Given I would like to eat Japanese food
When I add next dishes to a cart:
|dish name    |dish price|
|Sashimi Salad|        12|
|Edamame      |         4|
|Shiromi      |       9.5|
Then shopping cart should contain 3 dishes
And total cart price should be equal to 25.5

Scenario: Add several same dishes to a cart

Given I would like to eat Japanese food
When I add next dishes to a cart:
|dish name    |dish price|
|Sashimi Salad|        12|
|Sashimi Salad|        12|
Then shopping cart should contain 1 dishes
And total cart price should be equal to 24

Scenario: Empty cart
Given I would like to eat Japanese food
And I add 1 dish to a cart
When I would like to empty cart
Then cart should be empty