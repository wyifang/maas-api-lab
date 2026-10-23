
package org.openapitools.client.examples;

import org.openapitools.client.ApiException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiExceptionExample {

    public static void main(String[] args) {
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Retry-After", Arrays.asList("30"));

        String body =
            "{\"error\":{\"code\":\"rate_limit_exceeded\","
            + "\"message\":\"Too many requests.\"}}";

        ApiException exception = new ApiException(
            429,
            "Too Many Requests",
            headers,
            body
        );

        check(
            exception.getCode() == 429,
            "HTTP status code should be 429"
        );

        check(
            body.equals(exception.getResponseBody()),
            "Response body should match"
        );

        check(
            exception.getResponseHeaders().get("Retry-After")
                .contains("30"),
            "Retry-After header should be 30"
        );

        check(
            exception.getMessage().contains("429"),
            "Exception message should contain the status code"
        );

        System.out.println("HTTP status: " + exception.getCode());
        System.out.println("Response body: " + exception.getResponseBody());
        System.out.println(
            "Retry-After: "
                + exception.getResponseHeaders().get("Retry-After")
        );
        System.out.println("PASS: ApiException fields verified.");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException("FAIL: " + message);
        }
    }
}
