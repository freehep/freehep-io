// Copyright 2001-2009, FreeHEP.
package org.freehep.util.io.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.freehep.util.io.Base64OutputStream;

/**
 * Test for Base64 Output Stream.
 * 
 * @author Mark Donszelmann
 */
public class Base64OutputStreamTest extends AbstractStreamTest {

    /**
     * Test method for 'org.freehep.util.io.Base64OutputStream.write()'
     * @throws Exception if ref file cannot be found
     */
    public void testWrite() throws Exception {
        File testFile = new File(testDir, "Quote.txt");
        File outFile = new File(outDir, "Quote.b64");
        File refFile = new File(refDir, "Quote.b64");
        
        Base64OutputStream out = new Base64OutputStream(new FileOutputStream(outFile));
        FileInputStream in = new FileInputStream(testFile);
        int b;
        while ((b = in.read()) >= 0) {
            out.write(b);
        }
        in.close();
        out.close();

        Assert.assertEquals(refFile, outFile, true);
    }
    
    public void testEnding1() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base64OutputStream out = new Base64OutputStream(baos);
        out.write("sure.".getBytes());
        out.close();
        Assert.assertEquals( "c3VyZS4=", baos.toString());
    }

    public void testEnding2() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base64OutputStream out = new Base64OutputStream(baos);
        out.write("sure".getBytes());
        out.close();
        Assert.assertEquals( "c3VyZQ==", baos.toString());
    }

    public void testEnding3() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base64OutputStream out = new Base64OutputStream(baos);
        out.write("sur".getBytes());
        out.close();
        Assert.assertEquals( "c3Vy", baos.toString());
    }

    public void testEnding4() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base64OutputStream out = new Base64OutputStream(baos);
        out.write("su".getBytes());
        out.close();
        Assert.assertEquals( "c3U=", baos.toString());
    }

    public void testEnding5() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base64OutputStream out = new Base64OutputStream(baos);
        out.write("leasure.".getBytes());
        out.close();
        Assert.assertEquals( "bGVhc3VyZS4=", baos.toString());
    }

    public void testEnding6() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base64OutputStream out = new Base64OutputStream(baos);
        out.write("easure.".getBytes());
        out.close();
        Assert.assertEquals( "ZWFzdXJlLg==", baos.toString());
    }

    public void testEnding7() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base64OutputStream out = new Base64OutputStream(baos);
        out.write("asure.".getBytes());
        out.close();
        Assert.assertEquals( "YXN1cmUu", baos.toString());
    }
}
