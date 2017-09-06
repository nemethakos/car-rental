## MSCI Car Rental Assignment

Requirements: **Java 1.8, maven 3.5**    
Building, running: **mvn clean package exec:java**

Commands:
* **help [command1 command2 ... commandN]** - Returns help for commands  
 available commands: <pre>help list show book bookings stress</pre>

* **list** - Returns the list of available car types for rent

* **show car-code** - Returns the detailed specification of a car type    
car codes: <pre>CCMR, CDAR, CDMR, CFMR</pre>

* **book car-code from-date to-date [country1 country2 ... countryN]** - Books a car   

    car codes: <pre>CCMR, CDAR, CDMR, CFMR</pre>   
    from-date, to-date format: <pre>yyyyMMdd</pre>      
    countries (optional): <pre>HU, DE, AT, HR</pre>     
    Example: <pre>book CCMR 20180101 20180130 hu de</pre>     
    Note: Booking for country <code>HU</code> (default) takes 1 second; if there is another country in the country list, the booking takes 10 seconds        

* **bookings** - Returns the list of all bookings

* **stress** - Performs a stress test (100 bookings)
