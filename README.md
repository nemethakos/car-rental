## MSCI Car Rental Assignment

Requirements: **Java 1.8, maven 3.5**

Building only: **mvn clean package**      
Starting the Server: **mvn exec:java@server (or startserver.bat)**       
Start the client: **mvn exec:java@client (or startclient.bat)**        

Steps to run:     
1. Start the server
2. Start the client (multiple clients can be started on the same machine)

Type of results from the server:    
1. Soap Fault: on invalid Soap requests. From the client it is possible to create soap faults with invalid dates (start date is in the past, start date is after end date)
2. Unsuccessful booking: when there is no bookable car for the desired dates for the desired countries
3. Successful booking

Commands:
* **help [command1 command2 ... commandN]** - Returns help for commands  
 available commands: <pre>help list show book bookings stress</pre>

* **list** - Returns the list of available car instances for rent

* **show car-code** - Returns the detailed specification of a car type    
car codes: <pre>M, C, E</pre>

* **book car-code from-date to-date [country1 country2 ... countryN]** - Books a car   

    car codes: <pre>M, C, E</pre>   
    from-date, to-date format: <pre>yyyyMMdd</pre>      
    countries (optional): <pre>HU, DE, AT, HR</pre>     
    Example: <pre>book CCMR 20180101 20180130 hu de</pre>     
    Note: Booking for country <code>HU</code> (default) takes 1 second; if there is another country in the country list, the booking takes 10 seconds        

* **bookings** - Returns the list of all bookings

* **stress** - Performs a stress test with 100 bookings. The test includes generating invalid dates to create soap faults.
