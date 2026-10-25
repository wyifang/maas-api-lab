
package org.openapitools.client.examples;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.api.DefaultApi;
import org.openapitools.client.auth.HttpBearerAuth;
import org.openapitools.client.model.CreateChatCompletionRequest;
import org.openapitools.client.model.CreateChatCompletionRequestMessagesInner;
import org.openapitools.client.model.CreateChatCompletion200Response;

public class ChatCompletionExample {

    public static void main(String[] args) {

        // 1. Get API Client
        ApiClient client = Configuration.getDefaultApiClient();

        // 2. Set up API URL
        client.setBasePath("https://api.example.test/v1");

        // 3. Configure Bearer Token
        HttpBearerAuth auth =
            (HttpBearerAuth) client.getAuthentication("BearerAuth");

        String token = System.getenv("MAAS_API_TOKEN");

        if (token == null || token.isBlank()) {
            throw new IllegalStateException(
                "Please set the MAAS_API_TOKEN environment variable."
            );
        }

        auth.setBearerToken(token);

        // 4. Build the message
        CreateChatCompletionRequestMessagesInner message =
            new CreateChatCompletionRequestMessagesInner()
                .role(
                    CreateChatCompletionRequestMessagesInner.RoleEnum.USER
                )
                .content("Explain what a data lake is.");

        // 5. Build the request
        CreateChatCompletionRequest request =
            new CreateChatCompletionRequest()
                .model("demo-chat")
                .addMessagesItem(message);

        // 6. call API
        DefaultApi api = new DefaultApi(client);

        try {
            CreateChatCompletion200Response response =
                api.createChatCompletion(request);

            System.out.println(response);

        } catch (ApiException e) {
            System.err.println("API request failed.");
            System.err.println("HTTP status: " + e.getCode());
            System.err.println("Response body: " + e.getResponseBody());
        }
    }
}
