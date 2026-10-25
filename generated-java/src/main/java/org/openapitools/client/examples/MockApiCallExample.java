
package org.openapitools.client.examples;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.DefaultApi;
import org.openapitools.client.model.CreateChatCompletionRequest;
import org.openapitools.client.model.CreateChatCompletionRequestMessagesInner;
import org.openapitools.client.model.CreateChatCompletion200Response;

public class MockApiCallExample {

    public static void main(String[] args) throws ApiException {
        ApiClient client = new ApiClient();
        client.setBasePath("http://127.0.0.1:18080/v1");
        client.setBearerToken("local-test-token");

        DefaultApi api = new DefaultApi(client);

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

        System.out.println("Response ID: " + response.getId());
        System.out.println("Model: " + response.getModel());
        System.out.println(
            "Assistant: "
                + response.getChoices().get(0).getMessage().getContent()
        );

        if (!"chatcmpl-local-001".equals(response.getId())) {
            throw new IllegalStateException("Unexpected response ID");
        }

        System.out.println("PASS: SDK completed a local HTTP request.");
    }
}
