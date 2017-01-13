Feature: Actions on Order Details page

Scenario: Review order details

Given I would like to eat Japanese food
And next dishes are added to a cart:
|dish name    |dish price|
|Sashimi salad|        12|
|Edamame      |         4|
|Shiromi      |       9.5|
When I view order details
Then next dishes should be included to an order:
|item         |quantity|price|
|Sashimi Salad|       1|   12|
|Edamame      |       1|    4|
|Shiromi      |       1|  9.5|
And total order price should be equal to 25.5

Scenario: Change quantity of added dishes

Given I would like to eat Japanese food
And next dishes are added to a cart:
|dish name    |dish price|
|Sashimi Salad|        12|
|Edamame      |         4|
And I would like to view order details
When I change quantity of “Edamme” to “3”
Then next dishes should be included to an order:
|item         |quantity|price|
|Sashimi Salad|1       |   12|
|Edamame      |3       |    4|
And total order price should be equal to 24

Scenario: Cancel order

Given I would like to eat Japanese food
And next dishes are added to a cart:
|dish name    |dish price|
|Sashimi Salad|        12|
|Edamame      |         4|
And I would like to view order details
When I cancel an order
Then cart should be empty
And total order price should be empty