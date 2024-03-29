[![Smarter Web](https://sw.com.mx/hubfs/Webpage/logo-swsapien.svg)](http://sw.com.mx/)

# SDK JAVA #

Librería JAVA para el consumo de los servicios de SW sapien®.

Registrate en sw.com.mx

## Requirementos ##

Java 1.6 o superior

## Dependencias ##
* [org.json](http://www.json.org/java)
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
            <version>0.0.4.1</version>
        </dependency>

</dependencies>
```
## Instalación manual (sin Maven) ##
Descargar el modulo directamente de los siguientes links:

* [Relase Github](https://github.com/lunasoft/sw-sdk-java/releases)
* [Maven](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=mx.com.sw.services&a=SW-JAVA&v=0.0.3.4&e=jar)

Así como instalar manualmente cada una de las dependencias:
* [org.json](http://www.json.org/java)
* [httpclient 4.3.6](http://hc.apache.org/downloads.cgi)
* [httpmime 4.3.6](http://hc.apache.org/downloads.cgi)
* [httpasyncclient 4.0.2](http://hc.apache.org/downloads.cgi)

Alternativamente, tambien se cuenta con un archivo JAR en el que se incluyen todas las dependencias, este se encuentra en la carpeta [Relase Github](https://github.com/lunasoft/sw-sdk-java/releases),  con el sufijo _"jar-with-dependencies.jar"_


# Timbrado #
<details>
<summary><b>Timbrado</b></summary>
Métodos mediante los cuales se envía un XML previamente sellado.
<details><summary><b>Timbrado CFDI V1</b></summary>
    
<br>El método **TimbrarV1** recibe el contenido de un **XML** ya emitido (sellado) en formato **String**  o tambien puede ser en **Base64**, posteriormente si la factura y el token son correctos devuelve el complemento timbre en un string (**TFD**), en caso contrario lanza una excepción.

**Timbrar XML en formato string utilizando usuario y contraseña**
```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWStampService sdk = new SWStampService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV1Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el método "Stamp", que timbrara nuestro comprobante posteriormente sellado, así como la versión del servicio de timbrado,
    //puede ver más de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV1Response) sdk.Stamp(stringXML, "v1");
    //El objeto response tendrá así los atributos:
    // Status: estado de la petición procesada, puede ser: "success", "fail", "error"
    // HttpStatusCode: Código  de respuesta HTTP del servidor: eg. 200, 400, 500
    // Data: Cuerpo de la respuesta que arroja el servidor
    //En este caso arrojará el complemento timbre: {"tfd":"<Complemento>"}
    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.tfd);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

**Timbrar XML en formato string utilizando token**

```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWStampService sdk = new SWStampService("T2lYQ0t4L0R....", "http://services.test.sw.com.mx");
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV1Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el método "Stamp", que timbrara nuestro comprobante posteriormente sellado, así como la versión del servicio de timbrado,
    //puede ver más de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV1Response) sdk.Stamp(stringXML, "v1");
    //El objeto response tendrá así los atributos:
    // Status: estado de la petición procesada, puede ser:  "success", "fail", "error"
    // HttpStatusCode: Código  de respuesta HTTP del servidor: eg. 200, 400, 500
    // Data: Cuerpo de la respuesta que arroja el servidor
    //En este caso arrojará el complemento timbre: {"tfd":"<Complemento>"}
    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.tfd);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```
</details>


<details>
<summary>
Timbrado CFDI V2
</summary>
<br>TimbrarV2 Recibe el contenido de un XML ya emitido (sellado) en formato String , posteriormente si la factura y el token son correctos devuelve el CFDI ya timbrado, en caso contrario lanza una excepción.

**Timbrar XML en formato string utilizando usuario y contraseña**
```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWStampService sdk = new SWStampService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV2Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el método "Stamp", que timbrara nuestro comprobante posteriormente sellado, así como la versión del servicio de timbrado,
    //puede ver más de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV2Response) sdk.Stamp(stringXML, "v2");
    //El objeto response tendrá así los atributos:
    // Status: estado de la petición procesada, puede ser: "success", "fail", "error"
    // HttpStatusCode: Código de respuesta HTTP del servidor: eg. 200, 400, 500
    // Data: Cuerpo de la respuesta que arroja el servidor

    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.tfd);
    System.out.println(response.cfdi);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

**Timbrar XML en formato string utilizando token**

```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWStampService sdk = new SWStampService("T2lYQ0t4L0R....", "http://services.test.sw.com.mx");
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV2Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el método "Stamp", que timbrara nuestro comprobante posteriormente sellado, así como la versión del servicio de timbrado,
    //puede ver más de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV2Response) sdk.Stamp(stringXML, "v2");
    //El objeto response tendrá así los atributos:
    // Status: estado de la petición procesada, puede ser:  "success", "fail", "error"
    // HttpStatusCode: Código de respuesta HTTP del servidor: eg. 200, 400, 500
    // Data: Cuerpo de la respuesta que arroja el servidor

    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.tfd);
    System.out.println(response.cfdi);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```
</details>

<details>
<summary>
Timbrado CFDI V3
</summary>
<br>TimbrarV3 Recibe el contenido de un XML ya emitido (sellado) en formato String , posteriormente si la factura y el token son correctos devuelve el CFDI ya timbrado, en caso contrario lanza una excepción.

**Timbrar XML en formato string utilizando usuario y contraseña**
```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWStampService sdk = new SWStampService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV3Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el método "Stamp", que timbrara nuestro comprobante posteriormente sellado, así como la versión del servicio de timbrado,
    //puede ver más de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV3Response) sdk.Stamp(stringXML, "v3");
    //El objeto response tendra así los atributos:
    // Status: estado de la petición procesada, puede ser: "success", "fail", "error"
    // HttpStatusCode: Código de respuesta HTTP del servidor: eg. 200, 400, 500
    // Data: Cuerpo de la respuesta que arroja el servidor

    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);

    System.out.println(response.cfdi);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

