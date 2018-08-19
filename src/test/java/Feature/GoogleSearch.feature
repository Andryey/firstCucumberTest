Feature: SearchFeature
  This feature should find "Computer programming language" as one of result Google searching "Java"
  Scenario: Find out results of Google searching "Java"
    Given I navigate to Google search page
    When I input "Java" to the search textfield
    And I click on search button
    And I collected search results for finding "Computer programming language"
    Then I find "Computer programming language"