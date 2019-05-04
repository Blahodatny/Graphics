package com.project.bitmap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class BitmapImage extends BitmapImageHeader {
    final private static String DIR = "lab3/src/main/resources/";
    final private static String BMP = DIR + "bmp/tr33.bmp";
    final private static String PIXELS = DIR + "pixels.txt";
    final private static String BMP_IN_TXT = DIR + "primer_bmp.txt";

    private BitmapImage(String bmpFile) {
        BufferedInputStream reader = null;
        BufferedOutputStream writer = null;
        try {
            convertBmpToTxt(bmpFile, BMP_IN_TXT);

            reader = new BufferedInputStream(new FileInputStream(BMP_IN_TXT));
            writer = new BufferedOutputStream(new FileOutputStream(PIXELS));

            setImageHeader(reader);

            reader.mark(1);
            reader.reset();

            for (var line = reader.read(); line != -1; line = reader.read())
                writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(reader).close();
                Objects.requireNonNull(writer).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void convertBmpToTxt(String bmpFile, String txtFile)
            throws IOException {
        BufferedInputStream reader = null;
        BufferedOutputStream writer = null;
        try {
            reader = new BufferedInputStream(new FileInputStream(bmpFile));
            writer = new BufferedOutputStream(new FileOutputStream(txtFile));
            for (var line = reader.read(); line != -1; line = reader.read())
                writer.write(line);
        } finally {
            Objects.requireNonNull(reader).close();
            Objects.requireNonNull(writer).close();
        }
    }

    public String toString() {
        return "BitmapImage{" +
                "\ntype = " + bfType +
                "\nsize = " + bfSize +
                "\nreserved1 = " + bfReserved1 +
                "\nreserved2 = " + bfReserved2 +
                "\noffset = " + bfOffBits +
                "\nsize of bitmap = " + headerSize +
                "\nwidth = " + imageWidth +
                "\nheight = " + imageHeight +
                "\nnumber of planes = " + planes +
                "\nnumber of bits = " + bitCount +
                "\ntype of compression = " + compression +
                "\nsize of image after compression = " + imageSize +
                "\nhorizontal resolution = " + XPixelsPerMeter +
                "\nvertical resolution = " + YPixelsPerMeter +
                "\nnumber of used colors = " + CirUsed +
                "\nnumber of important colors = " + CirImportant +
                "\nhalf of width = " + halfOfWidth +
                "\n}";
    }

    public static void main(String... args) {
        System.out.println(new BitmapImage(BMP));
    }
}