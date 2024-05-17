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
import pe.gob.sunass.marcacion.common.FechaUtil;
import pe.gob.sunass.marcacion.common.StringUtil;
import pe.gob.sunass.marcacion.constant.PropertiesConstant;

@Service
public class AlfrescoService {

    @Autowired
    private PropertiesConstant props;

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

        alfrescoConnector = new AlfrescoConnector( props.getAlfrescoUrl() );

        // 0. Convirtinendo a file
        File file = convertMultipartFileToFile(mfile);

        // 1. Obteniendo la fecha de la carpeta
        String folderDateName = FechaUtil.toStr(new Date(), FechaUtil.PATTERN_YYYY_MM_DD);

        // 2. Generando el path de la carpeta con el usuario
        String folderPath = StringUtil.concat(props.getAlfrescoFolderRoot(), "/", username, "/", folderDateName, "/", foldername );

        // 3. Creando las carpetas
        createFolderIfNotExist( folderPath );

        // 4. Creando el archivo
        alfrescoConnector.createFile(props.getAlfrescoUser(), props.getAlfrescoPassword(), folderPath, file.getName());
        
        // 5. Subiendo el archivo
        NodeResponse nr = alfrescoConnector.modifyBinaryContent(props.getAlfrescoUser(), props.getAlfrescoPassword(), folderPath + "/" + file.getName(), file.getName(), file);

        AlfrescoFileRestOutRO alfrescoFileRestOutRO = new AlfrescoFileRestOutRO();
        alfrescoFileRestOutRO.setNodeId(nr.getEntry().getId());
        alfrescoFileRestOutRO.setFilename(file.getName());

        return alfrescoFileRestOutRO;
    }

    private NodeResponse createFolderIfNotExist( String path ){

        String[] foldersname = path.split("/");
        String relativePath = "";
        String relativePreview = "";

        for ( String fname : foldersname ) {
            relativePath += StringUtil.concat("/", fname);
            boolean isExist = alfrescoConnector.isNodeExists(props.getAlfrescoUser(), props.getAlfrescoPassword(), relativePath);
            if( !isExist ){
                alfrescoConnector.createDirectory(props.getAlfrescoUser(), props.getAlfrescoPassword(), relativePreview, fname);
            }
            relativePreview += StringUtil.concat("/", fname);
        }

        return alfrescoConnector.getNode(props.getAlfrescoUser(), props.getAlfrescoPassword(), path);
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return file;
    }

}
