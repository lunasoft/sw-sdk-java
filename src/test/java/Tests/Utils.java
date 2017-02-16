package Tests;

/**
 * Created by asalvio on 16/02/2017.
 */
public class Utils {
    public static String dummy_token = "T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbXB3YVZxTHdOdHAwVXY2NTdJb1hkREtXTzE3dk9pMmdMdkFDR2xFWFVPUTQyWFhnTUxGYjdKdG8xQTZWVjFrUDNiOTVrRkhiOGk3RHladHdMaEM0cS8rcklzaUhJOGozWjN0K2h6R3gwQzF0c0g5aGNBYUt6N2srR3VoMUw3amtvPQ.T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbFlVcU92YUJTZWlHU3pER1kySnlXRTF4alNUS0ZWcUlVS0NhelhqaXdnWTRncklVSWVvZlFZMWNyUjVxYUFxMWFxcStUL1IzdGpHRTJqdS9Zakw2UGRiMTFPRlV3a2kyOWI5WUZHWk85ODJtU0M2UlJEUkFTVXhYTDNKZVdhOXIySE1tUVlFdm1jN3kvRStBQlpLRi9NeWJrd0R3clhpYWJrVUMwV0Mwd3FhUXdpUFF5NW5PN3J5cklMb0FETHlxVFRtRW16UW5ZVjAwUjdCa2g0Yk1iTExCeXJkVDRhMGMxOUZ1YWlIUWRRVC8yalFTNUczZXdvWlF0cSt2UW0waFZKY2gyaW5jeElydXN3clNPUDNvU1J2dm9weHBTSlZYNU9aaGsvalpQMUxtTC9xaHRXOGFicVg1TE96RC9FODR4ZEFmWU8zVXBLOHB5dDY4QVpjRGNmTE1mVWhaVFgvSDgzbVhYWVRWN0VkYmlBZVB6NnhyOXNCSkhEemdSaGVkbzAyaEVDOEJNZFdIYndxYTNzTDQ0eTRRdjJlTkpuaGQ1Z0hXcVVFaW91eVFXUG5jV2x3RC83RTZsd0taMTd6TlE9.1G6P0nkiPTj7kTS4B1h-JPwLFnMj3MOF9ci_sZou774";

