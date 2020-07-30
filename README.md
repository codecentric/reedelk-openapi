# Reedelk OpenAPI v3.x serializer / deserializer

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/reedelk/reedelk-runtime/blob/master/LICENSE)
[![Twitter](https://img.shields.io/twitter/follow/reedelk.svg?style=social&label=Follow)](https://twitter.com/intent/follow?screen_name=reedelk)

This project refers to the [OpenAPI Specification](https://github.com/OAI/OpenAPI-Specification) project from the [OpenAPI initiative](https://www.openapis.org/).

## Overview
The Reedelk OpenAPI v3.x serializer/deserializer library is a lightweight library to serialize/deserialize OpenAPI 
v3.x model to/from JSON and to/from YAML. 

The library uses only [JSON-java (org.json)](https://github.com/stleary/JSON-java) and 
[SnakeYAML](https://github.com/asomov/snakeyaml) as dependencies making it the perfect choice if you are just 
looking for a very lightweight solution to serialize/deserialize OpenAPI v3.x definitions.

 
## Features

- Serialize OpenAPI v3.x model to JSON
- Serialize OpenAPI v3.x model to YAML
- Deserialize OpenAPI v3.x model from JSON
- Deserialize OpenAPI v3.x model from YAML

## Maven
Add the Reedelk Repository to your pom.xml:

```xml
<repositories>
    <repository>
        <id>reedelk-repository</id>
        <name>Reedelk Repository</name>
        <url>http://repository.reedelk.com/release/</url>
    </repository>
</repositories>
```

Add the following dependency to your pom.xml file:
```xml
<dependency>
    <groupId>com.reedelk</groupId>
    <artifactId>reedelk-openapi</artifactId>
    <version>X.Y.Z</version>
</dependency>
```

## Usage
### Serialize
#### To JSON or YAML:
```java
InfoObject infoModel = new InfoObject();
infoModel.setDescription("This is a sample API.");
infoModel.setVersion("1.0.2");

OpenApiObject openApiModel = new OpenApiObject();
openApiModel.setInfo(info);

// to JSON string
String openApiAsJson = OpenApi.toJson(openApiModel);

// to YAML string
String openApiAsYaml = OpenApi.toYaml(openApiModel);
```

### Deserialize
#### From JSON or YAML:
```java
// from JSON string
String openApiAsJson = "{"openapi": "3.0.3","info": {"title": "API","version": "v1" }}";
OpenApiObject openApiModel = OpenApiDeserializer.from(openApiAsJson);
InfoObject infoModel = actual.getInfo();
...

// from YAML string
String openApiAsYaml = "openapi: 3.0.0
                        info:
                          version: 1.0.2
                          title: Petstore API";
OpenApiObject openApiModel = OpenApiDeserializer.from(openApiAsYaml);
InfoObject infoModel = actual.getInfo();
...
```

## Contribute
- If you find a bug in Reedelk OpenAPI, please [file a bug report](https://github.com/reedelk/reedelk-openapi/issues).
- If you want to discuss Reedelk OpenAPI, suggest new features, you have issues getting started with the library or just say 'Hi', let us know in the [Reedelk Slack Developers Community](https://join.slack.com/t/reedelk/shared_invite/zt-fz3wx56f-XDylXpqXERooKeOtrhdZug) [![Join Reedelk Slack Developers Community](https://img.shields.io/badge/Slack-Join%20the%20chat%20room-blue)](https://join.slack.com/t/reedelk/shared_invite/zt-fz3wx56f-XDylXpqXERooKeOtrhdZug).
