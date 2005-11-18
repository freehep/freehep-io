package org.freehep.util.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Equivalent to writing to /dev/nul
 * @author tonyj
 */
public class DummyOutputStream extends OutputStream
{
   public DummyOutputStream()
   {
   }
   public void write(int b) throws IOException
   {
   }
   public void write(byte[] b) throws IOException
   {
   }
   public void write(byte[] b, int off, int len) throws IOException
   {
   }
}