**Timbrar XML en formato string utilizando token**

```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWStampService sdk = new SWStampService("T2lYQ0t4L0R....", "http://services.test.sw.com.mx");
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV3Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el método "Stamp", que timbrara nuestro comprobante posteriormente sellado, así como la versión del servicio de timbrado,
    //puede ver más de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV3Response) sdk.Stamp(stringXML, "v3");
    //El objeto response tendrá así los atributos:
    // Status: estado de la petición procesada, puede ser: "success", "fail", "error"
    // HttpStatusCode: Código de respuesta HTTP del servidor: eg. 200, 400, 500
    // Data: Cuerpo de la respuesta que arroja el servidor

    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.cfdi);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```
</details>

<details>
<summary>
Timbrado CFDI V4
</summary>
<br>TimbrarV4 Recibe el contenido de un XML ya emitido (sellado) en formato String , posteriormente si la factura y el token son correctos devuelve el CFDI ya timbrado, y campos extras como qr en formato base64, entre otros.

**Timbrar XML en formato string utilizando usuario y contraseña**
```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWStampService sdk = new SWStampService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
    //Se inicializa un objeto response, que obtendrá la respuesta del api
    SuccessV4Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el método "Stamp", que timbrara nuestro comprobante posteriormente sellado, así como la versión del servicio de timbrado,
    //puede ver más de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV4Response) sdk.Stamp(stringXML, "v4");
    //El objeto response tendra así los atributos:
    // Status: estado de la petición procesada, puede ser:  "success", "fail", "error"
    // HttpStatusCode: Código  de respuesta HTTP del servidor: eg. 200, 400, 500
    // Data: Cuerpo de la respuesta que arroja el servidor

    System.out.println(response.message);
    System.out.println(response.Status);
    System.out.println(response.cfdi);
    System.out.println(response.qrCode);
    System.out.println(response.cadenaOriginalSAT);
    System.out.println(response.selloCFDI);
    System.out.println(response.selloSAT);
    System.out.println(response.noCertificadoCFDI);
    System.out.println(response.noCertificadoSAT);
    System.out.println(response.fechaTimbrado);
    System.out.println(response.uuid);
    System.out.println(response.cfdi);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

**Timbrar XML en formato string utilizando token**

```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWStampService sdk = new SWStampService("T2lYQ0t4L0R....", "http://services.test.sw.com.mx");
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV4Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el método "Stamp", que timbrara nuestro comprobante posteriormente sellado, así como la versión del servicio de timbrado,
    //puede ver más de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV4Response) sdk.Stamp(stringXML, "v4");
    //El objeto response tendra así los atributos:
    // Status: estado de la petición procesada, puede ser:  "success", "fail", "error"
    // HttpStatusCode: Código  de respuesta HTTP del servidor: eg. 200, 400, 500
    // Data: Cuerpo de la respuesta que arroja el servidor

    System.out.println(response.message);
    System.out.println(response.Status);
    System.out.println(response.qrCode);
    System.out.println(response.cadenaOriginalSAT);
    System.out.println(response.selloCFDI);
    System.out.println(response.selloSAT);
    System.out.println(response.noCertificadoCFDI);
    System.out.println(response.noCertificadoSAT);
    System.out.println(response.fechaTimbrado);
    System.out.println(response.uuid);
    System.out.println(response.cfdi);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```
</details>
</details>
<br>
<details>
<summary>
<b>Emision Timbrado JSON<b>
</summary>

Métodos mediante los cuales se envía un string o byte array de un JSON. 
<details>
<summary>
Timbrado JSON V1
</summary>

* Recibe un String o byte array del JSON.
* Retorna TFD de la factura.
* Ejemplo de uso

```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWIssueService api = new SWIssueService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV1Response response = null;
    response = (SuccessV1Response) api.IssueJson(stringJson, "v1");
    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.tfd);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```
</details>

<details>
<summary>
Timbrado JSON V2
</summary>

* Recibe un String o byte array del JSON.
* Retorna TFD de la factura.
* Ejemplo de uso

```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWIssueService api = new SWIssueService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV1Response response = null;
    response = (SuccessV1Response) api.IssueJson(stringJson, "v2");
    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.tfd);
    System.out.println(response.cfdi);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```
</details>
<details>
<summary>
Timbrado JSON V3
</summary>

* Recibe un String o byte array del JSON.
* Retorna CFDI y el TFD ya unidos.
* Ejemplo de uso

```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWIssueService api = new SWIssueService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV1Response response = null;
    response = (SuccessV1Response) api.IssueJson(stringJson, "v3");
    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.cfdi);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```
</details>

<details>
<summary>
Timbrado JSON V4
</summary>

* Recibe un String o byte array del JSON.
* Retorna CFDI, TFD, CadenaOriginal, noCertificadoSat, noCertificadoCFDI, UUID, selloSAT, selloCFDI, fechaTimbrado y QR.
* Ejemplo de uso

```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWIssueService api = new SWIssueService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV1Response response = null;
    response = (SuccessV1Response) api.IssueJson(stringJson, "v4");
    System.out.println(response.Status);
	System.out.println(response.cfdi);
	System.out.println(response.qrCode);
	System.out.println(response.cadenaOriginalSAT);
	System.out.println(response.selloCFDI);
	System.out.println(response.selloSAT);
	System.out.println(response.noCertificadoCFDI);
	System.out.println(response.noCertificadoSAT);
	System.out.println(response.fechaTimbrado);
	System.out.println(response.uuid);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```
</details>
</details>
<br>
<details>
<summary>
Emisión Timbrado
</summary>

Métodos mediante los cuales se envía un XML sin sellar. 

<details>
<summary>
Emision Timbrado V1
</summary>

* Recibe un String o byte array del XML.
* Retorna TFD de la factura.
* Ejemplo de uso
```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWIssueService api = new SWIssueService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV1Response response = null;
    response = (SuccessV1Response) api.IssueXml(stringXml, "v1");
    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.tfd);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```
</details>

<details>
<summary>
Emisión Timbrado V2
</summary>

* Recibe un String o byte array del XML.
* Retorna TFD y el CFDI.
* Ejemplo de uso

```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWIssueService api = new SWIssueService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV1Response response = null;
    response = (SuccessV1Response) api.IssueXml(stringXml, "v2");
    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.tfd);
    System.out.println(response.cfdi);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```
</details>

<details>
<summary>
Emisión Timbrado V3
</summary> 

* Recibe un String o byte array del XML.
* Retorna CFDI y el TFD ya unidos.
* Ejemplo de uso

```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWIssueService api = new SWIssueService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV1Response response = null;
    response = (SuccessV1Response) api.IssueXml(stringXml, "v3");
    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.cfdi);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```
</details>

<details>
<summary>
Emisión Timbrado V4
</summary> 

* Recibe un String o byte array del XML.
* Retorna CFDI, TFD, CadenaOriginal, noCertificadoSat, noCertificadoCFDI, UUID, selloSAT, selloCFDI, fechaTimbrado y QR.
* Ejemplo de uso

```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el API de pruebas o productivo
    SWIssueService api = new SWIssueService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
    //Se inicializa un objeto response, que obtendrá la respuesta del API
    SuccessV1Response response = null;
    response = (SuccessV1Response) api.IssueXml(stringXml, "v4");
    System.out.println(response.Status);
	System.out.println(response.cfdi);
	System.out.println(response.qrCode);
	System.out.println(response.cadenaOriginalSAT);
	System.out.println(response.selloCFDI);
	System.out.println(response.selloSAT);
	System.out.println(response.noCertificadoCFDI);
	System.out.println(response.noCertificadoSAT);
	System.out.println(response.fechaTimbrado);
	System.out.println(response.uuid);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
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

