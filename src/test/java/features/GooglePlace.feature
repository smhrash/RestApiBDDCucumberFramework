Feature: Validation Place API
  Scenario: Verify that Place is successfully added
    Given User add Place by adding payload
    When user calls "addPlaceAPI" with "Post" Request
    Then  Api calls got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
