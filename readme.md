# README

## Overview
This tool converts a POST request into a mail.
A user can upload a file via http,
which will be forwarded by mail to a statically defined mail address.

An API interface is provided for this purpose: 
```shell
curl --location --request POST 'http://localhost:8080/send' --form 'file=@"/path/to/my/file.txt"'
```

For sending mails the credentials of a mail provider have to be specified.
A simple way to send mails automatically is provided by Gmail. 
The corresponding information can be defined in `docker-compose.yml`.

## Install
The project contains a dockerfile.
If docker is installed on the target system,
the server and the database used can be installed with the provided docker-compose file:
```shell
$ docker-compose up
```

## Security
### SSL
Not implemented yet. Will follow at a later time.

### Rate Limit
The api allows only a certain number of requests per time unit and per IP address.
The behavior can be defined in `application.yml`.


Additionally the hash value of each uploaded file is stored.
This is stored for three days.
If an attempt is made to send the same file again during this time, this is blocked.
This prevents the recipient from receiving the same file more than once.


