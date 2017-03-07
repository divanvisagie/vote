#!/bin/bash

sbt clean
sbt docker:stage
docker-compose build
docker-compose up -d
npm run barrage
docker-compose stop