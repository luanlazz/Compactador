package BWT;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class BWT {

	public static String encode(String str, boolean last) {
		str += '$';
		
		String[] strs = new String[str.length()];
		for (int i = 0; i < strs.length; ++i) {
			strs[i] = str.substring(i) + str.substring(0, i);
		}

		java.util.Arrays.sort(strs);

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < strs.length; ++i) {
			builder.append(strs[i].charAt(strs[i].length() - 1));
		}
		if (!last) {
			builder.append("@");
		}
		return builder.toString();
	}

	public static String decode(String encodedString) {
		Integer[] indices = new Integer[encodedString.length()];
		for (int i = 0; i < indices.length; ++i) {
			indices[i] = i;
		}
		java.util.Arrays.sort(indices, new BWTComparator(encodedString));

		int startIndex = 0;
		for (; encodedString.charAt(startIndex) != '$'; ++startIndex);

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < indices.length - 1; ++i) {
			startIndex = indices[startIndex];
			char c = encodedString.charAt(startIndex);
			builder.append(c);
		}
		return builder.toString();
	}

}
