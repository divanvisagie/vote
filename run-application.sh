#!/bin/bash

sbt docker:publishLocal
docker-compose up -d