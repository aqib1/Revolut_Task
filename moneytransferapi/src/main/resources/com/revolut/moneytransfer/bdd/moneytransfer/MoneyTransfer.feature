@MoneyTransfer
Feature: Money Transfer

  Scenario: Successful money transfer
    Given Transaction request with sender account id, reciever account and amount 
    When transfer action is called
    Then amount is deducted from sender account
    And reciever will recieve amount

