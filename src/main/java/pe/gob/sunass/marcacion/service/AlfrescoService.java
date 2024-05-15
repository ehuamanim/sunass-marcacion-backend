package pe.gob.sunass.marcacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import pe.gob.sunass.marcacion.constant.PropertiesConstant;

@Service
public class AlfrescoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private String alfrescoApiUrl;

    @Autowired
    private PropertiesConstant props;

    public byte[] downloadFile(String fileId) {
        String downloadUrl = alfrescoApiUrl + "/nodes/" + fileId + "/content";
        return restTemplate.getForObject(downloadUrl, byte[].class);
    }

    public void uploadFile(byte[] fileContent, String filename, String folderPath) {
        String folderId = getOrCreateFolder(folderPath);
        String uploadUrl = alfrescoApiUrl + "/nodes/" + folderId + "/children";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        ByteArrayResource fileResource = new ByteArrayResource(fileContent) {
            @Override
            public String getFilename() {
                return filename;
            }
        };
        body.add("filedata", fileResource);

        JsonObject json = new JsonObject();
        json.addProperty("name", filename);
        json.addProperty("nodeType", "cm:content");
        body.add("name", json.toString());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        restTemplate.postForObject(uploadUrl, requestEntity, String.class);
    }

    private String getOrCreateFolder(String folderPath) {
        String[] folders = folderPath.split("/");
        String currentParentId = props.getAlfrescoParentId();

        for (String folder : folders) {
            String folderId = getFolderId(currentParentId, folder);
            if (folderId == null) {
                folderId = createFolder(currentParentId, folder);
            }
            currentParentId = folderId;
        }

        return currentParentId;
    }

    private String getFolderId(String parentId, String folderName) {
        String folderUrl = alfrescoApiUrl + "/nodes/" + parentId + "/children?relativePath=" + folderName;
        ResponseEntity<String> response = restTemplate.getForEntity(folderUrl, String.class);
        JsonObject responseObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
        JsonArray entries = responseObject.getAsJsonArray("entries");

        try{
            if (entries != null && entries.size() > 0) {
                JsonObject firstEntry = entries.get(0).getAsJsonObject();
                return firstEntry.get("id").getAsString();
            } 
        }catch(Exception e){}

        return null;
    }

    private String createFolder(String parentId, String folderName) {
        String createFolderUrl = alfrescoApiUrl + "/nodes/" + parentId + "/children";
        JsonObject folderJson = new JsonObject();
        folderJson.addProperty("name", folderName);
        folderJson.addProperty("nodeType", "cm:folder");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(folderJson.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(createFolderUrl, requestEntity, String.class);
        JsonObject responseObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
        return responseObject.get("entry").getAsJsonObject().get("id").getAsString();
    }
}
