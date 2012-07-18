package org.freehep.util.io.test;

import java.io.IOException;
import java.io.OutputStream;

import org.freehep.util.io.ByteCountOutputStream;

public class ByteCountOutputStreamTest extends AbstractStreamTest
{
	public void testBugWithByteCountOutputStream() throws IOException
	{
		// The bug is that this will throw a stack overflow exception
		new ByteCountOutputStream(new OutputStream()
		{
			@Override
			public void write(int arg0) throws IOException
			{
				//noop
			}
		}, true).write(0);
	}
}
