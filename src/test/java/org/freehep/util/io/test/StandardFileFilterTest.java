// Copyright 2003, SLAC, Stanford, U.S.A
package org.freehep.util.io.test;

import java.io.*;

import org.freehep.util.io.StandardFileFilter;

public class StandardFileFilterTest {

    public static void main(String[] args) {
        FileFilter filter = new StandardFileFilter("*.html");
        File[] files = new File(".").listFiles(filter);

        for (int i=0; i<files.length; i++) {
            System.out.println(files[i].getPath().replaceAll("\\"+File.separator, "/"));
        }
    }
}

