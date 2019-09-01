package Utils.Responses.Taxplayer;

import Utils.Responses.IResponse;

public class TaxplayerResponse extends IResponse {
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

	public TaxplayerResponse(int httpStatusCode, String status, String id, String rfc, String nombre_Contribuyente,
			String situacion_del_contribuyente, String numero_y_fecha_oficio_global_presuncion,
			String publicacion_pagina_SAT_presuntos, String publicacion_DOF_presuntos,
			String publicacion_pagina_SAT_desvirtuados,
			String numero_fecha_oficio_global_contribuyentes_que_desvirtuaron, String publicacion_DOF_desvirtuados,
			String numero_fecha_oficio_global_definitivos, String publicacion_pagina_SAT_definitivos,
			String publicacion_DOF_definitivos, String numero_fecha_oficio_global_sentencia_favorable,
			String publicacion_pagina_SAT_sentencia_favorable, String publicacion_DOF_sentencia_favorable, String msg,
			String msgDetail) {
		super(httpStatusCode, status, msg, msgDetail);
		this.id = id;
		this.rfc = rfc;
		this.nombre_Contribuyente = nombre_Contribuyente;
		this.situacion_del_contribuyente = situacion_del_contribuyente;
		this.numero_y_fecha_oficio_global_presuncion = numero_y_fecha_oficio_global_presuncion;
		this.publicacion_pagina_SAT_presuntos = publicacion_pagina_SAT_presuntos;
		this.publicacion_DOF_presuntos = publicacion_DOF_presuntos;
		this.publicacion_pagina_SAT_desvirtuados = publicacion_pagina_SAT_desvirtuados;
		this.numero_fecha_oficio_global_contribuyentes_que_desvirtuaron = numero_fecha_oficio_global_contribuyentes_que_desvirtuaron;
		this.publicacion_DOF_desvirtuados = publicacion_DOF_desvirtuados;
		this.numero_fecha_oficio_global_definitivos = numero_fecha_oficio_global_definitivos;
		this.publicacion_pagina_SAT_definitivos = publicacion_pagina_SAT_definitivos;
		this.publicacion_DOF_definitivos = publicacion_DOF_definitivos;
		this.numero_fecha_oficio_global_sentencia_favorable = numero_fecha_oficio_global_sentencia_favorable;
		this.publicacion_pagina_SAT_sentencia_favorable = publicacion_pagina_SAT_sentencia_favorable;
		this.publicacion_DOF_sentencia_favorable = publicacion_DOF_sentencia_favorable;

	}
	
	  public TaxplayerResponse(int httpStatusCode, String status, String msg, String msgDetail) {
	        super(httpStatusCode, status, msg, msgDetail);
	    }
}
