/*
 * CleanOldFiles.java        30/11/21
 *
 * Program to calculate the area and the perimeter
 * of a circle knowing the radius in meter.
 *
 * Copyright 2021 Arnau Gràcia Taberner <agraciataberner02@gmail.com>
 *
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 */

package main.deb.CleanOldFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Main {
    /*
     * args[0] (String) = ruta directori
     * args[1] (int) = numero de dies
     */
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(args[0]);
        int n = Integer.parseInt(args[1]);

        Files.walk(path)
                .filter(Files::isRegularFile)
                .forEach(file -> {
                    try {
                        FileTime fileTime = Files.getLastModifiedTime(file);
                        boolean time = fileTime.toInstant().isBefore(Instant.now().minus(n, ChronoUnit.DAYS));
                        if (time) Files.delete(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}