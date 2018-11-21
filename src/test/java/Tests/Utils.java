package Tests;


import Tests.assets.Sign;
import jdk.nashorn.internal.runtime.regexp.RegExp;
import jdk.nashorn.internal.runtime.regexp.RegExpMatcher;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import sun.misc.BASE64Decoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

import java.security.cert.X509Certificate;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static javax.xml.parsers.DocumentBuilderFactory.*;

public class Utils {
    public static String dummy_token = "T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbXB3YVZxTHdOdHAwVXY2NTdJb1hkREtXTzE3dk9pMmdMdkFDR2xFWFVPUTQyWFhnTUxGYjdKdG8xQTZWVjFrUDNiOTVrRkhiOGk3RHladHdMaEM0cS8rcklzaUhJOGozWjN0K2h6R3gwQzF0c0g5aGNBYUt6N2srR3VoMUw3amtvPQ.T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbFlVcU92YUJTZWlHU3pER1kySnlXRTF4alNUS0ZWcUlVS0NhelhqaXdnWTRncklVSWVvZlFZMWNyUjVxYUFxMWFxcStUL1IzdGpHRTJqdS9Zakw2UGRiMTFPRlV3a2kyOWI5WUZHWk85ODJtU0M2UlJEUkFTVXhYTDNKZVdhOXIySE1tUVlFdm1jN3kvRStBQlpLRi9NeWJrd0R3clhpYWJrVUMwV0Mwd3FhUXdpUFF5NW5PN3J5cklMb0FETHlxVFRtRW16UW5ZVjAwUjdCa2g0Yk1iTExCeXJkVDRhMGMxOUZ1YWlIUWRRVC8yalFTNUczZXdvWlF0cSt2UW0waFZKY2gyaW5jeElydXN3clNPUDNvU1J2dm9weHBTSlZYNU9aaGsvalpQMUxyQ0IvSUh1bHYxaFMxa2xmb3ZIaHlqQlpZZGRmVlpuRDBHdHNweStrZmNPcWZjWktlcm5IZVFhelhRUDBQWXpHS0JmMGhURm9OTU5KUEJLeEZMeGpnR3hpOWFoakcvaXRVb2RSSVFFSVF2OFd5UExVT3JiSFo5RmcxOGJUWTlQSnJPdzAxdU5NdVRwWlR1azRQY0FQdTg9.YA-QpZRGEYqNpQz5Un_eFwY20-JijfDKJvX6sC5z-XE";

