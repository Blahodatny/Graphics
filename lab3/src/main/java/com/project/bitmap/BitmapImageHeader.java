package com.project.bitmap;

import java.io.BufferedInputStream;
import java.io.IOException;

abstract class BitmapImageHeader extends BitmapV5InfoHeader {
    long halfOfWidth;

    void setImageHeader(BufferedInputStream stream) throws IOException {
        var line = stream.read();
        setFileHeader(
                (short) (stream.read() * 0x100 + line), readLong(stream),
                readShort(stream), readShort(stream), readLong(stream)
        );
        setInfoHeader(
                readLong(stream), readLong(stream), readLong(stream),
                readShort(stream), readShort(stream), readLong(stream),
                readLong(stream), readLong(stream), readLong(stream),
                readLong(stream), readLong(stream)
        );
        halfOfWidth = imageWidth % 2 != 0 ? imageWidth + 1 : imageWidth / 2;
        if (halfOfWidth % 4 != 0)
            halfOfWidth /= 4 * 4 + 4;
    }

    // метод для коректного читання полів розміром 2 байти
    // у форматі запису little-endian
    private short readShort(BufferedInputStream stream) throws IOException {
        short value = 0;
        for (long i = 0x1; i <= 0x1000; i *= 0x100)
            value += stream.read() * i;
        return value;
    }

    // метод для коректного читання полів розміром 4 байти
    // у форматі запису little-endian
    private long readLong(BufferedInputStream stream) throws IOException {
        long value = 0;
        for (long i = 0x1; i <= 0x1000000; i *= 0x100)
            value += stream.read() * i;
        return value;
    }
}