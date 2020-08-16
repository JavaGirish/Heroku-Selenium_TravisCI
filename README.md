# Heroku-Selenium_TravisCI

[![Build Status](https://travis-ci.org/JavaGirish/Heroku-Selenium_TravisCI.svg?branch=master)](https://travis-ci.org/JavaGirish/Heroku-Selenium_TravisCI)


Selenium, TestNG based framework designed using POM design pattern with Page factory. <br>
Used Travis CI for continuous integration of the project repository hosted in Git <br>
Demo test scripts to read data from excel file and perform the below actions in Heroku website: <br>
Create a New Computer Name, Edit Computer Name & Delete Computer Name <br>
All three test can be run from command line using: mvn clean test or mvn test <br>
Allure Reports has been implemented in framework, custom Allure Test listener file has been added which monitors test execution.<br>
Post test completion, refresh project allure-results folder will be generated in project directory <br>
To generate reports, navigate to project folder path in command line and execute command: allure serve allure-results or you can run the batch job generatereports available in projects.