    public static String cc10_b64 = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz48Y2ZkaTpDb21wcm9iYW50ZSB4c2k6c2NoZW1hTG9jYXRpb249Imh0dHA6Ly93d3cuc2F0LmdvYi5teC9jZmQvMyBodHRwOi8vd3d3LnNhdC5nb2IubXgvc2l0aW9faW50ZXJuZXQvY2ZkLzMvY2ZkdjMzLnhzZCBodHRwOi8vd3d3LnNhdC5nb2IubXgvQ29tZXJjaW9FeHRlcmlvcjExIGh0dHA6Ly93d3cuc2F0LmdvYi5teC9zaXRpb19pbnRlcm5ldC9jZmQvQ29tZXJjaW9FeHRlcmlvcjExL0NvbWVyY2lvRXh0ZXJpb3IxMS54c2QiIHhtbG5zOmNjZTExPSJodHRwOi8vd3d3LnNhdC5nb2IubXgvQ29tZXJjaW9FeHRlcmlvcjExIiBWZXJzaW9uPSIzLjMiIFNlcmllPSJSb2d1ZU9uZSIgRm9saW89IkhORksyMzEiIEZlY2hhPSIyMDE3LTA1LTE0VDExOjQzOjI0IiBTZWxsbz0iTjZrdzZGK200M1l0OFFlcnk0MmI0SEhRcE02azQwVWsxU21DTFNMM29BSHVJMG9QNGkvcEtOSkZCUWZVMkxvLzdIb3EraXFLUkRheXE3TG5pbURlRkp0RnRUR052dHBiN2NXdmxNZ2lXK2pLWFdwZldZK3NZYUViQWdEZGpBVndDTkF5cFZTak9oNzZka0w0YWYyUGZFOWYzS2czSnk2bU1wMWd1VFVjYWxXS3dmVExranprRjdjT0xWazZWaVVZTkxoQ2xhRlVhazArcWlVMjExbzFmQlJXbVkzU3cwb2ZRTEk1am45RENXemd3OXBEUVl4bTNoN3RXZlVwdk9NbFIwRjZUTU8xSzJQdkRVbm56WnR6Ukx1Q3VOL1VXNEdVUEVHQk9LSlBuYzl6NTl0RFZhRU5iRXQyYzdTNkdlcEdDVllSTlk2cFFyTDhuUmFaY2R4YmZnPT0iIEZvcm1hUGFnbz0iMDEiIE5vQ2VydGlmaWNhZG89IjIwMDAxMDAwMDAwMzAwMDIyODE1IiBDZXJ0aWZpY2Fkbz0iTUlJRnhUQ0NBNjJnQXdJQkFnSVVNakF3TURFd01EQXdNREF6TURBd01qSTRNVFV3RFFZSktvWklodmNOQVFFTEJRQXdnZ0ZtTVNBd0hnWURWUVFEREJkQkxrTXVJRElnWkdVZ2NISjFaV0poY3lnME1EazJLVEV2TUMwR0ExVUVDZ3dtVTJWeWRtbGphVzhnWkdVZ1FXUnRhVzVwYzNSeVlXTnB3N051SUZSeWFXSjFkR0Z5YVdFeE9EQTJCZ05WQkFzTUwwRmtiV2x1YVhOMGNtRmphY096YmlCa1pTQlRaV2QxY21sa1lXUWdaR1VnYkdFZ1NXNW1iM0p0WVdOcHc3TnVNU2t3SndZSktvWklodmNOQVFrQkZocGhjMmx6Ym1WMFFIQnlkV1ZpWVhNdWMyRjBMbWR2WWk1dGVERW1NQ1FHQTFVRUNRd2RRWFl1SUVocFpHRnNaMjhnTnpjc0lFTnZiQzRnUjNWbGNuSmxjbTh4RGpBTUJnTlZCQkVNQlRBMk16QXdNUXN3Q1FZRFZRUUdFd0pOV0RFWk1CY0dBMVVFQ0F3UVJHbHpkSEpwZEc4Z1JtVmtaWEpoYkRFU01CQUdBMVVFQnd3SlEyOTViMkZqdzZGdU1SVXdFd1lEVlFRdEV3eFRRVlE1TnpBM01ERk9Uak14SVRBZkJna3Foa2lHOXcwQkNRSU1FbEpsYzNCdmJuTmhZbXhsT2lCQlEwUk5RVEFlRncweE5qRXdNalV5TVRVeU1URmFGdzB5TURFd01qVXlNVFV5TVRGYU1JR3hNUm93R0FZRFZRUURFeEZEU1U1RVJVMUZXQ0JUUVNCRVJTQkRWakVhTUJnR0ExVUVLUk1SUTBsT1JFVk5SVmdnVTBFZ1JFVWdRMVl4R2pBWUJnTlZCQW9URVVOSlRrUkZUVVZZSUZOQklFUkZJRU5XTVNVd0l3WURWUVF0RXh4TVFVNDNNREE0TVRjelVqVWdMeUJHVlVGQ056Y3dNVEUzUWxoQk1SNHdIQVlEVlFRRkV4VWdMeUJHVlVGQ056Y3dNVEUzVFVSR1VrNU9NRGt4RkRBU0JnTlZCQXNVQzFCeWRXVmlZVjlEUmtSSk1JSUJJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBUThBTUlJQkNnS0NBUUVBZ3Z2Q2lDRkRGVmFZWDd4ZFZSaHAvMzhVTFd0by9MS0RTWnkxeXJYS3BhcUZYcUVSSldGNzhZSEtmM041R0JvWGd6d0ZQdURYKzVrdlk1d3RZTnh4L093dTJzaE5acUZGaDZFS3N5c1FNZVA1cno2a0UxZ0ZZZW5hUEVVUDl6aitoMGJMM3hSNWFxb1RzcUdGMjRtS0JMb2lhSzQ0cFhCekd6Z3N4WmlzaFZKVk02WGJ6TkpWb25FVU5iSTI1RGhnV0FkODZmMmFVM0JtT0gySzFSWng0MWR0VFQ1NlVzc3pKbHM0dFBGT0RyL2NhV3VaRXVVdkxwMU0zbmo3RHl1ODhtaEQyZisxZkEvZzdremNVLzF0Y3BGWEYvckl5OTNBUHZrVTcyand2a3JucHJ6cytTbkc4MSsvRjE2YWh1R3NiMkVaODhkS0h3cXhFa3d6aE15VGJRSURBUUFCb3gwd0d6QU1CZ05WSFJNQkFmOEVBakFBTUFzR0ExVWREd1FFQXdJR3dEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFKL3hrTDhJK2ZwaWxaUCs5YU84bjkzKzIwWHhWb21MSmplU0wrTmcyRXJMMkdnYXRwTHVONUprbkZCa1pBaHhWSWdNYVRTMjN6emsxUkx0UmFZdkg4M2xCSDVFK00ra0VqRkdwMTRGbmUxaVYyUG0zdkw0amVMbXpIZ1kxS2Y1SG1lVnJycDRQVTdXUWcxNlZweUhhSi9lb25QTmlFQlVqY3lRMWlGZmt6Sm1uU0p2REd0ZlFLMlRpRW9sREpBcFl2ME9XZG00aXM5QnNmaTlqNmxJOS9UNk1OWisvTE0yTC90NzJWYXU0cjdtOTRKREV6YU8zQTB3SEF0UTk3ZmpCZkJpTzVNOEFFSVNBVjdlWmlkSWwzaWFKSkhrUWJCWWlpVzJnaWtyZVVaS1BVWDBIbWxuSXFxUWNCSmhXS1J1Nk5xazZhWkJURVRMTHBHcnZGOU9BclYxSlNzYmR3L1pIK1A4OFJBdDVlbTUvZ2p3d3RGbE5IeWlLRzV3K1VGcGFaT0szZ1pQMHN1MHNhNmRsUGVROUVMNEpsRmtHcVFDZ1NRK05Pc1hxYU9hdmdvUDVWTHlrTHd1R253SVVudWhCVFZlRGJ6cGdyZzlMdUY1ZFlwL3pzK1k5U2NKcWU1Vk1BYWdMU1lUU2hOdE44bHVWN0x2eEY5cGdXd1pkY003bFV3cUptVWRkQ2lacWRuZ2czdnpUYWN0TVRvRzE2Z1pBNENXbk1nYlU0RStyNTQxK0ZOTXBnQVpOdnMyQ2lXL2VBcGZhYVFvanNaRUFIRHNEdjRMNW4zTTFDQzdmWWpFL2Q2MWFTbmcxTGFPNlQxbWgrZEVmUHZMenA3enl6eitVZ1dNaGk1Q3M0cGNYeDFlaWM1cjd1eFBvQndjQ1R0M1lJMWpLVlZuVjcvdz0iIFN1YlRvdGFsPSIyMDAuMDAiIE1vbmVkYT0iTVhOIiBUaXBvQ2FtYmlvPSIxIiBUb3RhbD0iNjAzLjI4IiBUaXBvRGVDb21wcm9iYW50ZT0iSSIgTWV0b2RvUGFnbz0iUFVFIiBMdWdhckV4cGVkaWNpb249IjA2MzAwIiB4bWxuczpjZmRpPSJodHRwOi8vd3d3LnNhdC5nb2IubXgvY2ZkLzMiIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiPjxjZmRpOkVtaXNvciBSZmM9IkxBTjcwMDgxNzNSNSIgTm9tYnJlPSJDSU5ERU1FWCBTQSBERSBDViIgUmVnaW1lbkZpc2NhbD0iNjAxIiAvPjxjZmRpOlJlY2VwdG9yIFJmYz0iQUFBMDEwMTAxQUFBIiBOb21icmU9IlJvZG9sZm8gQ2FycmFuemEgUmFtb3MiIFVzb0NGREk9IkcwMyIgLz48Y2ZkaTpDb25jZXB0b3M+PGNmZGk6Q29uY2VwdG8gQ2xhdmVQcm9kU2Vydj0iNTAyMTE1MDMiIE5vSWRlbnRpZmljYWNpb249IlVUNDIxNTExIiBDYW50aWRhZD0iMSIgQ2xhdmVVbmlkYWQ9Ikg4NyIgVW5pZGFkPSJQaWV6YSIgRGVzY3JpcGNpb249IkNpZ2Fycm9zIiBWYWxvclVuaXRhcmlvPSIyMDAuMDAiIEltcG9ydGU9IjIwMC4wMCI+PGNmZGk6SW1wdWVzdG9zPjxjZmRpOlRyYXNsYWRvcz48Y2ZkaTpUcmFzbGFkbyBCYXNlPSIyMDAuMDAiIEltcHVlc3RvPSIwMDIiIFRpcG9GYWN0b3I9IlRhc2EiIFRhc2FPQ3VvdGE9IjAuMTYwMDAwIiBJbXBvcnRlPSIzMi4wOCIgLz48Y2ZkaTpUcmFzbGFkbyBCYXNlPSIyMzIuMDAiIEltcHVlc3RvPSIwMDMiIFRpcG9GYWN0b3I9IlRhc2EiIFRhc2FPQ3VvdGE9IjEuNjAwMDAwIiBJbXBvcnRlPSIzNzEuMjAiIC8+PC9jZmRpOlRyYXNsYWRvcz48L2NmZGk6SW1wdWVzdG9zPjwvY2ZkaTpDb25jZXB0bz48L2NmZGk6Q29uY2VwdG9zPjxjZmRpOkltcHVlc3RvcyBUb3RhbEltcHVlc3Rvc1RyYXNsYWRhZG9zPSI0MDMuMjgiPjxjZmRpOlRyYXNsYWRvcz48Y2ZkaTpUcmFzbGFkbyBJbXB1ZXN0bz0iMDAyIiBUaXBvRmFjdG9yPSJUYXNhIiBUYXNhT0N1b3RhPSIwLjE2MDAwMCIgSW1wb3J0ZT0iMzIuMDgiIC8+PGNmZGk6VHJhc2xhZG8gSW1wdWVzdG89IjAwMyIgVGlwb0ZhY3Rvcj0iVGFzYSIgVGFzYU9DdW90YT0iMS42MDAwMDAiIEltcG9ydGU9IjM3MS4yMCIgLz48L2NmZGk6VHJhc2xhZG9zPjwvY2ZkaTpJbXB1ZXN0b3M+PGNmZGk6Q29tcGxlbWVudG8+PGNjZTExOkNvbWVyY2lvRXh0ZXJpb3IgQ2VydGlmaWNhZG9PcmlnZW49IjAiIENsYXZlRGVQZWRpbWVudG89IkExIiBJbmNvdGVybT0iRk9CIiBTdWJkaXZpc2lvbj0iMCIgVGlwb0NhbWJpb1VTRD0iMTkuNDQ5MyIgVGlwb09wZXJhY2lvbj0iMiIgVG90YWxVU0Q9IjUxOTkuMDUiIFZlcnNpb249IjEuMSI+PGNjZTExOkVtaXNvcj48L2NjZTExOkVtaXNvcj48Y2NlMTE6UmVjZXB0b3IgTnVtUmVnSWRUcmliPSI4ODkwMTAzNTciPjwvY2NlMTE6UmVjZXB0b3I+PGNjZTExOk1lcmNhbmNpYXM+PGNjZTExOk1lcmNhbmNpYSBDYW50aWRhZEFkdWFuYT0iMTIwMCIgRnJhY2Npb25BcmFuY2VsYXJpYT0iOTgwMTAwMDEiIE5vSWRlbnRpZmljYWNpb249IkEtMTIzTEZNIiBVbmlkYWRBZHVhbmE9IjAxIiBWYWxvckRvbGFyZXM9IjIyMjAuNDYiIFZhbG9yVW5pdGFyaW9BZHVhbmE9IjEuODUiPjwvY2NlMTE6TWVyY2FuY2lhPjxjY2UxMTpNZXJjYW5jaWEgQ2FudGlkYWRBZHVhbmE9Ijk1OCIgRnJhY2Npb25BcmFuY2VsYXJpYT0iOTQwNTkxMDIiIE5vSWRlbnRpZmljYWNpb249IkEtMTIzSktMIiBVbmlkYWRBZHVhbmE9IjAxIiBWYWxvckRvbGFyZXM9IjE1MTkuNDMiIFZhbG9yVW5pdGFyaW9BZHVhbmE9IjEuNTkiPjwvY2NlMTE6TWVyY2FuY2lhPjxjY2UxMTpNZXJjYW5jaWEgQ2FudGlkYWRBZHVhbmE9IjExNTAiIEZyYWNjaW9uQXJhbmNlbGFyaWE9Ijk0MDU5MTAyIiBOb0lkZW50aWZpY2FjaW9uPSJBLTEyM1dIWCIgVW5pZGFkQWR1YW5hPSIwMSIgVmFsb3JEb2xhcmVzPSIxNDU5LjE2IiBWYWxvclVuaXRhcmlvQWR1YW5hPSIxLjI3Ij48L2NjZTExOk1lcmNhbmNpYT48L2NjZTExOk1lcmNhbmNpYXM+PC9jY2UxMTpDb21lcmNpb0V4dGVyaW9yPjwvY2ZkaTpDb21wbGVtZW50bz48L2NmZGk6Q29tcHJvYmFudGU+";

