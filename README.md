# Heroku-Selenium_TravisCI

[![Build Status](https://travis-ci.org/JavaGirish/Heroku-Selenium_TravisCI.svg?branch=master)](https://travis-ci.org/JavaGirish/Heroku-Selenium_TravisCI)


Selenium, TestNG based framework designed using POM design pattern with Page factory. <br>
Used Travis CI for continuous integration of the project repository hosted in Git <br>
Demo test scripts to read data from excel file and perform the below actions in Heroku website: <br>
Create a New Computer Name, Edit Computer Name & Delete Computer Name <br>

All three test can be run from command line using: mvn clean test or mvn test <br>
Allure Reports has been implemented in framework, custom Allure Test listener file has been added which monitors test execution <br>

Post test execution, refresh the project in eclipse, "allure-results" folder will be generated in project directory <br>
To generate allure reports, navigate to project root path in command line and execute the command: "allure serve allure-results" this will launch allure reports automatically in your browser which will be started on a jetty server <br>

To generate trends and graphs in allure for multiple test runs , execute the command in project path: "allure generate -o allure-report" this will create a new folder  
"allure-report" in the project directory using "allure-results", which will contain sub folders like history, data, export etc in it. Copy the "history" folder from "allure-report" and paste it in "allure-results" folder. After this, run the command: "allure serve allure-results" in project root path which will auto launch allure reports containing details of trends, durations, graphs etc based on previous runs.






