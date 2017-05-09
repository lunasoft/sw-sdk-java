package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asalvio on 16/02/2017.
 */
public class Utils {
    public static String dummy_token = "T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbXB3YVZxTHdOdHAwVXY2NTdJb1hkREtXTzE3dk9pMmdMdkFDR2xFWFVPUTQyWFhnTUxGYjdKdG8xQTZWVjFrUDNiOTVrRkhiOGk3RHladHdMaEM0cS8rcklzaUhJOGozWjN0K2h6R3gwQzF0c0g5aGNBYUt6N2srR3VoMUw3amtvPQ.T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbFlVcU92YUJTZWlHU3pER1kySnlXRTF4alNUS0ZWcUlVS0NhelhqaXdnWTRncklVSWVvZlFZMWNyUjVxYUFxMWFxcStUL1IzdGpHRTJqdS9Zakw2UGRiMTFPRlV3a2kyOWI5WUZHWk85ODJtU0M2UlJEUkFTVXhYTDNKZVdhOXIySE1tUVlFdm1jN3kvRStBQlpLRi9NeWJrd0R3clhpYWJrVUMwV0Mwd3FhUXdpUFF5NW5PN3J5cklMb0FETHlxVFRtRW16UW5ZVjAwUjdCa2g0Yk1iTExCeXJkVDRhMGMxOUZ1YWlIUWRRVC8yalFTNUczZXdvWlF0cSt2UW0waFZKY2gyaW5jeElydXN3clNPUDNvU1J2dm9weHBTSlZYNU9aaGsvalpQMUxyQ0IvSUh1bHYxaFMxa2xmb3ZIaHlqQlpZZGRmVlpuRDBHdHNweStrZmNPcWZjWktlcm5IZVFhelhRUDBQWXpHS0JmMGhURm9OTU5KUEJLeEZMeGpnR3hpOWFoakcvaXRVb2RSSVFFSVF2OFd5UExVT3JiSFo5RmcxOGJUWTlQSnJPdzAxdU5NdVRwWlR1azRQY0FQdTg9.YA-QpZRGEYqNpQz5Un_eFwY20-JijfDKJvX6sC5z-XE";

