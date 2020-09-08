@GetBooks_fromStore
Feature: This Feature main Purpose is Get All book data from the book store API.
##Getting all book data from bookstore
Background: Creating Extent reports

    Given i create the extent report and start a test

  Scenario Outline: Getting All Book data from the BookStore
    Given i start my before scenario block with <Servicename>and<Sheetname>
    Then I want to execute service for "serviceBaseURL" which has <Operation> operation
    When I submit the GET request as per the data in Excel Worksheet
    And I validate status code
    Then I validate mandatory tag in response from Excel Worksheet
   # And I Store the Response in the Excel

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
      | "GET_Books"      | "BookStore" | "Type"  |
      | "Get_book_by_ISBN"| "BookStore" | "Type"  |
 