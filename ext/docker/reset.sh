#!/bin/sh

docker container stop dev-settlement-db

docker rm dev-settlement-db

docker rmi dev-settlement-db:1.0.0
