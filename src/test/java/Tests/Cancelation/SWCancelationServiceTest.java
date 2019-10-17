package Tests.Cancelation;

import Services.Cancelation.SWCancelationService;
import Tests.Utils;
import Utils.Responses.Cancelation.CancelationResponse;
import org.junit.Assert;
import org.junit.Test;

public class SWCancelationServiceTest {

	public String uuid = "06a46e4b-b154-4c12-bb77-f9a63ed55ff2";
	public String password_csd = "12345678a";
	public String rfc = "LAN7008173R5";
	public String b64Cer = "MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=";
	public String b64Key = "MIIFDjBABgkqhkiG9w0BBQ0wMzAbBgkqhkiG9w0BBQwwDgQIAgEAAoIBAQACAggAMBQGCCqGSIb3DQMHBAgwggS9AgEAMASCBMh4EHl7aNSCaMDA1VlRoXCZ5UUmqErAbucRBAKNQXH8t1GNfLDIQejtcocS39VvWnpNXjZJeCg65Y2wI36UGn78gvnU0NOmyUkXksPVrkz7hqNtAVojPUtN65l+MVAsIRVD6OLJeKZ2bLx5z78zrx6Tp1zCGT/NpxL+CJSy5iY6TKqbJcK/9198noOvT2p8rKVqUUF3wLRvD6R/b3BC5wCon/exp3BUTZeiWJqGRRgaW4rn49ZbJPVIcDmUO8mojPesFHjJDSnA0nBnWaUvTYXi0srT+dLZOewsBR8d5GdSWh9ZkM29wJbjYHCMsXkObZjaap3YM8fU29zRyZ8KAqaCnBHCfYjbib56m+Lmnk+ScqMkQQ+S/+2pzn2LzauvBI4p/OjQgBDeblo22X7sX9OA9YaqB3q6CCjQ5tkDNrz3HOgTm+amh/kI8TEn9rcKf4Ru7mC1T7VMaFgBqpIS8YJNbcgegF0IF1FpCS05wjdU5CktYAnPnvC+Pj+MFDeH+184kIHBWqPNG6dAzALxRgtKTlGdJ1l5Do+4EWI+0mvKojREnKoDczFnDeCFnM51u3I9Vce3rkf0djRQKFomPVUnPDqxlR5lDAssYAYNcECAkvGxKcBDbjWi/6NHlwjS1r28+0Jhvfxjx9O6hi4AW82Q2/kBE5P/eOwln/jKSbLgi7Iyim1FFHxkQH1FY5kcKhAzFcIq85rGFlzHRfPF9OIQSmONI9kcWQCxkk8aG1u1zwbjZRYLTxlwmZvynOgaWRpTN8Y4ReBDIG1klhva7nqqoM416oXBG71IKaCtjAwRlE6pgaqnIz/WQAb2FR541pqynX6dB6DB1nIWnatsWZJZlu+Bnhf9DBlUsO9ZSAf9Fa9nJAzwFCzaKIsvGJIeKSZ/h+vInkjaO/rxswErVROTfZy1lO2CJ/xnAgzFGrpDxNJPliv3McO9TGwYy/zHhE6Pdo8Xu6NsMisNU6TB8Bc26uLNv/7kWhNmNnBA1qt5akln6hOHrPBXGBiTNUL0IoFVPNdCbS0834zAYXfgtZLDzVpeLqmeMpqXbIYK0/NXe9etxuOcN40O+B/fTHHmO7dMgBZ4vAApVQUPr7ilumVHsWSMRP/0p5R9q4qr1bDm9S5YCPevdyYWTSceGSrXHmjYzJLBtpc/s77mynNqZEYjhnKk2XRNp6kp/FYRu+QdsX9vaDJbLKR2EnSC4fU6UOTO03IZU15j3wOsg30QrXoKntSJ/beF99cvFHuPrQPWxCtws0lLwkkHNVOm6XNO948Moy1w1pL4i68CwmceYZaYrYhmHGdLuescFQrZQaULDWhpK2Stys8Vs/XwwxNi9MHAFSXpdy/b+Aro5n87w+0MHRcllF8ZKbtQ/ym4oG7aREuo7o71JXJQPjZKTOtVM1EQx/FLM/5brnDSoyvLtoYtv9/tTnIC+8gR6eErkzaGmn8pftPhGNuz6yzx8JeLFoMD7VWbGTefj46KS+yMweFJnpReHEqwnukXpEYq19EWVyQa/Sb7navtKt80y/vRs0aNZp3iL23AOs0u1kQ1CFNY2y12Gor1koaH2FUd5jAQnaSKmgarLy0H/QVvR2g8B3+Fh49QhKYrd8N6LvvI80cwbEoqYWn5DWA=";
	public String token = "T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbXB3YVZxTHdOdHAwVXY2NTdJb1hkREtXTzE3dk9pMmdMdkFDR2xFWFVPUXpTUm9mTG1ySXdZbFNja3FRa0RlYURqbzdzdlI2UUx1WGJiKzViUWY2dnZGbFloUDJ6RjhFTGF4M1BySnJ4cHF0YjUvbmRyWWpjTkVLN3ppd3RxL0dJPQ.T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbFlVcU92YUJTZWlHU3pER1kySnlXRTF4alNUS0ZWcUlVS0NhelhqaXdnWTRncklVSWVvZlFZMWNyUjVxYUFxMWFxcStUL1IzdGpHRTJqdS9Zakw2UGRiMTFPRlV3a2kyOWI5WUZHWk85ODJtU0M2UlJEUkFTVXhYTDNKZVdhOXIySE1tUVlFdm1jN3kvRStBQlpLRi9NeWJrd0R3clhpYWJrVUMwV0Mwd3FhUXdpUFF5NW5PN3J5cklMb0FETHlxVFRtRW16UW5ZVjAwUjdCa2g0Yk1iTExCeXJkVDRhMGMxOUZ1YWlIUWRRVC8yalFTNUczZXdvWlF0cSt2UW0waFZKY2gyaW5jeElydXN3clNPUDNvU1J2dm9weHBTSlZYNU9aaGsvalpQMUxrUndzK0dHS2dpTittY1JmR3o2M3NqNkh4MW9KVXMvUHhZYzVLQS9UK2E1SVhEZFJKYWx4ZmlEWDFuSXlqc2ZRYXlUQk1ldlZkU2tEdU10NFVMdHZKUURLblBxakw0SDl5bUxabDFLNmNPbEp6b3Jtd2Q1V2htRHlTdDZ6eTFRdUNnYnVvK2tuVUdhMmwrVWRCZi9rQkU9.7k2gVCGSZKLzJK5Ky3Nr5tKxvGSJhL13Q8W-YhT0uIo";
	public String xml = "<Cancelacion xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" Fecha=\"2018-09-10T09:59:17\" RfcEmisor=\"LAN7008173R5\" xmlns=\"http://cancelacfd.sat.gob.mx\"><Folios><UUID>BB01B192-7D69-4C61-A950-5CD0B32B21A3</UUID></Folios><Folios><UUID>832E14D8-F74F-418D-A985-EB53AA17531E</UUID></Folios><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\" /><Reference URI=\"\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\" /></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\" /><DigestValue>oloqoFY49yTYf4GTV69Ap+KY2zM=</DigestValue></Reference></SignedInfo><SignatureValue>c/PKTIS6RberIXFHEu6O1K1o/AFbq/jtAgE7j1oTfN8K+7KDhJzjJrHQdju4EAYrVq+/x7sbYBSCZ2lBme5xpPWqsREDLrz3icgDaX/LX7Y4v43fHi3mQ7XC7wAelQpxiWz8ybulD5nYeFHpvKEGmmeSOgO8tUlOmxfJ61Vs9+jTfdyEGkopnbnbLDMfnlcPnQRbeQuKL9Chgp4e6Mk13rEaMvalG3F0vy+hh27ZvENmdv2A6zmaB52nSkOWPhauBWvNB9czI48Ivs3T3eKGSjKNeqmou+xsNwnlWfiBWbAC7/sKJUA8/WEKTCB9evG0mZy8tIXf8NMk/6sI/MIbcg==</SignatureValue><KeyInfo><X509Data><X509IssuerSerial><X509IssuerName>OID.1.2.840.113549.1.9.2=Responsable: ACDMA, OID.2.5.4.45=SAT970701NN3, L=Coyoacán, S=Distrito Federal, C=MX, PostalCode=06300, STREET=\"Av. Hidalgo 77, Col. Guerrero\", E=asisnet@pruebas.sat.gob.mx, OU=Administración de Seguridad de la Información, O=Servicio de Administración Tributaria, CN=A.C. 2 de pruebas(4096)</X509IssuerName><X509SerialNumber>286524172099382162235533054548081509963388170549</X509SerialNumber></X509IssuerSerial><X509Certificate>MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=</X509Certificate></X509Data></KeyInfo></Signature></Cancelacion>";
	public String b64Pfx = "MIIMCQIBAzCCC88GCSqGSIb3DQEHAaCCC8AEggu8MIILuDCCBm8GCSqGSIb3DQEHBqCCBmAwggZcAgEAMIIGVQYJKoZIhvcNAQcBMBwGCiqGSIb3DQEMAQYwDgQIXBNHDmeisrgCAggAgIIGKJcMawHjf9xBaT9VO58xdlwS2Ufj764W2RbrMDN/0RXY8vLgnn+bfi1ifqlSTS5YwlcAfLEwCB6mk9T4nYorqsirI8eIzThz5tt0W/XP28byMrMxwl7Ebt99q1FdaDFwGCLSgeKb+hLmyssyS/+ne1eldmC31MVYU9DX2jLWZ5vKhxPNFDkqbx+iNq1Qc4uSrXqMeWhM36DhvAosWR3ZANhtvIwvsvg9T/jHrAf0FNeODL8CleHx91+uLVHfN/1zpO6kk5yHwx405WG7QJ7GQFanXpuF/9lotbeUn4D49ko+07QkxX8zheZjRG+8CQP0SMZGPdAOBw/OfjoRg/UXYAaPPNrJBAwXzxIyYntuXwvSykcxXKKY2p29eMkUkwEMy1Pqv4fzMO1fhr+N0E0QeMR4NWc6vA/HmAk3bVQ+cEy+BhtmZkQ3vqvl51sbpqMXc6LH+a0atxFeAOnaL5MNmZXiVs2+DAp0lQiqb/W/U782RBy8wVDlrQ3NandOtyz6B7HoaYeSqlWLC0eeHkn3lgRSHkosVkWzrx5tGZyvnj5GqAp9jYLkoQFYsyltFG6QjW45CowWWfSSmtMyvXDUxgHa5drmNxdrpQq3996M1nl4/1VJbT5RqHv9gejdE6vaPBS+ZIDL6fMhZjZSQNKS9/pmYMrrvC6X2+2v6EFHXWLVIFKR/OUn0FmjuhHpXhZoc365KYHUhG9rs92/Ht6zYAXECgonoRmSVgL7TUcsv9dZzR9IuNyzF6pOO1WrigwlDNpDe5ROE3OwUPznq+PRW4RqONVQMdmLbwgQjYu2KkeebU0zhO8HcMrgN146isblcOcpDtgHVv7RRCD3ZBwP2adNyJZAHxZco/2WpLMd80uMdgSZbp8kG5pVoqWILxhJJR+l2vlClCcR/Pf5RJHCS6eq1xWljQ03SJdBExJvgmOa7K3LP5uXUR3dSa8cV65q0jLfrjJoBHIudanymWp3iv71YPIQfhPZnyZCvPlIW68JPMDdXb7NzjmRNgEF6umA0uab/KKhIXt90fBLnLnNHmOyUFmjE1JAK7FRqAXc9UDreJQMtWF+EcdhqngP2wPBrtbbqiCorc3lvtyKfhFFtgppGKb8jHWiy/0nKMVzzADHRGAkoq9f5r2gjhwg0cNcNtYyH7TiB8Y1nQ/LuzfbczFjcaoxOGG9oiVY8ozRhhT/gICl7f+GQp4PabQdjWCpAkOPmUXCptYM75R7JxpH9QwfeapHWkP9ApUCoen2bGO4uu5Nx48L+hfZ9XtYJAk2h3Sv1BoHl50n7XtkyV2k6nuGYkam8TN8cbUG0Rkz85PEU1q0qbsf1ksz8evs9TCyojQCukfbKwAsVPRlatxQk3DR3RFoFSFy65jo5tG4zfstY976NyUrOTKIMS1SK82YLhSwYgrN90g/xpGWKGBhk5EqcdZSetNKkV8tzg7FQ51SNKJUtHULa2vUH/MQBsu+3Ab88WA2RCZ6iLQTzGiHr5KV3rFYFMbbJSzH9UPsW7QdLBMIKXAEL7C5IWR4AT9VrnYmLhxvqfnNa50xX9uua/L22YZIlNwU9kopDcAFMGpyTmivd2N3NdqE9wPGzxkVQkbXDJvhUfD3gnuLKdEonIyaU6AAObDiXkVEtBGhvr7rn6FOBreM1T8VMtDoiO2y+QG7SctxAxNue6SoqSmoouAPiwHF3OzyYJkhVgBv5uV22+8ShULQAZKoSnpIhg8sQvCGJ+G4/PgCV9TgIpd/OrtVpwSOTvIa9TCaGh6xJugAHzETCBuOxH8TjarZfLniMNkMVyyvrOW4xKJMf+Rt11b0KXMWcaWArIlLHahDO8ksM/koDODEWvBplMTjgkrApRyaPFwmovX7clBcJegf8vkZGwrQlvkRpzNUO4c5Qh3zmvAfMNSh3/tOKUET/2ulr7plhj8IuHMCk3MBmxALsx+jy5Ux9k44ZlSZR7SMEm/Uicry0Cajkn9i7EbI3kmAevNqUzSJR7WELtXbm1KASb43U+gmkmvd9yKo7GS9ig4yG4EOtx4ORrBLo2wTJQHlT87S8Q1gBicXu/+BwZflPeE4eqklh+ChYz6zD8wjkewo+W+WPnQoz2YwggVBBgkqhkiG9w0BBwGgggUyBIIFLjCCBSowggUmBgsqhkiG9w0BDAoBAqCCBO4wggTqMBwGCiqGSIb3DQEMAQMwDgQIER4XVjQqw6oCAggABIIEyF9B3nHdB3tjD2rZYsm0Vx2UCedQzZrIgwZNvKuPvKIareB0J/abpHOhp5JZwnL5881wW/sUhZthvbEYT6C1SzBcRh8LCBy6QcQQcLjgemPaQkVoZNV2XyHK/rUEBI71M2kx76X7HjPwUeFISsicqtHlTdOLjlh8jbNUnyUE9yH341SCelArSnxJzLnc7LO4BojVk1aJ5N0RGmn81Ckm6Z8A5E34BWCFvecrnl9AU1DAw/PNYx/UTm5EbERPEotmlXpLxDIaaamYEWD12c543zrVWEgWqkjECoSg3OPr8PbSaynLMyROmaNTWf4b4+Cvg/Duzvi6uFkEdmnycJFptYfkpryyeJWnhRDOC8wWtJfTy22aub5ujN8f4KppRU8HWA3vlxxjvj3hfjQwP1536IcUEidtPQ5GK5MG5B+3GMf/baTF2TKGz4FCbIR8RcP/JZk5vl4GPrJbuAcDPQxDyUkjHYAMfmZzWIbRoF9mm5y9sFS7STwf2lWG0/lIVNQbEh/rGav8dcQcLD3CT6/CE8++5WMMnhxJlWLR794ci4witIY2f3h16MZBdkLprRMFgWQA/DZjvICv4TW0oqyiIEK/CTqJj3aCI33CVdJPMWlomZEKCUtP+9Lv9wjsZk/pzr5n52z9mekj3YKoXpUj56sipHwMWPX5giHGtx2/8++z9NoyM9mxgmFLcS5tvUq39VjcKtNUzpY8+AE4QqG86mIkNJzbNMix09bNUdrKHx50Dw9fkGCyRaPLnwRDUcQUDr9sW1tJ8WEnZXgd2dWyY6urVlMLRUI+ZPh+LXyc9i8JwjQ8nR9AVenrEwm5DVWlGmry5cc+AMT9DGFsatazaC/CMs7mtaTRO17lDuWM8dARfIDqoV+o3wF4wvcBHy+NHkvJ8aNNhdv0gxoxQdDTh1VvhFG7Ij7487kicwIFpOXjDu/A+JzfutXpXemRkDAXMm3D/vDX0yKIyzHUNFzxEQDeAo/UZAfLMEbCE6XTjcrX5Gu/xoqC5NA/J389Wl3sMyAQxynsTld6yZlTid4rPpgxjbTm0m8yMyv+zvZNQFNjUxXgHyog36be5LIu7IU6zUj8hvl+jOG78a+lra+mXEKvowo/9/UEWZCZDUvWPbka67SLOLDdPrRwJB+KqNdMMkGZwfRauDyrvFi25yV87NtrHFQXz/9FNqIuGuRz7i1suDB+WPdk7Rc3CPYoK30IRo0Ae74mdA+AN07lQC7Q8FicpTDlNjqh+VV5nqLht8wzzq/l1QFgcjxgp23520G/YpCGhseJv2PWcV0H1rs0coAi3qCr7G+A2MYwGJA4DCAfD92xZWtZgPUpPSELvEBgWqMsW87xd1MSkumJ8e7zGOMXoFRo3ID4wot1FIaWU/EJyzb3xowrbY64Ytquuu9s9ef0weTumPMvkWcnGeqgKaEMnl24Is6CoG+qS+HfvgS8qxiv1TfTTIRmT0nhYWRoEkE/0ue6X4Zs+qT28Z8P/LzcIJECJvd39K5BI0q3RMqEVumgVcKGPB9nkxReDPR1M/cxY9qru99NaOK0dZJAin5AXDVOrCZzOdLeEU5lIRXWZwr9i1/rVvLh6f3tnhVWgbghp+JsuhaX2ALRY74pAosbzVMZJlf31TElMCMGCSqGSIb3DQEJFTEWBBTV2Mqe5ylxDwy8SvKAi/DPPo81QzAxMCEwCQYFKw4DAhoFAAQUro+9ySRmBaYPd9oC4rXFvP20CPkECEuQk0rStkWzAgIIAA==";

