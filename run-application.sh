#!/bin/bash

sbt docker:stage
docker-compose up -d