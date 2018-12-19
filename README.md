# What is it?

This application validates data presented in XML format using an XSD template.

## Installation

* Open the project folder
* Run command this command from the terminal to get the docker image "springio/xml-xsd-validation":

```bash
mvn package
```
* Run docker image:

```bash
docker run -p 80:80 springio/xml-xsd-validation
```

## How does it work

* You need to send a POST request to port 80, for example using curl:

```bash
curl -X POST -H "Content-type: application/xml" -d @temp.xml http://localhost:80
```
* Then you get the answer: valid or not valid