	//csd
	@Test
	public void testCancelationServiceCSD_authUser() throws Exception {
		SWCancelationService app = new SWCancelationService("demo", "123456789", Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceCSD_authToken() throws Exception {
		SWCancelationService app = new SWCancelationService(token, Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceCSD_incorrectParams() throws Exception {
		SWCancelationService app = new SWCancelationService(token, Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("123456", "123456", "123456", "123456", "123456");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceCSD_incorrectToken() throws Exception {
		SWCancelationService app = new SWCancelationService("wrong token", Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		Assert.assertTrue(response.HttpStatusCode == 401);
	}
	@Test
	public void testCancelationServiceCSD_emptyUserParams() throws Exception {
		try {
			SWCancelationService app = new SWCancelationService("", "", "");
			CancelationResponse response = null;
			response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key);
			System.out.println(response.Status);
			System.out.println(response.HttpStatusCode);
			System.out.println(response.message);
			System.out.println(response.messageDetail);
		} catch (Exception e) {
			System.out.println("Something bad happened");
			System.out.println(e.getMessage());
			Assert.assertNotNull("Something bad happened", e);
		}
	}
	@Test
	public void testCancelationServiceCSD_emptyCancelationParams() throws Exception {
		SWCancelationService app = new SWCancelationService("demo", "123456789", Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("", "", "", "", "");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		int expected_HttpStatusCode = 400;
		Assert.assertTrue(response.message.contains("CACFDI33"));
		Assert.assertTrue(expected_HttpStatusCode == response.HttpStatusCode);
	}

	//XML
	@Test
	public void testCancelationServiceXML_validXML() throws Exception {
		SWCancelationService app = new SWCancelationService("demo", "123456789", Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(xml);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceXML_invalidXML() throws Exception {
		SWCancelationService app = new SWCancelationService("demo", "123456789", Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("wrong xml");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
		Assert.assertTrue(response.message.contains("CASD - Acuse"));
	}
	@Test
	public void testCancelationServiceXML_nullXML() throws Exception {
		SWCancelationService app = new SWCancelationService("demo", "123456789", Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
		Assert.assertTrue(response.message.contains("CASD - Acuse"));
	}

	// Pfx
	@Test
	public void testCancelationServicePfx_authUser() throws Exception {
		SWCancelationService app = new SWCancelationService("demo", "123456789", Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Pfx);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServicePfx_authToken() throws Exception {
		SWCancelationService app = new SWCancelationService(token, Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Pfx);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServicePfx_incorrectParams() throws Exception {
		SWCancelationService app = new SWCancelationService(token, Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("123456", "123456", "123456", "123456");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServicePfx_incorrectToken() throws Exception {
		SWCancelationService app = new SWCancelationService("wrong token", Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Pfx);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		Assert.assertTrue(response.HttpStatusCode == 401);
	}
	@Test
	public void testCancelationServicePfx_emptyUserParams() throws Exception {
		try {
			SWCancelationService app = new SWCancelationService("", "", "");
			CancelationResponse response = null;
			response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Pfx);
			System.out.println(response.Status);
			System.out.println(response.HttpStatusCode);
			System.out.println(response.message);
			System.out.println(response.messageDetail);
		} catch (Exception e) {
			System.out.println("Something bad happened");
			System.out.println(e.getMessage());
			Assert.assertNotNull("Something bad happened", e);
		}
	}
	@Test
	public void testCancelationServicePfx_emptyCancelationParams() throws Exception {
		SWCancelationService app = new SWCancelationService("demo", "123456789", Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("", "", "", "");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		int expected_HttpStatusCode = 400;
		Assert.assertTrue(response.message.contains("CACFDI33"));
		Assert.assertTrue(expected_HttpStatusCode == response.HttpStatusCode);
	}

	// Uuid
	@Test
	public void testCancelationServiceUuid_authUser() throws Exception {
		SWCancelationService app = new SWCancelationService("demo", "123456789", Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, rfc);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceUuid_authToken() throws Exception {
		SWCancelationService app = new SWCancelationService(token, Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, rfc);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceUuid_incorrectParams() throws Exception {
		SWCancelationService app = new SWCancelationService(token, Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("123456", "123456");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceUuid_incorrectToken() throws Exception {
		SWCancelationService app = new SWCancelationService("wrong token", Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, rfc);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		Assert.assertTrue(response.HttpStatusCode == 401);
	}
	@Test
	public void testCancelationServiceUuid_emptyUserParams() throws Exception {
		try {
			SWCancelationService app = new SWCancelationService("", "", "");
			CancelationResponse response = null;
			response = (CancelationResponse) app.Cancelation(uuid, rfc);
			System.out.println(response.Status);
			System.out.println(response.HttpStatusCode);
			System.out.println(response.message);
			System.out.println(response.messageDetail);
		} catch (Exception e) {
			System.out.println("Something bad happened");
			System.out.println(e.getMessage());
			Assert.assertNotNull("Something bad happened", e);
		}
	}
	@Test
	public void testCancelationServiceUuid_emptyCancelationParams() throws Exception {
		SWCancelationService app = new SWCancelationService("demo", "123456789", Utils.url_pruebas);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("", "");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		int expected_HttpStatusCode = 404;
		Assert.assertTrue(response.message.equalsIgnoreCase("Not Found"));
		Assert.assertTrue(expected_HttpStatusCode == response.HttpStatusCode);
	}
}