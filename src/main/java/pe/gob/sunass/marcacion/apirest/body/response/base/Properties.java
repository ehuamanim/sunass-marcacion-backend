package pe.gob.sunass.marcacion.apirest.body.response.base;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Properties {

	@SerializedName( "cm:categories" ) 
	List<String> categories;
	
	@SerializedName( "sgd:description" )
	private String description;
	
	@SerializedName( "sgd:direccionTram2" )
	private String direccionTram2;

	@SerializedName( "sgd:direccionTram" )
	private String direccionTram;
	
	@SerializedName( "sgd:razonSocialTram" )
	private String razonSocialTram;
	
	@SerializedName( "sgd:numDocumentoTram" )
	private String numDocumentoTram;
	
	@SerializedName( "sgd:tipoDocumentoTram" )
	private String tipoDocumentoTram;
	
	@SerializedName( "sgd:correntista" )
	private String correntista;
	
	@SerializedName( "sgd:destinatario" )
	private String destinatario;
	
	@SerializedName( "sgd:areaDestino" )
	private String areaDestino;
	
	@SerializedName( "sgd:nombreProceso" )
	private String nombreProceso;
	
	@SerializedName( "sgd:fechaCreacionExpediente" )
	private String fechaCreacionExpediente;
	
	@SerializedName( "sgd:numExpediente" )
	private String numExpediente;
	
	@SerializedName( "sgd:nroMP" )
	private String nroMP;
	
	@SerializedName( "sgd:fechaCreacionDocumento" )
	private String fechaCreacionDocumento;
	
	@SerializedName( "sgd:numFolios" )
	private String numFolios;
	
	@SerializedName( "sgd:numDocumento" )
	private String numDocumento;
	
	@SerializedName( "sgd:tipoDocumento" )
	private String tipoDocumento;
	
	@SerializedName( "sgd:idDocumento" )
	private String idDocumento;
	
	@SerializedName( "sgd:LaserFicheComment" )
	private String laserFicheComment;
	
	@SerializedName("cm:title")
	private String title;

//	@SerializedName("sgd:archivoTram")
//	private String archivoTram;

	
	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDireccionTram2() {
		return direccionTram2;
	}

	public void setDireccionTram2(String direccionTram2) {
		this.direccionTram2 = direccionTram2;
	}

	public String getDireccionTram() {
		return direccionTram;
	}

	public void setDireccionTram(String direccionTram) {
		this.direccionTram = direccionTram;
	}

	public String getRazonSocialTram() {
		return razonSocialTram;
	}

	public void setRazonSocialTram(String razonSocialTram) {
		this.razonSocialTram = razonSocialTram;
	}

	public String getNumDocumentoTram() {
		return numDocumentoTram;
	}

	public void setNumDocumentoTram(String numDocumentoTram) {
		this.numDocumentoTram = numDocumentoTram;
	}

	public String getTipoDocumentoTram() {
		return tipoDocumentoTram;
	}

	public void setTipoDocumentoTram(String tipoDocumentoTram) {
		this.tipoDocumentoTram = tipoDocumentoTram;
	}

	public String getCorrentista() {
		return correntista;
	}

	public void setCorrentista(String correntista) {
		this.correntista = correntista;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getAreaDestino() {
		return areaDestino;
	}

	public void setAreaDestino(String areaDestino) {
		this.areaDestino = areaDestino;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public String getFechaCreacionExpediente() {
		return fechaCreacionExpediente;
	}

	public void setFechaCreacionExpediente(String fechaCreacionExpediente) {
		this.fechaCreacionExpediente = fechaCreacionExpediente;
	}

	public String getNumExpediente() {
		return numExpediente;
	}

	public void setNumExpediente(String numExpediente) {
		this.numExpediente = numExpediente;
	}

	public String getNroMP() {
		return nroMP;
	}

	public void setNroMP(String nroMP) {
		this.nroMP = nroMP;
	}

	public String getFechaCreacionDocumento() {
		return fechaCreacionDocumento;
	}

	public void setFechaCreacionDocumento(String fechaCreacionDocumento) {
		this.fechaCreacionDocumento = fechaCreacionDocumento;
	}

	public String getNumFolios() {
		return numFolios;
	}

	public void setNumFolios(String numFolios) {
		this.numFolios = numFolios;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getLaserFicheComment() {
		return laserFicheComment;
	}

	public void setLaserFicheComment(String laserFicheComment) {
		this.laserFicheComment = laserFicheComment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//	public String getArchivoTram() {
//		return archivoTram;
//	}
//
//	public void setArchivoTram(String archivoTram) {
//		this.archivoTram = archivoTram;
//	}
//	
	
	
}
