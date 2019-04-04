package com.project.header;

import java.io.BufferedInputStream;
import java.io.IOException;

public class BitmapImageHeader extends BitmapV5InfoHeader {
    private long halfOfWidth;

    public BitmapImageHeader(BufferedInputStream stream) {
        try {
            var line = stream.read();
            bfType += stream.read() * 0x100 + line;
            bfSize = readLong(stream);
            bfReserved1 = readShort(stream);
            bfReserved2 = readShort(stream);
            bfOffBits = readLong(stream);
            headerSize = readLong(stream);
            imageWidth = readLong(stream);
            imageHeight = readLong(stream);
            planes = readShort(stream);
            bitCount = readShort(stream);
            compression = readLong(stream);
            imageSize = readLong(stream);
            XPixelsPerMeter = readLong(stream);
            YPixelsPerMeter = readLong(stream);
            CirUsed = readLong(stream);
            CirImportant = readLong(stream);
            stream.mark(1);

            if (imageWidth % 2 != 0)  // перевірка чи ширина зображення кратна 2 і якщо ні, то збільшуємо це значення на 1
                halfOfWidth = imageWidth + 1;  // щоб доповнити значення половини від ширини зображення
            halfOfWidth /= 2;
            if (halfOfWidth % 4 != 0)  // якщо не ділиться на 4
                halfOfWidth = halfOfWidth / 4 * 4 + 4;  // доповнюємо значення половини ширини зображення, щоб вона була кратна 4
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    // метод для коректного читання полів розміром 2 байти у форматі запису little-endian
    private short readShort(BufferedInputStream stream) throws IOException {
        short value = 0;
        for (long i = 0x1; i <= 0x1000; i *= 0x100)
            value += stream.read() * i;
        return value;
    }

    // метод для коректного читання полів розміром 4 байти у форматі запису little-endian
    private long readLong(BufferedInputStream stream) throws IOException {
        long value = 0;
        for (long i = 0x1; i <= 0x1000000; i *= 0x100)
            value += stream.read() * i;
        return value;
    }
}