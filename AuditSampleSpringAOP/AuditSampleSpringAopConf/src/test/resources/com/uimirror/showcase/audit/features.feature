@large
Feature: Auditing Method Invocation

  Background: 
    Given I have PreLoaded Audit data
      | id | time   | method   | msg    |
      | 1  | 123456 | sayHello | Before |
      | 2  | 123457 | sayHello | After  |

  Scenario: Run The Service
    And I will press "1"
    And I will press "3"
    And I lunch the application
    Then Log Says "Service Executed and Audit performed"

  Scenario Outline: Query Data Base
    And I will press "2" to query db
    And I gave primary key as "<id>"
    And I will press "3"
    And I lunch the application
    Then I got response as "<time>", "<msg>"

    Examples: 
      | id | time   | msg    |
      | 1  | 123456 | Before |
      | 2  | 123457 | After  |

  Scenario: Exit The Luncher
    And I will press "3"
    And I lunch the application
    Then Log Says "[END]- I am closing Bye bye"