- Recibe los archivos CSD y KEY en Base64, password, así como el RFC Emisor, el motivo, el folio de sustitución y el UUID del CFDI a cancelar

```java
SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
CancelationResponse response = null;
response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key, motivo, foliosustitucion);

System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.acuse);
System.out.println(response.uuid);
System.out.println(response.uuidStatusCode);
//En caso de obtener un error, este puede obtenerse de los campos
System.out.println(response.message);
System.out.println(response.messageDetail);
```
</details>
<details>
<summary>
Cancelacion XML
</summary>

- Recibe el XML de cancelacion

```java
SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
CancelationResponse response = null;

response = (CancelationResponse) app.Cancelation(xml);

System.out.println(response.Status);
System.out.println(response.message);
System.out.println(response.HttpStatusCode);
System.out.println(response.acuse);
System.out.println(response.uuid);
System.out.println(response.uuidStatusCode);
//En caso de obtener un error, este puede obtenerse de los campos
System.out.println(response.message);
System.out.println(response.messageDetail);  
```
</details>

<details>
<summary>
Cancelacion PFX
</summary> 

- Recibe el Pfx (.pfx, password), así como el RFC Emisor, el motivo, el folio de sustitución y el UUID del CFDI a cancelar


```java
SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
CancelationResponse response = null;
response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Pfx, motivo, foliosustitucion);
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.acuse);
System.out.println(response.uuid);
System.out.println(response.uuidStatusCode);
//En caso de obtener un error, este puede obtenerse de los campos
System.out.println(response.message);
System.out.println(response.messageDetail);  
```
</details>
<details>
<summary>
Cancelacion UUID
</summary> 

