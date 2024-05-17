package pe.gob.sunass.marcacion.apirest.commons;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	public String reqPost(String uri, String data, String ticket) throws IOException {

		HttpPost request = new HttpPost(uri);
		String dataResponse = req(request, data, ticket);
		return dataResponse;

	}
	
	public String reqPost(String uri, String data, String ticket, Map<String, String> propiedades) throws IOException {

		HttpPost request = new HttpPost(uri);
		String dataResponse = req(request, StringUtil.join(data, StringUtil.toHtmlParams(propiedades)), ticket);
		return dataResponse;

	}

	public String reqGet(String uri, String ticket) throws IOException {

		HttpGet request = new HttpGet(uri);
		String dataResponse = req(request, ticket);
		return dataResponse;

	}
	
	public byte[] reqGetBytes(String uri, String ticket) throws IOException {
		HttpGet request = new HttpGet(uri);
		byte[] dataResponse = reqBinary(request, ticket);
		return dataResponse;
	}

	public String reqPut(String uri, String data, String ticket) throws IOException {
		HttpPut request = new HttpPut(uri);
		String dataResponse = req(request, data, ticket);
		return dataResponse;
	}
	
	public String reqPut(String uri, String data, File file,  String ticket) throws IOException {
		HttpPut request = new HttpPut(uri);
		String dataResponse = req(request, data, file, ticket);
		return dataResponse;
	}
	
	public String reqDel(String uri, String ticket) throws IOException {
		HttpDelete request = new HttpDelete(uri);
		String dataResponse = req(request, ticket);
		return dataResponse;
	}

	public String req(HttpEntityEnclosingRequestBase request, String data, String ticket)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response;
		String xhrResponse = null;

		try {

			HeaderConfig.asJson(request, ticket);
			request.setEntity(new StringEntity(data, "UTF-8")); // JDK 6+
			response = httpClient.execute(request);
			xhrResponse = EntityUtils.toString(response.getEntity());
		} finally {
			httpClient.close();
		}

		return xhrResponse;

	}
	
	public String req(HttpEntityEnclosingRequestBase request, String data, File file, String ticket)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response;
		String xhrResponse = null;
		
		try {
	         
	         FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY, file.getName() );
	         MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	         builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
	         builder.addPart("filedata", fileBody);
	         HeaderConfig.asBinary(request, ticket);
	         request.setEntity( builder.build() );
	         response = httpClient.execute(request);
			xhrResponse = EntityUtils.toString(response.getEntity());
		} finally {
			httpClient.close();
		}

		return xhrResponse;

	}

	public String req(HttpRequestBase request, String ticket) throws IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response;
		String xhrResponse = null;

		try {

			HeaderConfig.asJson(request, ticket);
			response = httpClient.execute(request);
			
			if( response.getEntity() != null ) {
				xhrResponse = EntityUtils.toString(response.getEntity());	
			}
			
		} finally {
			httpClient.close();
		}

		return xhrResponse;

	}
	
	public byte[] reqBinary(HttpRequestBase request, String ticket) throws IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response;
		byte[] xhrResponse = null;

		try {

			HeaderConfig.asJson(request, ticket);
			response = httpClient.execute(request);
			xhrResponse = EntityUtils.toByteArray( response.getEntity() );
		} finally {
			httpClient.close();
		}

		return xhrResponse;

	}

	public static String generateHttpParameters(String... parameters) {

		StringBuffer sb = new StringBuffer("?");

		for (String param : parameters) {
			sb.append(param).append("&");
		}

		sb.append("randomize=").append(StringUtil.randomText(8));

		return sb.toString();

	}
	
	public static String removeIfStartSlash(String path) {
		
		if( path.startsWith("/") ) {
			return path.substring(1);
		}
		
		return path;
		
	}
	
	public static String addIfNotStartSlash(String path) {
		
		if( path.startsWith("/") ) {
			return path;
		}
		
		return "/" + path;
		
	}
	
	
	public static String getUserDebug( String usuario ) {
		
		if( ApiConfigurator.IS_DEBUG ) {
			return ApiConfigurator.USUARIO_DEBUG;
		}
		
		return usuario;
		
	}
	
	public static String getPassDebug( String password ) {
		
		if( ApiConfigurator.IS_DEBUG ) {
			return ApiConfigurator.PASS_DEBUG;
		}
		
		return password;
		
	}
	
	

}
