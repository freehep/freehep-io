// Copyright 2001-2009, FreeHEP.
package org.freehep.util.io.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import org.freehep.util.io.ASCII85InputStream;
import org.freehep.util.io.ASCII85OutputStream;
import org.freehep.util.io.EncodingException;

/**
 * Test for ASCII85 Input Stream.
 * 
 * @author Mark Donszelmann
 */
public class ASCII85InputStreamTest extends AbstractStreamTest {
        
    /**
     * Test method for 'org.freehep.util.io.ASCII85InputStream.read()'
     * @throws Exception if ref file cannot be found
     */
    public void testRead() throws Exception {
        File testFile = new File(testDir, "Quote.a85");
        File refFile = new File(refDir, "Quote.txt");
            
        ASCII85InputStream in = new ASCII85InputStream(new FileInputStream(testFile));
        Assert.assertEquals(new FileInputStream(refFile), in, true, refFile.getPath());
    }
    
    public void testNull1() throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream("!!~>".getBytes());
        ASCII85InputStream in = new ASCII85InputStream(bais);
        
        byte[] b = new byte[10];
        int len = in.read(b);
        in.close();
        Assert.assertArrayEquals( new byte[] {0}, b, 1);
    }

    public void testNull2() throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream("!!!~>".getBytes());
        ASCII85InputStream in = new ASCII85InputStream(bais);
        
        byte[] b = new byte[10];
        int len = in.read(b);
        in.close();
        Assert.assertArrayEquals( new byte[] {0, 0}, b, 2);
    }
  
    public void testNull3() throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream("!!!!~>".getBytes());
        ASCII85InputStream in = new ASCII85InputStream(bais);
        
        byte[] b = new byte[10];
        int len = in.read(b);
        in.close();
        Assert.assertArrayEquals( new byte[] {0, 0, 0}, b, 3);
    }

    public void testNull4() throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream("z~>".getBytes());
        ASCII85InputStream in = new ASCII85InputStream(bais);
        
        byte[] b = new byte[10];
        int len = in.read(b);
        in.close();
        Assert.assertArrayEquals( new byte[] {0, 0, 0, 0}, b, 4);
    }

    public void testNull5() throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream("z!!~>".getBytes());
        ASCII85InputStream in = new ASCII85InputStream(bais);
        
        byte[] b = new byte[10];
        int len = in.read(b);
        in.close();
        Assert.assertArrayEquals( new byte[] {0, 0, 0, 0, 0}, b, 5);
    }

    public void testZincorrect() throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream("3z89~>".getBytes());
        ASCII85InputStream in = new ASCII85InputStream(bais);
        
        byte[] b = new byte[10];
        try {
            int len = in.read(b);
        } catch (EncodingException e) {
            // ok
            return;
        } finally {
            in.close();
        }
        throw new AssertionError("EncodingException expected");
    }
}
