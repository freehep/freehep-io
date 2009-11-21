// Copyright FreeHEP, 2007-2009
package org.freehep.util.io;

import java.io.IOException;
import java.io.Writer;

/**
 * The JavaArrayOutputStream writes out the text in java to compile into a byte
 * array.
 * 
 * @author Mark Donszelmann
 */
public class JavaArrayWriter extends IndentPrintWriter implements
		FinishableOutputStream {

	private final static int MAX_ENTRIES_PER_LINE = 8;

	private String author;

	private String arrayName;

	private int entries;

	private boolean start;

	private boolean end;

	public JavaArrayWriter(Writer out, String arrayName, String author) {
		super(out);
		this.arrayName = arrayName;
		this.author = author;

		entries = MAX_ENTRIES_PER_LINE;
		start = true;
		end = false;
	}

	@Override
	public void write(int b) {
		start();

		String s = Integer.toHexString(b & 0x00FF);
		switch (s.length()) {
		case 1:
			writeEntry(("0" + s).substring(0, 2));
			break;
		case 2:
			writeEntry(s);
			break;
		default:
			System.err.println("ERROR " + getClass() + ": byte '" + b
					+ "' was encoded in less than 1 or more than 2 chars");
		}
	}

	private void start() {
		if (start) {
			start = false;
			super.indent();
			super.println("// START: Generated Code by " + author);
			super.println("private final byte[] " + arrayName
					+ " = new byte[] {");
			super.indent();
		}
	}

	public void finish() {
		if (!end) {
			end = true;
			super.println();
			super.outdent();
			super.println("}; // " + arrayName);
			super.println("// END: Generated Code by " + author);
			super.outdent();
			flush();
			if (out instanceof FinishableOutputStream) {
				try {
					((FinishableOutputStream) out).finish();
				} catch (IOException e) {
					System.err.println("ERROR " + getClass() + ": " + e);
				}
			}
		}
	}

	@Override
	public void close() {
		finish();
		super.close();
	}

	private void writeEntry(String s) {
		if (entries == 0) {
			entries = MAX_ENTRIES_PER_LINE;
			super.println();
		}
		entries--;
		super.print("(byte)0x");
		super.print(s);
		super.print(", ");
	}
}
