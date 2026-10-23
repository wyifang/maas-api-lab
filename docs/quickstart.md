# Quickstart

## Overview

This guide introduces the MaaS Demo API and explains how to prepare a local Java development environment.

The learning project demonstrates how to:

- Define an API using OpenAPI.
- Generate a Java SDK from the API definition.
- Configure Bearer token authentication.
- Send a request and parse a JSON response.
- Handle HTTP errors returned by the server.

**Important:** This is a learning project. The configured API hostname `api.example.test` is a placeholder, not a live MaaS service.

## Prerequisites

Before you begin, make sure you have:

- Java Development Kit (JDK) 17.
- The project source code.
- A terminal that can run the Gradle Wrapper.
- The generated Java SDK in the `generated-java` directory.

## Build the Java SDK

Open PowerShell and navigate to the generated SDK directory:

```powershell
cd D:\Code\maas-api-lab\generated-java
```

Compile the Java source code:

```powershell
.\gradlew.bat clean compileJava
```

Wait for the build to finish. A successful build should report:

```text
BUILD SUCCESSFUL
```

## Next steps

Continue with the following guides:

- [Authentication](authentication.md)
- [Make your first request](first-request.md)
- [Handle API errors](error-handling.md)