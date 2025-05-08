Feature: SearchFeature This feature should find "Oracle" as one of result Google searching "Java"

  Scenario: Find out results of Google searching "Java"
    Given I navigate to Google search page
    When I input "Java" to the search textfield
    And I click on search button
    And I collected search results for finding "Oracle"
    Then I find "Oracle"


  Scenario: The result doesn't contain unexpected word
    Given I navigate to Google search page
    When I input "Java" to the search textfield
    And I click on search button
    And I collected search results for finding "Oracle"
    Then I didn't find "ewuyewuirywuie"