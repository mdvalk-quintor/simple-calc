# simple-calc back-end
De back-end van de eenvoudige rekenmachine gebouwd met behulp van Java 11.

# Benodigdheden om deze applicatie te kunnen draaien.
1) docker run --name some-mysql1 -e MYSQL_ROOT_HOST=% -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 -d mysql:latest
2) docker exec -it some-mysql1 bash
3) mysql -p
4) enter the password as we configured in step 1 (my-secret-pw)
5) CREATE DATABASE db_calculator;