    public static String dummy_xml_string = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<cfdi:Comprobante Certificado=\"MIIEYTCCA0mgAwIBAgIUMjAwMDEwMDAwMDAyMDAwMDE0MjgwDQYJKoZIhvcNAQEFBQAwggFcMRowGAYDVQQDDBFBLkMuIDIgZGUgcHJ1ZWJhczEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMTQwMgYJKoZIhvcNAQkCDCVSZXNwb25zYWJsZTogQXJhY2VsaSBHYW5kYXJhIEJhdXRpc3RhMB4XDTEzMDUwNzE2MDEyOVoXDTE3MDUwNzE2MDEyOVowgdsxKTAnBgNVBAMTIEFDQ0VNIFNFUlZJQ0lPUyBFTVBSRVNBUklBTEVTIFNDMSkwJwYDVQQpEyBBQ0NFTSBTRVJWSUNJT1MgRU1QUkVTQVJJQUxFUyBTQzEpMCcGA1UEChMgQUNDRU0gU0VSVklDSU9TIEVNUFJFU0FSSUFMRVMgU0MxJTAjBgNVBC0THEFBQTAxMDEwMUFBQSAvIEhFR1Q3NjEwMDM0UzIxHjAcBgNVBAUTFSAvIEhFR1Q3NjEwMDNNREZOU1IwODERMA8GA1UECxMIcHJvZHVjdG8wgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAKS/beUVy6E3aODaNuLd2S3PXaQre0tGxmYTeUxa55x2t/7919ttgOpKF6hPF5KvlYh4ztqQqP4yEV+HjH7yy/2d/+e7t+J61jTrbdLqT3WD0+s5fCL6JOrF4hqy//EGdfvYftdGRNrZH+dAjWWml2S/hrN9aUxraS5qqO1b7btlAgMBAAGjHTAbMAwGA1UdEwEB/wQCMAAwCwYDVR0PBAQDAgbAMA0GCSqGSIb3DQEBBQUAA4IBAQACPXAWZX2DuKiZVv35RS1WFKgT2ubUO9C+byfZapV6ZzYNOiA4KmpkqHU/bkZHqKjR+R59hoYhVdn+ClUIliZf2ChHh8s0a0vBRNJ3IHfA1akWdzocYZLXjz3m0Er31BY+uS3qWUtPsONGVDyZL6IUBBUlFoecQhP9AO39er8zIbeU2b0MMBJxCt4vbDKFvT9i3V0Puoo+kmmkf15D2rBGR+drd8H8Yg8TDGFKf2zKmRsgT7nIeou6WpfYp570WIvLJQY+fsMp334D05Up5ykYSAxUGa30RdUzA4rxN5hT+W9whWVGD88TD33Nw55uNRUcRO3ZUVHmdWRG+GjhlfsD\"\n" +
            "                  CondicionesDePago=\"CONDICIONES\"\n" +
            "                  Descuento=\"0.00\"\n" +
            "                  Fecha=\"2017-01-05T09:09:23\"\n" +
            "                  Folio=\"167ABC\"\n" +
            "                  FormaPago=\"01\"\n" +
            "                  LugarExpedicion=\"45079\"\n" +
            "                  MetodoPago=\"PUE\"\n" +
            "                  Moneda=\"MXN\"\n" +
            "                  NoCertificado=\"20001000000200001428\"\n" +
            "                  Sello=\"FuImBWd6+vEhDJKha+BLURti1qQnnst7mZL8HaOELHUW/0QblFBRP8XAJcIlp3tsie6R9SEQKurizCIgCZ5v3U4j68m2XPmezO2UUE79fwhrDLSKZkts/cDq9mdMfu3Q6O3oOQcgXCVAWy4b2K9c/CATdq72MsD8Q5uMOjkzKNE=\"\n" +
            "                  Serie=\"A\"\n" +
            "                  SubTotal=\"1000\"\n" +
            "                  TipoCambio=\"1.0\"\n" +
            "                  TipoDeComprobante=\"I\"\n" +
            "                  Total=\"1500\"\n" +
            "                  Version=\"3.3\"\n" +
            "                  xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\"\n" +
            "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "                  xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd\">\n" +
            "    <cfdi:CfdiRelacionados TipoRelacion=\"01\">\n" +
            "        <cfdi:CfdiRelacionado UUID=\"A39DA66B-52CA-49E3-879B-5C05185B0EF7\"/>\n" +
            "    </cfdi:CfdiRelacionados>\n" +
            "    <cfdi:Emisor Nombre=\"HORACIO LLANOS\"\n" +
            "                 RegimenFiscal=\"601\"\n" +
            "                 Rfc=\"AAA010101AAA\"/>\n" +
            "    <cfdi:Receptor Nombre=\"RAFAEL ALEJANDRO HERNADEZ PALACIOS\"\n" +
            "                   NumRegIdTrib=\"0000000000000\"\n" +
            "                   ResidenciaFiscal=\"MEX\"\n" +
            "                   Rfc=\"HEPR930322977\"\n" +
            "                   UsoCFDI=\"G01\"/>\n" +
            "    <cfdi:Conceptos>\n" +
            "        <cfdi:Concepto Cantidad=\"1.5\"\n" +
            "                       ClaveProdServ=\"01010101\"\n" +
            "                       ClaveUnidad=\"F52\"\n" +
            "                       Descripcion=\"ACERO\"\n" +
            "                       Importe=\"1\"\n" +
            "                       NoIdentificacion=\"00001\"\n" +
            "                       Unidad=\"TONELADA\"\n" +
            "                       ValorUnitario=\"1500000\">\n" +
            "            <cfdi:Impuestos>\n" +
            "                <cfdi:Traslados>\n" +
            "                    <cfdi:Traslado Base=\"2250000\"\n" +
            "                                   Importe=\"360000\"\n" +
            "                                   Impuesto=\"001\"\n" +
            "                                   TasaOCuota=\"0.160000\"\n" +
            "                                   TipoFactor=\"Tasa\"/>\n" +
            "                </cfdi:Traslados>\n" +
            "                <cfdi:Retenciones>\n" +
            "                    <cfdi:Retencion Base=\"2250000\"\n" +
            "                                    Importe=\"247500\"\n" +
            "                                    Impuesto=\"002\"\n" +
            "                                    TasaOCuota=\"0.530000\"\n" +
            "                                    TipoFactor=\"Tasa\"/>\n" +
            "                </cfdi:Retenciones>\n" +
            "            </cfdi:Impuestos>\n" +
            "            <cfdi:CuentaPredial Numero=\"51888\"/>\n" +
            "        </cfdi:Concepto>\n" +
            "        <cfdi:Concepto Cantidad=\"1.6\"\n" +
            "                       ClaveProdServ=\"01010101\"\n" +
            "                       ClaveUnidad=\"F52\"\n" +
            "                       Descripcion=\"ALUMINIO\"\n" +
            "                       Importe=\"1\"\n" +
            "                       NoIdentificacion=\"00002\"\n" +
            "                       Unidad=\"TONELADA\"\n" +
            "                       ValorUnitario=\"1500\">\n" +
            "            <cfdi:Impuestos>\n" +
            "                <cfdi:Traslados>\n" +
            "                    <cfdi:Traslado Base=\"2400\"\n" +
            "                                   Importe=\"384\"\n" +
            "                                   Impuesto=\"001\"\n" +
            "                                   TasaOCuota=\"0.160000\"\n" +
            "                                   TipoFactor=\"Tasa\"/>\n" +
            "                </cfdi:Traslados>\n" +
            "                <cfdi:Retenciones>\n" +
            "                    <cfdi:Retencion Base=\"2400\"\n" +
            "                                    Importe=\"264\"\n" +
            "                                    Impuesto=\"002\"\n" +
            "                                    TasaOCuota=\"0.530000\"\n" +
            "                                    TipoFactor=\"Tasa\"/>\n" +
            "                </cfdi:Retenciones>\n" +
            "            </cfdi:Impuestos>\n" +
            "            <cfdi:InformacionAduanera NumeroPedimento=\"15  48  4567  6001234\"/>\n" +
            "        </cfdi:Concepto>\n" +
            "        <cfdi:Concepto Cantidad=\"1.7\"\n" +
            "                       ClaveProdServ=\"01010101\"\n" +
            "                       ClaveUnidad=\"F52\"\n" +
            "                       Descripcion=\"ZAMAC\"\n" +
            "                       Importe=\"998\"\n" +
            "                       NoIdentificacion=\"00003\"\n" +
            "                       Unidad=\"TONELADA\"\n" +
            "                       ValorUnitario=\"10000\">\n" +
            "            <cfdi:Impuestos>\n" +
            "                <cfdi:Traslados>\n" +
            "                    <cfdi:Traslado Base=\"17000\"\n" +
            "                                   Importe=\"2720\"\n" +
            "                                   Impuesto=\"001\"\n" +
            "                                   TasaOCuota=\"0.530000\"\n" +
            "                                   TipoFactor=\"Tasa\"/>\n" +
            "                </cfdi:Traslados>\n" +
            "                <cfdi:Retenciones>\n" +
            "                    <cfdi:Retencion Base=\"17000\"\n" +
            "                                    Importe=\"1870\"\n" +
            "                                    Impuesto=\"002\"\n" +
            "                                    TasaOCuota=\"0.160000\"\n" +
            "                                    TipoFactor=\"Tasa\"/>\n" +
            "                </cfdi:Retenciones>\n" +
            "            </cfdi:Impuestos>\n" +
            "            <cfdi:Parte Cantidad=\"1.0\"\n" +
            "                        ClaveProdServ=\"01010101\"\n" +
            "                        Descripcion=\"PARTE EJEMPLO\"\n" +
            "                        Importe=\"1.00\"\n" +
            "                        NoIdentificacion=\"055155\"\n" +
            "                        Unidad=\"UNIDAD\"\n" +
            "                        ValorUnitario=\"1.00\">\n" +
            "                <cfdi:InformacionAduanera NumeroPedimento=\"15  48  4567  6981235\"/>\n" +
            "            </cfdi:Parte>\n" +
            "        </cfdi:Concepto>\n" +
            "    </cfdi:Conceptos>\n" +
            "    <cfdi:Impuestos TotalImpuestosRetenidos=\"247500\"\n" +
            "                    TotalImpuestosTrasladados=\"360000\">\n" +
            "        <cfdi:Retenciones>\n" +
            "            <cfdi:Retencion Importe=\"247500\"\n" +
            "                            Impuesto=\"002\"/>\n" +
            "        </cfdi:Retenciones>\n" +
            "        <cfdi:Traslados>\n" +
            "            <cfdi:Traslado Importe=\"360000\"\n" +
            "                           Impuesto=\"001\"\n" +
            "                           TasaOCuota=\"0.160000\"\n" +
            "                           TipoFactor=\"Tasa\"/>\n" +
            "        </cfdi:Traslados>\n" +
            "    </cfdi:Impuestos>\n" +
            "    <cfdi:Complemento>\n" +
            "    </cfdi:Complemento>\n" +
            "</cfdi:Comprobante>";
}
