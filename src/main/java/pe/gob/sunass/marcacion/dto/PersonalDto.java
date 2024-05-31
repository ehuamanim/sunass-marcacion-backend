package pe.gob.sunass.marcacion.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PersonalDto {
    
    // Personal
    private String personalId;
    private String planillaId;
    private String nroDoc;
    private String trabajador;
    private String flag;
    private String situacionEspId;
    private String cargoId;
    private String tipoPersonaId;
    private String itemUo;
    private String tipoDocId;
    private String condicionId;
    private Date fechaIngreso;
    private Date fechaCese;

    // Tipo Documento
    private String tipoDoc;

    // Cargo
    private String cargo;
    
    // Tipo trabajador
    private String tipoPersona;

    // Unidad Organizativa
    private String unidadOrganizativa;
    
    // Institucion Sede
    private String institucionSede;
    private String direccionSede;

    private String username;
    private String rol;
    private String macAddress;
    private String macAddress2;
    private String flagMacAddress;
}
