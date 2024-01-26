# Challenge

## :computer: How to execute
1) run `docker compose up`
2) run `curl --location --request POST 'localhost:8081/pokemons/crawl'` to get pokemons from datasource
3) run one of the following APIs to retrieve information you want:

`
-> curl --location 'localhost:8081/pokemons/heaviest?size=<SIZE>'
`

`
-> curl --location 'localhost:8081/pokemons/highest?size=<SIZE>'
`

`
-> curl --location 'localhost:8081/pokemons/most-base-experience?size=<SIZE>' 
`

## :memo: Notes

* There are 4 layers, `controller`, `service`, `repository` and `storage`.
* Other packages are `api` for connecting to `PokeApi` and `exception` for handling exceptions.
* I used `H2` database in stage `test` for simplicity, but it can be changed to any other local databases or in containers.
* I used `lombok` for generating some codes, `flyway` for database migration.
* 

## :pushpin: Things to improve

* Write more tests to increase the coverage.
* Handle non-primitive types in entities in order to be more type-safe.
* Add a queue (Kafka, RabbitMQ, ... ) in order to handle urls which may not be crawled in the first time.
* Add more logs.