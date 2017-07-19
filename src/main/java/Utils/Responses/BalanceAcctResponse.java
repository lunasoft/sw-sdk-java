package Utils.Responses;

//@author Lupita Alvarado

public class BalanceAcctResponse extends IResponse{
    public String idSaldoCliente;
    public String idClienteUsuario;
    public int saldoTimbres;
    public int timbresUtilizados;
    public String fechaExpiracion;
    public boolean unlimited;
    public int timbresAsignados;
    
    public BalanceAcctResponse(int httpStatusCode, String status, String idSaldoCliente, String idClienteUsuario, int saldoTimbres, int timbresUtilizados, String fechaExpiracion, boolean unlimited, int timbresAsignados) {
        super(httpStatusCode, status);
        this.idSaldoCliente = idSaldoCliente;
        this.idClienteUsuario= idClienteUsuario;
        this.saldoTimbres=saldoTimbres;
        this.timbresUtilizados=timbresUtilizados;
        this.fechaExpiracion= fechaExpiracion;
        this.unlimited= unlimited;
        this.timbresAsignados = timbresAsignados;
    }
}
