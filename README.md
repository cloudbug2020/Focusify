<h1 align="center">Focusify</h1>

## :tada: Project

This project should be a reference implementation using Clean Architecture principles. 
The web application is provided by Java Quarkus Framework.

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=cloudbug2020_focusify&metric=coverage)](https://sonarcloud.io/dashboard?id=cloudbug2020_focusify)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=cloudbug2020_focusify&metric=alert_status)](https://sonarcloud.io/dashboard?id=cloudbug2020_focusify)

## Tools

- [Maven](https://www.maven.apache.org)
- [Locust](https://www.locust.io)
- [Postman](https://www.postman.com)
- [Docker](https://www.docker.com)
- [Keycloak](https://www.keycloak.org)

## :construction: Future Updates

- [x] add users
    - [x] user use cases
    - [ ] new datasource "users"
    - [x] user api
- [ ] add auth
    - [x] secured REST resources
    - [x] combine with user resource and datasource
    - [x] use OAuth2

## :wrench: Infrastructure

### Environment Variables

| Variable | Description | Default Value |
| :---: | :---: | :---: |
| **QUARKUS_POSTGRES_DATABASE_URL** | JDBC-Connection-String to postgresql datasource | jdbc:postgresql://localhost:5432/hibernate_db |
| **DATABASE_USERNAME** | username of user to use for provided datasource | hibernate |
| **DATABASE_PASSWORD** | password of user to use for provided datasource | hibernate |
| **AUTH_SERVER_URL** | URL to keycloak auth server | http://localhost:8180/auth/realms/focusify |
| **CLIENT_ID** | keycloak-configured client identifier | focusify-backend |

### Docker-Compose

If you haven't installed **Docker** yet, then follow this guide:
https://docs.docker.com/get-docker/

> /infrastructure/docker/docker-compose.yml

### Keycloak
The integrationtests are using KeycloakTestResourceLifecycleManager to start internal
keycloak docker container, which means no special configuration needed.  
To configure your own keycloak realm please consult [keycloak documentation](https://www.keycloak.org/getting-started/getting-started-docker)

### Loadtests

If you haven't installed **Locust** yet, then follow this guide:
https://docs.locust.io/en/stable/installation.html

These loadtests can be run after installing locust through the cli as

```shell script
locust --host=http://<deployed_host>:<port>
```

Then url http://localhost:8089/ should be access to start the test.

Can also be run using no UI mode as

```shell script
locust --no-web -c <number_of_clients> -r <clients_per_second> --run-time <time e.g. 1h30m> --host=http://<deployed_host>:<port>
```

### Integrationtests

If you haven't installed **Postman** yet, then follow this guide:
https://www.postman.com/downloads/

You can find the Postman-Collection here:
> integrationtest/TodoResource.postman_collection.json

The Postman-Collection also includes test scripts with assertions. You can use
[Newman](https://github.com/postmanlabs/newman) to execute them via CLI.

## :rotating_light: Developer Responsibilities

- Code within a feature branch starting from latest develop
- Whenever is ready, create a Pull Request against develop
- Make sure your branch passes all unit tests and every piece of code you introduced contains its
  own unit test
- Make sure integration tests still work. Introduce some if needed.
- Make sure the technial debt in Sonar is the same or better when your code is merged.

## :green_heart: Quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

### Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory. Be aware that it’s
not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

### Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container
using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/focusify-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please
consult https://quarkus.io/guides/maven-tooling.html.