    public static String dummy_xml_string = "<?xml version=\"1.0\" encoding=\"utf-8\"?><cfdi:Comprobante xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd\" Version=\"3.3\" Serie=\"RogueOne\" Folio=\"HNFK231\" Fecha=\"2017-05-08T11:43:24\" Sello=\"Lt0ixutSuNqOtIJgzhQKEE8ovWhb6UyQp0d6zL8FiLoyxzwF/opJh6pLuk49JLhhyeQQSypWdcbiWOQGFhZgg/Ym2kxrng5sUAqEXfIygXUTQHNukpPf7N53Mj4YaljKa+pUmNdVihH14D7WcatZITYIj4fAyEMItVwId2xnNpXKtUnpwBRUQJbYZeMwbyE0B90jgDNtGnD0XV3udrodY2m3EHNi1YVy75ra4wQQfvtvgOwWCig1EaFKYtdkc6MxSMFPmwAKdNRfZX9m8bLLiDOOSXPrI8/5FOtNuwqEReNL90ndH6aNmpoYplpGIzjRgzyG2J9+kMcGanHPrEadFQ==\" FormaPago=\"01\" NoCertificado=\"20001000000300022763\" Certificado=\"MIIFzDCCA7SgAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI3NjMwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjEyMDU0MDFaFw0yMDEwMjEyMDU0MDFaMIG4MRwwGgYDVQQDExNJTk1PQiBFRE1BIFNBIERFIENWMRwwGgYDVQQpExNJTk1PQiBFRE1BIFNBIERFIENWMRwwGgYDVQQKExNJTk1PQiBFRE1BIFNBIERFIENWMSUwIwYDVQQtExxUTUU5NjA3MDlMUjIgLyBIRUdUNzYxMDAzNFMyMR4wHAYDVQQFExUgLyBIRUdUNzYxMDAzTURGUk5OMDkxFTATBgNVBAsUDFBydWViYXNfQ0ZESTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAJgqZ+ezJyeJXACMK8ehFp64ecAl8jfYKB4xMJy0RRb+qXKRewxtLojiTFECWdCx283tEkdHUj8bLzsCfFAMnsP2G4CS2aE2/1LLCHoZpdImaasLX1YJL2bUzxKQKi+RlL63M49yyfvGBjEgG7f6TMwVSUSbgDFpYAFHqx4LK+p2GVHuUUzoiIm8xRYaW1YPMa457be5W8wsjw0nGRLfo8hRIjPHedkwtcqYPj57xsPXMfxWP45vOlW7GuLkMq/ECccHxJiPitiThcDFKlf/mAR0kaux9LTffvWilA2uQAlyVyNVjqfdpvDdq4ycTaoIMYKrv/9R31dQ0AmdXT8cfbcCAwEAAaMdMBswDAYDVR0TAQH/BAIwADALBgNVHQ8EBAMCBsAwDQYJKoZIhvcNAQELBQADggIBAF5kwvyBUp7Ad99DktzEhrJwnMQyhA79sVc4Ns2SpLON/cV244ZnG5hgXk2awKbHEiSj/ke7EhgEpGS818ERsj7eW/wRgugBZraVn48GOn6qX0uV9EjwWEGK5uT6IDN25igeXxVJHP3hn40fX2BPqsaqRP49YMxcOWD7mhWRh2E6BnoKYjgHVJbavUN6pjCBLmy4hKwfitbjqtUiiWOmBDvvmLFpEGXG8OXn2xladBUkfC4sfgMBpVZVuEV7RqAgCSCZ2xo6UEyd4KKpTjbdp0Tj5gw+NmiovAZHwU/NPRojN95f/ibj7268LBr2DcO5rlmr7szwJ3dtwu86N7HkUxW3vo3qGHTVK2HRBArda9VN4pEyIL0Qt46ci5rFYXB2cCWU8XAh8gaZnxJoNTSY4A4yMJG9UfM/2rHC+YvOouIZ2kJZ2h+SwKOYGJOX749P/QeF4Z/L/ODs3E08bV7IQna1ZHmd6ydYhZVpheMgNoNnIG6jdzfyuo8NZAIIW/JGmPTANPCwTSHqBY1lmnp/oZNrkxGWtGhbltRfBoFQfTqCZALm6fsVeQqHQ6a7W45FJ2RD1nltPSdniMo3Iz/t4eHCjFvM3aORvA9oJEPr5ZzzBV2fQOXkyS8QdsSVb5ZmJG+FqZKKlsiaX6xhqK6gqTLyJN+7/yr9T/ZZ4M7VrRoL\" SubTotal=\"200.00\" Moneda=\"MXN\" TipoCambio=\"1\" Total=\"603.28\" TipoDeComprobante=\"I\" MetodoPago=\"PUE\" LugarExpedicion=\"06300\" xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><cfdi:Emisor Rfc=\"TME960709LR2\" Nombre=\"INMOB EDMA SA DE CV \" RegimenFiscal=\"601\" /><cfdi:Receptor Rfc=\"AAA010101AAA\" Nombre=\"Rodolfo Carranza Ramos\" UsoCFDI=\"G03\" /><cfdi:Conceptos><cfdi:Concepto ClaveProdServ=\"50211503\" NoIdentificacion=\"UT421511\" Cantidad=\"1\" ClaveUnidad=\"H87\" Unidad=\"Pieza\" Descripcion=\"Cigarros\" ValorUnitario=\"200.00\" Importe=\"200.00\"><cfdi:Impuestos><cfdi:Traslados><cfdi:Traslado Base=\"200.00\" Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"32.08\" /><cfdi:Traslado Base=\"232.00\" Impuesto=\"003\" TipoFactor=\"Tasa\" TasaOCuota=\"1.600000\" Importe=\"371.20\" /></cfdi:Traslados></cfdi:Impuestos></cfdi:Concepto></cfdi:Conceptos><cfdi:Impuestos TotalImpuestosTrasladados=\"403.28\"><cfdi:Traslados><cfdi:Traslado Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"32.08\" /><cfdi:Traslado Impuesto=\"003\" TipoFactor=\"Tasa\" TasaOCuota=\"1.600000\" Importe=\"371.20\" /></cfdi:Traslados></cfdi:Impuestos><cfdi:Complemento><tfd:TimbreFiscalDigital xsi:schemaLocation=\"http://www.sat.gob.mx/TimbreFiscalDigital http://www.sat.gob.mx/sitio_internet/cfd/TimbreFiscalDigital/TimbreFiscalDigitalv11.xsd\" Version=\"1.1\" UUID=\"f7ea4fe2-8c07-4429-b59f-fd393a50de29\" FechaTimbrado=\"2017-05-08T17:23:10\" RfcProvCertif=\"AAA010101AAA\" SelloCFD=\"Lt0ixutSuNqOtIJgzhQKEE8ovWhb6UyQp0d6zL8FiLoyxzwF/opJh6pLuk49JLhhyeQQSypWdcbiWOQGFhZgg/Ym2kxrng5sUAqEXfIygXUTQHNukpPf7N53Mj4YaljKa+pUmNdVihH14D7WcatZITYIj4fAyEMItVwId2xnNpXKtUnpwBRUQJbYZeMwbyE0B90jgDNtGnD0XV3udrodY2m3EHNi1YVy75ra4wQQfvtvgOwWCig1EaFKYtdkc6MxSMFPmwAKdNRfZX9m8bLLiDOOSXPrI8/5FOtNuwqEReNL90ndH6aNmpoYplpGIzjRgzyG2J9+kMcGanHPrEadFQ==\" NoCertificadoSAT=\"20001000000300022323\" SelloSAT=\"nE9gBgz7QC7GeCGxPIomxocx5idvJjnCGd3kLFqEY/t0rxRKZiOGXHpjXY5Mi/3flvLfWqdVj3eK5cmU/z9ha+3I+4uSThh4Vzz2RQO67QBVzJQdvkmKafqgoiE/ouNk+qGgwnYAU8udcx4ihGsCUr/4owaulG63xZCUKg5CaBQKgE18M6RacE8NucdglvMdni1+XxJWwTnQGgAVY/s+HcTJbyAjC2R6C6OCZMVG95pHD62a/qFe5uDxz2meosxdXnuJi6bPkizD2LbBuGF8GjxgixlNRDuj4adzyxuo46cx7joynAJpBq4MZIuBTWzvSx20tN8K0uGZiYaitB6elA==\" xmlns:tfd=\"http://www.sat.gob.mx/TimbreFiscalDigital\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" /></cfdi:Complemento></cfdi:Comprobante>";
    public static String remove2(String input) {
        // Descomposición canónica
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Nos quedamos únicamente con los caracteres ASCII
        Pattern pattern = Pattern.compile("\\p{ASCII}+");
        return pattern.matcher(normalized).replaceAll("");
    }

