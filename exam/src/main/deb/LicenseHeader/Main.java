/*
 * LicenseHeader.java        30/11/21
 *
 * Program to calculate the area and the perimeter
 * of a circle knowing the radius in meter.
 *
 * Copyright 2021 Arnau Gràcia Taberner <agraciataberner02@gmail.com>
 *
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 */

package main.deb.LicenseHeader;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //                     args[0]                    args[1]               args[2]
        byteOriented("/tmp/dir/license", "/tmp/dir/src", ".java");
        caracterOriented("/tmp/dir/license", "/tmp/dir/src", ".java");

    }

    static void byteOriented(String license, String directory, String extension) throws IOException {
        byte[] headerBytes = Files.newInputStream(Paths.get(license)).readAllBytes();
        Path temporalFile = Paths.get("/tmp/temporal");

        Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .filter(f -> f.toString().endsWith(extension))
                .forEach(f -> {
                    try {
                        OutputStream tmpOutputStream = Files.newOutputStream(temporalFile);
                        tmpOutputStream.write(headerBytes);
                        tmpOutputStream.write(Files.newInputStream(f).readAllBytes());
                        Files.delete(f);
                        Files.move(temporalFile, f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    static void caracterOriented(String license, String directory, String extension) throws IOException {
        List<String> headerLines = Files.readAllLines(Paths.get(license));
        Path temporalFile = Paths.get("/tmp/temporal");

        Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .filter(f -> f.toString().endsWith(extension))
                .forEach(f -> {
                    try {
                        Files.write(temporalFile, headerLines);
                        Files.write(temporalFile, Files.readAllLines(f), StandardOpenOption.APPEND);
                        Files.delete(f);
                        Files.move(temporalFile, f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}