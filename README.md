[![Smarter Web](https://sw.com.mx/hubfs/Webpage/logo-swsapien.svg)](http://sw.com.mx/)

# SDK JAVA #

Librería JAVA para el consumo de los servicios de SW sapien®.

Registrate en sw.com.mx

## Requerimientos ##

Java 1.6 o superior

## Dependencias ##
* [org.json](https://mavenlibs.com/jar/file/org.json/json)
* [httpclient 4.3.6](http://hc.apache.org/downloads.cgi)
* [httpmime 4.3.6](http://hc.apache.org/downloads.cgi)
* [httpasyncclient 4.0.2](http://hc.apache.org/downloads.cgi)


## Instalación ##

Descargas el modulo mediante Maven:
	
```html
<dependencies>

        <dependency>
            <groupId>mx.com.sw.services</groupId>
            <artifactId>SW-JAVA</artifactId>
            <version>1.0.7.1</version>
        </dependency>

</dependencies>
```
## Instalación manual (sin Maven) ##
Descargar el modulo directamente de los siguientes links:

* [Relase Github](https://github.com/lunasoft/sw-sdk-java/releases)
* [Maven](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=mx.com.sw.services&a=SW-JAVA&v=1.0.7.1&e=jar)

Así como instalar manualmente cada una de las dependencias:
* [org.json](http://www.json.org/java)
* [httpclient 4.3.6](http://hc.apache.org/downloads.cgi)
* [httpmime 4.3.6](http://hc.apache.org/downloads.cgi)
* [httpasyncclient 4.0.2](http://hc.apache.org/downloads.cgi)

Alternativamente, tambien se cuenta con un archivo JAR en el que se incluyen todas las dependencias, este se encuentra en la carpeta [Relase Github](https://github.com/lunasoft/sw-sdk-java/releases),  con el sufijo _"jar-with-dependencies.jar"_

# Autenticación #
El servicio de Autenticación es utilizado principalmente para obtener el token el cual será utilizado para poder usar nuestros servicios SW, es necesario que cuente con un usuario y contraseña para posteriormente obtenga el token.

  **Ejemplo de consumo del servicio Autenticación**
```java
package com.mycompany.examplereadme;

import Services.Authentication.SWAuthenticationService; // Importación del servicio de autenticación
import Utils.Responses.Authentication.SuccessAuthResponse; // Importación de la clase de respuesta de autenticación exitosa

public class ExampleReadme {
    public static void main(String[] args) {
        try {
            // Creación de una instancia de servicio de autenticación
            // Se proporcionan el nombre de usuario, la contraseña y la URL para obtener el token de autenticación
            SWAuthenticationService auth = new SWAuthenticationService("user", "password", "http://services.test.sw.com.mx");
            // Llamada al método Token para obtener la respuesta de autenticación
            SuccessAuthResponse response = (SuccessAuthResponse) auth.Token();
            // Impresión de información obtenida de la respuesta
            System.out.println("Token: " + response.token);
            System.out.println("Succes: " + response.Status);
            System.out.println("Message: " + response.message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
# Timbrado #
<details><summary><b>Timbrado</b></summary>
    
<br>El método servicio **Timbrado** recibe el contenido de un **XML** ya emitido (sellado) en formato **String**  o tambien puede ser en **Base64**, posteriormente,  si la factura y la forma de autenticación son correctos devuelve el response dependiendo de la versión indicada, en caso contrario lanza una excepción.

**Timbrar XML en formato string utilizando usuario y contraseña**
```java
package com.mycompany.examplereadme;

import Services.Stamp.SWStampService;
import Utils.Responses.Stamp.SuccessV1Response;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            // Inicializar el objeto con la información de la cuenta o el token de acceso especifica la URL base para acceder al entorno deseado
            SWStampService sdk = new SWStampService("user", "password", "http://services.test.sw.com.mx");
            // Inicializar un objeto de respuesta para almacenar la respuesta
            SuccessV1Response response = null;
            //Envia el XML previamente sellado acompañado de la versión de respuesta que requieras
            response = (SuccessV1Response) sdk.Stamp(stringXML, "v1");
            // En response se mostrará la informacion de respuesta del servicio-
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.tfd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

**Timbrar XML en formato string utilizando token**

```java
    //Basta con sustituir esta linea en el ejemplo anterior, colocarás el token de tu cuenta y la URL base del ambiente que requieres acceder
    SWStampService sdk = new SWStampService("tokenUser", "http://services.test.sw.com.mx");
```
</details>
<details><summary><b>Emisión Timbrado</b></summary>
<br>Emisión Timbrado realiza el sellado y timbrado de un comprobante CFDI.Recibe el contenido de un XML en formato String, posteriormente, si la factura y la forma de autenticación son correctos devuelve el response dependiendo de la versión indicada, en caso contrario lanza una excepción.

**Timbrar XML en formato string utilizando usuario y contraseña**

```java
package com.mycompany.examplereadme;

import Services.Issue.SWIssueService;
import Utils.Responses.Stamp.SuccessV1Response;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            // Inicializar el objeto con la información de la cuenta o el token de acceso especifica la URL base para acceder al entorno deseado
            SWIssueService sdk = new SWIssueService("user", "password", "http://services.test.sw.com.mx");
            // Inicializar un objeto de respuesta para almacenar la respuesta
            SuccessV1Response response = null;
            //Envia el JSON acompañado de la versión de respuesta que requieras
           response = (SuccessV1Response) sdk.IssueXml(stringXml, "v3");
            // En response se mostrará la informacion de respuesta del servicio-
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.tfd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```
**Timbrar XML en formato string utilizando token**

```java
    //Basta con sustituir esta linea en el ejemplo anterior, colocarás el token de tu cuenta y la URL base del ambiente que requieres acceder
     SWIssueService sdk = new SWIssueService("tokenUser", "http://services.test.sw.com.mx");
```
</details>

<details><summary><b>Emisión Timbrado JSON</b></summary>
<br>Emisión Timbrado JSON realiza el sellado y timbrado de un comprobante CFDI.Recibe el contenido de un JSON en formato String, posteriormente,  si la factura y la forma de autenticación son correctos devuelve el response dependiendo de la versión indicada, en caso contrario lanza una excepción.

**Timbrar JSON en formato string utilizando usuario y contraseña**
```java
package com.mycompany.examplereadme;

import Services.Issue.SWIssueService;
import Utils.Responses.Stamp.SuccessV1Response;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            // Inicializar el objeto con la información de la cuenta o el token de acceso especifica la URL base para acceder al entorno deseado
            SWIssueService sdk = new SWIssueService("user", "password", "http://services.test.sw.com.mx");
            // Inicializar un objeto de respuesta para almacenar la respuesta
            SuccessV1Response response = null;
            //Envia el JSON acompañado de la versión de respuesta que requieras
           response = (SuccessV1Response) sdk.IssueJson(stringJson, "v4");
            // En response se mostrará la informacion de respuesta del servicio-
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.tfd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
**Timbrar XML en formato string utilizando token**

```java
    //Basta con sustituir esta linea en el ejemplo anterior, colocarás el token de tu cuenta y la URL base del ambiente que requieres acceder
     SWIssueService sdk = new SWIssueService("tokenUser", "http://services.test.sw.com.mx");
```
</details>
</details>

:pushpin: ***NOTA:*** Existen varias versiones de respuesta, las cuales son las siguientes:

| Version |                         Respuesta                             | 
|---------|---------------------------------------------------------------|
|  V1     | Devuelve el timbre fiscal digital                             | 
|  V2     | Devuelve el timbre fiscal digital y el CFDI timbrado          | 
|  V3     | Devuelve el CFDI timbrado                                     | 
|  V4     | Devuelve todos los datos del timbrado                         |

Para mayor referencia de estas versiones de respuesta, favor de visitar el siguiente [link](https://developers.sw.com.mx/knowledge-base/versiones-de-respuesta-timbrado/).


# Cancelación #

Este servicio se utiliza para cancelar CFDI, aquí los métodos que se ofrecen:


<details>
<summary>
Cancelacion CSD
</summary>
Como su nombre lo indica, este metodo recibe todos los elementos que componen el CSD los cuales son los siguientes:

- Certificado (.cer) en Base64
- Key (.key) en Base64
- RFC emisor
- Password del archivo key
- UUID
- Motivo
- Folio Sustitución (requerido sólo cuando Motivo es 01)

**Ejemplo de consumo de la libreria para cancelar con CSD**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Cancelation.SWCancelationService;
import Utils.Responses.Cancelation.CancelationResponse;
import java.io.IOException;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Instancia del servicio y autenticación
            SWCancelationService sdk = new SWCancelationService("user", "password", "https://services.test.sw.com.mx");
            CancelationResponse response = null;
            //Paso de datos de cancelación
            response = (CancelationResponse) sdk.Cancelation("uuid", "password_csd", "rfc", "b64Cer", "b64Key", "motivo", "folio_sustitucion");
           //Muestra los resultados
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.acuse);
            System.out.println(response.uuid);
            System.out.println(response.uuidStatusCode);
           //En caso de obtener un error, este puede obtenerse de los campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException e) {
            System.out.println(e);
        }

    }
}
```
</details>
<details>
<summary>
Cancelacion XML
</summary>

Este método recibe únicamente el XML sellado con los UUID a cancelar.
**Ejemplo de XML para cancelar**
```xml
<Cancelacion xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" Fecha="2023-10-25T15:52:58" RfcEmisor="EKU9003173C9" xmlns="http://cancelacfd.sat.gob.mx"><Folios><Folio UUID="cfc771b4-7d90-459e-ab06-afd2b3c59c10" Motivo="02" FolioSustitucion="" /></Folios><Signature xmlns="http://www.w3.org/2000/09/xmldsig#"><SignedInfo><CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" /><SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1" /><Reference URI=""><Transforms><Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature" /></Transforms><DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1" /><DigestValue>+FdkoG3oHCp4n4xsrsY9XxXnyCg=</DigestValue></Reference></SignedInfo><SignatureValue>mw+gnK8skM38PWW8IG8qa3NfaNmek7tNInVroTMO6NuMPVkZGiZiKzlcSlZAgusCr1ujnna7ICzjbSSNugtn6OSyff25GXZ95yOHx+CoEIbW2QDuVx5BN0IxM3rWY8Kh8n1zYAqz52czGjPu7lhuNAziKkxksmAgWbct6de2GxCTXX97npqv/YUGt2l6a5e10IDC6EQGQGW8/VhYvfWEzoajoG0t7tuRnP/pJre+Z531lYxdjEdxWXzF9fquiSpMs4Tsbqqm+EU2cusXj4ufT2GLRUabbIgSedvqAgqQElK7z+1GbXoC1bcRvsawkkTA12WmEwV9Dx2CBQXOUTaIRA==</SignatureValue><KeyInfo><X509Data><X509IssuerSerial><X509IssuerName>OID.1.2.840.113549.1.9.2=responsable: ACDMA-SAT, OID.2.5.4.45=2.5.4.45, L=COYOACAN, S=CIUDAD DE MEXICO, C=MX, PostalCode=06370, STREET=3ra cerrada de caliz, E=oscar.martinez@sat.gob.mx, OU=SAT-IES Authority, O=SERVICIO DE ADMINISTRACION TRIBUTARIA, CN=AC UAT</X509IssuerName><X509SerialNumber>292233162870206001759766198462772978647764840758</X509SerialNumber></X509IssuerSerial><X509Certificate>MIIFsDCCA5igAwIBAgIUMzAwMDEwMDAwMDA1MDAwMDM0MTYwDQYJKoZIhvcNAQELBQAwggErMQ8wDQYDVQQDDAZBQyBVQVQxLjAsBgNVBAoMJVNFUlZJQ0lPIERFIEFETUlOSVNUUkFDSU9OIFRSSUJVVEFSSUExGjAYBgNVBAsMEVNBVC1JRVMgQXV0aG9yaXR5MSgwJgYJKoZIhvcNAQkBFhlvc2Nhci5tYXJ0aW5lekBzYXQuZ29iLm14MR0wGwYDVQQJDBQzcmEgY2VycmFkYSBkZSBjYWxpejEOMAwGA1UEEQwFMDYzNzAxCzAJBgNVBAYTAk1YMRkwFwYDVQQIDBBDSVVEQUQgREUgTUVYSUNPMREwDwYDVQQHDAhDT1lPQUNBTjERMA8GA1UELRMIMi41LjQuNDUxJTAjBgkqhkiG9w0BCQITFnJlc3BvbnNhYmxlOiBBQ0RNQS1TQVQwHhcNMjMwNTE4MTE0MzUxWhcNMjcwNTE4MTE0MzUxWjCB1zEnMCUGA1UEAxMeRVNDVUVMQSBLRU1QRVIgVVJHQVRFIFNBIERFIENWMScwJQYDVQQpEx5FU0NVRUxBIEtFTVBFUiBVUkdBVEUgU0EgREUgQ1YxJzAlBgNVBAoTHkVTQ1VFTEEgS0VNUEVSIFVSR0FURSBTQSBERSBDVjElMCMGA1UELRMcRUtVOTAwMzE3M0M5IC8gVkFEQTgwMDkyN0RKMzEeMBwGA1UEBRMVIC8gVkFEQTgwMDkyN0hTUlNSTDA1MRMwEQYDVQQLEwpTdWN1cnNhbCAxMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtmecO6n2GS0zL025gbHGQVxznPDICoXzR2uUngz4DqxVUC/w9cE6FxSiXm2ap8Gcjg7wmcZfm85EBaxCx/0J2u5CqnhzIoGCdhBPuhWQnIh5TLgj/X6uNquwZkKChbNe9aeFirU/JbyN7Egia9oKH9KZUsodiM/pWAH00PCtoKJ9OBcSHMq8Rqa3KKoBcfkg1ZrgueffwRLws9yOcRWLb02sDOPzGIm/jEFicVYt2Hw1qdRE5xmTZ7AGG0UHs+unkGjpCVeJ+BEBn0JPLWVvDKHZAQMj6s5Bku35+d/MyATkpOPsGT/VTnsouxekDfikJD1f7A1ZpJbqDpkJnss3vQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAFaUgj5PqgvJigNMgtrdXZnbPfVBbukAbW4OGnUhNrA7SRAAfv2BSGk16PI0nBOr7qF2mItmBnjgEwk+DTv8Zr7w5qp7vleC6dIsZFNJoa6ZndrE/f7KO1CYruLXr5gwEkIyGfJ9NwyIagvHHMszzyHiSZIA850fWtbqtythpAliJ2jF35M5pNS+YTkRB+T6L/c6m00ymN3q9lT1rB03YywxrLreRSFZOSrbwWfg34EJbHfbFXpCSVYdJRfiVdvHnewN0r5fUlPtR9stQHyuqewzdkyb5jTTw02D2cUfL57vlPStBj7SEi3uOWvLrsiDnnCIxRMYJ2UA2ktDKHk+zWnsDmaeleSzonv2CHW42yXYPCvWi88oE1DJNYLNkIjua7MxAnkNZbScNw01A6zbLsZ3y8G6eEYnxSTRfwjd8EP4kdiHNJftm7Z4iRU7HOVh79/lRWB+gd171s3d/mI9kte3MRy6V8MMEMCAnMboGpaooYwgAmwclI2XZCczNWXfhaWe0ZS5PmytD/GDpXzkX0oEgY9K/uYo5V77NdZbGAjmyi8cE2B2ogvyaN2XfIInrZPgEffJ4AB7kFA2mwesdLOCh0BLD9itmCve3A1FGR4+stO2ANUoiI3w3Tv2yQSg4bjeDlJ08lXaaFCLW2peEXMXjQUk7fmpb5MNuOUTW6BE=</X509Certificate></X509Data></KeyInfo></Signature></Cancelacion>
```
Para caso de motivo 01 deberá añadir el atributo "FolioSustitucion" dentro del Nodo
**Ejemplo nodo Folio motivo 01**
```xml
<Folios>
	<Folio UUID="cfc771b4-7d90-459e-ab06-afd2b3c59c10" Motivo="01" FolioSustitucion="b3641a4b-7177-4323-aaa0-29bd34bf1ff8" />
</Folios>
```

**Ejemplo de consumo de la libreria para cancelar por XML**
```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Cancelation.SWCancelationService;
import Utils.Responses.Cancelation.CancelationResponse;
import java.io.IOException;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Instancia del servicio y autenticación
            SWCancelationService sdk = new SWCancelationService("user", "password", "https://services.test.sw.com.mx");
            CancelationResponse response = null;
            //Paso de XML de cancelación
            response = (CancelationResponse) sdk.Cancelation("xmlCancelacion");
           //Muestra los resultados
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.acuse);
            System.out.println(response.uuid);
            System.out.println(response.uuidStatusCode);
           //En caso de obtener un error, este puede obtenerse de los campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException e) {
            System.out.println(e);
        }

    }
}
```
</details>

<details>
<summary>
Cancelacion PFX
</summary> 

Este método recibe los siguientes parametros:

- Archivo PFX en Base64
- RFC emisor
- Password de PFX
- UUID
- Motivo
- Folio Sustitución (requerido sólo cuando Motivo es 01) 

**Ejemplo de consumo de la libreria para cancelar con PFX**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Cancelation.SWCancelationService;
import Utils.Responses.Cancelation.CancelationResponse;
import java.io.IOException;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Instancia del servicio y autenticación
            SWCancelationService sdk = new SWCancelationService("user", "password", "https://services.test.sw.com.mx");
            CancelationResponse response = null;
            //Paso de datos de cancelación
            response = (CancelationResponse) sdk.Cancelation("uuid","password_pfx","rfc","pfxb64","motivo","folio_sustitucion");
           //Muestra los resultados
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.acuse);
            System.out.println(response.uuid);
            System.out.println(response.uuidStatusCode);
           //En caso de obtener un error, este puede obtenerse de los campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException e) {
            System.out.println(e);
        }

    }
}

```
</details>
<details>
<summary>
Cancelacion UUID
</summary> 
Este método recibe los siguientes parámetros:

- RFC emisor
- UUID
- Motivo
- Folio Sustitución (requerido sólo cuando Motivo es 01) 

**Ejemplo de consumo de la libreria para cancelar con UUID**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Cancelation.SWCancelationService;
import Utils.Responses.Cancelation.CancelationResponse;
import java.io.IOException;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Instancia del servicio y autenticación
            SWCancelationService sdk = new SWCancelationService("user", "password", "https://services.test.sw.com.mx");
            CancelationResponse response = null;
            //Paso de datos de cancelación
            response = (CancelationResponse) sdk.Cancelation("uuid","rfc","motivo","folio_sustitucion");
           //Muestra los resultados
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.acuse);
            System.out.println(response.uuid);
            System.out.println(response.uuidStatusCode);
           //En caso de obtener un error, este puede obtenerse de los campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException e) {
            System.out.println(e);
        }

    }
}

```
</details>


# Validación #

Este servicio recibe un comprobante CFDI en formato XML mediante el cual se valida integridad, sello, errores de estructura, matriz de errores del SAT incluyendo complementos, se valida su validez ante el SAT.

<details>
<summary>
Ejemplo de uso
</summary> 

- Recibe el XML del CFDI a validar.
- La respuesta vendrá representada con datos planos y un par de listas anidadas. Para obtener la información de las mismas se iterará.

**Ejemplo de consumo de la libreria para validar un XML**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Services.Validate.SWValidateService;
import Utils.Responses.Validate.DetailData;
import Utils.Responses.Validate.DetailNode;
import Utils.Responses.Validate.ValidateXmlResponse;
import java.util.LinkedList;

public class ExampleReadme {

    public static void main(String[] args) {

        try {
            //Instancia para el servicio de validacion y autenticación
            SWValidateService sdk = new SWValidateService("user", "password", "https://services.test.sw.com.mx");
            ValidateXmlResponse response = null;
            response = (ValidateXmlResponse) sdk.ValidateXml(xml);
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.message);
            LinkedList<DetailNode> List = (LinkedList<DetailNode>) response.detail;
            for (int i = 0; i < List.size(); i++) {
                DetailNode node = List.get(i);
                LinkedList<DetailData> ListData = node.detail;
                for (int j = 0; j < ListData.size(); j++) {
                    DetailData datos = ListData.get(j);
                    System.out.println("\t\t" + datos.message);
                    System.out.println("\t\t" + datos.messageDetail);
                    System.out.println("\t\t" + datos.type);
                }
                System.out.println("\t" + node.section);
            }
            System.out.println(response.cadenaOriginalComprobante);
            System.out.println(response.cadenaOriginalSAT);
            System.out.println(response.uuid);
            System.out.println(response.statusSat);
            System.out.println(response.statusCodeSat);
            //En caso de obtener un error, este puede obtenerse de los campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException ex) {
            System.out.println(ex);
        }
    }
}
 
```
</details>

# Consulta de Saldos #
Se obtiene el balance de nuestra cuenta con respecto a los timbres, es necesario el usuario y contraseña o bien el token para identificar la cuenta de la cual se solicita la consulta.
<details>
<summary>
Ejemplo de consumo del servicio para consultar el saldo de una cuenta
</summary> 

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.BalanceAccount.SWBalanceAccountService;
import Utils.Responses.BalanceAccount.BalanceAcctResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {

        try {
            //Intancia del servicio de Consulta de saldo y autenticación
            SWBalanceAccountService sdk = new SWBalanceAccountService("user", "password", "https://services.test.sw.com.mx");
            BalanceAcctResponse response = null;
            response = (BalanceAcctResponse) sdk.GetBalanceAccount();
            //Imprimimos los datos de la respuesta que se obtuvo
            System.out.println(response.Status);
            System.out.println(response.timbresAsignados);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.fechaExpiracion);
            System.out.println(response.idClienteUsuario);
            System.out.println(response.idSaldoCliente);
            System.out.println(response.saldoTimbres);
            System.out.println(response.timbresUtilizados);
            System.out.println(response.unlimited);
            //En caso de obtener error, este puede obtenerse de los siguientes campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException ex) {
            System.out.println(ex);
        } catch (GeneralException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

```
</details>

# Consulta y Asignación de Timbres #
Métodos para realizar la consulta de saldo así como la asignación y eliminación de timbres a un usuario.
<details>
<summary>
Consulta de timbres
</summary> 

<br>Este método recibe los siguientes parametros:
* Usuario y contraseña o Token
* Url Servicios SW

**Ejemplo de consumo de la libreria para consultar el saldo utilizando Token**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.BalanceAccount.SWBalanceAccountService;
import Utils.Responses.BalanceAccount.BalanceAcctResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {

        try {
            //Intancia del servicio de Consulta de saldo y autenticación
            SWBalanceAccountService sdk = new SWBalanceAccountService("T2lYQ0t4L0R...", "https://services.test.sw.com.mx");
            BalanceAcctResponse response = null;
            response = (BalanceAcctResponse) sdk.GetBalanceAccount();

            //Imprimimos los datos de la respuesta que se obtuvo
            System.out.println(response.Status);
            System.out.println(response.timbresAsignados);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.fechaExpiracion);
            System.out.println(response.idClienteUsuario);
            System.out.println(response.idSaldoCliente);
            System.out.println(response.saldoTimbres);
            System.out.println(response.timbresUtilizados);
            System.out.println(response.unlimited);

            //En caso de obtener error, este puede obtenerse de los siguientes campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException ex) {
            System.out.println(ex);
        } catch (GeneralException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

```
</details>

<details>
<summary>
Agregar timbres
</summary> 

<br>Este método recibe los siguientes parametros:
* Usuario y contraseña o Token
* Url Servicios SW
* Url Api
* IdUser
* Número de timbres
* Comentario

**Ejemplo de consumo de la libreria para agregar timbres utilizando Token**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.BalanceAccount.SWBalanceAccountService;
import Utils.Responses.BalanceAccount.BalanceAcctResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {

        try {
            //Intancia del servicio para agregar timbres y autenticación
            SWBalanceAccountService sdk = new SWBalanceAccountService("T2lYQ0t4L0R...", "http://api.test.sw.com.mx");
            BalanceAcctResponse response = null;
            response = (BalanceAcctResponse) sdk.AddStamp("32701CF2-DC63-4370-B47D-25024C44E131", 1, "PruebaJava16");

            //Imprimimos los datos de la respuesta que se obtuvo
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.message);
            
            //En caso de obtener error, este puede obtenerse de los siguientes campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException ex) {
            System.out.println(ex);
        } catch (GeneralException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

```
</details>

<details>
<summary>
Eliminar timbres
</summary> 

<br>Este método recibe los siguientes parametros:
* Usuario y contraseña o Token
* Url Servicios SW
* Url Api
* IdUser
* Número de timbres
* Comentario

**Ejemplo de consumo de la libreria para eliminar timbres utilizando Token**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.BalanceAccount.SWBalanceAccountService;
import Utils.Responses.BalanceAccount.BalanceAcctResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {

        try {
            //Intancia del servicio para agregar timbres y autenticación
            SWBalanceAccountService sdk = new SWBalanceAccountService("T2lYQ0t4L0R...", "http://api.test.sw.com.mx");
            BalanceAcctResponse response = null;
            response = (BalanceAcctResponse) sdk.RemoveStamp("32701CF2-DC63-4370-B47D-25024C44E131", 1, "PruebaJava16");
            
            //Imprimimos los datos de la respuesta que se obtuvo
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.message);
            
            //En caso de obtener error, este puede obtenerse de los siguientes campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException ex) {
            System.out.println(ex);
        } catch (GeneralException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

```
</details>

# Estatus CFDI #
Método necesario para conocer el estado de un CFDI a través del servicio de consulta del SAT.[Este servicio es consumido directamente del SAT].

Este método recibe los siguientes parametros:

- RFC emisor
- RFC receptor
- Total de la factura
- UUID
- 8 ultimos digitos sello del Emisor
<details>
<summary>
Ejemplo de consumo del servicio para consultar estatus del CFDI
</summary> 

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.StatusCfdi.StatusCfdiService;
import Utils.Responses.StatusCfdi.StatusCfdiResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.SOAPException;

public class ExampleReadme {

    public static void main(String[] args) throws AuthException {

        try {
            //Instancia del servicio
            StatusCfdiService app = new StatusCfdiService("https://consultaqr.facturaelectronica.sat.gob.mx/ConsultaCFDIService.svc", "http://tempuri.org/IConsultaCFDIService/Consulta");
            StatusCfdiResponse response = null;
            //Paso de parametros para la consulta
            response = (StatusCfdiResponse) app.StatusCfdi("rfcEmisor", "rfcReceptor", "total", "uuid","dididj==");
            //Imprimimos la respuesta de la consulta
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.codigoEstatus);
            System.out.println(response.estado);
            System.out.println(response.esCancelable);
            System.out.println(response.estatusCancelacion);
            //En caso de obtener error, este puede obtenerse de los siguientes campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (GeneralException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SOAPException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>

# CFDI  Relacionados #
Servicio necesario para conocer los CFDI relacionados que existen a una factura. Con el nuevo método de cancelación, no se podrá cancelar una factura si existen CFDI que lo relacionen, en ello radica la importancia del uso de este servicio.

<details>
<summary>
CFDI Relacionados por CSD
</summary> 

Este método recibe los siguientes parametros:

- UUID
- Password del CSD
- RFC emisor
- Certificado (.cer) en Base64
- Key (.key) en Base64

**Ejemplo de consumo del servicio para consultar relacionados por CSD**
```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Relations.SWRelationsService;
import Utils.Responses.Relations.CfdiRelacionadosResponse;
import Utils.Responses.Relations.RelacionData;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) throws AuthException {
        try {
            //Instancia del servicio y autenticación
            SWRelationsService app = new SWRelationsService("user", "password", "https://services.test.sw.com.mx");
            CfdiRelacionadosResponse response = null;
            //Paso de parametros a consultar
            response = (CfdiRelacionadosResponse) app.CfdiRelacionadosCSD("uuid", "password_csd", "rfcEmisor", "cerB64", "keyB64");
            //Imprimimos la respuesta de la consulta.
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.resultado);
            LinkedList<RelacionData> padres = (LinkedList<RelacionData>) response.uuidsRelacionadosPadres;
            if (padres != null) {
                for (int i = 0; i < padres.size(); i++) {
                    RelacionData datos = padres.get(i);
                    System.out.println(datos.uuid);
                    System.out.println(datos.rfcEmisor);
                    System.out.println(datos.rfcReceptor);
                }
            }
            LinkedList<RelacionData> hijos = (LinkedList<RelacionData>) response.uuidsRelacionadosHijos;
            if (hijos != null) {
                for (int i = 0; i < hijos.size(); i++) {
                    RelacionData datos = hijos.get(i);
                    System.out.println(datos.uuid);
                    System.out.println(datos.rfcEmisor);
                    System.out.println(datos.rfcReceptor);
                }
            }
            //En caso de obtener error, este puede obtenerse de los siguientes campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

```
</details>
<details>
<summary>
CFDI Relacionados por PFX
</summary> 

Este método recibe los siguientes parametros:

- UUID
- Password del PFX
- RFC emisor
- PFX en Base64

**Ejemplo de consumo del servicio para consultar relacionados por PFX**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Relations.SWRelationsService;
import Utils.Responses.Relations.CfdiRelacionadosResponse;
import Utils.Responses.Relations.RelacionData;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.SOAPException;

public class ExampleReadme {

    public static void main(String[] args) throws AuthException {
        try {
            //Instancia del servicio y autenticación
            SWRelationsService app = new SWRelationsService("user", "password", "https://services.test.sw.com.mx");
            CfdiRelacionadosResponse response = null;
            //Paso de parametros para la consulta
            response = (CfdiRelacionadosResponse) app.CfdiRelacionadosPFX("uuid", "password_pfx", "rfcEmisor", "pfxB64");
            //Imprimimos lor resultados de la consulta
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.resultado);
            LinkedList< RelacionData> padres = (LinkedList< RelacionData>) response.uuidsRelacionadosPadres;
            if (padres != null) {
                for (int i = 0; i < padres.size(); i++) {
                    RelacionData datos = padres.get(i);
                    System.out.println(datos.uuid);
                    System.out.println(datos.rfcEmisor);
                    System.out.println(datos.rfcReceptor);
                }
            }
            LinkedList< RelacionData> hijos = (LinkedList< RelacionData>) response.uuidsRelacionadosHijos;
            if (hijos != null) {
                for (int i = 0; i < hijos.size(); i++) {
                    RelacionData datos = hijos.get(i);
                    System.out.println(datos.uuid);
                    System.out.println(datos.rfcEmisor);
                    System.out.println(datos.rfcReceptor);
                }
            }
            //En caso de obtener error, este puede obtenerse de los siguientes campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SOAPException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

```
</details>


# Consulta Pendientes por Aceptar/Rechazar #
Este servicio devuelve una lista con los UUID que tiene pendientes por Aceptación/Rechazo un RFC.

Este método sólo recibe un parametro:
- RFC del cual consultaremos las facturas que tiene por Aceptar/Rechazar.

<details>
<summary>
Ejemplo de consumo del servicio para consultar solicitudes pendientes de Aceptar/Rechazar
</summary>

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Pendings.SWPendingsService;
import Utils.Responses.Pendings.PendientesCancelarResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.SOAPException;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Instancia del servicio y autenticación
            SWPendingsService app = new SWPendingsService("user", "password", "https://services.test.sw.com.mx");
            PendientesCancelarResponse response = null;
            //Paso de parametro RFC a consultar
            response = (PendientesCancelarResponse) app.PendientesPorCancelar("EKU9003173C9");
            //Imprimimos los datos de respuesta
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.message);
            System.out.println(response.CodStatus);
            List< String> uuids = (LinkedList< String>) response.UUIDS;
            if (uuids != null) {
                for (int i = 0; i < uuids.size(); i++) {
                    String datos = uuids.get(i);
                    System.out.println(datos);
                }
            }
            //En caso de obtener error, este puede obtenerse de los siguientes campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SOAPException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>

## Aceptar/Rechazar ##
Método para Aceptar o Rechazar una o más facturas.

<details>
<summary>
Aceptar/Rechazar por CSD
</summary> 
Este método recibe los siguientes parametros:

- **Map** con los UUID y la acción a realizar
- Password del CSD
- RFC Receptor
- Certificado (.cer) en Base 64
- Llave privada (.key) en Base 64

**Ejemplo de consumo de la libreria para Aceptacion/Rechazo por CSD**
```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.AcceptReject.SWAcceptRejectService;
import Utils.Responses.AcceptReject.AceptarRechazarCancelationResponse;
import Utils.Responses.AcceptReject.CancelationData;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Instancia del servicio y autenticación
            SWAcceptRejectService app = new SWAcceptRejectService("user", "password", "https://services.test.sw.com.mx");
            //Objeto para almacenar los uuids
            Map< String, String> uuids = new HashMap< String, String>();
            //UUID a Aceptar o Rechazar
            uuids.put("06a46e4b-b154-4c12-bb77-f9a63ed55ff2", "Aceptacion");
            AceptarRechazarCancelationResponse response = null;
            //Envio de parametros al servicio
            response = (AceptarRechazarCancelationResponse) app.AceptarRechazarCancelacionCSD(uuids, "password_csd", "rfcReceptor", "b64Cer", "b64Key");
            //Imprimir datos de respuestas
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            LinkedList< CancelationData> folios = (LinkedList< CancelationData>) response.folios;
            if (folios != null) {
                for (int i = 0; i < folios.size(); i++) {
                    CancelationData datos = folios.get(i);
                    System.out.println(datos.uuid);
                    System.out.println(datos.estatusUUID);
                    System.out.println(datos.respuesta);
                }
            }
            System.out.println(response.acuse);
            //En caso de obtener error, este puede obtenerse de los siguientes campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

```
</details>

<details>
<summary>
Aceptar/Rechazar por PFX
</summary> 
Este método recibe los siguientes parametros:

- **Map** con los UUID y la acción a realizar
- Password del PFX
- RFC Receptor
- PFX en Base 64

**Ejemplo de consumo de la libreria para Aceptacion/Rechazo por PFX**
```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.AcceptReject.SWAcceptRejectService;
import Utils.Responses.AcceptReject.AceptarRechazarCancelationResponse;
import Utils.Responses.AcceptReject.CancelationData;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Instancia del servicio y autenticación
            SWAcceptRejectService app = new SWAcceptRejectService("user", "password", "https://services.test.sw.com.mx");
            //Objeto para almacenar los uuids
            Map< String, String> uuids = new HashMap< String, String>();
            //UUID a Aceptar o Rechazar
            uuids.put("06a46e4b-b154-4c12-bb77-f9a63ed55ff2", "Aceptacion");
            AceptarRechazarCancelationResponse response = null;
            //Envio de parametros al servicio
            response = (AceptarRechazarCancelationResponse) app.AceptarRechazarCancelacionPFX(uuids, "password_pfx", "rfcReceptor", "b64Pfx");
            //Imprimir datos de respuestas
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            LinkedList< CancelationData> folios = (LinkedList< CancelationData>) response.folios;
            if (folios != null) {
                for (int i = 0; i < folios.size(); i++) {
                    CancelationData datos = folios.get(i);
                    System.out.println(datos.uuid);
                    System.out.println(datos.estatusUUID);
                    System.out.println(datos.respuesta);
                }
            }
            System.out.println(response.acuse);
            //En caso de obtener error, este puede obtenerse de los siguientes campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>

<details>
<summary>
Aceptar/Rechazar por XML
</summary> 

Para el consumo a través de este método necesitaremos el XML para la Aceptación/Rechazo.

**Ejemplo de XML de Aceptación/Rechazo**
```xml
<SolicitudAceptacionRechazo xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" Fecha="2023-10-27T00:13:12" RfcPacEnviaSolicitud="LSO1306189R5" RfcReceptor="EKU9003173C9" xmlns="http://cancelacfd.sat.gob.mx"><Folios><UUID>fd74d156-b9b0-44a5-9906-e08182e8363e</UUID><Respuesta>Aceptacion</Respuesta></Folios><Signature xmlns="http://www.w3.org/2000/09/xmldsig#"><SignedInfo><CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" /><SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1" /><Reference URI=""><Transforms><Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature" /></Transforms><DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1" /><DigestValue>+orEHofYPc6O+VHtr7CiLTjLuxA=</DigestValue></Reference></SignedInfo><SignatureValue>dPu512eyVIx7QDh5sGdEnJelHYP/+gZGKE09qS5eAoxGCOsGlZzLsO3bjIHZwNFnKuV15i/uvWVn3eHkXZGNxUf63bT3sfv55Xeb1+fxy31Goq8jSfmebJw9Os0M1RlGVSjTtHvl0NRWFiUtWdoqGe0G9m57IWurcVWCtr1EPfUc/+Cw0wd+s3+zxOY2FvwG3EnBhwUCAF1PFon1x5h0GQtRARuVdwsMYyPzgQdxd/E4DMxCrRf9yqy8AosdXbogy9k06ki5xz1a+rr5sugVgZk83quMw6/l1vcXer3k6AwU34HrJ5QsoL7X5U8j+Miz8W1l1Sda2TvbXugzAQy+Ww==</SignatureValue><KeyInfo><X509Data><X509IssuerSerial><X509IssuerName>OID.1.2.840.113549.1.9.2=responsable: ACDMA-SAT, OID.2.5.4.45=2.5.4.45, L=COYOACAN, S=CIUDAD DE MEXICO, C=MX, PostalCode=06370, STREET=3ra cerrada de caliz, E=oscar.martinez@sat.gob.mx, OU=SAT-IES Authority, O=SERVICIO DE ADMINISTRACION TRIBUTARIA, CN=AC UAT</X509IssuerName><X509SerialNumber>292233162870206001759766198462772978647764840758</X509SerialNumber></X509IssuerSerial><X509Certificate>MIIFsDCCA5igAwIBAgIUMzAwMDEwMDAwMDA1MDAwMDM0MTYwDQYJKoZIhvcNAQELBQAwggErMQ8wDQYDVQQDDAZBQyBVQVQxLjAsBgNVBAoMJVNFUlZJQ0lPIERFIEFETUlOSVNUUkFDSU9OIFRSSUJVVEFSSUExGjAYBgNVBAsMEVNBVC1JRVMgQXV0aG9yaXR5MSgwJgYJKoZIhvcNAQkBFhlvc2Nhci5tYXJ0aW5lekBzYXQuZ29iLm14MR0wGwYDVQQJDBQzcmEgY2VycmFkYSBkZSBjYWxpejEOMAwGA1UEEQwFMDYzNzAxCzAJBgNVBAYTAk1YMRkwFwYDVQQIDBBDSVVEQUQgREUgTUVYSUNPMREwDwYDVQQHDAhDT1lPQUNBTjERMA8GA1UELRMIMi41LjQuNDUxJTAjBgkqhkiG9w0BCQITFnJlc3BvbnNhYmxlOiBBQ0RNQS1TQVQwHhcNMjMwNTE4MTE0MzUxWhcNMjcwNTE4MTE0MzUxWjCB1zEnMCUGA1UEAxMeRVNDVUVMQSBLRU1QRVIgVVJHQVRFIFNBIERFIENWMScwJQYDVQQpEx5FU0NVRUxBIEtFTVBFUiBVUkdBVEUgU0EgREUgQ1YxJzAlBgNVBAoTHkVTQ1VFTEEgS0VNUEVSIFVSR0FURSBTQSBERSBDVjElMCMGA1UELRMcRUtVOTAwMzE3M0M5IC8gVkFEQTgwMDkyN0RKMzEeMBwGA1UEBRMVIC8gVkFEQTgwMDkyN0hTUlNSTDA1MRMwEQYDVQQLEwpTdWN1cnNhbCAxMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtmecO6n2GS0zL025gbHGQVxznPDICoXzR2uUngz4DqxVUC/w9cE6FxSiXm2ap8Gcjg7wmcZfm85EBaxCx/0J2u5CqnhzIoGCdhBPuhWQnIh5TLgj/X6uNquwZkKChbNe9aeFirU/JbyN7Egia9oKH9KZUsodiM/pWAH00PCtoKJ9OBcSHMq8Rqa3KKoBcfkg1ZrgueffwRLws9yOcRWLb02sDOPzGIm/jEFicVYt2Hw1qdRE5xmTZ7AGG0UHs+unkGjpCVeJ+BEBn0JPLWVvDKHZAQMj6s5Bku35+d/MyATkpOPsGT/VTnsouxekDfikJD1f7A1ZpJbqDpkJnss3vQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAFaUgj5PqgvJigNMgtrdXZnbPfVBbukAbW4OGnUhNrA7SRAAfv2BSGk16PI0nBOr7qF2mItmBnjgEwk+DTv8Zr7w5qp7vleC6dIsZFNJoa6ZndrE/f7KO1CYruLXr5gwEkIyGfJ9NwyIagvHHMszzyHiSZIA850fWtbqtythpAliJ2jF35M5pNS+YTkRB+T6L/c6m00ymN3q9lT1rB03YywxrLreRSFZOSrbwWfg34EJbHfbFXpCSVYdJRfiVdvHnewN0r5fUlPtR9stQHyuqewzdkyb5jTTw02D2cUfL57vlPStBj7SEi3uOWvLrsiDnnCIxRMYJ2UA2ktDKHk+zWnsDmaeleSzonv2CHW42yXYPCvWi88oE1DJNYLNkIjua7MxAnkNZbScNw01A6zbLsZ3y8G6eEYnxSTRfwjd8EP4kdiHNJftm7Z4iRU7HOVh79/lRWB+gd171s3d/mI9kte3MRy6V8MMEMCAnMboGpaooYwgAmwclI2XZCczNWXfhaWe0ZS5PmytD/GDpXzkX0oEgY9K/uYo5V77NdZbGAjmyi8cE2B2ogvyaN2XfIInrZPgEffJ4AB7kFA2mwesdLOCh0BLD9itmCve3A1FGR4+stO2ANUoiI3w3Tv2yQSg4bjeDlJ08lXaaFCLW2peEXMXjQUk7fmpb5MNuOUTW6BE=</X509Certificate></X509Data></KeyInfo></Signature></SolicitudAceptacionRechazo>
```
**Ejemplo de consumo de la libreria para Aceptacion/Rechazo por XML**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.AcceptReject.SWAcceptRejectService;
import Utils.Responses.AcceptReject.AceptarRechazarCancelationResponse;
import Utils.Responses.AcceptReject.CancelationData;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Instancia del servicio y autenticación
            SWAcceptRejectService app = new SWAcceptRejectService("user", "password", "https://services.test.sw.com.mx");
            AceptarRechazarCancelationResponse response = null;
            //Envio de parametros al servicio
            response = (AceptarRechazarCancelationResponse) app.AceptarRechazarCancelacionXML("xml");
            //Imprimir datos de respuestas
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            LinkedList< CancelationData> folios = (LinkedList< CancelationData>) response.folios;
            if (folios != null) {
                for (int i = 0; i < folios.size(); i++) {
                    CancelationData datos = folios.get(i);
                    System.out.println(datos.uuid);
                    System.out.println(datos.estatusUUID);
                    System.out.println(datos.respuesta);
                }
            }
            System.out.println(response.acuse);
            //En caso de obtener error, este puede obtenerse de los siguientes campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>

<details>
<summary>
Aceptar/Rechazar por UUID
</summary> 
Este método recibe los siguientes parametros:

- UUID
- RFC Receptor
- Acción a realizar (Aceptacion o Rechazo)

**Ejemplo de consumo de la libreria para Aceptacion/Rechazo por UUID**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.AcceptReject.SWAcceptRejectService;
import Utils.Responses.AcceptReject.AceptarRechazarCancelationResponse;
import Utils.Responses.AcceptReject.CancelationData;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Instancia del servicio y autenticación
            SWAcceptRejectService app = new SWAcceptRejectService("user", "password", "https://services.test.sw.com.mx");
            AceptarRechazarCancelationResponse response = null;
            //Envio de parametros al servicio
            response = (AceptarRechazarCancelationResponse) app.AceptarRechazarCancelacionUUID("uuid","rfcReceptor","Rechazo");
            //Imprimir datos de respuestas
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            LinkedList< CancelationData> folios = (LinkedList< CancelationData>) response.folios;
            if (folios != null) {
                for (int i = 0; i < folios.size(); i++) {
                    CancelationData datos = folios.get(i);
                    System.out.println(datos.uuid);
                    System.out.println(datos.estatusUUID);
                    System.out.println(datos.respuesta);
                }
            }
            System.out.println(response.acuse);
            //En caso de obtener error, este puede obtenerse de los siguientes campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
            
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>

# Recuperar XML por UUID #
Método para recuperar la información de un XML enviando el UUID de la factura.
<details>
<summary>
Ejemplo de consumo de la libreria para Recuperar un XML por UUID
</summary> 

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Storage.SWStorageService;
import Utils.Responses.Storage.StorageResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Instancia del servicio y autenticación
            SWStorageService storage = new SWStorageService("user", "password","http://services.test.sw.com.mx" "https://api.test.sw.com.mx", null, 0);
            //Paso de parametro UUID
            StorageResponse response = (StorageResponse) storage.getXml(UUID.fromString("c75f87db-e059-4a7c-a922-e4b9c871e8c1"));
            //Imprimimos los datos de la respuesta de la solicitud
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.getData);
            //En caso de obtener un error, este puede obtenerse de los campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);

        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>

# Servicio PDF #    
<details>
<summary>
Generar PDF 
</summary> 
Servicio para generar PDF de un XML previamente timbrado. 
Se permite especificar una de las plantillas genericas o una plantilla personalizada en caso de contar con una.

Este método recibe los siguientes parametros:

- XML en formato String previamente timbrado
- Plantilla a usar (TemplateId)
- Logo Base64 (opcional)
- Extras (opcional)

**Ejemplo de consumo de la libreria para Generar PDF**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Pdf.SWPdfService;
import Utils.Responses.Pdf.PdfResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Creamos una instancia de tipo PDF y realizamos autenticación
            SWPdfService pdf = new SWPdfService("user", "password", "https://api.test.sw.com.mx", "http://services.test.sw.com.mx");
            //Obtenemos el xml
            String xmlcontent = new String(Files.readAllBytes(Paths.get("cfdi_pdf.xml")), "UTF-8");
            //Creamos un arreglo de objetos donde se mencionan las observaciones y/o datos extras
            HashMap<String, String> extras = new HashMap<String, String>();
            extras.put("Observaciones", "Entregar de 9am a 6pm");
            PdfResponse response = null;
            //Realizamos la petición de generacion al servicio.
            response = (PdfResponse) pdf.GeneratePdf(xmlcontent, "cfdi40", "logoB64", extras);
            //Imprimimos el resultado de la generacion
            System.out.println(response.Status);
            System.out.println(response.contentB64);
            //En caso de obtener un error, este puede obtenerse de los campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

```
</details>
<details>
<summary>
Regenerar PDF 
</summary> 
El servicio podrá generar o regenerar un PDF de un CFDI previamente timbrados y podrá guardar o remplazar el archivo PDF para ser visualizado posteriormente desde el portal de Smarter. Puede ser consumido ingresando tu usuario y contraseña así como tambien ingresando solo el token. 

Este método recibe solo un parámetro:
- UUID

**Ejemplo de consumo de la libreria para Regenerar PDF**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Pdf.SWPdfService;
import Utils.Responses.Pdf.PdfResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Creamos una instancia de tipo PDF y realizamos autenticación
            SWPdfService pdf = new SWPdfService("user", "password", "https://api.test.sw.com.mx", "http://services.test.sw.com.mx");
            PdfResponse response = null;
            response = (PdfResponse) pdf.RegeneratePdf("4714f6f7-ccb4-4eb5-8ba6-3a523092e2b4");
            //Imprimimos el resultado de la generacion
            System.out.println(response.Status);
            System.out.println(response.contentB64);
            //En caso de obtener un error, este puede obtenerse de los campos
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>

:pushpin: ***NOTA:*** Existen varias plantillas genéricas que puedes usar
| TemplateID     |             CFDI                | 
|----------------|---------------------------------|
|  cfdi40        |Factura ingreso, egreso          | 
|  payroll40     | Nómina                          | 
|  payment20     | Pagos 2.0                       | 
|  billoflading40| Carta porte 2.0                 |

Puedes encontrar más informacion en nuestro artículo [Plantillas PDF](https://developers.sw.com.mx/knowledge-base/plantillas-pdf/).


</details>

# Certificados # 
Servicio para gestionar los certificados CSD de tu cuenta, será posible cargar, consultar y eliminar los certificados. Para administrar los certificados de manera gráfica, puede hacerlo desde el [Administrador de timbres](https://portal.sw.com.mx/).   
<details>
<summary>
Cargar certificado
</summary> 
Método para cargar un certificado en la cuenta.

Este metodo recibe los siguientes parametros:
- CSD (.cer) en Base64
- Llave privada (.key) en Base64
- Contraseña del certificado

**Ejemplo de consumo de la libreria para Cargar CSD**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Csd.SWCsdService;
import Utils.Responses.Csd.CsdResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
        //Creamos la instancia del servicio y nos autenticamos con user y password   
		SWCsdService app = new SWCsdService("user", "password", "https://services.test.sw.com.mx");
		CsdResponse response = null;
		response = (CsdResponse) app.UploadMyCsd("cerB64", "keyB64", "password_csd", "stamp", true);
		//Imprimimos los datos de la consulta
        System.out.println(response.HttpStatusCode);
		System.out.println(response.data);
		System.out.println(response.Status);
        //En caso de obtener un error, este puede obtenerse de los campos
		System.out.println(response.message);
		System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>

<details>
<summary>
Consultar todos los certificados
</summary> 
Método para consultar todos los certificados cargados en la cuenta.

Este metodo solo requiere de accesar con usuario y contraseña o con token

**Ejemplo de consumo de la libreria para Consultar todos los Certificados**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Csd.SWCsdService;
import Utils.Responses.Csd.InfoCsd;
import Utils.Responses.Csd.ListInfoCsdResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Creamos la instancia del servicio y nos autenticamos con user y password  
            SWCsdService app = new SWCsdService("user", "password", "https://services.test.sw.com.mx");
            ListInfoCsdResponse response = null;
            //Llamamos el metodo para mostrar la lista de certificados
            response = (ListInfoCsdResponse) app.GetListCsd();
            //Imprimimos los datos de la consulta
            System.out.println(response.HttpStatusCode);
            List<InfoCsd> lista = response.data;
            if (lista != null) {
                for (int i = 0; i < lista.size(); i++) {
                    InfoCsd dato = lista.get(i);
                    System.out.println("Número certificado: " + dato.certificateNumber);
                    System.out.println("Tipo certificado: " + dato.certificateType);
                    System.out.println("Está activo?: " + dato.isActive);
                    System.out.println("Razón social: " + dato.issuerBussinesName);
                    System.out.println("Rfc: " + dato.issuerRfc);
                    System.out.println("Valido desde: " + dato.validFrom);
                    System.out.println("Valido hasta: " + dato.validTo + "\n");
                }
            }
            System.out.println(response.Status);
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

```
</details>

<details>
<summary>
Consultar certificados por NoCertificado
</summary> 
Método para obtener un certificado cargado enviando como parámetro el número de certificado.

Este metodo solo recibe el parametro:

- Número de certificado a obtener

**Ejemplo de consumo de la libreria para Consultar un certificado por su numero**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Csd.SWCsdService;
import Utils.Responses.Csd.InfoCsd;
import Utils.Responses.Csd.InfoCsdResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
            //Creamos la instancia del servicio y nos autenticamos con user y password  
            SWCsdService app = new SWCsdService("user", "password", "https://services.test.sw.com.mx");
            InfoCsdResponse response = null;
            //Pasamos el numero de certificado a buscar
            response = (InfoCsdResponse) app.SearchMyCsd("30001000000500003416");
            //Imprimimos los datos de la consulta
            InfoCsd dato = (InfoCsd) response.data;
            System.out.println(response.HttpStatusCode);
            System.out.println("Número certificado: " + dato.certificateNumber);
            System.out.println("Tipo certificado: " + dato.certificateType);
            System.out.println("Está activo?: " + dato.isActive);
            System.out.println("Razón social: " + dato.issuerBussinesName);
            System.out.println("Rfc: " + dato.issuerRfc);
            System.out.println("Valido desde: " + dato.validFrom);
            System.out.println("Valido hasta: " + dato.validTo);
            System.out.println(response.Status);
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>

<details>
<summary>
Consultar certificados por RFC
</summary> 
Método para obtener los certificados cargados en la cuenta,  enviando como parámetro el RFC.

Este metodo solo recibe el parametro:

- RFC de los certificados a obtener

**Ejemplo de consumo de la libreria para Consultar un certificado por el RFC ligado a él**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Csd.SWCsdService;
import Utils.Responses.Csd.InfoCsd;
import Utils.Responses.Csd.ListInfoCsdResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
        //Creamos la instancia del servicio y nos autenticamos con user y password  
        SWCsdService app = new SWCsdService("user", "password", "https://services.test.sw.com.mx");
        //Obtenemos la respuesta del servicio GetListCsdByRfc enviandole un parametro
		ListInfoCsdResponse response = null;
		response = (ListInfoCsdResponse) app.GetListCsdByRfc("EKU9003173C9");
        //Mostramos el codigo que obtuvimos en la solicitud
		System.out.println(response.HttpStatusCode);
        //Mostramos los datos obtenidos de la respuesta
		List<InfoCsd> lista = response.data;
		if(lista != null) {
			for(int i=0; i<lista.size(); i++) {
				InfoCsd dato = lista.get(i);
				System.out.println("Número certificado: " + dato.certificateNumber);
				System.out.println("Tipo certificado: " + dato.certificateType);
				System.out.println("Está activo?: " + dato.isActive);
				System.out.println("Razón social: " + dato.issuerBussinesName);
				System.out.println("Rfc: " + dato.issuerRfc);
				System.out.println("Valido desde: " + dato.validFrom);
				System.out.println("Valido hasta: " + dato.validTo + "\n");
			}
		}
        //Mostramos los demas datos de la respuesta
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>

<details>
<summary>
Eliminar certificado
</summary> 
Método para eliminar un certificado de la cuenta.

Este metodo solo recibe el parametro:
- Número de certificado a eliminar

**Ejemplo de consumo de la libreria para Eliminar un certificado por su numero**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Csd.SWCsdService;
import Utils.Responses.Csd.CsdResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
        //Creamos la instancia del servicio y nos autenticamos con user y password  
		SWCsdService app = new SWCsdService("user", "password", "https://services.test.sw.com.mx");
		CsdResponse response = null;
         //Pasamos como parametro del numero de certificado que deseamos eliminar
		response = (CsdResponse) app.DisableMyCsd("30001000000400002321");
		//Imprimimos los datos de la consulta
        System.out.println(response.HttpStatusCode);
		System.out.println(response.data);
		System.out.println(response.Status);
        //En caso de obtener un error, este puede obtenerse de los campos
		System.out.println(response.message);
		System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>

# Administración de Usuarios # 
API para gestionar y consultar los usuarios cliente de una cuenta.
<details>
<summary>
Crear Usuario
</summary> 
Método para crear un nuevo usuario.

Este metodo recibe los siguientes parametros:
- Email
- Nombre
- Password
- RFC
- Profile
- Stamps
- Unlimited
- Active

**Ejemplo de consumo de la libreria para crear una cuenta nueva**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Account.AccountUser.SWAccountUserService;
import Utils.Helpers.EnumAccountUser.AccountUserProfiles;
import Utils.Responses.Account.AccountUser.AccountUserResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
        //Creamos la instancia del servicio y nos autenticamos con user y password   
        SWAccountUserService app = new SWAccountUserService ("user", "password", "https://services.test.sw.com.mx","https://api.test.sw.com.mx",null, 0)
		ccountUserResponse<String> response = null;
			response = (AccountUserResponse<String>) app.CrearUsuario("usuario_nuevo", "password_nuevo", "Prueba SW Java 1.6", "CACX7605101P8", 20, AccountUserProfiles.Hijo, false, true);
		//Imprimimos los datos de la solicitud
        System.out.println(response.HttpStatusCode);
		System.out.println(response.data);
		System.out.println(response.Status);
        //En caso de obtener un error, este puede obtenerse de los campos
		System.out.println(response.message);
		System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>
<details>
<summary>
Actualizar Usuario
</summary> 
Método para actualizar datos de un usuario.

Este metodo recibe los siguientes parametros:
- Id Usuario
- Nombre
- RFC
- Unlimited
- Active

**Ejemplo de consumo de la libreria para modificar una cuenta**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Account.AccountUser.SWAccountUserService;
import Utils.Responses.Account.AccountUser.AccountUserResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
        //Creamos la instancia del servicio y nos autenticamos con user y password   
        SWAccountUserService app = new SWAccountUserService ("user", "password", "https://services.test.sw.com.mx","https://api.test.sw.com.mx",null, 0)
		ccountUserResponse<String> response = null;
			response = (AccountUserResponse<String>) app.ActualizarUsuario(UUID.fromString("be2a859c-cd5f-42b5-b35d-f065b3dfecac4"),
				"Prueba", "RAQÑ7701212M3", false, true);
		//Imprimimos los datos de la solicitud
        System.out.println(response.HttpStatusCode);
		System.out.println(response.data);
		System.out.println(response.Status);
        //En caso de obtener un error, este puede obtenerse de los campos
		System.out.println(response.message);
		System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>
<details>
<summary>
Eliminar usuario
</summary> 
Método para eliminar un usuario.

Este metodo solo recibe el parametro:
- ID Usuario

**Ejemplo de consumo de la libreria para Eliminar un usuario por su numero de identificacion**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Account.AccountUser.SWAccountUserService;
import Utils.Responses.Account.AccountUser.AccountUserResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
        //Creamos la instancia del servicio y nos autenticamos con user y password   
        SWAccountUserService app = new SWAccountUserService ("user", "password", "https://services.test.sw.com.mx","https://api.test.sw.com.mx",null, 0)
		ccountUserResponse<String> response = null;
			response = (AccountUserResponse<String>) app.EliminarUsuario(UUID.fromString("be2a859c-cd5f-42b5-b35d-f065b3dfecac4"));
		//Imprimimos los datos de la solicitud
        System.out.println(response.HttpStatusCode);
		System.out.println(response.data);
		System.out.println(response.Status);
        //En caso de obtener un error, este puede obtenerse de los campos
		System.out.println(response.message);
		System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>
<details>
<summary>
Consultar todos los usuarios de una cuenta
</summary> 
Método para consultar todos las cuentas hijas.

Este metodo muestra los datos de la consulta de forma paginada, por ello se debe agregar:
- Page (numero de página a consultar)
- PageSize (numero de registros por página)

**Ejemplo de consumo de la libreria para Consultar todos los Certificados**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Account.AccountUser.SWAccountUserService;
import Utils.Responses.Account.AccountUser.DataAccountUser;
import Utils.Responses.Account.AccountUser.ListDataAccountUserResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
        //Creamos la instancia del servicio y nos autenticamos con user y password   
        SWAccountUserService app = new SWAccountUserService ("user", "password", "https://services.test.sw.com.mx","https://api.test.sw.com.mx",null, 0)
		AccountUserResponse<List<DataAccountUser>> response = null;
		response = (AccountUserResponse<List<DataAccountUser>>) app.ObtenerUsuarios(1, 10);
		System.out.println(response.HttpStatusCode);
		List<DataAccountUser> lista = response.data;
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				DataAccountUser dato = lista.get(i);
				System.out.println("Email: " + dato.email);
				System.out.println("Password: " + dato.password);
				System.out.println("Nombre: " + dato.name);
				System.out.println("Perfil: " + dato.profile);
				System.out.println("Stamps: " + dato.stamps);
				System.out.println("idUsuario: " + dato.idUsuario);
				System.out.println("Rfc: " + dato.apellidoPaterno);
				System.out.println("Ilimitado: " + dato.unlimited);
				System.out.println("Activo: " + dato.activo + "\n");
			}
		}
		//Imprimimos los datos de la solicitud
        System.out.println(response.HttpStatusCode);
		System.out.println(response.Status);
        //En caso de obtener un error, este puede obtenerse de los campos
		System.out.println(response.message);
		System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

```
</details>

<details>
<summary>
Consultar certificados por ID Usuario
</summary> 
Método para consultar la informacion de una cuenta.

Este metodo requiere sólo un parametro:
- Id Usuario

**Ejemplo de consumo de la libreria para Consultar la informacion de una cuenta**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Account.AccountUser.SWAccountUserService;
import Utils.Responses.Account.AccountUser.DataAccountUser;
import Utils.Responses.Account.AccountUserDataAccountUserResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
        //Creamos la instancia del servicio y nos autenticamos con user y password   
        SWAccountUserService app = new SWAccountUserService ("user", "password", "https://services.test.sw.com.mx","https://api.test.sw.com.mx",null, 0)
		AccountUserResponse<DataAccountUser> response = null;
		response = (AccountUserResponse<DataAccountUser>) app.ObtenerInfoUsuarioId(UUID.fromString("be2a859c-cd5f-42b5-b35d-f058b3a9aac4"));
		DataAccountUser usuario = response.data;
		if (usuario != null) {
			System.out.println("Email: " + usuario.email);
			System.out.println("Password: " + usuario.password);
			System.out.println("Nombre: " + usuario.name);
			System.out.println("Perfil: " + usuario.profile);
			System.out.println("Stamps: " + usuario.stamps);
			System.out.println("idUsuario: " + usuario.idUsuario);
			System.out.println("Rfc: " + usuario.apellidoPaterno);
			System.out.println("Ilimitado: " + usuario.unlimited);
			System.out.println("Activo: " + usuario.activo + "\n");
		}
		//Imprimimos los datos de la solicitud
        System.out.println(response.HttpStatusCode);
		System.out.println(response.Status);
        //En caso de obtener un error, este puede obtenerse de los campos
		System.out.println(response.message);
		System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

```
</details>

<details>
<summary>
Consultar certificados por token
</summary> 
Método para obtener la informacion de una cuenta por el medio de autenticacion ya sea por token o usuario y contraseña.


**Ejemplo de consumo de la libreria para Consultar la informacion de una cuenta**

```java
package com.mycompany.examplereadme;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Account.AccountUser.SWAccountUserService;
import Utils.Responses.Account.AccountUser.DataAccountUser;
import Utils.Responses.Account.AccountUserDataAccountUserResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleReadme {

    public static void main(String[] args) {
        try {
        //Creamos la instancia del servicio y nos autenticamos con user y password   
        SWAccountUserService app = new SWAccountUserService ("user", "password", "https://services.test.sw.com.mx","https://api.test.sw.com.mx",null, 0)
		AccountUserResponse<DataAccountUser> response = null;
		response = (AccountUserResponse<DataAccountUser>) app.ObtenerInfoUsuario();
		DataAccountUser usuario = response.data;
		if (usuario != null) {
			System.out.println("Email: " + usuario.email);
			System.out.println("Password: " + usuario.password);
			System.out.println("Nombre: " + usuario.name);
			System.out.println("Perfil: " + usuario.profile);
			System.out.println("Stamps: " + usuario.stamps);
			System.out.println("idUsuario: " + usuario.idUsuario);
			System.out.println("Rfc: " + usuario.apellidoPaterno);
			System.out.println("Ilimitado: " + usuario.unlimited);
			System.out.println("Activo: " + usuario.activo + "\n");
		}
		//Imprimimos los datos de la solicitud
        System.out.println(response.HttpStatusCode);
		System.out.println(response.Status);
        //En caso de obtener un error, este puede obtenerse de los campos
		System.out.println(response.message);
		System.out.println(response.messageDetail);
        } catch (AuthException | GeneralException | IOException ex) {
            Logger.getLogger(ExampleReadme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```
</details>


---


Para mayor referencia de un listado completo de los servicios favor de visitar nuestro [sitio developers](https://developers.sw.com.mx/).

Si deseas contribuir a la librería o tienes dudas envianos un correo a soporte@sw.com.mx.