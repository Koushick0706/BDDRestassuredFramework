@All
Feature: This feature is to check POST API

Scenario Outline: Check if user is able to submit POST API request and Checks The Status Code
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
     And i end my After Scenario block
     Examples:
    |Servicename|Sheetname|Jsonfile|Operation|
    |"RequisAPI_Post_User"|"Getdata"|"Createuser.json"|"Type"|
    |"Swagger_Post_Pet"|"Getdata"|"Postpet.json"|"Type"|
    |"Swagger_Post_Inventory"|"Getdata"|"SwaggerStore.json"|"Type"|