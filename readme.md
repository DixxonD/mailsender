# README

## Overview
This tool converts a POST request into a mail.
A user can upload a file via http,
which will be forwarded by mail to a statically defined mail address.
The upload form is available at the address `localhost:8080/form`.

An API interface is provided for this purpose: 
```shell
curl --location --request POST 'http://localhost:8080/send' --form 'file=@"/path/to/my/file.txt"'
```

For sending mails the credentials of a mail provider have to be specified.
A simple way to send mails automatically is provided by Gmail. 
The corresponding information can be defined in `docker-compose.yml`.

Furthermore, the project contains code to automatically compile and display competition results of the own club colleague. The corresponding HTML page is displayed via the path `/results`. 
The search criteria are currently not parameterized, because it is only a proof of concept for the club board and will probably soon be revised anyway and moved to a separate project. And yes, the look is not the prettiest ;-)


## Install
The project contains a dockerfile.
If docker is installed on the target system,
the server and the database used can be installed with the provided docker-compose file:
```shell
$ docker-compose up
```

## Security
### SSL
Currently the ssl connection is not enforced yet (this will change).

https://mkyong.com/spring-boot/spring-boot-ssl-https-examples/

### Rate Limit
The api allows only a certain number of requests per time unit and per IP address.
The behavior can be defined in `application.yml`.


Additionally the hash value of each uploaded file is stored.
This is stored for three days.
If an attempt is made to send the same file again during this time, this is blocked.
This prevents the recipient from receiving the same file more than once.


