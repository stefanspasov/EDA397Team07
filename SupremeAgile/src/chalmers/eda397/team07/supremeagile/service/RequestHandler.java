package chalmers.eda397.team07.supremeagile.service;

import java.io.*;
import java.net.*;

import org.apache.commons.codec.binary.Base64;

import chalmers.eda397.team07.supremeagile.common.SAContext;

public class RequestHandler {
	public enum RequestMethod {
		GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;
	}
	
	public static String executeHttpRequest(String targetURL,
			RequestMethod requestMethod) {
		return executeHttpRequest(targetURL, requestMethod, null);
	}

	public static String executeHttpRequest(String targetURL,
			RequestMethod requestMethod, String urlParameters) {
		URL url;
		HttpURLConnection connection = null;

		String authString = SAContext.username + ":" + SAContext.password;
		String authStringEnc = new String (Base64.encodeBase64(authString.getBytes()));

		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(requestMethod.toString());
			connection.setRequestProperty("Authorization", "Basic "
					+ authStringEnc);

			if (urlParameters != null && urlParameters != "") {
				connection.setDoOutput(true);
				// Send request parameters
				DataOutputStream wr = new DataOutputStream(
						connection.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close();
			}

			// Get response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
