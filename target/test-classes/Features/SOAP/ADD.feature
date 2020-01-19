@All
Feature: This feature is to check ADD SOAP Testing

  Scenario: Check if user is able to submit GET API request and Checks The Status Code
  Given i Execute the SOAP service
  Then  i Submit my SOAP request for "Add.xml"
  And   i check my SOAP status
  Then  i Store all my logs into File