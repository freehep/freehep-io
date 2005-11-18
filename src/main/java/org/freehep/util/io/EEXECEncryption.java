// Copyright 2001 freehep
package org.freehep.util.io;

//import java.util.Random;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Encrypts using the EEXEC form (Used by Type 1 fonts).
 *
 * @author Simon Fischer
 * @version $Id: src/main/java/org/freehep/util/io/EEXECEncryption.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class EEXECEncryption extends OutputStream implements EEXECConstants {

    private int n, c1, c2, r;
    private OutputStream out;
    private boolean first = true;

    //private static final int[] RANDOM_BYTES = {1,2,4,8};
    //private static final int[] RANDOM_BYTES = {0,0,0,0};

    public EEXECEncryption(OutputStream out) {
	this(out, EEXEC_R, N);
    }

    public EEXECEncryption(OutputStream out, int r) {
	this(out, r, N);
    }

    public EEXECEncryption(OutputStream out, int r, int n) {
	this.out = out;
	this.c1 = C1;
	this.c2 = C2;
	this.r = r;
	this.n = n;

    }

    private int encrypt(int plainByte) {
	int cipher = (plainByte ^ (r >>> 8)) % 256;
	r = ((cipher+r) * c1 + c2) % 65536;
	return cipher;	
    }

    public void write(int b) throws IOException {
	if (first) {
	    for (int i = 0; i < n; i++) 
		out.write(encrypt(0));
	    first = false;
	}

	out.write(encrypt(b));
    }

    public void flush() throws IOException {
	super.flush();
	out.flush();
    }

    public void close() throws IOException {
	flush();
	super.close();
	out.close();
    }


    private static class IntOutputStream extends OutputStream {
	int[] chars;
	int i;
	public IntOutputStream(int size) { chars = new int[size]; i = 0;}
	public void write(int b) { chars[i++] = b; } //str += (char)b; }
	//public String getString() { return str; }
	public int[] getInts() { return chars; }
    }

    public static int[] encryptString(int[] chars, int r, int n) throws IOException {
	IntOutputStream resultStr = new IntOutputStream(chars.length+4);
	EEXECEncryption eout = new EEXECEncryption(resultStr, r, n);
	for (int i = 0; i < chars.length; i++)
	    eout.write(chars[i]);
	return resultStr.getInts();
    }
}

