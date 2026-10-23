
package org.openapitools.client.examples;

import org.openapitools.client.model.CreateChatCompletion200Response;

public class ResponseValidationExample {

    private static void test(String label, String json) {
        try {
            CreateChatCompletion200Response.fromJson(json);
            System.out.println(label + ": ACCEPTED");
        } catch (Exception e) {
            System.out.println(
                label + ": REJECTED ("
                + e.getClass().getSimpleName()
                + ": " + e.getMessage() + ")"
            );
        }
    }

    public static void main(String[] args) {
        String validJson =
            "{\"id\":\"chatcmpl-123\","
            + "\"model\":\"demo-model\","
            + "\"choices\":[{\"message\":{"
            + "\"role\":\"assistant\","
            + "\"content\":\"Hello\"}}]}";

        String missingModel =
            "{\"id\":\"chatcmpl-123\","
            + "\"choices\":[{\"message\":{"
            + "\"role\":\"assistant\","
            + "\"content\":\"Hello\"}}]}";

        String missingChoices =
            "{\"id\":\"chatcmpl-123\","
            + "\"model\":\"demo-model\"}";

        test("Valid response", validJson);
        test("Missing model", missingModel);
        test("Missing choices", missingChoices);

        String missingMessage =
            "{\"id\":\"chatcmpl-123\","
            + "\"model\":\"demo-model\","
            + "\"choices\":[{}]}";

        String missingContent =
            "{\"id\":\"chatcmpl-123\","
            + "\"model\":\"demo-model\","
            + "\"choices\":[{\"message\":{"
            + "\"role\":\"assistant\"}}]}";

        test("Missing message", missingMessage);
        test("Missing content", missingContent);
    }
}
