package pe.gob.sunass.marcacion.apirest.commons;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

public class HeaderConfig {

	private List<Header> headers;
	private static String alfrescoTicket;

	public void createJson() {
		add("Accept", "application/json; charset=UTF-8");
		add("Content-Type", "application/json; charset=UTF-8");
	}
	
	public void createOctetStream() {
		add("Accept", "application/json; charset=UTF-8");
		add("Content-Type", "multipart/form-data; charset=utf-8");
	}
	
	public void createBynaryUpload() {
		add("Accept", "application/octet-stream");
		add("Content-Type", "application/json; charset=UTF-8");
	}

	private void addAuthorization(HttpEntityEnclosingRequestBase req, String ticket) {
		if (ticket != null && !ticket.trim().isEmpty()) {
			req.addHeader("Authorization", "Basic " + Encode.encodeBase64(ticket));
			alfrescoTicket = ticket;
		}
	}

	private void addAuthorization(HttpRequestBase req, String ticket) {
		if (ticket != null && !ticket.trim().isEmpty()) {
			String encode = Encode.encodeBase64(ticket);
			req.addHeader("Authorization", "Basic " + encode );
			alfrescoTicket = ticket;
		}
	}

	public static HeaderConfig asJson(HttpEntityEnclosingRequestBase req, String ticket) {
		HeaderConfig hc = new HeaderConfig();
		hc.createJson();
		hc.addHeaders(req);
		hc.addAuthorization(req, ticket);
		return hc;
	}
	
	public static HeaderConfig asBinaryDownload(HttpEntityEnclosingRequestBase req, String ticket) {
		HeaderConfig hc = new HeaderConfig();
		hc.createBynaryUpload();
		hc.addHeaders(req);
		hc.addAuthorization(req, ticket);
		return hc;
	}

	public static HeaderConfig asJson(HttpRequestBase req, String ticket) {
		HeaderConfig hc = new HeaderConfig();
		hc.createJson();
		hc.addHeaders(req);
		hc.addAuthorization(req, ticket);
		return hc;
	}
	
	public static HeaderConfig asBinary(HttpRequestBase req, String ticket) {
		HeaderConfig hc = new HeaderConfig();
//		hc.createOctetStream();
//		hc.addHeaders(req);
		hc.addAuthorization(req, ticket);
		return hc;
	}

	private void addHeaders(HttpEntityEnclosingRequestBase req) {
		for (Header h : headers) {
			req.addHeader(h.getName(), h.getValue());
		}
	}

	private void addHeaders(HttpRequestBase req) {
		for (Header h : headers) {
			req.addHeader(h.getName(), h.getValue());
		}
	}

	public void add(String key, String value) {

		if (headers == null) {
			headers = new ArrayList<Header>();
		}

		headers.add(new Header(key, value));
	}

	public static String getTicket() {
		return alfrescoTicket;
	}

}