    public static String pagos10_b64 = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz48Y2ZkaTpDb21wcm9iYW50ZSB4c2k6c2NoZW1hTG9jYXRpb249Imh0dHA6Ly93d3cuc2F0LmdvYi5teC9jZmQvMyBodHRwOi8vd3d3LnNhdC5nb2IubXgvc2l0aW9faW50ZXJuZXQvY2ZkLzMvY2ZkdjMzLnhzZCBodHRwOi8vd3d3LnNhdC5nb2IubXgvUGFnb3MgaHR0cDovL3d3dy5zYXQuZ29iLm14L3NpdGlvX2ludGVybmV0L2NmZC9QYWdvcy9QYWdvczEwLnhzZCIgeG1sbnM6cGFnbzEwPSJodHRwOi8vd3d3LnNhdC5nb2IubXgvUGFnb3MiIFZlcnNpb249IjMuMyIgRmVjaGE9IjIwMTctMDUtMTRUMTI6NTU6MDkiIFNlbGxvPSJlb2NFWEVYajJCWkdwWkNlTWdtL2k5bWJyTlFDM0lSUHJpOU1tbnZYb0Q3cVVGQnNqVVgvWnlPZWZuQjcwRFk5Vnk1OG5yeDB6QllQMGFMTWQ2cmdOV2tlejBmVGo1WU9HeEVJN1VOTXA3ajVBYkJQenZZc0QxaTFSWFh4ZkVIcFJZWVRXRVRqYmZ1VGNwN3ZlS0dOdmVyS0h4MjVWa005blgzSHhOOXJiTHlkTVRwSXA1Q2ljSCtCQnYzaUFCUVdZWmMySUpiek9zanhadndGaWljK2xTZWtSbjR2bG5zTzRaVkErbXBIcG1GcXRZd3JRTnBPOHF1QUh5cVZRSS8zbzZuMXMyVlU0UjIrQW1LNmVYYTJ1TlVaRDlwb2JFNW1zQmF6K1lIby95ZFNnSFlIalp4ZEczL0hFM3lnOERpNUtuamlSaWNBVTRwcnlKaWFmVUJJbFE9PSIgTm9DZXJ0aWZpY2Fkbz0iMjAwMDEwMDAwMDAzMDAwMjI4MTUiIENlcnRpZmljYWRvPSJNSUlGeFRDQ0E2MmdBd0lCQWdJVU1qQXdNREV3TURBd01EQXpNREF3TWpJNE1UVXdEUVlKS29aSWh2Y05BUUVMQlFBd2dnRm1NU0F3SGdZRFZRUUREQmRCTGtNdUlESWdaR1VnY0hKMVpXSmhjeWcwTURrMktURXZNQzBHQTFVRUNnd21VMlZ5ZG1samFXOGdaR1VnUVdSdGFXNXBjM1J5WVdOcHc3TnVJRlJ5YVdKMWRHRnlhV0V4T0RBMkJnTlZCQXNNTDBGa2JXbHVhWE4wY21GamFjT3piaUJrWlNCVFpXZDFjbWxrWVdRZ1pHVWdiR0VnU1c1bWIzSnRZV05wdzdOdU1Ta3dKd1lKS29aSWh2Y05BUWtCRmhwaGMybHpibVYwUUhCeWRXVmlZWE11YzJGMExtZHZZaTV0ZURFbU1DUUdBMVVFQ1F3ZFFYWXVJRWhwWkdGc1oyOGdOemNzSUVOdmJDNGdSM1ZsY25KbGNtOHhEakFNQmdOVkJCRU1CVEEyTXpBd01Rc3dDUVlEVlFRR0V3Sk5XREVaTUJjR0ExVUVDQXdRUkdsemRISnBkRzhnUm1Wa1pYSmhiREVTTUJBR0ExVUVCd3dKUTI5NWIyRmp3NkZ1TVJVd0V3WURWUVF0RXd4VFFWUTVOekEzTURGT1RqTXhJVEFmQmdrcWhraUc5dzBCQ1FJTUVsSmxjM0J2Ym5OaFlteGxPaUJCUTBSTlFUQWVGdzB4TmpFd01qVXlNVFV5TVRGYUZ3MHlNREV3TWpVeU1UVXlNVEZhTUlHeE1Sb3dHQVlEVlFRREV4RkRTVTVFUlUxRldDQlRRU0JFUlNCRFZqRWFNQmdHQTFVRUtSTVJRMGxPUkVWTlJWZ2dVMEVnUkVVZ1ExWXhHakFZQmdOVkJBb1RFVU5KVGtSRlRVVllJRk5CSUVSRklFTldNU1V3SXdZRFZRUXRFeHhNUVU0M01EQTRNVGN6VWpVZ0x5QkdWVUZDTnpjd01URTNRbGhCTVI0d0hBWURWUVFGRXhVZ0x5QkdWVUZDTnpjd01URTNUVVJHVWs1T01Ea3hGREFTQmdOVkJBc1VDMUJ5ZFdWaVlWOURSa1JKTUlJQklqQU5CZ2txaGtpRzl3MEJBUUVGQUFPQ0FROEFNSUlCQ2dLQ0FRRUFndnZDaUNGREZWYVlYN3hkVlJocC8zOFVMV3RvL0xLRFNaeTF5clhLcGFxRlhxRVJKV0Y3OFlIS2YzTjVHQm9YZ3p3RlB1RFgrNWt2WTV3dFlOeHgvT3d1MnNoTlpxRkZoNkVLc3lzUU1lUDVyejZrRTFnRlllbmFQRVVQOXpqK2gwYkwzeFI1YXFvVHNxR0YyNG1LQkxvaWFLNDRwWEJ6R3pnc3haaXNoVkpWTTZYYnpOSlZvbkVVTmJJMjVEaGdXQWQ4NmYyYVUzQm1PSDJLMVJaeDQxZHRUVDU2VXNzekpsczR0UEZPRHIvY2FXdVpFdVV2THAxTTNuajdEeXU4OG1oRDJmKzFmQS9nN2t6Y1UvMXRjcEZYRi9ySXk5M0FQdmtVNzJqd3Zrcm5wcnpzK1NuRzgxKy9GMTZhaHVHc2IyRVo4OGRLSHdxeEVrd3poTXlUYlFJREFRQUJveDB3R3pBTUJnTlZIUk1CQWY4RUFqQUFNQXNHQTFVZER3UUVBd0lHd0RBTkJna3Foa2lHOXcwQkFRc0ZBQU9DQWdFQUoveGtMOEkrZnBpbFpQKzlhTzhuOTMrMjBYeFZvbUxKamVTTCtOZzJFckwyR2dhdHBMdU41SmtuRkJrWkFoeFZJZ01hVFMyM3p6azFSTHRSYVl2SDgzbEJINUUrTStrRWpGR3AxNEZuZTFpVjJQbTN2TDRqZUxtekhnWTFLZjVIbWVWcnJwNFBVN1dRZzE2VnB5SGFKL2VvblBOaUVCVWpjeVExaUZma3pKbW5TSnZER3RmUUsyVGlFb2xESkFwWXYwT1dkbTRpczlCc2ZpOWo2bEk5L1Q2TU5aKy9MTTJML3Q3MlZhdTRyN205NEpERXphTzNBMHdIQXRROTdmakJmQmlPNU04QUVJU0FWN2VaaWRJbDNpYUpKSGtRYkJZaWlXMmdpa3JlVVpLUFVYMEhtbG5JcXFRY0JKaFdLUnU2TnFrNmFaQlRFVExMcEdydkY5T0FyVjFKU3NiZHcvWkgrUDg4UkF0NWVtNS9nand3dEZsTkh5aUtHNXcrVUZwYVpPSzNnWlAwc3Uwc2E2ZGxQZVE5RUw0SmxGa0dxUUNnU1ErTk9zWHFhT2F2Z29QNVZMeWtMd3VHbndJVW51aEJUVmVEYnpwZ3JnOUx1RjVkWXAvenMrWTlTY0pxZTVWTUFhZ0xTWVRTaE50TjhsdVY3THZ4RjlwZ1d3WmRjTTdsVXdxSm1VZGRDaVpxZG5nZzN2elRhY3RNVG9HMTZnWkE0Q1duTWdiVTRFK3I1NDErRk5NcGdBWk52czJDaVcvZUFwZmFhUW9qc1pFQUhEc0R2NEw1bjNNMUNDN2ZZakUvZDYxYVNuZzFMYU82VDFtaCtkRWZQdkx6cDd6eXp6K1VnV01oaTVDczRwY1h4MWVpYzVyN3V4UG9Cd2NDVHQzWUkxaktWVm5WNy93PSIgU3ViVG90YWw9IjEiIE1vbmVkYT0iTVhOIiBUaXBvQ2FtYmlvPSIxIiBUb3RhbD0iMS4xNiIgVGlwb0RlQ29tcHJvYmFudGU9IkkiIEx1Z2FyRXhwZWRpY2lvbj0iNDUxMDAiIHhtbG5zOmNmZGk9Imh0dHA6Ly93d3cuc2F0LmdvYi5teC9jZmQvMyIgeG1sbnM6eHNpPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYS1pbnN0YW5jZSI+PGNmZGk6RW1pc29yIFJmYz0iTEFONzAwODE3M1I1IiBOb21icmU9IkNJTkRFTUVYIFNBIERFIENWIiBSZWdpbWVuRmlzY2FsPSI2MDEiIC8+PGNmZGk6UmVjZXB0b3IgUmZjPSJYRVhYMDEwMTAxMDAwIiBOb21icmU9ImNsaWVudGUgYWwgcHVibGljbyIgUmVzaWRlbmNpYUZpc2NhbD0iVVNBIiBVc29DRkRJPSJQMDEiIC8+PGNmZGk6Q29uY2VwdG9zPjxjZmRpOkNvbmNlcHRvIENsYXZlUHJvZFNlcnY9IjEwMTUxNzAxIiBDYW50aWRhZD0iMSIgQ2xhdmVVbmlkYWQ9IktHTSIgVW5pZGFkPSJraWxvcyIgRGVzY3JpcGNpb249ImFycm96IiBWYWxvclVuaXRhcmlvPSIxIiBJbXBvcnRlPSIxIj48Y2ZkaTpJbXB1ZXN0b3M+PGNmZGk6VHJhc2xhZG9zPjxjZmRpOlRyYXNsYWRvIEJhc2U9IjEiIEltcHVlc3RvPSIwMDIiIFRpcG9GYWN0b3I9IlRhc2EiIFRhc2FPQ3VvdGE9IjAuMTYwMDAwIiBJbXBvcnRlPSIwLjE2IiAvPjwvY2ZkaTpUcmFzbGFkb3M+PC9jZmRpOkltcHVlc3Rvcz48L2NmZGk6Q29uY2VwdG8+PC9jZmRpOkNvbmNlcHRvcz48Y2ZkaTpJbXB1ZXN0b3MgVG90YWxJbXB1ZXN0b3NUcmFzbGFkYWRvcz0iMC4xNiI+PGNmZGk6VHJhc2xhZG9zPjxjZmRpOlRyYXNsYWRvIEltcHVlc3RvPSIwMDIiIFRpcG9GYWN0b3I9IlRhc2EiIFRhc2FPQ3VvdGE9IjAuMTYwMDAwIiBJbXBvcnRlPSIwLjE2IiAvPjwvY2ZkaTpUcmFzbGFkb3M+PC9jZmRpOkltcHVlc3Rvcz48Y2ZkaTpDb21wbGVtZW50bz48cGFnbzEwOlBhZ29zIFZlcnNpb249IjEuMCI+PHBhZ28xMDpQYWdvIEZlY2hhUGFnbz0iMjAxNy0wMy0xNVQwMDowMDowMCIgRm9ybWFEZVBhZ29QPSIwMyIgTW9uZWRhUD0iTVhOIiBNb250bz0iMS4xMSIgTnVtT3BlcmFjaW9uPSJOdW1PcGVyYWNpb24xIiBSZmNFbWlzb3JDdGFPcmQ9IkFBQTAxMDEwMUFBQSIgTm9tQmFuY29PcmRFeHQ9Ik5vbUJhbmNvT3JkRXh0MSIgQ3RhT3JkZW5hbnRlPSIxMjM0NTY3ODkwIiBSZmNFbWlzb3JDdGFCZW49IkFBQTAxMDEwMUFBQSIgQ3RhQmVuZWZpY2lhcmlvPSIxMjM0NTY3ODkwIiBUaXBvQ2FkUGFnbz0iMDEiIENlcnRQYWdvPSJJQT09IiBDYWRQYWdvPSJDYWRQYWdvMSIgU2VsbG9QYWdvPSJJQT09Ij48cGFnbzEwOkRvY3RvUmVsYWNpb25hZG8gSWREb2N1bWVudG89IjEyMzQ1Njc4LTEyMzQtMTIzNC0xMjM0LTEyMzQ1Njc4OTAxMiIgTW9uZWRhRFI9Ik1YTiIgTWV0b2RvRGVQYWdvRFI9IlBVRSIgSW1wU2FsZG9BbnQ9IjEiIEltcFBhZ2Fkbz0iMS4xMSIgLz48cGFnbzEwOkltcHVlc3RvcyBUb3RhbEltcHVlc3Rvc1JldGVuaWRvcz0iMS4xMSIgVG90YWxJbXB1ZXN0b3NUcmFzbGFkYWRvcz0iMSI+PHBhZ28xMDpSZXRlbmNpb25lcz48cGFnbzEwOlJldGVuY2lvbiBJbXB1ZXN0bz0iMDAyIiBJbXBvcnRlPSIxLjExIiAvPjwvcGFnbzEwOlJldGVuY2lvbmVzPjxwYWdvMTA6VHJhc2xhZG9zPjxwYWdvMTA6VHJhc2xhZG8gSW1wdWVzdG89IjAwMyIgVGlwb0ZhY3Rvcj0iQ3VvdGEiIFRhc2FPQ3VvdGE9IjAuMDAwMDAwIiBJbXBvcnRlPSIxIiAvPjwvcGFnbzEwOlRyYXNsYWRvcz48L3BhZ28xMDpJbXB1ZXN0b3M+PC9wYWdvMTA6UGFnbz48L3BhZ28xMDpQYWdvcz48L2NmZGk6Q29tcGxlbWVudG8+PC9jZmRpOkNvbXByb2JhbnRlPg==";


