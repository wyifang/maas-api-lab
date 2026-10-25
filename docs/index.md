# MaaS Demo API Developer Guide

Welcome to the MaaS Demo API learning project.

This documentation demonstrates how to define an API with OpenAPI, generate a Java SDK, make HTTP requests, and document authentication and error handling.

**Important:** This is a learning project. The hostname `api.example.test` is a placeholder, and the local mock server is not a production MaaS service.

## Get started

Follow these guides in order.

### 1. Quickstart

Prepare your local Java development environment and compile the generated SDK.

[Read Quickstart](quickstart.md)

### 2. Authentication

Learn how to configure a Bearer token and keep credentials out of source code.

[Read Authentication](authentication.md)

### 3. Make your first request

Learn how to construct a request, call the generated Java SDK, and read the response.

[Make your first request](first-request.md)

### 4. Handle API errors

Learn how to inspect HTTP status codes, error responses, and retry considerations.

[Read Error Handling](error-handling.md)

### 5. Troubleshooting

Find practical steps for diagnosing compilation, authentication, request, and connection problems.

[Open Troubleshooting](troubleshooting.md)

## API Reference

The API definition is maintained in `openapi/openapi.yaml`.

A standalone HTML API Reference can be generated from that definition using Redocly CLI. See the project instructions for the build command.

## Learning project scope

This project focuses on:

- OpenAPI specification design.
- Java SDK generation.
- Request and response serialization.
- Bearer authentication.
- HTTP error handling.
- Developer documentation and quality checks.

Examples that use the local mock server are intended for local learning and do not establish compatibility with a live service.