// Copyright 2002, FreeHEP.
package org.freehep.util.io.test;

import java.io.*;

import org.freehep.util.io.*;

/**
 *
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/RoutedInputStreamTest.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class RoutedInputStreamTest {

    public static void main(String[] args) throws IOException {
        if ((args.length < 1) || ((args.length % 2) != 1)) {
            System.out.println("Usage: RoutedInputStreamTest filename [start end]");
            System.exit(1);
        }

        RoutedInputStream in = new RoutedInputStream(new BufferedInputStream(new FileInputStream(args[0])));

        for (int i=1; i<args.length; i+=2) {
            in.addRoute(args[i], args[i+1], new RouteListener() {

                public void routeFound(RoutedInputStream.Route route) throws IOException {
                    System.out.write('[');
                    System.out.write(route.getStart());
                    System.out.write(':');
                    int b = route.read();
                    while (b != -1) {
                        System.out.write(b);
                        b = route.read();
                    }
                    route.close();
                    System.out.write(']');
                    System.out.flush();
                }
            });
            System.out.println("Added route: "+args[i]+"-"+args[i+1]);
        }

        in.addRoute("SClosed", "EClosed", new RouteListener() {

            public void routeFound(RoutedInputStream.Route route) throws IOException {
                System.out.print("[EarlyClosed:");
                for (int i=0; i<6; i++) {
                    System.out.write(route.read());
                }
                route.close();
                System.out.write(']');
                System.out.flush();
            }
        });

        long t0 = System.currentTimeMillis();

        int b = in.read();
        while (b != -1) {
            System.out.write(b);
            b = in.read();
        }
        in.close();
        System.out.flush();
        System.err.println("Reading took: "+(System.currentTimeMillis()-t0)+" ms.");
    }
}
