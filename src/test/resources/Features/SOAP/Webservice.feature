@All
Feature: This feature is to check Webservice Testing

Background: Creating Extent reports

#Given i create the extent report and start a test

  Scenario: Checking weather Webserive is working as per Expected
   Given i start my before scenario block with "Webservice_Post_ADD"and"Getdata"
    And i hit the webservice
    Then i check the response and status of the service
     And i end my After Scenario block