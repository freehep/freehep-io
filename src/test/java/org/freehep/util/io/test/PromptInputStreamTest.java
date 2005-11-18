// Copyright 2001, FreeHEP.
package org.freehep.util.io.test;

import java.io.*;

import org.freehep.util.io.*;

/**
 *
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/PromptInputStreamTest.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class PromptInputStreamTest {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: PromptInputStreamTest filename [prompts...]");
            System.exit(1);
        }

        PromptInputStream in = new PromptInputStream(new FileInputStream(args[0]));
        for (int i=1; i<args.length; i++) {
            final int promptNo = i-1;
            in.addPromptListener(args[i], new PromptListener() {
                public void promptFound(RoutedInputStream.Route route) {
                    System.out.println("\nPROMPT["+promptNo+"]: "+new String(route.getStart()));
                }
            });
        }

        int b = in.read();
        while (b >= 0) {
            System.out.write(b);
            b = in.read();
        }
        in.close();
        System.out.flush();
    }
}
