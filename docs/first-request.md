# Make Your First Request

## Overview

This guide demonstrates how to construct a chat completion request using the generated Java SDK, send it to an HTTP endpoint, and read the response.

The example uses the local mock server from the learning project. It does not call a live MaaS service.

## Request structure

The API operation is:

- **Method:** `POST`
- **Path:** `/v1/chat/completions`
- **Content type:** `application/json`

The request body contains a model identifier and a list of messages:

```json
{
  "model": "demo-chat",
  "messages": [
    {
      "role": "user",
      "content": "Hello"
    }
  ]
}
```

The `model` field identifies the model requested by the client. Each message contains a `role` and `content`.

The accepted role values in this learning API definition are `system`, `user`, and `assistant`.

## Configure the client

The local mock server listens at `http://127.0.0.1:18080`.

```java
ApiClient client = new ApiClient();
client.setBasePath("http://127.0.0.1:18080/v1");
client.setBearerToken("local-test-token");

DefaultApi api = new DefaultApi(client);
```

The token shown here is a local test value, not a real API credential.

## Construct and send the request

```java
CreateChatCompletionRequest request =
    new CreateChatCompletionRequest()
        .model("demo-chat")
        .addMessagesItem(
            new CreateChatCompletionRequestMessagesInner()
                .role(CreateChatCompletionRequestMessagesInner.RoleEnum.USER)
                .content("Hello")
        );

CreateChatCompletion200Response response =
    api.createChatCompletion(request);
```

The SDK serializes the request object, sends the HTTP request, and converts a successful JSON response into a Java response model.

## Example response

The local mock server returns a response similar to this:

```json
{
  "id": "chatcmpl-local-001",
  "model": "demo-chat",
  "choices": [
    {
      "message": {
        "role": "assistant",
        "content": "Hello from the local mock server."
      }
    }
  ]
}
```

## Read the response

```java
System.out.println("Response ID: " + response.getId());
System.out.println("Model: " + response.getModel());

String answer = response.getChoices()
    .get(0)
    .getMessage()
    .getContent();

System.out.println("Assistant: " + answer);
```

The response ID and message content are returned by the local mock server. A real service may return different values.

## What you have learned

You have seen how to:

1. Construct a request using generated Java model classes.
2. Configure the SDK's base URL and Bearer token.
3. Call the generated API method.
4. Read the response through typed Java objects.

## Related guides

- [Quickstart](quickstart.md)
- [Authentication](authentication.md)
- [Handle API errors](error-handling.md)