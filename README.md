# MaaS API Documentation Engineering Lab

A hands-on API documentation engineering project demonstrating how to build, validate, generate, and maintain API documentation and SDK artifacts from an OpenAPI specification.

## Project Overview

This project simulates the documentation workflow for a Model-as-a-Service (MaaS) chat completion API.

It brings together API design, reference documentation, SDK generation, developer guides, and automated documentation checks in one repository.

**Note:** This is a learning project. The API server URL uses a placeholder domain and is not a live production service. Local request examples use a mock server.

## Key Deliverables

- **OpenAPI Specification** — API operations, request and response schemas, authentication, and error responses.
- **API Reference** — HTML documentation generated with Redocly CLI.
- **Java SDK** — Client code generated from the OpenAPI specification.
- **Developer Guide** — Quickstart, authentication, first request, error handling, and troubleshooting.
- **Local Mock Server** — Demonstrates successful requests and HTTP 401 error handling without calling a real API.
- **Documentation Quality Check** — A reusable PowerShell script that checks Markdown relative links.

## Technology Stack

- OpenAPI 3.0.3
- Redocly CLI
- OpenAPI Generator
- Java 17
- Gradle Wrapper
- Markdown
- PowerShell
- Git and GitHub

## Repository Structure

```text
maas-api-lab/
├── docs/                  # Developer Guide
├── generated-java/        # Generated Java SDK and examples
├── openapi/
│   └── openapi.yaml       # Source API specification
├── scripts/
│   └── check-doc-links.ps1
├── api-reference.html     # Generated API Reference
├── openapitools.json      # Generator configuration
└── .gitignore
```

## Documentation

Start with the [Developer Guide](docs/index.md).

- [Quickstart](docs/quickstart.md)
- [Authentication](docs/authentication.md)
- [First Request](docs/first-request.md)
- [Error Handling](docs/error-handling.md)
- [Troubleshooting](docs/troubleshooting.md)

The generated API Reference is available in [`api-reference.html`](api-reference.html).

## Build and Validate

### 1. Check Documentation Links

From the repository root, run:

```powershell
.\scripts\check-doc-links.ps1
```

### 2. Compile the Java SDK

```powershell
cd .\generated-java
.\gradlew.bat clean compileJava
```

### 3. Run the Local Mock API Example

Start the mock server in one PowerShell window:

```powershell
.\gradlew.bat runMockMaaSServer
```

In another PowerShell window, from `generated-java`, run:

```powershell
.\gradlew.bat runMockApiCallExample
```

The example sends an HTTP request to the local mock server and demonstrates SDK response parsing. Other examples demonstrate error handling and request serialization.

### 4. Rebuild the API Reference

From the repository root:

```powershell
npx @redocly/cli@2.64.1 build-docs .\openapi\openapi.yaml --output .\api-reference.html
```

This command requires Node.js and npm.

## Design Principle

The OpenAPI specification serves as the central source for API contract information. Generated SDK artifacts and API Reference documentation are derived from that contract, while the Developer Guide explains how developers use the API.

This project explores how documentation, code generation, and repeatable validation can work together in an API documentation engineering workflow.

## Scope and Limitations

- The API endpoint is fictional and is not intended for production use.
- The mock server runs locally and does not connect to a real MaaS provider.
- The Markdown link checker validates a subset of relative `.md` links; it does not validate external URLs or all Markdown anchor formats.

## Learning Objectives

- Maintain an OpenAPI-based API contract.
- Generate and compile a Java client SDK.
- Explain authentication, request construction, and API errors.
- Verify SDK behavior using a local mock service.
- Build repeatable checks for documentation quality.