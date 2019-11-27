#!/bin/sh


docker build -t dev-settlement-db:1.0.0 .

docker run --name dev-settlement-db -d -p 27017:27017 dev-settlement-db:1.0.0
