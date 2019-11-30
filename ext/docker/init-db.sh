#!/bin/bash

mongoimport --host=127.0.0.1 -u settlement-user -p 21405apple --authenticationDatabase admin --db settlement --collection order --type json --file /order.json --jsonArray
mongoimport --host=127.0.0.1 -u settlement-user -p 21405apple --authenticationDatabase admin --db settlement --collection billing --type json --file /billing.json --jsonArray
mongoimport --host=127.0.0.1 -u settlement-user -p 21405apple --authenticationDatabase admin --db settlement --collection merchant --type json --file /merchant.json --jsonArray
