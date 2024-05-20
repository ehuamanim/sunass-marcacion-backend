package pe.gob.sunass.marcacion.service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.sunass.marcacion.apirest.alfresco.AlfrescoConnector;
import pe.gob.sunass.marcacion.apirest.body.response.NodeResponse;
import pe.gob.sunass.marcacion.apirest.dto.AlfrescoFileRestOutRO;
import pe.gob.sunass.marcacion.apirest.exception.AlfrescoConnectorException;
import pe.gob.sunass.marcacion.common.FechaUtil;
import pe.gob.sunass.marcacion.common.StringUtil;
import pe.gob.sunass.marcacion.config.AlfrescoConfig;
import pe.gob.sunass.marcacion.constant.PropertiesConstant;

@Service
public class AlfrescoService {

    @Autowired
    private AlfrescoConfig alfConfig;

    private AlfrescoConnector alfrescoConnector;

    /**
     * Suber archivo
     * @param username
     * @param filename
     * @param foldername
     * @param file
     * @return
     * @throws IOException 
     */
    public AlfrescoFileRestOutRO uploadFile( String username, String filename, String foldername, MultipartFile mfile ) throws IOException{

        alfrescoConnector = new AlfrescoConnector( alfConfig.getUrl() );

        // 0. Convirtinendo a file
        File file = convertMultipartFileToFile(mfile);

        // 1. Obteniendo la fecha de la carpeta
        String folderDateName = FechaUtil.toStr(new Date(), FechaUtil.PATTERN_YYYY_MM_DD);

        // 2. Generando el path de la carpeta con el usuario
        String folderPath = StringUtil.concat(alfConfig.getRootFolderName(), "/", username, "/", folderDateName, "/", foldername );

        // 3. Creando las carpetas
        createFolderIfNotExist( folderPath );

        // 4. Creando el archivo
        alfrescoConnector.createFile(alfConfig.getUsername(), alfConfig.getPassword(), folderPath, file.getName());
        
        // 5. Subiendo el archivo
        NodeResponse nr = alfrescoConnector.modifyBinaryContent(alfConfig.getUsername(), alfConfig.getPassword(), folderPath + "/" + file.getName(), file.getName(), file);

        AlfrescoFileRestOutRO alfrescoFileRestOutRO = new AlfrescoFileRestOutRO();
        alfrescoFileRestOutRO.setNodeId(nr.getEntry().getId());
        alfrescoFileRestOutRO.setFilename(file.getName());

        return alfrescoFileRestOutRO;
    }
    
    public byte[] getBytesNode(String nodeId) throws AlfrescoConnectorException{
    	alfrescoConnector = new AlfrescoConnector( alfConfig.getUrl() );
    	String ticket = alfrescoConnector.getTicket(alfConfig.getUsername(), alfConfig.getPassword());
    	return alfrescoConnector.getBytesNode(nodeId, ticket);
    }

    private NodeResponse createFolderIfNotExist( String path ){

        String[] foldersname = path.split("/");
        String relativePath = "";
        String relativePreview = "";

        for ( String fname : foldersname ) {
            relativePath += StringUtil.concat("/", fname);
            boolean isExist = alfrescoConnector.isNodeExists(alfConfig.getUsername(), alfConfig.getPassword(), relativePath);
            if( !isExist ){
                alfrescoConnector.createDirectory(alfConfig.getUsername(), alfConfig.getPassword(), relativePreview, fname);
            }
            relativePreview += StringUtil.concat("/", fname);
        }

        return alfrescoConnector.getNode(alfConfig.getUsername(), alfConfig.getPassword(), path);
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return file;
    }

}
