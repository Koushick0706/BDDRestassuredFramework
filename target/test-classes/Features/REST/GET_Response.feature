@All
Feature: This feature is to check GET API

Background: Creating Extent reports

Given i create the extent report and start a test

  Scenario Outline: Check if user is able to submit GET API request and Checks The Status Code
    Given i start my before scenario block with <Servicename>and<Sheetname>
    Then I want to execute service for "serviceBaseURL" which has <Operation> operation
    When I submit the GET request as per the data in Excel Worksheet
    And I validate status code
    Then I validate mandatory tag in response from Excel Worksheet
    And I Store the Response in the Excel

  #Check if user is able to submit GET API request and Checks StatusLine
    And I validate StatusLine of the Response
    Then i Store the Status Line in Excel

  #Check if user is able to submit GET API request and Checks The Content_Type
    And I validate Content-type code
    Then i Store all my logs into File
    And i end my After Scenario block
    
    Examples:
    |Servicename|Sheetname|Operation|
    |"RequisAPI_Get_AllUser"|"Getdata"|"Type"|
    |"RequisAPI_Get_SpecificUser"|"Getdata"|"Type"|
    |"Swagger_Get_Specificpet"|"Getdata"|"Type"|
    |"Swagger_Get_Inventory"|"Getdata"|"Type"|
