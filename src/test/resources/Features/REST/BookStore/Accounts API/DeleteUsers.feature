@DeleteUsers_fromBookStore
Feature: This Feature is Get the Userdeatils and Books from the API
##1.) First This Feature main Purpose is Get the Data of the Users
##2.) But, Mainly it Concentates of Specific User only .
##3.) Specific User in the case, we will mention the User's User ID and delete the approprirate data
## for UserId, books, what book he is holding Etc.... 

Background: Creating Extent reports

    Given i create the extent report and start a test

  Scenario Outline: Delete User data and book data using the specific UserID
    Given i start my before scenario block with <Servicename>and<Sheetname>
    Then I want to execute service for "serviceBaseURL" which has <Operation> operation
    When I submit the DELETE request as per the data in Excel Worksheet
    And I validate status code
    Then I validate mandatory tag in response from Excel Worksheet
    And I Store the Response in the Excel

    #Check if user is able to submit GET API request and Checks StatusLine
    And I validate StatusLine of the Response
    Then i Store the Status Line in Excel

    #Check if user is able to submit GET API request and Checks The Content_Type
    And I validate Content-type code
    Then i Store all my logs into File
    And  i store my response to property file
    Then i end my After Scenario block

    Examples:
      | Servicename     | Sheetname | Operation |
      | "Delete_user"      | "BookStore" | "Type"  |
 