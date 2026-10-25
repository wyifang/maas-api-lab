# Authentication

## Overview

The MaaS Demo API uses HTTP Bearer authentication.

Clients send the API token in the `Authorization` request header:

```http
Authorization: Bearer YOUR_API_TOKEN
```

Replace `YOUR_API_TOKEN` with your actual token.

**Important:** The API hostname `api.example.test` is a placeholder. This learning project does not connect to a live MaaS service.

## Configure authentication in Java

The generated Java SDK provides a method for configuring the Bearer token.

```java
ApiClient client = new ApiClient();

client.setBasePath("https://api.example.test/v1");
client.setBearerToken(token);
```

The `token` variable should contain the token value only. Do not include the `Bearer ` prefix; the SDK constructs the authorization header.

## Store the token securely

Avoid hardcoding API tokens in source code or committing them to version control.

For local development, you can store the token in an environment variable named `MAAS_API_TOKEN`.

In PowerShell, set the variable for the current terminal session:

```powershell
$env:MAAS_API_TOKEN = "your-token-here"
```

Read it in Java:

```java
String token = System.getenv("MAAS_API_TOKEN");

if (token == null || token.isBlank()) {
    throw new IllegalStateException(
        "Set the MAAS_API_TOKEN environment variable."
    );
}

client.setBearerToken(token);
```

Use a real token only when you have obtained one from the actual API provider. Do not use a real secret in this learning example.

## Handle authentication errors

An API may return HTTP `401 Unauthorized` when authentication credentials are missing, invalid, or expired.

The generated Java SDK exposes non-success HTTP responses through `ApiException`. You can inspect the status code and response body:

```java
try {
    // Call the API here.
} catch (ApiException e) {
    System.out.println("HTTP status: " + e.getCode());
    System.out.println("Response body: " + e.getResponseBody());
}
```

The local mock-server exercise verified that a request without a configured token received HTTP 401 and that the SDK exposed the status code and error body.

## Related guides

- [Quickstart](quickstart.md)
- [Make your first request](first-request.md)
- [Handle API errors](error-handling.md)