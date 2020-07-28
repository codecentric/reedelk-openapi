# Reedelk OpenAPI v3.x serializer / deserializer

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/reedelk/reedelk-runtime/blob/master/LICENSE)
[![Twitter](https://img.shields.io/twitter/follow/reedelk.svg?style=social&label=Follow)](https://twitter.com/intent/follow?screen_name=reedelk)

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
#### To JSON:
```java
OpenApiSerializableContext context = new OpenApiSerializableContext();

OpenApiObject openApiModel = new OpenApiObject();
openApiModel.setBasePath("/api/v3");
...
Schema schema = new Schema("mySchemaId", "{ \"type\": \"string\"}");
MediaTypeObject mediaTypeObject = new MediaTypeObject();
mediaTypeObject.setSchema(schema, context);
...
String openApiAsJson = OpenApiSerializer.toJson(openApiModel, context);
```

#### To YAML:
```java
OpenApiSerializableContext context = new OpenApiSerializableContext();

OpenApiObject openApiModel = new OpenApiObject();
openApiModel.setBasePath("/api/v3");
...
Schema schema = new Schema("mySchemaId", "{ \"type\": \"string\"}");
MediaTypeObject mediaTypeObject = new MediaTypeObject();
mediaTypeObject.setSchema(schema, context);
...
String openApiAsYaml = OpenApiSerializer.toYaml(openApiModel, context);
```

### Deserialize
#### From JSON:
```java
String openApiAsJson = "{"openapi": "3.0.3","info": {"title": "API","version": "v1" }}";
OpenApiObject openApiModel = OpenApiDeserializer.from(input);
InfoObject infoModel = actual.getInfo();
```
#### From YAML:
```java
String openApiAsYaml = "openapi: 3.0.0
                        info:
                          description: \"My description\"
                          version: 1.0.2";
OpenApiObject openApiModel = OpenApiDeserializer.from(input);
InfoObject infoModel = actual.getInfo();
```

## Contribute
- If you find a bug in Reedelk OpenAPI, please [file a bug report](https://github.com/reedelk/reedelk-openapi/issues).
- If you want to discuss Reedelk OpenAPI, suggest new features, you have issues getting started with the library or just say 'Hi', let us know in the [Reedelk Slack Developers Community](https://join.slack.com/t/reedelk/shared_invite/zt-fz3wx56f-XDylXpqXERooKeOtrhdZug) [![Join Reedelk Slack Developers Community](https://img.shields.io/badge/Slack-Join%20the%20chat%20room-blue)](https://join.slack.com/t/reedelk/shared_invite/zt-fz3wx56f-XDylXpqXERooKeOtrhdZug).
