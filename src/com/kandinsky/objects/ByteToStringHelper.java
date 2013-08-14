package com.kandinsky.objects;

import java.text.DecimalFormat;

/**
 * Hilfsklasse zum Umformen eines Byte-Werts in einen aussagekr�ftigen String.
 * @author Benne
 */
public class ByteToStringHelper {

	/** Namensrepr�sentationen */
	private static final String[] NAMES = { "B", "KB", "MB", "GB", "TB" };
	/** Byte-Factor */
	private static final double FACTOR = 1024.0;
	/** Formatierung des Werts */
	private static final DecimalFormat FORMAT = new DecimalFormat("#0.000");

	/**
	 * Konvertiert einen Byte-Wert in einen String, der nicht wie normal nur die Bytes angibt, sondern
	 * so rechnet, dass Bytes, KiloBytes, MegaByte, GigaBytes oder TeraBytes angezeigt werden. Das h�lt der String relativ kurz.
	 * @param byteValue
	 * @return aussagekr�ftiger String f�r den Byte-Wert
	 */
	public static String convertToString(long byteValue) {

		double valueAsDouble = (double) byteValue;
		final int count = NAMES.length - 1;
		int x = 0;

		while (valueAsDouble >= FACTOR && x < count) {
			valueAsDouble /= FACTOR;
			x++;
		}

		String value = FORMAT.format(valueAsDouble) + " " + NAMES[x];
		return value;
	}
}