- Recibe el RFC Emisor, el motivo, el folio de sustitución y el UUID del CFDI a cancelar (Los archivos .Cer y .Key deben estar en tu administrador de timbres).


```java
SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
CancelationResponse response = null;
response = (CancelationResponse) app.Cancelation(uuid, rfc, motivo, foliosustitucion);
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.acuse);
System.out.println(response.uuid);
System.out.println(response.uuidStatusCode);
//En caso de obtener un error, este puede obtenerse de los campos
System.out.println(response.message);
System.out.println(response.messageDetail); 
```
</details>


# Validación #

Este servicio recibe un comprobante CFDI 4.0 en formato XML mediante el cual se valida integridad, sello, errores de estructura, matriz de errores del SAT incluyendo complementos, se valida su validez ante el SAT.

<details>
<summary>
Ejemplo de uso
</summary> 

- Recibe el XML del CFDI a validar.
- La respuesta vendrá representada con datos planos y un par de listas anidadas. Para obtener la información de las mismas se iterará.

- Ejemplo de uso

```java
SWValidateService api = new SWValidateService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
ValidateXmlResponse response = null;
response = (ValidateXmlResponse) api.ValidateXml(xml);
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.message);
LinkedList <DetailNode> List = response.detail;
for (int i = 0; i < List.size(); i++) {
    DetailNode node = List.get(i);
    LinkedList <DetailData> ListData = node.detail;
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
```
</details>

# Consulta de Saldos #
Se obtiene el balance de nuestra cuenta con respecto a los timbres
<details>
<summary>
Ejemplo de uso
</summary> 

```java
SWBalanceAccountService app = new SWBalanceAccountService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
BalanceAcctResponse response = null;
response = (BalanceAcctResponse) app.GetBalanceAccount();
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
```
</details>

# Estatus CFDI #
Método necesario para conocer el estado de un CFDI a través del servicio de consulta del SAT.
Será necesario conocer el RFC emisor, RFC receptor, total de la factura, y UUID de la factura que vamos a consultar. [Este servicio es consumido directamente del SAT].
<details>
<summary>
Ejemplo de uso
</summary> 

```java
StatusCfdiService app = new StatusCfdiService("https://consultaqr.facturaelectronica.sat.gob.mx/ConsultaCFDIService.svc", "http://tempuri.org/IConsultaCFDIService/Consulta");

StatusCfdiResponse response = null;
response = (StatusCfdiResponse) app.StatusCfdi("rfcEmisor", "rfcReceptor", "0.0", "E0AAE6B3-43CC-4B9C-B229-7E221000E2BB");
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.codigoEstatus);
System.out.println(response.estado);
System.out.println(response.esCancelable);
System.out.println(response.estatusCancelacion);
//En caso de obtener error, este puede obtenerse de los siguientes campos
System.out.println(response.message);
System.out.println(response.messageDetail);
```
</details>

