#!/usr/bin/env bash

docker run --rm --name housekeepinbgook-postgres -e POSTGRES_PASSWORD=secret -e POSTGRES_DB=housekeepingbook -p 5432:5432 postgres
