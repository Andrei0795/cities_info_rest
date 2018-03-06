# cities_info_rest

## Description

This is a RESTful Web Service built with spring. At the moment the service is limited to accepting HTTP GET requests.

The functionality is pretty simple and straight forward. This Spring app is meant to provide information about cities such as their name, country and coordinates. To request such information, you must use an URL and put the city name in order to make the GET request. The RESTful service returns thus a JSON with the details.

I have used an SQLLite database that is being called everytime we want to display a request.

### How to run:
To run the app, just go into the main folder in your terminal and write `./gradlew bootRun`
I assume that you have gradle installed. I used homebrew to install it.

### Example:
Once you have the service running, just go to your browser and write `http://localhost:8080/city?cityName=Manchester`. You should get a JSON with the following results `{"id":3,"cityId":1,"name":"Manchester","country":"United Kingdom","population":500000,"lat":53.483959,"lon":-2.244644}`.
If you haven't entered the extension `?cityName=CITY` or you have entered a city which is not in the database, you will get something like `{"id":2,"cityId":0,"name":"City not entered or not found!","country":"","population":0,"lat":0.0,"lon":0.0}`

### TODO:
make a request using ID as well. At the moment you can only put the name of the city
display another error instead of one using the empty values of a city
