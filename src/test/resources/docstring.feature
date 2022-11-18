Feature: Dosctring demo


   @docstring
  Scenario: Example scenario for Database
    When I send the following query
          """
           select first_name,last_name,email,address,district,city,country from customer
           join address
           on customer.address_id = address.address_id
           join city
           on address.city_id  = city.city_id
           join country
           on city.country_id = country.country_id;
           """
     And I send the following json body
         """
         {"menu": {
          "id": "file",
          "value": "File",
          "popup": {
            "menuitem": [
            {"value": "New", "onclick": "CreateNewDoc()"},
            {"value": "Open", "onclick": "OpenDoc()"},
            {"value": "Close", "onclick": "CloseDoc()"}
              ]
            }
          }
          }
         """

