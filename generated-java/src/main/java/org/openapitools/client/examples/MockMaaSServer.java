
package org.openapitools.client.examples;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class MockMaaSServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(
            new InetSocketAddress("127.0.0.1", 18080), 0
        );

        server.createContext("/v1/chat/completions", exchange -> {
            try {
                handleRequest(exchange);
            } catch (Exception e) {
                e.printStackTrace();
                exchange.close();
            }
        });

        server.start();

        System.out.println(
            "Mock MaaS server listening at http://127.0.0.1:18080"
        );
        System.out.println("Press Ctrl+C to stop.");
    }

    private static void handleRequest(HttpExchange exchange)
            throws IOException {

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String auth = exchange.getRequestHeaders()
            .getFirst("Authorization");

        System.out.println(method + " " + path);
        System.out.println("Authorization header present: "
            + (auth != null && !auth.isBlank()));

        String responseBody =
            "{\"id\":\"chatcmpl-local-001\","
            + "\"model\":\"demo-chat\","
            + "\"choices\":[{\"message\":{"
            + "\"role\":\"assistant\","
            + "\"content\":\"Hello from the local mock server.\""
            + "}}]}";

        int status = 200;

        if (!"POST".equalsIgnoreCase(method)) {
            status = 405;
            responseBody =
                "{\"error\":{\"code\":\"method_not_allowed\","
                + "\"message\":\"Use POST.\"}}";
        } else if (auth == null || !auth.startsWith("Bearer ")) {
            status = 401;
            responseBody =
                "{\"error\":{\"code\":\"invalid_api_key\","
                + "\"message\":\"Missing or invalid bearer token.\"}}";
        }

        byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set(
            "Content-Type", "application/json; charset=utf-8"
        );

        if (status == 401) {
            exchange.getResponseHeaders().set("WWW-Authenticate", "Bearer");
        }

        exchange.sendResponseHeaders(status, bytes.length);

        try (OutputStream output = exchange.getResponseBody()) {
            output.write(bytes);
        }
    }
}
