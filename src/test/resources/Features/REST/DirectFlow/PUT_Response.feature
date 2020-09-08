@All
Feature: This feature is to check PUT API

Scenario Outline: Check if user is able to submit PUT API request and Checks The Status Code
    Given i start my before scenario block with <Servicename>and<Sheetname>
    Then I want to execute service for "serviceBaseURL" which has <Operation> operation
    When i Post My Json Response file <Jsonfile>
    Then I submit the PUT request
    And I validate status code
    Then I validate mandatory tag in response from Excel Worksheet
    And I Store the Response in the Excel

#Check if user is able to submit PUT API request and Checks StatusLine
    And I validate StatusLine of the Response
    Then i Store the Status Line in Excel

#Check if user is able to submit PUT API request and Checks The Content_Type
    And I validate Content-type code
    Then i Store all my logs into File
    And  i store my response to property file
    Then i end my After Scenario block
     Examples:
    |Servicename|Sheetname|Jsonfile|Operation|
    |"RequisAPI_Put_User"|"Getdata"|"DirectFlow/updateuser.json"|"Type"|
    |"Swagger_Put_Pet"|"Getdata"|"DirectFlow/Updatepet.json"|"Type"|