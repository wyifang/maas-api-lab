# Troubleshooting

## Overview

This guide lists common problems you may encounter when building or using the MaaS Demo API learning project.

The examples use a local mock server and a generated Java SDK. The hostname `api.example.test` is a placeholder, not a live service.

## Java compilation fails

**Symptoms**

Gradle reports a compilation error.

**What to check**

1. Confirm that you are in the `generated-java` directory.
2. Confirm that JDK 17 is installed.
3. Read the first relevant compiler error rather than focusing only on the final Gradle failure message.

Run:

```powershell
cd D:\Code\maas-api-lab\generated-java
java -version
.\gradlew.bat clean compileJava
```

## HTTP 401 Unauthorized

**Symptoms**

The SDK throws an `ApiException` with status code `401`.

**What to check**

1. Confirm that a token has been configured.
2. Confirm that `setBearerToken()` receives the token value only, without the `Bearer ` prefix.
3. Check whether the token is valid and has not expired.
4. Confirm that the server supports the documented authentication scheme.

For the local mock server, a missing Bearer token produces HTTP 401.

## HTTP 400 Bad Request

**Symptoms**

The server rejects the request.

**What to check**

1. Confirm that `model` is present.
2. Confirm that `messages` is an array.
3. Confirm that every message includes `role` and `content`.
4. Confirm that the JSON structure matches the OpenAPI request schema.

These checks reflect the learning project's request definition. The exact validation behavior of a production service may differ.

## HTTP 429 Too Many Requests

**Symptoms**

The server reports a rate-limit error.

**What to check**

1. Consult the service provider's rate-limit documentation.
2. Check for a `Retry-After` header if the service documents its use.
3. Avoid rapid, unbounded retry loops.
4. Apply a bounded retry policy only when appropriate for the operation.

The local example does not establish the rate limits of any real service.

## HTTP 500 Internal Server Error

**Symptoms**

The server returns an internal error.

**What to check**

1. Record the HTTP status code and relevant error body.
2. Remove tokens and sensitive data from diagnostic logs.
3. Check the service status or provider documentation.
4. Retry cautiously only when appropriate.

## The request cannot connect to the server

**Symptoms**

The client fails before receiving an HTTP response.

**What to check**

1. Confirm that the mock server is running.
2. Confirm that the client base URL is `http://127.0.0.1:18080/v1` when using the local mock server.
3. Confirm that the mock server is listening on port `18080`.
4. Distinguish a connection failure from an HTTP error response. A connection failure may not provide an HTTP status code.

## Related guides

- [Quickstart](quickstart.md)
- [Authentication](authentication.md)
- [Make your first request](first-request.md)
- [Handle API errors](error-handling.md)