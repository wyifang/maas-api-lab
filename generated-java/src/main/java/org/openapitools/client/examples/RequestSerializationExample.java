
package org.openapitools.client.examples;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.openapitools.client.model.CreateChatCompletionRequest;
import org.openapitools.client.model.CreateChatCompletionRequestMessagesInner;

public class RequestSerializationExample {

    public static void main(String[] args) {

        // 1. Build a message
        CreateChatCompletionRequestMessagesInner message =
            new CreateChatCompletionRequestMessagesInner()
                .role(
                    CreateChatCompletionRequestMessagesInner.RoleEnum.USER
                )
                .content("Explain what a data lake is.");

        // 2. Build the request
        CreateChatCompletionRequest request =
            new CreateChatCompletionRequest()
                .model("demo-chat")
                .addMessagesItem(message)
                .temperature(new java.math.BigDecimal("0.7"));

        // 3. Convert the request object to JSON
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

        String json = gson.toJson(request);

        // 4. Print the JSON
        System.out.println(json);
    }
}
