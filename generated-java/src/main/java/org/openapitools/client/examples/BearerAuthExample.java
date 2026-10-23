package org.openapitools.client.examples;

import org.openapitools.client.ApiException;
import org.openapitools.client.Pair;
import org.openapitools.client.auth.HttpBearerAuth;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BearerAuthExample {

    public static void main(String[] args) throws ApiException {
        HttpBearerAuth auth = new HttpBearerAuth("bearer");
        auth.setBearerToken("demo-token");

        List<Pair> queryParams = new ArrayList<>();
        Map<String, String> headerParams = new HashMap<>();
        Map<String, String> cookieParams = new HashMap<>();

        auth.applyToParams(
            queryParams,
            headerParams,
            cookieParams,
            "{}",
            "POST",
            URI.create("https://api.example.test/v1/chat/completions")
        );

        String actual = headerParams.get("Authorization");
        String expected = "Bearer demo-token";

        System.out.println("Authorization: " + actual);

        if (!expected.equals(actual)) {
            throw new IllegalStateException(
                "Unexpected Authorization header: " + actual
            );
        }

        System.out.println("PASS: Bearer authentication header is correct.");
    }
}
