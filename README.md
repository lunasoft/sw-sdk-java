  

[![Smarter Web](http://sw.com.mx/images/logo.png)](http://sw.com.mx/)

# SDK JAVA #

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

            <version>0.0.3.4</version>

        </dependency>
</dependencies>
```
## Instalación manual (sin Maven) ##
Descargar el modulo directamente de los siguientes links:

* [Relase Github](https://github.com/lunasoft/sw-sdk-java/releases)
* [Maven](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=mx.com.sw.services&a=SW-JAVA&v=0.0.3.4&e=jar)

Asi como instalar manualmente cada una de las dependencias:
* [org.json](http://www.json.org/java)
* [httpclient 4.3.6](http://hc.apache.org/downloads.cgi)
* [httpmime 4.3.6](http://hc.apache.org/downloads.cgi)
* [httpasyncclient 4.0.2](http://hc.apache.org/downloads.cgi)

Alternativamente tambien se cuenta con un archivo JAR en el que se incluyen todas las dependencias, este se encuentra en la carpeta [Relase Github](https://github.com/lunasoft/sw-sdk-java/releases),  con el sufijo _"jar-with-dependencies.jar"_



**Uso rapido...**

```java
try{
	//Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el api de pruebas o productivo
    SWStampService sdk = new SWStampService("demo","123456789","http://services.test.sw.com.mx");
    //Si deseas utilizar un proxy customizado, debes agregar dos parametros mas
    //Se inicializa un objeto response, que obtendra la respuesta del api
    SuccessV1Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV1Response)sdk.Stamp("String o File XML","v1");
    //El objeto response tendra así los atributos:
    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
    // tfd: El campo donde se encuentra el timbre fiscal digital
    if(response.Status.equals("success")){
	    System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
    }
    else{
	    //En caso de obtener estatus "fail", "error"
        //Se puede identificar el error en los campos message, messageDetail
        System.out.println(response.message);
        System.out.println(response.messageDetail);
        // ***Como observacion***
        //El campo messageDetail puede no incluirse en ciertos errores                        
    }
}
catch(Exception e){
	System.out.println(e.getMessage());
}
            
```

## Timbrar CFDI V1 ##
TimbrarV1 Recibe el contenido de un XML ya emitido (sellado) en formato String ó tambien puede ser en Base64, posteriormente si la factura y el token son correctos devuelve el complemento timbre en un string (TFD), en caso contrario lanza una excepción.

**Timbrar XML en formato string utilizando usuario y contraseña**
```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el api de pruebas o productivo
    SWStampService sdk = new SWStampService("demo", "123456789", "http://services.test.sw.com.mx");
    //Se inicializa un objeto response, que obtendra la respuesta del api
    SuccessV1Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV1Response) sdk.Stamp(stringXML, "v1");
    //El objeto response tendra así los atributos:
    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
    // Data: Cuerpo de la respuesta que arroja el servidor
    //En este caso arrojara el complemento timbre: {"tfd":"<Complemento>"}
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
    //Se especifica el base path, esto para consumir el api de pruebas o productivo
    SWStampService sdk = new SWStampService("T2lYQ0t4L0R....", "http://services.test.sw.com.mx");
    //Se inicializa un objeto response, que obtendra la respuesta del api
    SuccessV1Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV1Response) sdk.Stamp(stringXML, "v1");
    //El objeto response tendra así los atributos:
    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
    // Data: Cuerpo de la respuesta que arroja el servidor
    //En este caso arrojara el complemento timbre: {"tfd":"<Complemento>"}
    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.tfd);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```





## Timbrar CFDI V2 ##
TimbrarV2 Recibe el contenido de un XML ya emitido (sellado) en formato String , posteriormente si la factura y el token son correctos devuelve el CFDI ya tibrado, en caso contrario lanza una excepción.

**Timbrar XML en formato string utilizando usuario y contraseña**
```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el api de pruebas o productivo
    SWStampService sdk = new SWStampService("demo", "123456789", "http://services.test.sw.com.mx");
    //Se inicializa un objeto response, que obtendra la respuesta del api
    SuccessV2Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV2Response) sdk.Stamp(stringXML, "v2");
    //El objeto response tendra así los atributos:
    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
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
    //Se especifica el base path, esto para consumir el api de pruebas o productivo
    SWStampService sdk = new SWStampService("T2lYQ0t4L0R....", "http://services.test.sw.com.mx");
    //Se inicializa un objeto response, que obtendra la respuesta del api
    SuccessV2Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV2Response) sdk.Stamp(stringXML, "v2");
    //El objeto response tendra así los atributos:
    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
    // Data: Cuerpo de la respuesta que arroja el servidor

    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.tfd);
    System.out.println(response.cfdi);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## Timbrar CFDI V3 ##
TimbrarV3 Recibe el contenido de un XML ya emitido (sellado) en formato String , posteriormente si la factura y el token son correctos devuelve el CFDI ya tibrado, en caso contrario lanza una excepción.

