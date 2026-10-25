package org.openapitools.client.examples;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.DefaultApi;
import org.openapitools.client.model.CreateChatCompletionRequest;
import org.openapitools.client.model.CreateChatCompletionRequestMessagesInner;

public class MockApiErrorExample {

    public static void main(String[] args) {
        ApiClient client = new ApiClient();
        client.setBasePath("http://127.0.0.1:18080/v1");

        // Intentionally do not configure a Bearer token.
        DefaultApi api = new DefaultApi(client);

        CreateChatCompletionRequest request =
            new CreateChatCompletionRequest()
                .model("demo-chat")
                .addMessagesItem(
                    new CreateChatCompletionRequestMessagesInner()
                        .role(CreateChatCompletionRequestMessagesInner.RoleEnum.USER)
                        .content("Hello")
                );

        try {
            api.createChatCompletion(request);
            throw new IllegalStateException(
                "Expected HTTP 401, but the request succeeded."
            );
        } catch (ApiException e) {
            System.out.println("HTTP status: " + e.getCode());
            System.out.println("Response body: " + e.getResponseBody());

            if (e.getCode() != 401) {
                throw new IllegalStateException(
                    "Expected HTTP 401, got " + e.getCode()
                );
            }

            if (e.getResponseBody() == null
                    || !e.getResponseBody().contains("invalid_api_key")) {
                throw new IllegalStateException(
                    "Expected invalid_api_key in the response body."
                );
            }

            System.out.println(
                "PASS: SDK exposed the HTTP 401 error correctly."
            );
        }
    }
}
