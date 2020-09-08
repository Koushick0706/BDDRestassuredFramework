@CreateAccount_Authorize
Feature: This Feature is to Create User and Generate Token and Authorize the User
##1.)First this feature will create the User
##2.)Once The User is Created, we will try to Generate the Token
##3.)To Generate Token, We will hit the Generate Token API and get the Token ID
##4.) Finally, Once the Token is Create we will Authorize the User

Background: Start the Report for Book Store
## This will create testcase in my Extentreport to Proceed with extent report execution
Given i create the extent report and start a test

Scenario Outline: This Scenario will contuinosly used to create,Authorize,Generate Token for the users
    Given i start my before scenario block with <Servicename>and<Sheetname>
    Then I want to execute service for "serviceBaseURL" which has <Operation> operation
    When i Post My Json Response file <Jsonfile>
    Then I submit the POST request 
    And I validate status code
    Then I validate mandatory tag in response from Excel Worksheet
    And I Store the Response in the Excel

  #Check if user is able to submit POST API request and Checks StatusLine
    And I validate StatusLine of the Response
    Then i Store the Status Line in Excel

  #Check if user is able to submit POST API request and Checks The Content_Type
    And I validate Content-type code
    Then i Store all my logs into File
     And  i store my response to property file
     Then i end my After Scenario block
     Examples:
    |Servicename|Sheetname|Jsonfile|Operation|
    |"Create_User"|"BookStore"|"BookStore/CreateUser.json"|"Type"|
    |"GenerateToken_User"|"BookStore"|"BookStore/GenerateToken.json"|"Type"|
    |"Authorize_User"|"BookStore"|"BookStore/AuthorizeUser.json"|"Type"|
