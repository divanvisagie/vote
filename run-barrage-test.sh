#!/bin/bash

sbt docker:publishLocal
docker-compose up -d
npm run barrage
# docker-compose stop