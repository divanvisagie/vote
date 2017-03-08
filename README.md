# Vote

[![Build Status](https://travis-ci.org/divanvisagie/vote.svg?branch=master)](https://travis-ci.org/divanvisagie/vote)

It is the intent of this application to allow for the voting of individuals with regards to some sort of rewards ceremony

## Running
```sh
./run-application
```


### Migrations

```sh
sbt flywayMigrate
```

The application will be available on port 8080 unless otherwise configured in the `docker-compose.yml`.


## Usage

### Terminology
- [Ticket](https://en.wikipedia.org/wiki/Ticket_(election))