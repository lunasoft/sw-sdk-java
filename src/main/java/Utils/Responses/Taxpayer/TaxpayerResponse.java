package Utils.Responses.Taxpayer;

import java.util.List;
import Utils.Responses.IResponse;

public class TaxpayerResponse extends IResponse {
	public String id;
	public String rfc;
	public String nombre_Contribuyente;
	public String situacion_del_contribuyente;
	public String numero_y_fecha_oficio_global_presuncion;
	public String publicacion_pagina_SAT_presuntos;
	public String publicacion_DOF_presuntos;
	public String publicacion_pagina_SAT_desvirtuados;
	public String numero_fecha_oficio_global_contribuyentes_que_desvirtuaron;
	public String publicacion_DOF_desvirtuados;
	public String numero_fecha_oficio_global_definitivos;
	public String publicacion_pagina_SAT_definitivos;
	public String publicacion_DOF_definitivos;
	public String numero_fecha_oficio_global_sentencia_favorable;
	public String publicacion_pagina_SAT_sentencia_favorable;
	public String publicacion_DOF_sentencia_favorable;

	public TaxpayerResponse(int httpStatusCode, String status, List<TaxpayerData> Taxpayer, String msg,
			String msgDetail) {
		super(httpStatusCode, status, msg, msgDetail);
		this.rfc = Taxpayer.get(0).getRfc();
		this.nombre_Contribuyente = Taxpayer.get(0).getNombre_Contribuyente();
		this.situacion_del_contribuyente = Taxpayer.get(0).getSituacion_del_contribuyente();
		this.numero_y_fecha_oficio_global_presuncion = Taxpayer.get(0).getNumero_y_fecha_oficio_global_presuncion();
		this.publicacion_pagina_SAT_presuntos = Taxpayer.get(0).getPublicacion_pagina_SAT_presuntos();
		this.publicacion_DOF_presuntos = Taxpayer.get(0).getPublicacion_DOF_presuntos();
		this.publicacion_pagina_SAT_desvirtuados = Taxpayer.get(0).getPublicacion_pagina_SAT_desvirtuados();
		this.numero_fecha_oficio_global_contribuyentes_que_desvirtuaron = Taxpayer.get(0)
				.getNumero_fecha_oficio_global_contribuyentes_que_desvirtuaron();
		this.publicacion_DOF_desvirtuados = Taxpayer.get(0).getPublicacion_DOF_desvirtuados();
		this.numero_fecha_oficio_global_definitivos = Taxpayer.get(0).getNumero_fecha_oficio_global_definitivos();
		this.publicacion_pagina_SAT_definitivos = Taxpayer.get(0).getPublicacion_pagina_SAT_definitivos();
		this.publicacion_DOF_definitivos = Taxpayer.get(0).getPublicacion_DOF_definitivos();
		this.numero_fecha_oficio_global_sentencia_favorable = Taxpayer.get(0)
				.getNumero_fecha_oficio_global_sentencia_favorable();
		this.publicacion_pagina_SAT_sentencia_favorable = Taxpayer.get(0)
				.getPublicacion_pagina_SAT_sentencia_favorable();
		this.publicacion_DOF_sentencia_favorable = Taxpayer.get(0).getPublicacion_DOF_sentencia_favorable();
	}

	public TaxpayerResponse(int httpStatusCode, String status, String msg, String msgDetail) {
		super(httpStatusCode, status, msg, msgDetail);
	}
}
