@large
Feature: Auditing Method Invocation

  Background: 
    Given I have PreLoaded Audit data
      | id | time   | method   | msg    |
      | 1  | 123456 | sayHello | Before |
      | 2  | 123457 | sayHello | After  |
    

  Scenario: Run The Service
    And I press "1"
    And I lunch the application
    Then Log Says "Service Executed and Audit performed"

  Scenario Outline: Query Data Base
    And I press "2"
    And I lunch the application
    And I gave primary key as "<id>"
    Then I got response as "<time>", "<msg>"

    Examples: 
      | id | time   | msg    |
      | 1  | 123456 | Before |
      | 2  | 123457 | After  |

  Scenario: Exit The Luncher
    And I press "3"
    And I lunch the application
    Then Log Says "I am closing Bye Bye"
