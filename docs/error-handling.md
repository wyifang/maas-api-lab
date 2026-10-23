# Handle API Errors

## Overview

API requests can fail because of invalid input, authentication problems, rate limits, server errors, or local connectivity issues.

This guide explains how to inspect HTTP errors returned by the MaaS Demo API using the generated Java SDK.

**Note:** The status codes and error codes below are defined in the learning project's OpenAPI specification. Their exact behavior in a production service must be confirmed with the service provider.

## Error response format

The API defines a common error response structure:

```json
{
  "error": {
    "code": "invalid_api_key",
    "message": "The API key is invalid."
  }
}
```

The `code` field identifies the error category. The `message` field provides a human-readable explanation.

## HTTP status codes

| Status | Example error code | Recommended action |
|---|---|---|
| `400 Bad Request` | `invalid_request` | Check required fields, parameter values, and JSON structure. |
| `401 Unauthorized` | `invalid_api_key` | Check whether the token is configured correctly and is still valid. |
| `429 Too Many Requests` | `rate_limit_exceeded` | Check the service's rate-limit policy before retrying. |
| `500 Internal Server Error` | `internal_error` | Record diagnostic information and retry cautiously when appropriate. |

The table describes the learning API's documented error scenarios. It does not guarantee that a live service will use identical error messages or recovery policies.

## Catch API exceptions

The generated Java SDK exposes non-success HTTP responses through `ApiException`.

```java
try {
    CreateChatCompletion200Response response =
        api.createChatCompletion(request);

    System.out.println(response.getId());

} catch (ApiException e) {
    System.err.println("HTTP status: " + e.getCode());
    System.err.println("Response body: " + e.getResponseBody());
}
```

Use `getCode()` to inspect the HTTP status code and `getResponseBody()` to inspect the response body.

Avoid printing secrets, authorization headers, or sensitive user data to application logs.

## Retry considerations

Do not retry every failed request automatically.

- For `400`, correct the request before sending it again.
- For `401`, check the credentials before retrying.
- For `429`, follow the service's documented rate-limit and retry guidance. If the server provides a `Retry-After` header, interpret it according to the provider's contract.
- For `500`, consider a bounded retry policy only when the operation and service contract make retries appropriate.

Production retry logic should account for timeouts, retry limits, backoff, and whether repeating the operation could cause unintended effects.

## Local verification

The learning project includes a local mock-server example that returns HTTP `401` when no Bearer token is configured.

The example verified that the generated SDK exposes:

- HTTP status code `401`.
- The response body containing `invalid_api_key`.

This is a local integration test, not a test against a live MaaS service.

## Related guides

- [Quickstart](quickstart.md)
- [Authentication](authentication.md)
- [Make your first request](first-request.md)
- [Troubleshooting](troubleshooting.md)