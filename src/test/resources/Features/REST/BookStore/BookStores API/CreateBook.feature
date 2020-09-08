@CreateBook_UserID
Feature: This Feature is to Create Books with Specific UserID

Background: Start the Report for Book Store
## This will create testcase in my Extentreport to Proceed with extent report execution
Given i create the extent report and start a test

Scenario Outline: This Scenario will contuinosly used to create Books for UserID
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
    |"Create_Books_UserID"|"BookStore"|"BookStore/CreateBook.json"|"Type"|
