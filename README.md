## Hotel_Reservation_System
Learn full stack development using Spring-boot + JPA + angularJS2 + bootstrap3 + Junit. 

### Project Dynamics - 
1. Get the room available for booking during particular period.
2. Book the room for that period.

### FrontEnd/BackEnd Location - 
1. Front-part: Whole frontend part is under `webapp` folder.
2. Spring-boot(Back-end): whole repo is bank-end except `webapp`.

### Technical Details/ Key Points -
1. DB used - H2 DB (Spring-boot in-memory)
2. JPA - Lib for persisting the data into DB
3. Jackson ObjectMapper/ObjectWriter - Converting object into JSON and from Json to Object (ApiConfig.java)
4. Core Convertors - Converting one object to another using core lib of java.(ConversionConfig.java)
5. Junit/RestAssured - Testing the applicaiton before building the application.

#### Set-up for Front-End- 
1. Install npm
2. Run following command -
  - npm install -g angular/cli
  - npm install bootstrap@3
  - npm install fuelfx
  - Under webapp/angular2 copy and paste ngmodules(~180MB) from internet.

#### Set-up for back-End- 
1. Nothing is required. Just Clone the repo and run `mvn clean install`.
