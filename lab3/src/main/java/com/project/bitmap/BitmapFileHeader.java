package com.project.bitmap;

/*
 * To store general information about the bitmap image file.
 * Not needed after the file is loaded in memory.
 * Size: 14 bytes.
 * */
abstract class BitmapFileHeader {
    short bfType; // Отметка для отличия формата от других (сигнатура формата).
    long bfSize; // Размер файла в байтах.
    // Зарезервированы и должны содержать ноль.
    short bfReserved1;
    short bfReserved2;
    // Положение пиксельных данных относительно
    // начала данной структуры (в байтах).
    long bfOffBits;

    void setFileHeader(short bfType, long bfSize, short bfReserved1,
            short bfReserved2, long bfOffBits) {
        this.bfType = bfType;
        this.bfSize = bfSize;
        this.bfReserved1 = bfReserved1;
        this.bfReserved2 = bfReserved2;
        this.bfOffBits = bfOffBits;
    }
}