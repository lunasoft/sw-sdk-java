package Utils.Responses.Storage;

import java.util.List;
/*Estructura de los datos que se enviaran en el Storage response */

public class StorageData {
    public Data data;

    public Data getData() {
        return this.data;
    }

    public class Data {

        public MetaData metaData;
        public List<Records> records;
        public String status;

        public MetaData getMetaData() {
            return this.metaData;
        }

        public List getRecords() {
            return this.records;
        }

        public String getStatus() {
            return this.status;
        }
    }

    public class Records {
        private String codigoCancelacion;
        private String statusSAT;
        private String urlPDF;
        private String urlAckCfdi;
        private String urlAckCancellation;
        private boolean hasAddenda;
        private String addenda;
        private String urlAddenda;
        private String fechaGeneracionPdf;
        private String idDealer;
        private String idUser;
        private String version;
        private String serie;
        private String folio;
        private String fecha;
        private String numeroCertificado;
        private Double subTotal;
        private Double descuento;
        private Double total;
        private String moneda;
        private Double tipoCambio;
        private String tipoDeComprobante;
        private String metodoPago;
        private String formaPago;
        private String condicionesPago;
        private String luegarExpedicion;
        private String emisorRfc;
        private String emisorNombre;
        private String regimenFiscal;
        private String receptorRfc;
        private String receptorNombre;
        private String residenciaFiscal;
        private String numRegIdTrib;
        private String usoCFDI;
        private Double totalImpuestosTraslados;
        private Double totalImpuestosRetencion;
        private Double trasladosIVA;
        private Double trasladosIEPS;
        private Double retencionesISR;
        private Double retencionesIVA;
        private Double retencionesIEPS;
        private Double totalImpuestosLocalesTraslados;
        private Double totalImpuestosLocalesRetencion;
        private String complementos;
        private String uuid;
        private String fechaTimbrado;
        private String rfcProvCertif;
        private String selloCFD;
        private String urlXml;
        private String yearMonth;
        private boolean status;

        public String getCodigoCancelacion() {
            return codigoCancelacion;
        }

        public void setCodigoCancelacion(String codigoCancelacion) {
            this.codigoCancelacion = codigoCancelacion;
        }

        public String getStatusSAT() {
            return statusSAT;
        }

        public void setStatusSAT(String statusSAT) {
            this.statusSAT = statusSAT;
        }

        public String getUrlPDF() {
            return urlPDF;
        }

        public void setUrlPDF(String urlPDF) {
            this.urlPDF = urlPDF;
        }

        public String getUrlAckCfdi() {
            return urlAckCfdi;
        }

        public void setUrlAckCfdi(String urlAckCfdi) {
            this.urlAckCfdi = urlAckCfdi;
        }

        public String getUrlAckCancellation() {
            return urlAckCancellation;
        }

        public void setUrlAckCancellation(String urlAckCancellation) {
            this.urlAckCancellation = urlAckCancellation;
        }

        public boolean isHasAddenda() {
            return hasAddenda;
        }

        public void setHasAddenda(boolean hasAddenda) {
            this.hasAddenda = hasAddenda;
        }

        public String getAddenda() {
            return addenda;
        }

        public void setAddenda(String addenda) {
            this.addenda = addenda;
        }

        public String getUrlAddenda() {
            return urlAddenda;
        }

        public void setUrlAddenda(String urlAddenda) {
            this.urlAddenda = urlAddenda;
        }

        public String getFechaGeneracionPdf() {
            return fechaGeneracionPdf;
        }

        public void setFechaGeneracionPdf(String fechaGeneracionPdf) {
            this.fechaGeneracionPdf = fechaGeneracionPdf;
        }

        public String getIdDealer() {
            return idDealer;
        }

        public void setIdDealer(String idDealer) {
            this.idDealer = idDealer;
        }

        public String getIdUser() {
            return idUser;
        }

