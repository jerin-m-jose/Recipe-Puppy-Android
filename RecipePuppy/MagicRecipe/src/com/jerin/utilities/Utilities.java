package com.jerin.utilities;

import android.net.Uri;
import android.os.Bundle;

/**
 * Class to hold utility methods.
 * 
 * @author jerin
 * 
 */
public class Utilities {

	private static final String URL_SCHEME = "http";

	/**
	 * Builder utility method to generate http query
	 * 
	 * @param authority
	 * @param path
	 * @param parameters
	 * @return
	 */
	public static Uri buildUri(String authority, String path, Bundle parameters) {
		Uri.Builder builder = new Uri.Builder();
		builder.scheme(URL_SCHEME);
		builder.authority(authority);
		if (null != path && (path.trim().length() > 0)) {
			builder.path(path);
		}
		if (null != parameters) {
			for (String key : parameters.keySet()) {
				Object parameter = parameters.get(key);
				if (parameter instanceof String) {
					builder.appendQueryParameter(key, (String) parameter);
				}
			}
		}
		return builder.build();
	}
}
