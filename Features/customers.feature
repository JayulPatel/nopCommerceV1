#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Customers

@sanity
Scenario: Add new Customer
    Given User Launch firefox browser
    When  User opens URL "http://admin-demo.nopcommerce.com/login"
    And   User enters Email as "admin@yourstore.com" and Password as "admin"
    And   Click on Login
    Then  User can view Dashboard
    When  User click on customers Menu
    And   click on customers Menu Item
    And   click on Add new button
    Then  User can view Add new customer page
    When  User enter customer info
    And   click on Save button
    Then  User can view confirmation message "The new customer has been added successfully."
    And   close browser
    
   