        public void setIdUser(String idUser) {
            this.idUser = idUser;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getSerie() {
            return serie;
        }

        public void setSerie(String serie) {
            this.serie = serie;
        }

        public String getFolio() {
            return folio;
        }

        public void setFolio(String folio) {
            this.folio = folio;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getNumeroCertificado() {
            return numeroCertificado;
        }

        public void setNumeroCertificado(String numeroCertificado) {
            this.numeroCertificado = numeroCertificado;
        }

        public Double getSubTotal() {
            return subTotal;
        }

        public void setSubTotal(Double subTotal) {
            this.subTotal = subTotal;
        }

        public Double getDescuento() {
            return descuento;
        }

        public void setDescuento(Double descuento) {
            this.descuento = descuento;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public String getMoneda() {
            return moneda;
        }

        public void setMoneda(String moneda) {
            this.moneda = moneda;
        }

        public Double getTipoCambio() {
            return tipoCambio;
        }

        public void setTipoCambio(Double tipoCambio) {
            this.tipoCambio = tipoCambio;
        }

        public String getTipoDeComprobante() {
            return tipoDeComprobante;
        }

        public void setTipoDeComprobante(String tipoDeComprobante) {
            this.tipoDeComprobante = tipoDeComprobante;
        }

        public String getMetodoPago() {
            return metodoPago;
        }

        public void setMetodoPago(String metodoPago) {
            this.metodoPago = metodoPago;
        }

        public String getFormaPago() {
            return formaPago;
        }

        public void setFormaPago(String formaPago) {
            this.formaPago = formaPago;
        }

        public String getCondicionesPago() {
            return condicionesPago;
        }

        public void setCondicionesPago(String condicionesPago) {
            this.condicionesPago = condicionesPago;
        }

        public String getLuegarExpedicion() {
            return luegarExpedicion;
        }

        public void setLuegarExpedicion(String luegarExpedicion) {
            this.luegarExpedicion = luegarExpedicion;
        }

        public String getEmisorRfc() {
            return emisorRfc;
        }

        public void setEmisorRfc(String emisorRfc) {
            this.emisorRfc = emisorRfc;
        }

        public String getEmisorNombre() {
            return emisorNombre;
        }

        public void setEmisorNombre(String emisorNombre) {
            this.emisorNombre = emisorNombre;
        }

        public String getRegimenFiscal() {
            return regimenFiscal;
        }

        public void setRegimenFiscal(String regimenFiscal) {
            this.regimenFiscal = regimenFiscal;
        }

        public String getReceptorRfc() {
            return receptorRfc;
        }

        public void setReceptorRfc(String receptorRfc) {
            this.receptorRfc = receptorRfc;
        }

        public String getReceptorNombre() {
            return receptorNombre;
        }

        public void setReceptorNombre(String receptorNombre) {
            this.receptorNombre = receptorNombre;
        }

        public String getResidenciaFiscal() {
            return residenciaFiscal;
        }

        public void setResidenciaFiscal(String residenciaFiscal) {
            this.residenciaFiscal = residenciaFiscal;
        }

        public String getNumRegIdTrib() {
            return numRegIdTrib;
        }

        public void setNumRegIdTrib(String numRegIdTrib) {
            this.numRegIdTrib = numRegIdTrib;
        }

        public String getUsoCFDI() {
            return usoCFDI;
        }

        public void setUsoCFDI(String usoCFDI) {
            this.usoCFDI = usoCFDI;
        }

        public Double getTotalImpuestosTraslados() {
            return totalImpuestosTraslados;
        }

        public void setTotalImpuestosTraslados(Double totalImpuestosTraslados) {
            this.totalImpuestosTraslados = totalImpuestosTraslados;
        }

        public Double getTotalImpuestosRetencion() {
            return totalImpuestosRetencion;
        }

        public void setTotalImpuestosRetencion(Double totalImpuestosRetencion) {
            this.totalImpuestosRetencion = totalImpuestosRetencion;
        }

        public Double getTrasladosIVA() {
            return trasladosIVA;
        }

        public void setTrasladosIVA(Double trasladosIVA) {
            this.trasladosIVA = trasladosIVA;
        }

        public Double getTrasladosIEPS() {
            return trasladosIEPS;
        }

        public void setTrasladosIEPS(Double trasladosIEPS) {
            this.trasladosIEPS = trasladosIEPS;
        }

        public Double getRetencionesISR() {
            return retencionesISR;
        }

        public void setRetencionesISR(Double retencionesISR) {
            this.retencionesISR = retencionesISR;
        }

        public Double getRetencionesIVA() {
            return retencionesIVA;
        }

        public void setRetencionesIVA(Double retencionesIVA) {
            this.retencionesIVA = retencionesIVA;
        }

        public Double getRetencionesIEPS() {
            return retencionesIEPS;
        }

        public void setRetencionesIEPS(Double retencionesIEPS) {
            this.retencionesIEPS = retencionesIEPS;
        }

        public Double getTotalImpuestosLocalesTraslados() {
            return totalImpuestosLocalesTraslados;
        }

        public void setTotalImpuestosLocalesTraslados(Double totalImpuestosLocalesTraslados) {
            this.totalImpuestosLocalesTraslados = totalImpuestosLocalesTraslados;
        }

        public Double getTotalImpuestosLocalesRetencion() {
            return totalImpuestosLocalesRetencion;
        }

        public void setTotalImpuestosLocalesRetencion(Double totalImpuestosLocalesRetencion) {
            this.totalImpuestosLocalesRetencion = totalImpuestosLocalesRetencion;
        }

        public String getComplementos() {
            return complementos;
        }

        public void setComplementos(String complementos) {
            this.complementos = complementos;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getFechaTimbrado() {
            return fechaTimbrado;
        }

        public void setFechaTimbrado(String fechaTimbrado) {
            this.fechaTimbrado = fechaTimbrado;
        }

        public String getRfcProvCertif() {
            return rfcProvCertif;
        }

        public void setRfcProvCertif(String rfcProvCertif) {
            this.rfcProvCertif = rfcProvCertif;
        }

        public String getSelloCFD() {
            return selloCFD;
        }

        public void setSelloCFD(String selloCFD) {
            this.selloCFD = selloCFD;
        }

        public String getUrlXml() {
            return urlXml;
        }

        public void setUrlXml(String urlXml) {
            this.urlXml = urlXml;
        }

        public String getYearMonth() {
            return yearMonth;
        }

        public void setYearMonth(String yearMonth) {
            this.yearMonth = yearMonth;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

    }

    public class MetaData {

        public String page;
        public String perPage;
        public String pageCount;
        public String totalCount;
        public Links links;

        public String getPage() {
            return this.page;
        }

        public String getPerPage() {
            return this.perPage;
        }

        public String getPageCount() {
            return this.pageCount;
        }

        public String getTotalCount() {
            return this.totalCount;
        }

        public Links getLinks() {
            return this.links;
        }
    }

    public class Links {

        public String current;

        public String getCurrent() {
            return this.current;
        }
    }

}