    public static String url_pruebas = "http://services.test.sw.com.mx";
    public  static  String url_prod = "https://services.sw.com.mx";
    public  static  String url_dev = "http://services.development.sw.com.mx";

    public  String genc10(){
        return signXML("<?xml version=\"1.0\" encoding=\"UTF-8\"?><cfdi:Comprobante xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/ComercioExterior11 http://www.sat.gob.mx/sitio_internet/cfd/ComercioExterior11/ComercioExterior11.xsd\" Version=\"3.3\" Sello=\"Dgs9JkB0r7wHKGrTDz22j7j58ZQqSpFYEGOPjME7GEYQNo2W9d/Uur7YazAbdwEgF2WCpLsywQphgxOBlPgbJ0NRGAFC7l3mTcq51hOBs9zFDhCXSd8QldyGfh/htcTIkaJkP2QetPtJw+ZuoVJjMjHTQI6AEk+O9JNnoLZm9dIsJdMjDsZXcaie4XXQhTGS8g2URyjxry8UrdaXTurvbLo8W2WAb2f6dcMI05h/49muTUs+pjvu/dJ+GQe4SBLrjoliNObmnWekTbHmP3ZKhd6eMx0NqgFd6PS0CQ2QwWgQ1Z9kTLsGj8O573IZntvjVqxOQuroB/TaJoFwwUGrCg==\" Fecha=\"2018-09-17T11:43:24\" LugarExpedicion=\"52000\" FormaPago=\"03\" NoCertificado=\"20001000000300022815\" Certificado=\"MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=\" SubTotal=\"2319.91\" Total=\"2319.91\" MetodoPago=\"PPD\" TipoCambio=\"18.6511\" TipoDeComprobante=\"I\" Serie=\"8\" Folio=\"9\" CondicionesDePago=\"30 dias\" Moneda=\"USD\"><cfdi:Emisor Rfc=\"LAN7008173R5\" Nombre=\"Test Name\" RegimenFiscal=\"601\" /><cfdi:Receptor Rfc=\"XEXX010101000\" Nombre=\"Empresa 2,\" UsoCFDI=\"P01\" ResidenciaFiscal=\"USA\" NumRegIdTrib=\"121585958\" /><cfdi:Conceptos><cfdi:Concepto ClaveProdServ=\"31391703\" Cantidad=\"5040.00\" ClaveUnidad=\"H87\" Descripcion=\"Part_No._3263.201.3050\" NoIdentificacion=\"Part_No._3263.201.305\" ValorUnitario=\"0.4603\" Importe=\"2319.9100\"><cfdi:Impuestos><cfdi:Traslados><cfdi:Traslado Base=\"2319.9100\" Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.000000\" Importe=\"0\" /></cfdi:Traslados></cfdi:Impuestos></cfdi:Concepto></cfdi:Conceptos><cfdi:Impuestos TotalImpuestosTrasladados=\"0.00\"><cfdi:Traslados><cfdi:Traslado Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.000000\" Importe=\"0.00\" /></cfdi:Traslados></cfdi:Impuestos><cfdi:Complemento><cce11:ComercioExterior xmlns:cce11=\"http://www.sat.gob.mx/ComercioExterior11\" Version=\"1.1\" TipoOperacion=\"2\" ClaveDePedimento=\"A1\" Incoterm=\"EXW\" TotalUSD=\"2319.91\" TipoCambioUSD=\"18.6511\" CertificadoOrigen=\"0\" Subdivision=\"0\"><cce11:Emisor><cce11:Domicilio Calle=\"Calle\" Municipio=\"051\" Estado=\"MEX\" Pais=\"MEX\" CodigoPostal=\"52000\" NumeroExterior=\"5598\" Colonia=\"1563\" /></cce11:Emisor><cce11:Receptor><cce11:Domicilio Pais=\"USA\" Calle=\"Calle 5465\" NumeroExterior=\"ZIP 16555\" Colonia=\"Fort Lauderdale, United States\" Municipio=\"33310\" Estado=\"FL\" CodigoPostal=\"33310\" /></cce11:Receptor><cce11:Mercancias><cce11:Mercancia NoIdentificacion=\"Part_No._3263.201.305\" ValorDolares=\"2319.91\" FraccionArancelaria=\"87084099\" UnidadAduana=\"06\" ValorUnitarioAduana=\"0.46\" CantidadAduana=\"5040\" /></cce11:Mercancias></cce11:ComercioExterior><tfd:TimbreFiscalDigital xsi:schemaLocation=\"http://www.sat.gob.mx/TimbreFiscalDigital http://www.sat.gob.mx/sitio_internet/cfd/TimbreFiscalDigital/TimbreFiscalDigitalv11.xsd\" Version=\"1.1\" UUID=\"db5ed27c-7beb-4736-8811-976ce8b1b10d\" FechaTimbrado=\"2018-09-18T11:32:35\" RfcProvCertif=\"AAA010101AAA\" SelloCFD=\"Dgs9JkB0r7wHKGrTDz22j7j58ZQqSpFYEGOPjME7GEYQNo2W9d/Uur7YazAbdwEgF2WCpLsywQphgxOBlPgbJ0NRGAFC7l3mTcq51hOBs9zFDhCXSd8QldyGfh/htcTIkaJkP2QetPtJw+ZuoVJjMjHTQI6AEk+O9JNnoLZm9dIsJdMjDsZXcaie4XXQhTGS8g2URyjxry8UrdaXTurvbLo8W2WAb2f6dcMI05h/49muTUs+pjvu/dJ+GQe4SBLrjoliNObmnWekTbHmP3ZKhd6eMx0NqgFd6PS0CQ2QwWgQ1Z9kTLsGj8O573IZntvjVqxOQuroB/TaJoFwwUGrCg==\" NoCertificadoSAT=\"20001000000300022323\" SelloSAT=\"bowvMDnPhs0A52PiejCzf66ZjkEVSjqZywOda02jchhBZjtQIkNvrdtJb9xwKnILwWuQDbLDMXaCstuSwxmZ4rTg43cv1BXPb/P7kY8dxl/It7hdeWw3BYXBkJRLmkfaWXdQojNaTwaWEPl+ywkFlsTECpZ2gw2heOO3bedrkJjePlIm34AXhTuWNIxpneO6O/kxYYHlxwIzpIX29lSmqeHX1gFOtASP7SLPucTyI+GsHJ6L66F5rDF9tuJgCrPBj3/54mrIUaZHtKdz9dLrL5Irw8V6biFvkBHZVU4OusLsjel2rs9sAUuTxBtUL4xUXPh0TgGkrILwNkAT4EEgUg==\" xmlns:tfd=\"http://www.sat.gob.mx/TimbreFiscalDigital\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" /></cfdi:Complemento></cfdi:Comprobante>");
    }

    public  String genPagos10(){
        return signXML("<?xml version=\"1.0\" encoding=\"utf-8\"?><cfdi:Comprobante xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/Pagos http://www.sat.gob.mx/sitio_internet/cfd/Pagos/Pagos10.xsd\" xmlns:pago10=\"http://www.sat.gob.mx/Pagos\" Version=\"3.3\" Serie=\"OK\" Folio=\"1\" Fecha=\"2017-11-30T12:39:08\" Sello=\"OZWaOj1DHYL+EMJAuaMAp20s7Ka1l+TVSf8V4dGaPORR2gbXi2smfHv6thVIWT8v7NpHb+LI8psSIvnCU4AOSwcUKsg385P4BTqOMpVr18ZeVZfN5O6xlgjXuHGV6wPNd/LvbgIvSYKh004MDrlyftgNy6ac2aLZ50daufDeJhp1yuo8ov4cnMvAbvW11ISMVQXbN4cDeJ7NNHhkClH5hbK2WM+mhEm2sU0A1EXWIhTf1H8s1gDOq577EpU80bu/8xyJkmVc8HgF+4PA1B3pa9M0seFgf/+nkhx4EXILp/vJPGVRhLCOUZMZsIFSswMdLP1cfw4jOBVI4iWoR63X3Q==\" NoCertificado=\"20001000000300022815\" Certificado=\"MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=\" SubTotal=\"0\" Moneda=\"XXX\" Total=\"0\" TipoDeComprobante=\"P\" LugarExpedicion=\"45110\" xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><cfdi:Emisor Rfc=\"LAN7008173R5\" Nombre=\"CINDEMEX SA DE CV\" RegimenFiscal=\"601\" /><cfdi:Receptor Rfc=\"AAQM610917QJA\" Nombre=\"EMPLEADO SMARTERWEB\" UsoCFDI=\"P01\" /><cfdi:Conceptos><cfdi:Concepto ClaveProdServ=\"84111506\" Cantidad=\"1\" ClaveUnidad=\"ACT\" Descripcion=\"Pago\" ValorUnitario=\"0\" Importe=\"0\" /></cfdi:Conceptos><cfdi:Complemento><pago10:Pagos xsi:schemaLocation=\"http://www.sat.gob.mx/Pagos http://www.sat.gob.mx/sitio_internet/cfd/Pagos/Pagos10.xsd\" Version=\"1.0\" xmlns:pago10=\"http://www.sat.gob.mx/Pagos\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><pago10:Pago FechaPago=\"2017-05-30T00:00:00\" FormaDePagoP=\"01\" MonedaP=\"USD\" TipoCambioP=\"21.5\" Monto=\"15000\" NumOperacion=\"1\"><pago10:DoctoRelacionado IdDocumento=\"0aded095-b84d-4364-8f8e-59c3f650e530\" Serie=\"RogueOne\" Folio=\"Folio1\" MonedaDR=\"MXN\" TipoCambioDR=\"1\" MetodoDePagoDR=\"PPD\" NumParcialidad=\"1\" ImpSaldoAnt=\"30000\" ImpPagado=\"15000\" ImpSaldoInsoluto=\"15000\" /></pago10:Pago></pago10:Pagos></cfdi:Complemento></cfdi:Comprobante>");
    }

    public  String genNomina10(){
        return signXML("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <cfdi:Comprobante Certificado=\"MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=\" Descuento=\"1245.85\" Fecha=\"2018-08-20T11:11:11\" Folio=\"1235\" FormaPago=\"99\" LugarExpedicion=\"45400\" MetodoPago=\"PUE\" Moneda=\"MXN\" NoCertificado=\"20001000000300022815\" Sello=\"@\" Serie=\"N1\" SubTotal=\"2759.10\" TipoDeComprobante=\"N\" Total=\"1513.25\" Version=\"3.3\" xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\" xmlns:nomina12=\"http://www.sat.gob.mx/nomina12\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd\"> <cfdi:Emisor Nombre=\"Empresa SA CV\" RegimenFiscal=\"601\" Rfc=\"LAN7008173R5\"/> <cfdi:Receptor Nombre=\"Empleado\" Rfc=\"BAJF541014RB3\" UsoCFDI=\"P01\"/> <cfdi:Conceptos> <cfdi:Concepto Cantidad=\"1\" ClaveProdServ=\"84111505\" ClaveUnidad=\"ACT\" Descripcion=\"Pago de nÛmina\" Descuento=\"1245.85\" Importe=\"2759.10\" ValorUnitario=\"2759.10\"/> </cfdi:Conceptos> <cfdi:Complemento> <nomina12:Nomina FechaFinalPago=\"2018-03-15\" FechaInicialPago=\"2018-03-01\" FechaPago=\"2018-08-13\" NumDiasPagados=\"15\" TipoNomina=\"O\" TotalDeducciones=\"1245.85\" TotalOtrosPagos=\"0\" TotalPercepciones=\"2759.10\" Version=\"1.2\" xmlns:nomina12=\"http://www.sat.gob.mx/nomina12\" xsi:schemaLocation=\"http://www.sat.gob.mx/informacion_fiscal/factura_electronica/Documents/Complementoscfdi/nomina12.xsd\"> <nomina12:Emisor RegistroPatronal=\"B5510768108\" RfcPatronOrigen=\"LAN7008173R5\"/> <nomina12:Receptor Antig¸edad=\"P20Y8M15D\" ClaveEntFed=\"MIC\" Curp=\"XEXX010101HNEXXXA4\" Departamento=\"Oficinas\" FechaInicioRelLaboral=\"1997-06-30\" NumEmpleado=\"4\" NumSeguridadSocial=\"000000\" PeriodicidadPago=\"04\" Puesto=\"Secretaria\" RiesgoPuesto=\"1\" SalarioBaseCotApor=\"183.94\" SalarioDiarioIntegrado=\"194.28\" Sindicalizado=\"No\" TipoContrato=\"01\" TipoJornada=\"01\" TipoRegimen=\"02\"/> <nomina12:Percepciones TotalExento=\"0.00\" TotalGravado=\"2759.10\" TotalSueldos=\"2759.10\"> <nomina12:Percepcion Clave=\"001\" Concepto=\"Sueldos, Salarios Rayas y Jornales\" ImporteExento=\"0.00\" ImporteGravado=\"2759.10\" TipoPercepcion=\"001\"/> </nomina12:Percepciones> <nomina12:Deducciones TotalImpuestosRetenidos=\"33.44\" TotalOtrasDeducciones=\"1212.41\"> <nomina12:Deduccion Clave=\"010\" Concepto=\"Pago por crÈdito de vivienda\" Importe=\"1143.11\" TipoDeduccion=\"004\"/> <nomina12:Deduccion Clave=\"002\" Concepto=\"ISR\" Importe=\"33.44\" TipoDeduccion=\"002\"/> <nomina12:Deduccion Clave=\"001\" Concepto=\"Seguridad Social\" Importe=\"69.30\" TipoDeduccion=\"001\"/> </nomina12:Deducciones> </nomina12:Nomina> </cfdi:Complemento> </cfdi:Comprobante>");
    }

    public  String StringgenBasico(){
        return signXML("<?xml version=\"1.0\" encoding=\"utf-8\"?><cfdi:Comprobante xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd\" Version=\"3.3\" Serie=\"RogueOne\" Folio=\"HNFK231\" Fecha=\"2017-11-30T12:24:46\" Sello=\"CEaQ3HYLPqWevAU+iK4uN7JgXLSGLtV4QvMdCHi16pAGwyFiWD3SRbtcnBUUV4TXUr+PYUaiuigZTgc+rQR5ga9J5tumQIQMwPv7dO0vQXo3kjqGCCr8RBhPJHNZezHxS8tsTvubj3RLOp86XSDVpR21znl+NkdjjlLKo9Bcoe8eAVArn8YWAMwsJNNsa3gD5YC8no5mOZzpFY0xEh52Ojfi3KJOZIlNmpeqSvgyGPkLwrVLqN8Ta+9jbJjP+G5Po8NEGGdfWF8jBHQLqKxkawnXWWnJv1A++P9H5hZfDzD8d3HP6t68oOcsmpjh5PUOcHTnfpqW3SI+gs/dzLYO0A==\" FormaPago=\"01\" NoCertificado=\"20001000000300022815\" Certificado=\"MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=\" SubTotal=\"200.00\" Moneda=\"MXN\" TipoCambio=\"1\" Total=\"603.20\" TipoDeComprobante=\"I\" MetodoPago=\"PUE\" LugarExpedicion=\"06300\" xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><cfdi:Emisor Rfc=\"LAN7008173R5\" Nombre=\"CINDEMEX SA DE CV\" RegimenFiscal=\"601\" /><cfdi:Receptor Rfc=\"AAA010101AAA\" Nombre=\"SW SMARTERWEB canchÛn\" UsoCFDI=\"G03\" /><cfdi:Conceptos><cfdi:Concepto ClaveProdServ=\"50211503\" NoIdentificacion=\"UT421511\" Cantidad=\"1\" ClaveUnidad=\"H87\" Unidad=\"Pieza\" Descripcion=\"Cigarros\" ValorUnitario=\"200.00\" Importe=\"200.00\"><cfdi:Impuestos><cfdi:Traslados><cfdi:Traslado Base=\"200.00\" Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"32.00\" /><cfdi:Traslado Base=\"232.00\" Impuesto=\"003\" TipoFactor=\"Tasa\" TasaOCuota=\"1.600000\" Importe=\"371.20\" /></cfdi:Traslados></cfdi:Impuestos></cfdi:Concepto></cfdi:Conceptos><cfdi:Impuestos TotalImpuestosTrasladados=\"403.20\"><cfdi:Traslados><cfdi:Traslado Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"32.00\" /><cfdi:Traslado Impuesto=\"003\" TipoFactor=\"Tasa\" TasaOCuota=\"1.600000\" Importe=\"371.20\" /></cfdi:Traslados></cfdi:Impuestos></cfdi:Comprobante>");
    }

    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }



