package pe.gob.sunass.marcacion.apirest.exception;

public interface ConnectorErrorCodes {

	public static int NODE_NOT_EXISTS = 10000;
	public static int NODE_FOLDER_NOT_CREATED = 10001;
	public static int NODE_NOT_COPIED = 10002;
	public static int NODE_NOT_OBTAINED = 10003;
	public static int NODE_EMPTY = 10004;
	
	public static int FILE_NOT_EXISTS = 20000;
	public static int FILE_NOT_COPIED = 20002;
	public static int FILE_NOT_DOWNLOADED = 20003;
	
	public static int TICKET_NOT_GENERATED = 30000;
	
}
