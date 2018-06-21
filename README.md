#### _DnD Spell Tracker API_

#### By: _**Kayl Eubanks**_

## Description

_This API will help players quickly call what spells are accessible to a character of a specific class._

## API Use:

* API route is available at https://dnd-spell-organizer.herokuapp.com
* Use Postman to query the database
* Routes include:

    | HTTP Method | Route | Effect |
    | :-------------     | :------------- | :------------- |
    | POST | /spells/add | Add new spell |
    | POST | /classes/add | Add new class |
    | POST | /characters/add | Add new character |
    | GET | /classes | Get all classes |
    | GET | /characters | Get all characters |
    | GET | /spells | Get all spells |
    | GET | /spells/:spellId | Get single spell |
    | GET | /classes/:classId | Get single class |
    | GET | /characters/:characterId | Get single character |
    | POST | /classes/:classId/spells/:spellId/add | Add spell to class |
    | POST | /characters/:characterId/classes/:classId/add | Add class to character |
    | GET | /classes/:classId/spells | Get all spells by class |
    | DELETE | /classes/:classId | Delete single class |
    | DELETE | /spells/:spellId | Delete single spell |
    | DELETE | /characters/:characterId | Delete single character |
    | PUT | /classes/:classId/update | Update single class |
    | PUT | /spells/:spellId/update | Update single spell |
    | PUT | /characters/:characterId/update | Update single character |
    | GET | /classes/:classId/delete | Delete single class |
    | GET | /spells/:spellId/delete | Delete single spell |
    | GET | /characters/:characterId/delete | Delete single character |


## Setup/Installation Requirement for Developers:

* Clone repository on your local computer from https://github.com/K-Banks/dnd-organizer-api.
* If you already have Java and IntelliJ IDEA installed, then skip to "Running Application"

  #### Java Installation Instructions:
  * Navigate to http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
  * Download appropriate Java SDK
  * Install file
  * Navigate to https://www.java.com/en/
  * Download Java JRE
  * Install file
  * open Command Line/Bash/Console
  * run command '$ java -version' to confirm installation

  #### IntelliJ IDEA Installation Instructions:
  * Navigate to https://www.jetbrains.com/idea/#chooseYourEdition
  * Download the community version (available for free)
  * Install file

  #### Postman Installation Instructions
  * Navigate to https://www.getpostman.com/apps
  * Download the appropriate app for your own operating system
  * Follow instructions for installation

  #### Running Application:
  * Open IntelliJ IDEA
  * Go to File -> Open
  * Find the project folder and open from root directory of project
  * Select src -> main -> java -> App.java
  * Right click on App.java
  * Press 'Run'
  * Open Postman
  * Set the API route to 'localhost:4567'

## Specs
 * User can submit new player character with specified class
 * User can submit a new class
 * User can submit a new spell
 * User can assign spells to a class
 * User can assign a class to a player character
 * User can update spells, classes, and player character details
 * User can see all classes
 * User can see all player characters
 * User can see all spells
 * User can see all spells assigned to a specific class
 * User can details of a single spell, class, or player character
 * User can delete spells, classes, or player characters

## Known Issues

_While updating project, IDE configurations were changed and prevented effective testing. Files were copied to a new project folder and force pushed to same repository. History of commits prior to issues is available on the "dev" branch._

_No known bugs at this time._
_Please contact author at kayleubanks@gmail.com with any bugs._

## Technologies Used

 * Java
 * IntelliJ IDEA
 * Maven
 * Gradle
 * JUnit
 * Spark
 * Postman
 * SQL
 * POSTGRESQL
 * Heroku

### License

This software is licensed under the MIT license.

Copyright (c) 2018 ****_Kayl Eubanks_****