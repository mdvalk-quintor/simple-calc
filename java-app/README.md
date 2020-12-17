# simple-calc back-end
De back-end van de eenvoudige rekenmachine gebouwd met behulp van Java 11.

#### 1. Benodigdheden om deze applicatie te kunnen draaien.
1) Java 11 SDK
2) Maven
2) MySQL server lokaal draaiende op uw machine op poort 3306. Voor alle verbindingsdetails kijk in de /resources/application.properties file
3) In de MySQL server moet de database 'db_calculator' vooraf aangemaakt zijn

Letop: indien u geen MySQL server lokaal draaiende heeft op uw computer kunt u het stappenplan in hoofdstuk 1.1 volgen om met behulp van Docker gemakkelijk een instantie van MySQL server te draaien.


##### 1.1 stappenplan om lokaal MySQL te draaien (hiervoor heeft u wel docker nodig)
1) verifieer dat u docker heeft geinstalleerd door het commando 'docker -v' uit te voeren
2) voer het volgende commando uit: docker run --name some-mysql1 -e MYSQL_ROOT_HOST=% -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 -d mysql:latest
3) voer het volgende commando uit: docker exec -it some-mysql1 bash
4) voer het volgende commando uit: mysql -p
5) voer het wachtwoord in zoals we hebben ingesteld in stap 1 (my-secret-pw)
6) voer het volgende commando uit: CREATE DATABASE db_calculator;