    public static String b64xml = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz48Y2ZkaTpDb21wcm9iYW50ZSB4c2k6c2NoZW1hTG9jYXRpb249Imh0dHA6Ly93d3cuc2F0LmdvYi5teC9jZmQvMyBodHRwOi8vd3d3LnNhdC5nb2IubXgvc2l0aW9faW50ZXJuZXQvY2ZkLzMvY2ZkdjMzLnhzZCIgVmVyc2lvbj0iMy4zIiBTZXJpZT0iUm9ndWVPbmUiIEZvbGlvPSJITkZLMjMxIiBGZWNoYT0iMjAxNy0wNS0wOFQxMTo0MzoyNCIgU2VsbG89Ikx0MGl4dXRTdU5xT3RJSmd6aFFLRUU4b3ZXaGI2VXlRcDBkNnpMOEZpTG95eHp3Ri9vcEpoNnBMdWs0OUpMaGh5ZVFRU3lwV2RjYmlXT1FHRmhaZ2cvWW0ya3hybmc1c1VBcUVYZkl5Z1hVVFFITnVrcFBmN041M01qNFlhbGpLYStwVW1OZFZpaEgxNEQ3V2NhdFpJVFlJajRmQXlFTUl0VndJZDJ4bk5wWEt0VW5wd0JSVVFKYllaZU13YnlFMEI5MGpnRE50R25EMFhWM3Vkcm9kWTJtM0VITmkxWVZ5NzVyYTR3UVFmdnR2Z093V0NpZzFFYUZLWXRka2M2TXhTTUZQbXdBS2ROUmZaWDltOGJMTGlET09TWFBySTgvNUZPdE51d3FFUmVOTDkwbmRINmFObXBvWXBscEdJempSZ3p5RzJKOStrTWNHYW5IUHJFYWRGUT09IiBGb3JtYVBhZ289IjAxIiBOb0NlcnRpZmljYWRvPSIyMDAwMTAwMDAwMDMwMDAyMjc2MyIgQ2VydGlmaWNhZG89Ik1JSUZ6RENDQTdTZ0F3SUJBZ0lVTWpBd01ERXdNREF3TURBek1EQXdNakkzTmpNd0RRWUpLb1pJaHZjTkFRRUxCUUF3Z2dGbU1TQXdIZ1lEVlFRRERCZEJMa011SURJZ1pHVWdjSEoxWldKaGN5ZzBNRGsyS1RFdk1DMEdBMVVFQ2d3bVUyVnlkbWxqYVc4Z1pHVWdRV1J0YVc1cGMzUnlZV05wdzdOdUlGUnlhV0oxZEdGeWFXRXhPREEyQmdOVkJBc01MMEZrYldsdWFYTjBjbUZqYWNPemJpQmtaU0JUWldkMWNtbGtZV1FnWkdVZ2JHRWdTVzVtYjNKdFlXTnB3N051TVNrd0p3WUpLb1pJaHZjTkFRa0JGaHBoYzJsemJtVjBRSEJ5ZFdWaVlYTXVjMkYwTG1kdllpNXRlREVtTUNRR0ExVUVDUXdkUVhZdUlFaHBaR0ZzWjI4Z056Y3NJRU52YkM0Z1IzVmxjbkpsY204eERqQU1CZ05WQkJFTUJUQTJNekF3TVFzd0NRWURWUVFHRXdKTldERVpNQmNHQTFVRUNBd1FSR2x6ZEhKcGRHOGdSbVZrWlhKaGJERVNNQkFHQTFVRUJ3d0pRMjk1YjJGanc2RnVNUlV3RXdZRFZRUXRFd3hUUVZRNU56QTNNREZPVGpNeElUQWZCZ2txaGtpRzl3MEJDUUlNRWxKbGMzQnZibk5oWW14bE9pQkJRMFJOUVRBZUZ3MHhOakV3TWpFeU1EVTBNREZhRncweU1ERXdNakV5TURVME1ERmFNSUc0TVJ3d0dnWURWUVFERXhOSlRrMVBRaUJGUkUxQklGTkJJRVJGSUVOV01Sd3dHZ1lEVlFRcEV4TkpUazFQUWlCRlJFMUJJRk5CSUVSRklFTldNUnd3R2dZRFZRUUtFeE5KVGsxUFFpQkZSRTFCSUZOQklFUkZJRU5XTVNVd0l3WURWUVF0RXh4VVRVVTVOakEzTURsTVVqSWdMeUJJUlVkVU56WXhNREF6TkZNeU1SNHdIQVlEVlFRRkV4VWdMeUJJUlVkVU56WXhNREF6VFVSR1VrNU9NRGt4RlRBVEJnTlZCQXNVREZCeWRXVmlZWE5mUTBaRVNUQ0NBU0l3RFFZSktvWklodmNOQVFFQkJRQURnZ0VQQURDQ0FRb0NnZ0VCQUpncVorZXpKeWVKWEFDTUs4ZWhGcDY0ZWNBbDhqZllLQjR4TUp5MFJSYitxWEtSZXd4dExvamlURkVDV2RDeDI4M3RFa2RIVWo4Ykx6c0NmRkFNbnNQMkc0Q1MyYUUyLzFMTENIb1pwZEltYWFzTFgxWUpMMmJVenhLUUtpK1JsTDYzTTQ5eXlmdkdCakVnRzdmNlRNd1ZTVVNiZ0RGcFlBRkhxeDRMSytwMkdWSHVVVXpvaUltOHhSWWFXMVlQTWE0NTdiZTVXOHdzancwbkdSTGZvOGhSSWpQSGVka3d0Y3FZUGo1N3hzUFhNZnhXUDQ1dk9sVzdHdUxrTXEvRUNjY0h4SmlQaXRpVGhjREZLbGYvbUFSMGthdXg5TFRmZnZXaWxBMnVRQWx5VnlOVmpxZmRwdkRkcTR5Y1Rhb0lNWUtydi85UjMxZFEwQW1kWFQ4Y2ZiY0NBd0VBQWFNZE1Cc3dEQVlEVlIwVEFRSC9CQUl3QURBTEJnTlZIUThFQkFNQ0JzQXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnSUJBRjVrd3Z5QlVwN0FkOTlEa3R6RWhySnduTVF5aEE3OXNWYzROczJTcExPTi9jVjI0NFpuRzVoZ1hrMmF3S2JIRWlTai9rZTdFaGdFcEdTODE4RVJzajdlVy93Umd1Z0JacmFWbjQ4R09uNnFYMHVWOUVqd1dFR0s1dVQ2SUROMjVpZ2VYeFZKSFAzaG40MGZYMkJQcXNhcVJQNDlZTXhjT1dEN21oV1JoMkU2Qm5vS1lqZ0hWSmJhdlVONnBqQ0JMbXk0aEt3Zml0YmpxdFVpaVdPbUJEdnZtTEZwRUdYRzhPWG4yeGxhZEJVa2ZDNHNmZ01CcFZaVnVFVjdScUFnQ1NDWjJ4bzZVRXlkNEtLcFRqYmRwMFRqNWd3K05taW92QVpId1UvTlBSb2pOOTVmL2liajcyNjhMQnIyRGNPNXJsbXI3c3p3SjNkdHd1ODZON0hrVXhXM3ZvM3FHSFRWSzJIUkJBcmRhOVZONHBFeUlMMFF0NDZjaTVyRllYQjJjQ1dVOFhBaDhnYVpueEpvTlRTWTRBNHlNSkc5VWZNLzJySEMrWXZPb3VJWjJrSloyaCtTd0tPWUdKT1g3NDlQL1FlRjRaL0wvT0RzM0UwOGJWN0lRbmExWkhtZDZ5ZFloWlZwaGVNZ05vTm5JRzZqZHpmeXVvOE5aQUlJVy9KR21QVEFOUEN3VFNIcUJZMWxtbnAvb1pOcmt4R1d0R2hibHRSZkJvRlFmVHFDWkFMbTZmc1ZlUXFIUTZhN1c0NUZKMlJEMW5sdFBTZG5pTW8zSXovdDRlSENqRnZNM2FPUnZBOW9KRVByNVp6ekJWMmZRT1hreVM4UWRzU1ZiNVptSkcrRnFaS0tsc2lhWDZ4aHFLNmdxVEx5Sk4rNy95cjlUL1paNE03VnJSb0wiIFN1YlRvdGFsPSIyMDAuMDAiIE1vbmVkYT0iTVhOIiBUaXBvQ2FtYmlvPSIxIiBUb3RhbD0iNjAzLjI4IiBUaXBvRGVDb21wcm9iYW50ZT0iSSIgTWV0b2RvUGFnbz0iUFVFIiBMdWdhckV4cGVkaWNpb249IjA2MzAwIiB4bWxuczpjZmRpPSJodHRwOi8vd3d3LnNhdC5nb2IubXgvY2ZkLzMiIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiPjxjZmRpOkVtaXNvciBSZmM9IlRNRTk2MDcwOUxSMiIgTm9tYnJlPSJJTk1PQiBFRE1BIFNBIERFIENWICIgUmVnaW1lbkZpc2NhbD0iNjAxIiAvPjxjZmRpOlJlY2VwdG9yIFJmYz0iQUFBMDEwMTAxQUFBIiBOb21icmU9IlJvZG9sZm8gQ2FycmFuemEgUmFtb3MiIFVzb0NGREk9IkcwMyIgLz48Y2ZkaTpDb25jZXB0b3M+PGNmZGk6Q29uY2VwdG8gQ2xhdmVQcm9kU2Vydj0iNTAyMTE1MDMiIE5vSWRlbnRpZmljYWNpb249IlVUNDIxNTExIiBDYW50aWRhZD0iMSIgQ2xhdmVVbmlkYWQ9Ikg4NyIgVW5pZGFkPSJQaWV6YSIgRGVzY3JpcGNpb249IkNpZ2Fycm9zIiBWYWxvclVuaXRhcmlvPSIyMDAuMDAiIEltcG9ydGU9IjIwMC4wMCI+PGNmZGk6SW1wdWVzdG9zPjxjZmRpOlRyYXNsYWRvcz48Y2ZkaTpUcmFzbGFkbyBCYXNlPSIyMDAuMDAiIEltcHVlc3RvPSIwMDIiIFRpcG9GYWN0b3I9IlRhc2EiIFRhc2FPQ3VvdGE9IjAuMTYwMDAwIiBJbXBvcnRlPSIzMi4wOCIgLz48Y2ZkaTpUcmFzbGFkbyBCYXNlPSIyMzIuMDAiIEltcHVlc3RvPSIwMDMiIFRpcG9GYWN0b3I9IlRhc2EiIFRhc2FPQ3VvdGE9IjEuNjAwMDAwIiBJbXBvcnRlPSIzNzEuMjAiIC8+PC9jZmRpOlRyYXNsYWRvcz48L2NmZGk6SW1wdWVzdG9zPjwvY2ZkaTpDb25jZXB0bz48L2NmZGk6Q29uY2VwdG9zPjxjZmRpOkltcHVlc3RvcyBUb3RhbEltcHVlc3Rvc1RyYXNsYWRhZG9zPSI0MDMuMjgiPjxjZmRpOlRyYXNsYWRvcz48Y2ZkaTpUcmFzbGFkbyBJbXB1ZXN0bz0iMDAyIiBUaXBvRmFjdG9yPSJUYXNhIiBUYXNhT0N1b3RhPSIwLjE2MDAwMCIgSW1wb3J0ZT0iMzIuMDgiIC8+PGNmZGk6VHJhc2xhZG8gSW1wdWVzdG89IjAwMyIgVGlwb0ZhY3Rvcj0iVGFzYSIgVGFzYU9DdW90YT0iMS42MDAwMDAiIEltcG9ydGU9IjM3MS4yMCIgLz48L2NmZGk6VHJhc2xhZG9zPjwvY2ZkaTpJbXB1ZXN0b3M+PGNmZGk6Q29tcGxlbWVudG8+PHRmZDpUaW1icmVGaXNjYWxEaWdpdGFsIHhzaTpzY2hlbWFMb2NhdGlvbj0iaHR0cDovL3d3dy5zYXQuZ29iLm14L1RpbWJyZUZpc2NhbERpZ2l0YWwgaHR0cDovL3d3dy5zYXQuZ29iLm14L3NpdGlvX2ludGVybmV0L2NmZC9UaW1icmVGaXNjYWxEaWdpdGFsL1RpbWJyZUZpc2NhbERpZ2l0YWx2MTEueHNkIiBWZXJzaW9uPSIxLjEiIFVVSUQ9ImY3ZWE0ZmUyLThjMDctNDQyOS1iNTlmLWZkMzkzYTUwZGUyOSIgRmVjaGFUaW1icmFkbz0iMjAxNy0wNS0wOFQxNzoyMzoxMCIgUmZjUHJvdkNlcnRpZj0iQUFBMDEwMTAxQUFBIiBTZWxsb0NGRD0iTHQwaXh1dFN1TnFPdElKZ3poUUtFRThvdldoYjZVeVFwMGQ2ekw4RmlMb3l4endGL29wSmg2cEx1azQ5SkxoaHllUVFTeXBXZGNiaVdPUUdGaFpnZy9ZbTJreHJuZzVzVUFxRVhmSXlnWFVUUUhOdWtwUGY3TjUzTWo0WWFsakthK3BVbU5kVmloSDE0RDdXY2F0WklUWUlqNGZBeUVNSXRWd0lkMnhuTnBYS3RVbnB3QlJVUUpiWVplTXdieUUwQjkwamdETnRHbkQwWFYzdWRyb2RZMm0zRUhOaTFZVnk3NXJhNHdRUWZ2dHZnT3dXQ2lnMUVhRktZdGRrYzZNeFNNRlBtd0FLZE5SZlpYOW04YkxMaURPT1NYUHJJOC81Rk90TnV3cUVSZU5MOTBuZEg2YU5tcG9ZcGxwR0l6alJnenlHMko5K2tNY0dhbkhQckVhZEZRPT0iIE5vQ2VydGlmaWNhZG9TQVQ9IjIwMDAxMDAwMDAwMzAwMDIyMzIzIiBTZWxsb1NBVD0ibkU5Z0JnejdRQzdHZUNHeFBJb214b2N4NWlkdkpqbkNHZDNrTEZxRVkvdDByeFJLWmlPR1hIcGpYWTVNaS8zZmx2TGZXcWRWajNlSzVjbVUvejloYSszSSs0dVNUaGg0Vnp6MlJRTzY3UUJWekpRZHZrbUthZnFnb2lFL291TmsrcUdnd25ZQVU4dWRjeDRpaEdzQ1VyLzRvd2F1bEc2M3haQ1VLZzVDYUJRS2dFMThNNlJhY0U4TnVjZGdsdk1kbmkxK1h4Sld3VG5RR2dBVlkvcytIY1RKYnlBakMyUjZDNk9DWk1WRzk1cEhENjJhL3FGZTV1RHh6Mm1lb3N4ZFhudUppNmJQa2l6RDJMYkJ1R0Y4R2p4Z2l4bE5SRHVqNGFkenl4dW80NmN4N2pveW5BSnBCcTRNWkl1QlRXenZTeDIwdE44SzB1R1ppWWFpdEI2ZWxBPT0iIHhtbG5zOnRmZD0iaHR0cDovL3d3dy5zYXQuZ29iLm14L1RpbWJyZUZpc2NhbERpZ2l0YWwiIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiIC8+PC9jZmRpOkNvbXBsZW1lbnRvPjwvY2ZkaTpDb21wcm9iYW50ZT4=";
    public static boolean isNoAlphaNumeric(String s) {
        return s.matches("[ \\w]+");
    }

    public static byte[] fileToBytes(File file) {
        byte[] bytesArray = new byte[(int) file.length()];

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();

            return bytesArray;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static String removeBadChars(String s) {
        if (s == null) return null;
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i++){
            if (Character.isHighSurrogate(s.charAt(i))) continue;
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static StringBuffer removeUTFCharacters(String data){
        Pattern p = Pattern.compile("\\\\u(\\p{XDigit}{4})");
        Matcher m = p.matcher(data);
        StringBuffer buf = new StringBuffer(data.length());
        while (m.find()) {
            String ch = String.valueOf((char) Integer.parseInt(m.group(1), 16));
            m.appendReplacement(buf, Matcher.quoteReplacement(ch));
        }
        m.appendTail(buf);
        return buf;
    }


    }
