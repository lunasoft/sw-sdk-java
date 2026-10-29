# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

SW-JAVA is a Java SDK for consuming SW sapien® CFDI (Mexican electronic invoicing) services. It provides authentication, stamping (timbrado), cancellation, validation, and other tax document operations.

## Build Commands

```bash
# Build the project
mvn clean package

# Build with dependencies JAR
mvn clean package assembly:single

# Run all tests
mvn test

# Run a single test class
mvn test -Dtest=SWAuthenticationServiceTest

# Run a specific test method
mvn test -Dtest=SWAuthenticationServiceTest#testAuth

# Build for release (includes GPG signing)
mvn clean deploy -Prelease
```

## Test Environment Variables

Tests require the following environment variables:
- `SDKTEST_USER` - SW account username
- `SDKTEST_PASSWORD` - SW account password
- `SDKTEST_TOKEN` - SW authentication token

Test endpoints:
- Services URL: `https://services.test.sw.com.mx`
- API URL: `https://api.test.sw.com.mx`

## Architecture

### Service Layer (`Services/`)
All services extend the abstract `SWService` base class which handles:
- Authentication (user/password or token-based)
- Token auto-refresh based on expiration
- Proxy support
- TLS 1.2 enforcement

Key services:
- `SWAuthenticationService` - Token generation
- `SWStampService` / `SWStampServiceV2` / `SWStampServiceV4` - CFDI stamping
- `SWIssueService` / `SWIssueServiceV2` / `SWIssueServiceV4` - CFDI sealing and stamping
- `SWCancelationService` - Invoice cancellation
- `SWValidateService` - CFDI validation
- `SWBalanceAccountService` - Account balance queries
- `SWPdfService` - PDF generation from CFDI
- `SWStorageService` - Document storage operations

### Request/Response Pattern (`Utils/Requests/`, `Utils/Responses/`)
Each service has corresponding request and response classes:
- Request classes handle HTTP communication to SW APIs
- Response classes model the API responses with status, data, and error information
- Response versions (V1-V4) provide different levels of detail for stamping operations

### Response Versions for Stamping
| Version | Response Content |
|---------|------------------|
| V1 | Timbre fiscal digital (TFD) only |
| V2 | TFD + timbrado CFDI |
| V3 | Timbrado CFDI only |
| V4 | All stamping data |

### Exceptions (`Exceptions/`)
- `AuthException` - Authentication failures
- `GeneralException` - General API errors
- `ValidationException` - Input validation errors

### Helpers (`Utils/Helpers/`)
- `BuildResponseV1-V4` - Response builders for different stamp versions
- `RequestHelper` / `RequestZipHelper` - HTTP request utilities
- `Validations` - Input validation utilities

## CFDI Types Supported

Test resources in `src/test/resources/CFDI40/` show supported document types:
- Standard invoices (Ingreso)
- Payments (Pagos20)
- Payroll (Nomina12)
- Foreign trade (ComercioExterior11)
- Transportation (CartaPorte20)
- Retentions (Retenciones20)
- Donations (Donatarias11)
- Other complements (INE, Addenda, etc.)

## Dependencies

The SDK uses:
- Apache HttpClient 4.x (`httpclient`, `httpmime`; `httpclient` comes in through `httpasyncclient`) for HTTP operations. The code imports `org.apache.http.*`; there is no HttpClient 5 usage.
- org.json and Gson for JSON processing
- `jakarta.xml.soap` (`jakarta.xml.ws-api` + `saaj-impl`) for the SOAP status query
- JUnit 4 (comes through `sw-resources-java`) for testing; `junit-jupiter-api` is test scope and only provides `@Disabled`
- External dependency `sw-resources-java` for CFDI cadena original generation

## Java Version

- `pom.xml` compiles to Java 1.8 (`source`/`target`). Do not claim a lower minimum in the README.
- The SOAP stack must stay at Java 8 bytecode: `jakarta.xml.ws-api` 3.0.1, `jakarta.xml.soap-api` 2.0.1 and `saaj-impl` 2.0.1 (all class version 52). The 4.x/3.x versions used between 1.0.20.1 and 1.0.26.1 are Java 11 bytecode and made `StatusCfdiService` fail on Java 8 with `UnsupportedClassVersionError`. Do not upgrade them without checking the bytecode.
- JDK 25 cannot target below Java 8. Use JDK 11 (the publish workflow) or JDK 8 to build.
