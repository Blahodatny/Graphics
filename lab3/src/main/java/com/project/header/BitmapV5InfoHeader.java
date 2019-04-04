package com.project.header;

/*
 * A bitmap image file loaded into memory becomes a DIB data structure.
 * DIB - Device Independent Bitmap.
 * */
abstract class BitmapV5InfoHeader extends BitmapFileHeader {
    long headerSize; // Размер данной структуры в байтах.
    long imageWidth; // Ширина растра в пикселях.
    // Целое число со знаком, содержащее два параметра: высота растра в пикселях
    // (абсолютное значение числа) и порядок следования строк в двумерных массивах
    // (знак числа).
    long imageHeight;
    short planes;
    short bitCount; // Количество бит на пиксель.
    long compression; // Указывает на способ хранения пикселей.
    long imageSize; // Размер пиксельных данных в байтах.
    // Количество пикселей на метр по горизонтали и вертикали.
    long XPixelsPerMeter;
    long YPixelsPerMeter;
    // CIr - colors in color table
    long CirUsed; // Размер таблицы цветов в ячейках.
    // Количество ячеек от начала таблицы цветов до последней используемой
    // (включая её саму).
    long CirImportant;
}