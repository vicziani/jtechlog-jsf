JSF használata Spring Boottal
=============================

Ez a program a JTechLog (<http://jtechlog.hu>) blog _JSF használata Spring Boottal_ posztjához készült példaprogram.
Háromrétegű Spring Boot alkalmazás, Spring Data JPA perzisztens réteggel és JSF
felülettel. A letöltést követően Mavennel, az `mvnw spring-boot:run` paranccsal futtatható. Fejlesztőeszközben a `EmployeesAppApplication`
osztály futtatásával indítható.

Docker image-be csomagolható a következő paranccsal:

```shell
mvn spring-boot:build-image
```

Ezután indítható a következő paranccsal:

```shell
docker run -d -p 8080:8080 --name employees-app employees-app:0.0.1-SNAPSHOT
```

Saját `Dockerfile` használata esetén a csomagolás:

```shell
docker build -t employees-app:0.0.1-SNAPSHOT .
```

viczian.istvan a gmail-en

