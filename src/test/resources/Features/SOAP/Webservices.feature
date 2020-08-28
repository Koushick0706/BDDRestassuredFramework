@All
Feature: This feature is to check Webservice Testing

Background: Creating Extent reports

Given i create the extent report and start a test

  Scenario Outline: Check if user is able to submit API request and Checks The Status Code for SOAP Operation
    Given i start my before scenario block with <Servicename>and<Sheetname>
    Then I want to execute service for "serviceBaseURL" which has <Operation> operation
    And i Submit my SOAP request for <XML files>
    Then I submit the POST request
    And I validate status code
    And I Store the Response in the Excel
    #Check if user is able to submit GET API request and Checks StatusLine
    And I validate StatusLine of the Response
    Then i Store the Status Line in Excel

  #Check if user is able to submit GET API request and Checks The Content_Type
    And I validate Content-type code
    Then i Store all my logs into File
    Then i end my After Scenario block
    
    Examples:
    |Servicename|Sheetname|Operation|XML files|
    |"Webservice_Post_ADD"|"Getdata"|"Type"|"Add.xml"|
    |"Webservice_Post_SUBTRACT"|"Getdata"|"Type"|"Subtract.xml"|
    |"Webservice_Post_DIVIDE"|"Getdata"|"Type"|"Divide.xml"|
    |"Webservice_Post_MULTIPLY"|"Getdata"|"Type"|"Multiply.xml"|
