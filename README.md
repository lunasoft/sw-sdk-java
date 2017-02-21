

[![Smarter Web](http://sw.com.mx/images/logo.png)](http://sw.com.mx/)

# Libreria JAVA

Registrate en sw.com.mx/

## Requirementos

Java 1.6 o superior

##Instalación
Descargas el modulo mediante Maven:
	
```html
<dependencies>

        <dependency>
            <groupId>mx.com.sw.services</groupId>
            <artifactId>SW-JAVA</artifactId>
            <version>1.0.0</version>
        </dependency>
</dependencies>
```

##Uso rapido...

```java
            try{
                    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
                    //Se especifica el base path, esto para consumir el api de pruebas o productivo
                    SWStampService api = new SWStampService("demo","123456789","http://swservicestest.azurewebsites.net");
                    //Se inicializa un objeto response, que obtendra la respuesta del api
                    IResponse response = null;
                    //Se asigna el resultado de la respuesta a dicho objeto
                    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
                    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
                    response = api.Stamp("String o File XML","v1");
                    //El objeto response tendra así los atributos:
                    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
                    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
                    // Data: Cuerpo de la respuesta que arroja el servidor
                    //En este caso arrojara el complemento timbre: {"tfd":"<Complemento>"}
                    System.out.println(response.Status);
                    System.out.println(response.HttpStatusCode);
                    System.out.println(response.Data);
                    }
                    catch(Exception e){
                        //En caso de obtener estatus "fail", "error"
                        //Se generara una excepción
                        System.out.println(e.getMessage());
                    }
            
```