# CFDI  Relacionados #
Método necesario para conocer los CFDI relacionados que existen a una factura. Con el nuevo método de cancelación, no se podrá cancelar una factura si existen CFDI que lo relacionen.

<details>
<summary>
CFDI Relacionados por CSD
</summary> 

Para el consumo a través de este método necesitaremos el UUID de la factura, RFC emisor, Certificado en base64, Llave en base64 y Password del Certificado.
* Ejemplo de uso: 
```java
SWRelationsService app = new SWRelationsService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
CfdiRelacionadosResponse response = null;
response = (CfdiRelacionadosResponse) app.CfdiRelacionadosCSD(uuid, password_csd, rfc, b64Cer, b64Key);
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.resultado);
LinkedList<RelacionData> padres = (LinkedList<RelacionData>) response.uuidsRelacionadosPadres;
if(padres != null) {
	for (int i = 0; i < padres.size(); i++) {
		RelacionData datos = padres.get(i);
		System.out.println(datos.uuid);
		System.out.println(datos.rfcEmisor);
		System.out.println(datos.rfcReceptor);
	}
}
LinkedList<RelacionData> hijos = (LinkedList<RelacionData>) response.uuidsRelacionadosHijos;
if(hijos != null) {
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
```
</details>
<details>
<summary>
CFDI Relacionados por PFX
</summary> 

Para el consumo a través de este método necesitaremos el UUID de la factura, RFC emisor, PFX en base64 y Password del Certificado.
* Ejemplo de uso

```java
SWRelationsService app = new SWRelationsService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
CfdiRelacionadosResponse response = null;
response = (CfdiRelacionadosResponse) app.CfdiRelacionadosPFX(uuid, password_csd, rfc, b64Pfx);
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.resultado);
LinkedList < RelacionData > padres = (LinkedList < RelacionData > ) response.uuidsRelacionadosPadres;
if (padres != null) {
    for (int i = 0; i < padres.size(); i++) {
        RelacionData datos = padres.get(i);
        System.out.println(datos.uuid);
        System.out.println(datos.rfcEmisor);
        System.out.println(datos.rfcReceptor);
    }
}
LinkedList < RelacionData > hijos = (LinkedList < RelacionData > ) response.uuidsRelacionadosHijos;
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
```
</details>


# Consulta Pendientes por Aceptar/Rechazar #
Este servicio devuelve una lista con los UUID que tiene pendientes por Aceptación/Rechazo un RFC.
Para el consumo de este método necesitaremos el RFC del cual consultaremos las facturas que tiene por Aceptar/Rechazar.
<details>
<summary>
Ejemplo de uso 
</summary>

```java
SWPendingsService app = new SWPendingsService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
String rfc = "CACX7605101P8";
PendientesCancelarResponse response = null;
response = (PendientesCancelarResponse) app.PendientesPorCancelar(rfc);
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.message);
System.out.println(response.CodStatus);
List < String > uuids = (LinkedList < String > ) response.UUIDS;
if (uuids != null) {
    for (int i = 0; i < uuids.size(); i++) {
        String datos = uuids.get(i);
        System.out.println(datos);
    }
}
//En caso de obtener error, este puede obtenerse de los siguientes campos
System.out.println(response.message);
System.out.println(response.messageDetail);
```
</details>

## Aceptar/Rechazar ##
Método para Aceptar o Rechazar una o más facturas.

<details>
<summary>
Aceptar/Rechazar por CSD
</summary> 
Para el consumo a través de este método necesitaremos el un **Map** con los UUID y la acción a realizar, **password** del certificado, **RFC** emisor, certificado en base64, llave en base64.

```java
SWAcceptRejectService app = new SWAcceptRejectService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
Map < String, String > uuids = new HashMap < String, String > ();
uuids.put("06a46e4b-b154-4c12-bb77-f9a63ed55ff2", "Aceptacion");
AceptarRechazarCancelationResponse response = null;
response = (AceptarRechazarCancelationResponse) app.AceptarRechazarCancelacionCSD(uuids, password_csd, rfc, b64Cer, b64Key);
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
LinkedList < CancelationData > folios = (LinkedList < CancelationData > ) response.folios;
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
```
</details>

