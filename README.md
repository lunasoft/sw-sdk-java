

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
            IResponse response = auth.Token();
            System.out.println(response.Data);
            SWStampService stamp = new SWStampService("demo","123456789","http://swservicestest.azurewebsites.net");
            IResponse stam_response = stamp.Stamp(Constants.xml,"v1");
```