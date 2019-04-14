package com.project.bitmap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapImage extends BitmapImageHeader {
    public BitmapImage(BufferedInputStream stream, String file) {
        try {
            setImageHeader(stream);
            stream.mark(1);
            stream.reset();

            var writer = new BufferedOutputStream(new FileOutputStream(file));

            for (var line = stream.read(); line != -1; line = stream.read())
                writer.write(line);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}