    public static String remove2(String input) {
        // Descomposici√≥n can√≥nica
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Nos quedamos √∫nicamente con los caracteres ASCII
        Pattern pattern = Pattern.compile("\\p{ASCII}+");
        return pattern.matcher(normalized).replaceAll("");
    }



    public  String toBase64(String xmlF) throws UnsupportedEncodingException{

        String xml = this.signXML(xmlF);
        byte[]   bytesEncoded = Base64.encodeBase64(xml.getBytes("UTF-8"));
        return new String(bytesEncoded);
    }

    public String changeDate(String xml) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        date.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        time.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        String datetime;
        datetime = date.format(new Date())+"T"+time.format(new Date());

        DocumentBuilderFactory factory = newInstance();
        DocumentBuilder builder;
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        Sign si = new Sign();
        try
    {       UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString().replace("-","");
            long unixTime = System.currentTimeMillis() / 1000L;
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse( new InputSource( new StringReader( xml ) ) );
            doc.getDocumentElement().setAttribute("Fecha",datetime);
            if(getRandomBoolean()){
                doc.getDocumentElement().setAttribute("Folio",unixTime+"k");
            }else{
                doc.getDocumentElement().setAttribute("Folio",randomUUIDString+"k");
            }

            transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();

            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public  String signXML(String xml){
        Sign si = new Sign();
        try{
            String xmlDateChanged = changeDate(xml);
            String cadena = si.getCadena(xmlDateChanged);
            String sello = si.getSign(cadena,"12345678a");

            return putsSign(xmlDateChanged,sello);

        }catch (Exception e){

        }
        return null;
    }

    public  String putsSign(String xml, String sello){

        try{


            DocumentBuilderFactory factory = newInstance();
            DocumentBuilder builder;
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer;

            builder = factory.newDocumentBuilder();
            Document doc = builder.parse( new InputSource( new StringReader( xml ) ) );
            doc.getDocumentElement().setAttribute("Sello",sello);
            transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();


            return  output;

        }catch (Exception e){

        }

        return null;
    }



    public static String b64xml = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz48Y2ZkaTpDb21wcm9iYW50ZSB4c2k6c2NoZW1hTG9jYXRpb249Imh0dHA6Ly93d3cuc2F0LmdvYi5teC9jZmQvMyBodHRwOi8vd3d3LnNhdC5nb2IubXgvc2l0aW9faW50ZXJuZXQvY2ZkLzMvY2ZkdjMzLnhzZCIgVmVyc2lvbj0iMy4zIiBGZWNoYT0iMjAxNy0wNy0xN1QxNzowNToxMiIgU2VsbG89IkpzWGp5Y2tkdm9MdDI3NCtKSUtUeVJnVGliZ1E5ZjZ3OTZtaVVjUmt1UVZEamxwRWErMmFtc2QxUjE5VGEwOURQSCtWcXdNNE5SOWN1QnRTKzJ4RUoyTU9TOWYyVGtkYlY4ZEhZS0xyMi9wZW4wSnNOaTZSTmpwVEFSbzZ5b2pUbGY5NHorLytScUFleGp6MVRpNDRrTFE1R1Y4VE8vd2M2eHhkc091aVFFQ3pYTFhKM1lIVjBucWd3UkxtaXAwZnlxWWlJUm5vcXJyQmlHdHViWEtrYmJDY3VIY0VQUXpZdElKNS9jZVozMEJnNkcyeVJiMm5VZkZsZE91cW9PTjlKQzFBb21reGljeVJoL2NjdkZBb1kySHNiYVMycHNqd1pDb0xzSFM5V3dBbE52ZHloOFJMQm93elpkd09IQmdXYnd6a09jb21oYlRud0M0TXR3NDRwUT09IiBGb3JtYVBhZ289IjAxIiBOb0NlcnRpZmljYWRvPSIyMDAwMTAwMDAwMDMwMDAyMjgxNSIgQ2VydGlmaWNhZG89Ik1JSUZ4VENDQTYyZ0F3SUJBZ0lVTWpBd01ERXdNREF3TURBek1EQXdNakk0TVRVd0RRWUpLb1pJaHZjTkFRRUxCUUF3Z2dGbU1TQXdIZ1lEVlFRRERCZEJMa011SURJZ1pHVWdjSEoxWldKaGN5ZzBNRGsyS1RFdk1DMEdBMVVFQ2d3bVUyVnlkbWxqYVc4Z1pHVWdRV1J0YVc1cGMzUnlZV05wdzdOdUlGUnlhV0oxZEdGeWFXRXhPREEyQmdOVkJBc01MMEZrYldsdWFYTjBjbUZqYWNPemJpQmtaU0JUWldkMWNtbGtZV1FnWkdVZ2JHRWdTVzVtYjNKdFlXTnB3N051TVNrd0p3WUpLb1pJaHZjTkFRa0JGaHBoYzJsemJtVjBRSEJ5ZFdWaVlYTXVjMkYwTG1kdllpNXRlREVtTUNRR0ExVUVDUXdkUVhZdUlFaHBaR0ZzWjI4Z056Y3NJRU52YkM0Z1IzVmxjbkpsY204eERqQU1CZ05WQkJFTUJUQTJNekF3TVFzd0NRWURWUVFHRXdKTldERVpNQmNHQTFVRUNBd1FSR2x6ZEhKcGRHOGdSbVZrWlhKaGJERVNNQkFHQTFVRUJ3d0pRMjk1YjJGanc2RnVNUlV3RXdZRFZRUXRFd3hUUVZRNU56QTNNREZPVGpNeElUQWZCZ2txaGtpRzl3MEJDUUlNRWxKbGMzQnZibk5oWW14bE9pQkJRMFJOUVRBZUZ3MHhOakV3TWpVeU1UVXlNVEZhRncweU1ERXdNalV5TVRVeU1URmFNSUd4TVJvd0dBWURWUVFERXhGRFNVNUVSVTFGV0NCVFFTQkVSU0JEVmpFYU1CZ0dBMVVFS1JNUlEwbE9SRVZOUlZnZ1UwRWdSRVVnUTFZeEdqQVlCZ05WQkFvVEVVTkpUa1JGVFVWWUlGTkJJRVJGSUVOV01TVXdJd1lEVlFRdEV4eE1RVTQzTURBNE1UY3pValVnTHlCR1ZVRkNOemN3TVRFM1FsaEJNUjR3SEFZRFZRUUZFeFVnTHlCR1ZVRkNOemN3TVRFM1RVUkdVazVPTURreEZEQVNCZ05WQkFzVUMxQnlkV1ZpWVY5RFJrUkpNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQWd2dkNpQ0ZERlZhWVg3eGRWUmhwLzM4VUxXdG8vTEtEU1p5MXlyWEtwYXFGWHFFUkpXRjc4WUhLZjNONUdCb1hnendGUHVEWCs1a3ZZNXd0WU54eC9Pd3Uyc2hOWnFGRmg2RUtzeXNRTWVQNXJ6NmtFMWdGWWVuYVBFVVA5emoraDBiTDN4UjVhcW9Uc3FHRjI0bUtCTG9pYUs0NHBYQnpHemdzeFppc2hWSlZNNlhiek5KVm9uRVVOYkkyNURoZ1dBZDg2ZjJhVTNCbU9IMksxUlp4NDFkdFRUNTZVc3N6SmxzNHRQRk9Eci9jYVd1WkV1VXZMcDFNM25qN0R5dTg4bWhEMmYrMWZBL2c3a3pjVS8xdGNwRlhGL3JJeTkzQVB2a1U3Mmp3dmtybnByenMrU25HODErL0YxNmFodUdzYjJFWjg4ZEtId3F4RWt3emhNeVRiUUlEQVFBQm94MHdHekFNQmdOVkhSTUJBZjhFQWpBQU1Bc0dBMVVkRHdRRUF3SUd3REFOQmdrcWhraUc5dzBCQVFzRkFBT0NBZ0VBSi94a0w4SStmcGlsWlArOWFPOG45MysyMFh4Vm9tTEpqZVNMK05nMkVyTDJHZ2F0cEx1TjVKa25GQmtaQWh4VklnTWFUUzIzenprMVJMdFJhWXZIODNsQkg1RStNK2tFakZHcDE0Rm5lMWlWMlBtM3ZMNGplTG16SGdZMUtmNUhtZVZycnA0UFU3V1FnMTZWcHlIYUovZW9uUE5pRUJVamN5UTFpRmZrekptblNKdkRHdGZRSzJUaUVvbERKQXBZdjBPV2RtNGlzOUJzZmk5ajZsSTkvVDZNTlorL0xNMkwvdDcyVmF1NHI3bTk0SkRFemFPM0Ewd0hBdFE5N2ZqQmZCaU81TThBRUlTQVY3ZVppZElsM2lhSkpIa1FiQllpaVcyZ2lrcmVVWktQVVgwSG1sbklxcVFjQkpoV0tSdTZOcWs2YVpCVEVUTExwR3J2RjlPQXJWMUpTc2Jkdy9aSCtQODhSQXQ1ZW01L2dqd3d0RmxOSHlpS0c1dytVRnBhWk9LM2daUDBzdTBzYTZkbFBlUTlFTDRKbEZrR3FRQ2dTUStOT3NYcWFPYXZnb1A1Vkx5a0x3dUdud0lVbnVoQlRWZURienBncmc5THVGNWRZcC96cytZOVNjSnFlNVZNQWFnTFNZVFNoTnROOGx1VjdMdnhGOXBnV3daZGNNN2xVd3FKbVVkZENpWnFkbmdnM3Z6VGFjdE1Ub0cxNmdaQTRDV25NZ2JVNEUrcjU0MStGTk1wZ0FaTnZzMkNpVy9lQXBmYWFRb2pzWkVBSERzRHY0TDVuM00xQ0M3ZllqRS9kNjFhU25nMUxhTzZUMW1oK2RFZlB2THpwN3p5enorVWdXTWhpNUNzNHBjWHgxZWljNXI3dXhQb0J3Y0NUdDNZSTFqS1ZWblY3L3c9IiBTdWJUb3RhbD0iMSIgRGVzY3VlbnRvPSIwLjEiIE1vbmVkYT0iTVhOIiBUaXBvQ2FtYmlvPSIxIiBUb3RhbD0iMS4wNiIgVGlwb0RlQ29tcHJvYmFudGU9IkkiIE1ldG9kb1BhZ289IlBVRSIgTHVnYXJFeHBlZGljaW9uPSI0NTEwMCIgeG1sbnM6Y2ZkaT0iaHR0cDovL3d3dy5zYXQuZ29iLm14L2NmZC8zIiB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hLWluc3RhbmNlIj48Y2ZkaTpFbWlzb3IgUmZjPSJMQU43MDA4MTczUjUiIE5vbWJyZT0iQ0lOREVNRVggU0EgREUgQ1YiIFJlZ2ltZW5GaXNjYWw9IjYwMSIgLz48Y2ZkaTpSZWNlcHRvciBSZmM9IlhFWFgwMTAxMDEwMDAiIE5vbWJyZT0iY2xpZW50ZSBhbCBwdWJsaWNvIiBVc29DRkRJPSJQMDEiIC8+PGNmZGk6Q29uY2VwdG9zPjxjZmRpOkNvbmNlcHRvIENsYXZlUHJvZFNlcnY9IjEwMTUxNzAxIiBDYW50aWRhZD0iMSIgQ2xhdmVVbmlkYWQ9IktHTSIgVW5pZGFkPSJraWxvcyIgRGVzY3JpcGNpb249ImFycm96IiBWYWxvclVuaXRhcmlvPSIxIiBJbXBvcnRlPSIxIiBEZXNjdWVudG89IjAuMSI+PGNmZGk6SW1wdWVzdG9zPjxjZmRpOlRyYXNsYWRvcz48Y2ZkaTpUcmFzbGFkbyBCYXNlPSIxIiBJbXB1ZXN0bz0iMDAyIiBUaXBvRmFjdG9yPSJUYXNhIiBUYXNhT0N1b3RhPSIwLjE2MDAwMCIgSW1wb3J0ZT0iMC4xNiIgLz48L2NmZGk6VHJhc2xhZG9zPjwvY2ZkaTpJbXB1ZXN0b3M+PC9jZmRpOkNvbmNlcHRvPjwvY2ZkaTpDb25jZXB0b3M+PGNmZGk6SW1wdWVzdG9zIFRvdGFsSW1wdWVzdG9zVHJhc2xhZGFkb3M9IjAuMTYiPjxjZmRpOlRyYXNsYWRvcz48Y2ZkaTpUcmFzbGFkbyBJbXB1ZXN0bz0iMDAyIiBUaXBvRmFjdG9yPSJUYXNhIiBUYXNhT0N1b3RhPSIwLjE2MDAwMCIgSW1wb3J0ZT0iMC4xNiIgLz48L2NmZGk6VHJhc2xhZG9zPjwvY2ZkaTpJbXB1ZXN0b3M+PGNmZGk6Q29tcGxlbWVudG8gLz48L2NmZGk6Q29tcHJvYmFudGU+";
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

    public static boolean isValidB64(String value){
        String pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(value);
        if (m.find()) {
           return true;
        } else {
           return false;
        }
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