**Timbrar XML en formato string utilizando usuario y contraseña**
```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el api de pruebas o productivo
    SWStampService sdk = new SWStampService("demo", "123456789", "http://services.test.sw.com.mx");
    //Se inicializa un objeto response, que obtendra la respuesta del api
    SuccessV3Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV3Response) sdk.Stamp(stringXML, "v3");
    //El objeto response tendra así los atributos:
    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
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
    //Se especifica el base path, esto para consumir el api de pruebas o productivo
    SWStampService sdk = new SWStampService("T2lYQ0t4L0R....", "http://services.test.sw.com.mx");
    //Se inicializa un objeto response, que obtendra la respuesta del api
    SuccessV3Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV3Response) sdk.Stamp(stringXML, "v3");
    //El objeto response tendra así los atributos:
    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
    // Data: Cuerpo de la respuesta que arroja el servidor

    System.out.println(response.Status);
    System.out.println(response.HttpStatusCode);
    System.out.println(response.cfdi);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## Timbrar CFDI V4 ##
TimbrarV4 Recibe el contenido de un XML ya emitido (sellado) en formato String , posteriormente si la factura y el token son correctos devuelve el cfdi ya timbrado, y campos extras como qr en formato base64, entre otros.

**Timbrar XML en formato string utilizando usuario y contraseña**
```java
try {
    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
    //Se especifica el base path, esto para consumir el api de pruebas o productivo
    SWStampService sdk = new SWStampService("demo", "123456789", "http://services.test.sw.com.mx");
    //Se inicializa un objeto response, que obtendra la respuesta del api
    SuccessV4Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV4Response) sdk.Stamp(stringXML, "v4");
    //El objeto response tendra así los atributos:
    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
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
    //Se especifica el base path, esto para consumir el api de pruebas o productivo
    SWStampService sdk = new SWStampService("T2lYQ0t4L0R....", "http://services.test.sw.com.mx");
    //Se inicializa un objeto response, que obtendra la respuesta del api
    SuccessV4Response response = null;
    //Se asigna el resultado de la respuesta a dicho objeto
    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
    response = (SuccessV4Response) sdk.Stamp(stringXML, "v4");
    //El objeto response tendra así los atributos:
    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
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


# CANCELACIÓN #

## Cancelacion CSD ##
Recibe los archivos CSD y KEY en Base64, password, asi como el rfc emisor y el UUID del CFDI a cancelar


```java
SWCancelationService app = new SWCancelationService("demo", "123456789", "http://services.test.sw.com.mx");
CancelationResponse response = null;
response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key);

System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.acuse);
System.out.println(response.uuid);
System.out.println(response.uuidStatusCode);
//En caso de obtener un error, este puede obtenerse de los campos
System.out.println(response.message);
System.out.println(response.messageDetail);
```

## Cancelación XML ##
Recibe el XML de cancelacion

```java
SWCancelationService app = new SWCancelationService("demo", "123456789", Utils.url_pruebas);
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

## Cancelacion Pfx ##
Recibe el Pfx (.pfx, password), asi como el rfc emisor y el UUID del CFDI a cancelar


```java
SWCancelationService app = new SWCancelationService("demo", "123456789", Utils.url_pruebas);
CancelationResponse response = null;
response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Pfx);
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.acuse);
System.out.println(response.uuid);
System.out.println(response.uuidStatusCode);
//En caso de obtener un error, este puede obtenerse de los campos
System.out.println(response.message);
System.out.println(response.messageDetail);  
```


## Cancelacion Uuid ##
Recibe el rfc emisor y el UUID del CFDI a cancelar (Los archivos .Cer y .Key deben estar en tu administrador de timbres).


```java
SWCancelationService app = new SWCancelationService("demo", "123456789", Utils.url_pruebas);
CancelationResponse response = null;
response = (CancelationResponse) app.Cancelation(uuid, rfc);
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.acuse);
System.out.println(response.uuid);
System.out.println(response.uuidStatusCode);
//En caso de obtener un error, este puede obtenerse de los campos
System.out.println(response.message);
System.out.println(response.messageDetail); 
```


# Validación #

## Validación Xml ##
Recibe el Xml del CFDI a validar.

>La respuesta vendrá representada con datos planos y un par de listas anidadas. Para obtener la información de las mismas se iterará con "for".

Ejemplo de uso

```java
SWValidateService api = new SWValidateService("demo", "123456789", Utils.url_pruebas);
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
//En caso de obtener un error, este puede obtenerse de los campos
System.out.println(response.message);
System.out.println(response.messageDetail); 
```

## Validación Lco ##
Recibe el número de certificado a validar.

Ejemplo de uso

```java
SWValidateService api = new SWValidateService("demo", "123456789", Utils.url_pruebas);
ValidateLcoResponse response = null;
response = (ValidateLcoResponse) api.ValidateLco("20001000000300022815");
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.message);
System.out.println(response.noCertificado);
System.out.println(response.rfc);
System.out.println(response.validezObligaciones);
System.out.println(response.estatusCertificado);
System.out.println(response.fechaInicio);
System.out.println(response.fechaFinal);
//En caso de obtener un error, este puede obtenerse de los campos
System.out.println(response.message);
System.out.println(response.messageDetail); 
```

## Validación Lrfc ##
Recibe el rfc a validar.

Ejemplo de uso

```java
SWValidateService api = new SWValidateService("demo", "123456789", Utils.url_pruebas);
ValidateLrfcResponse response = null;
response = (ValidateLrfcResponse) api.ValidateLrfc("LAN7008173R5");
System.out.println(response.Status);
System.out.println(response.HttpStatusCode);
System.out.println(response.message);
System.out.println(response.contribuyenteRFC);
System.out.println(response.sncf);
System.out.println(response.subcontratacion);
//En caso de obtener un error, este puede obtenerse de los campos
System.out.println(response.message);
System.out.println(response.messageDetail); 
```



# Consulta de Saldos #
Se obtiene el balance de nuestra cuenta con respecto a los timbres
```java
SWBalanceAccountService app = new SWBalanceAccountService("demo", "123456789", Utils.url_pruebas);
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