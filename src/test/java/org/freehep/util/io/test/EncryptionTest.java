// Copyright 2001 freehep
package org.freehep.util.io.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.freehep.util.io.EEXECConstants;
import org.freehep.util.io.EEXECDecryption;
import org.freehep.util.io.EEXECEncryption;

/**
 * Test for EEXECEncryption.
 * 
 * FIXME rename class
 * 
 * @author duns
 * @version $Id: src/test/java/org/freehep/util/io/test/EncryptionTest.java 96b41b903496 2005/11/21 19:50:18 duns $
 */
public class EncryptionTest {

    /**
     * FIXME use junit
     * @param argv
     */
    public static void main(String[] argv) {
        try {

            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int[] ba = new int[] { 0x0010, 0x00BF, 0x0031, 0x0070, 0x004F,
                    0x00AB, 0x005B, 0x001F };
            for (int i = 0; i < ba.length; i++)
                bo.write(ba[i]);

            EEXECDecryption in = new EEXECDecryption(new ByteArrayInputStream(
                    bo.toByteArray()), EEXECConstants.CHARSTRING_R, 4);
            int r = 0;
            while ((r = in.read()) != -1) {
                System.out.print(Integer.toHexString(r) + " ");
            }

            System.out.println();

            int result[] = EEXECEncryption.encryptString(new int[] { 0x00BD,
                    0x00F9, 0x00B4, 0x000D }, EEXECEncryption.CHARSTRING_R,
                    EEXECEncryption.N);
            for (int i = 0; i < result.length; i++)
                System.out.print(Integer.toHexString(result[i]) + " ");
            System.out.println("\n");

            // System.exit(0);

            PipedOutputStream pipeOut = new PipedOutputStream();
            PipedInputStream pipeIn = new PipedInputStream();
            pipeOut.connect(pipeIn);

            EEXECEncryption eout = new EEXECEncryption(pipeOut,
                    EEXECConstants.EEXEC_R, EEXECEncryption.N);
            EEXECDecryption ein = new EEXECDecryption(pipeIn,
                    EEXECConstants.EEXEC_R, EEXECEncryption.N);

            byte[] bytes = "Hello World! - advanced version (by Adobe)"
                    .getBytes();
            for (int i = 0; i < bytes.length; i++)
                eout.write(bytes[i]);
            eout.close();
            int b = -1;

            while ((b = ein.read()) != -1)
                System.out.print((char) b + " ");

            ein.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