<details>
<summary>
Aceptar/Rechazar por PFX
</summary> 

Para el consumo a través de este método necesitaremos el un **Map** con los UUID y la acción a realizar, **password** del certificado, **RFC** emisor, **PFX** en base64.

```java
SWAcceptRejectService app = new SWAcceptRejectService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
Map < String, String > uuids = new HashMap < String, String > ();
uuids.put("06a46e4b-b154-4c12-bb77-f9a63ed55ff2", "Aceptacion");
AceptarRechazarCancelationResponse response = null;
response = (AceptarRechazarCancelationResponse) app.AceptarRechazarCancelacionPFX(uuids, password_csd, rfc, b64Pfx);
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
LinkedList < CancelationData > folios = (LinkedList < CancelationData > ) response.folios;
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
```
</details>

<details>
<summary>
Aceptar/Rechazar por XML
</summary> 
Para el consumo a través de este método necesitaremos el XML para la Aceptación/Rechazo.

```java
SWAcceptRejectService app = new SWAcceptRejectService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
AceptarRechazarCancelationResponse response = null;
response = (AceptarRechazarCancelationResponse) app.AceptarRechazarCancelacionXML(xml);
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
List < CancelationData > folios = response.folios;
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
```
</details>

<details>
<summary>
Aceptar/Rechazar por UUID
</summary> 

Para el consumo a través de este método necesitaremos el un **String** con el UUID y la acción a realizar, así como el **RFC** emisor.

```java
SWAcceptRejectService app = new SWAcceptRejectService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
AceptarRechazarCancelationResponse response = null;
response = (AceptarRechazarCancelationResponse) app.AceptarRechazarCancelacionUUID(uuid, rfc, "Aceptacion");//Acción → "Aceptacion" o "Rechazo"
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
LinkedList < CancelationData > folios = (LinkedList < CancelationData > ) response.folios;
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
```
</details>

# Recuperar XML por UUID #
Método para recuperar la información de un XML enviando el UUID de la factura, así como el token de la cuenta en la cual fue timbrada.
<details>
<summary>
Ejemplo de uso
</summary> 

```java
SWStorageService storage = new SWStorageService(Utils.tokenSW, Utils.urlApiSW, null, 0);
StorageResponse response = (StorageResponse) storage.getXml(UUID.fromString("c75f87db-e059-4a7c-a922-e4b9c871e8c1"));
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.getData);
//En caso de obtener un error, este puede obtenerse de los campos
System.out.println(response.message);
System.out.println(response.messageDetail); 
```
</details>

# Servicio PDF #
Servicio para generar PDF de un XML previamente timbrado. 
Se permite especificar una de las plantillas genericas o una plantilla personalizada en caso de contar con una.

### Crear instancia de la clase.
* Usuario y contraseña.
    ```java
    SWPdfService app = new SWPdfService(Utils.userSW, Utils.passwordSW, "http://api.test.sw.com.mx", "http://services.test.sw.com.mx");
    ```
* Token
    ```java
    SWPdfService app = new SWPdfService(Utils.tokenSW, "http://api.test.sw.com.mx");
    ```
    
<details>
<summary>
Generar PDF Default 
</summary> 

Generar PDF con plantilla por defecto CFDI 4.0.
```java
PdfResponse response = null;
response = (PdfResponse) app.GeneratePdf(stamp.cfdi, this.logoB64);
```
</details>
<details>
<summary>
Generar PDF Default Extras
</summary> 

Generar PDF con plantilla por defecto CFDI 4.0 con datos adicionales.
```java
HashMap<String, String> extras = new HashMap<String,String>();
PdfResponse response = null;
response = (PdfResponse) app.GeneratePdf(stamp.cfdi, this.logoB64, extras);
```
</details>
<details>
<summary>
Generar PDF Plantilla Generica.
</summary> 

Generar PDF con plantilla generica.
```java
HashMap<String, String> extras = new HashMap<String,String>();
PdfResponse response = null;
response = (PdfResponse) app.GeneratePdf(stamp.cfdi, PdfTemplates.payment20, this.logoB64, extras);
```
</details>
<details>
<summary>
Generar PDF Plantilla Personalizada
</summary> 

Generar PDF especificando una plantilla como string.
```java
HashMap<String, String> extras = new HashMap<String,String>();
PdfResponse response = null;
response = (PdfResponse) app.GeneratePdf(stamp.cfdi, "cfdi40", this.logoB64, extras);
```