package com.project;

public class HeaderBitmapImage {
    private short type;  // тип зображення або сигнатура
    private long size;  // розмір файлу
    private short reserveField1;  // резервоване поле №1
    private short reserveField2;  // резервоване поле №2
    private long offset;    // зміщення
    private long sizeOfHeader;  // розмір заголовку
    private long width;  // ширина
    private long height;  // висота
    private short numberOfColorPlanes; // площини
    private short bitsCount;  // кількість біт
    private long compression;  // тип ущільнення
    private long sizeOfCompImage;  // розмір ущільненого зображення
    private long horizontalResolution; // горизонтальна роздільна здатність
    private long verticalResolution;  // вертикальна роздільна здатність
    private long numbOfUsedColors;  // кількість кольорів палітри
    private long numbOfImportantColors; // кількість важливих кольорів
    private long halfOfWidth; // половина від ширини зображення (не міститься в заголовку)

    public HeaderBitmapImage () {} // конструктор

    public void setType (short type) // метод "сеттер" для безпечного встановлення значення поля type
    {
        this.type = type;
    }

    public short getType () // метод "геттер" для безпечного отримання значення, що зберігається в полі type
    {
        return type;
    }

    public void setSize (long size)
    {
        this.size = size;
    }

    public long getSize ()
    {
        return size;
    }

    public void setReserveField1 (short reserveField1)
    {
        this.reserveField1 = reserveField1;
    }

    public short getReserveField1 ()
    {
        return reserveField1;
    }

    public void setReserveField2 (short reserveField2)
    {
        this.reserveField2 = reserveField2;
    }

    public short getReserveField2 ()
    {
        return reserveField2;
    }

    public void setOffset (long offset)
    {
        this.offset = offset;
    }

    public long getOffset ()
    {
        return offset;
    }

    public void setSizeOfHeader (long sizeOfHeader)
    {
        this.sizeOfHeader = sizeOfHeader;
    }

    public long getSizeOfHeader ()
    {
        return sizeOfHeader;
    }

    public void setWidth (long width)
    {
        this.width = width;
    }

    public long getWidth ()
    {
        return width;
    }

    public void setHeight (long height)
    {
        this.height = height;
    }

    public long getHeight ()
    {
        return height;
    }

    public void setNumberOfColorPlanes (short numberOfColorPlanes)
    {
        this.numberOfColorPlanes = numberOfColorPlanes;
    }

    public short getNumberOfColorPlanes ()
    {
        return numberOfColorPlanes;
    }

    public void setBitsCount (short bitsCount)
    {
        this.bitsCount = bitsCount;
    }

    public short getBitsCount ()
    {
        return bitsCount;
    }

    public void setCompression (long compression)
    {
        this.compression = compression;
    }

    public long getCompression ()
    {
        return compression;
    }

    public void setSizeOfCompImage (long sizeOfCompImage)
    {
        this.sizeOfCompImage = sizeOfCompImage;
    }

    public long getSizeOfCompImage ()
    {
        return sizeOfCompImage;
    }

    public void setHorizontalResolution (long horizontalResolution)
    {
        this.horizontalResolution = horizontalResolution;
    }

    public long getHorizontalResolution ()
    {
        return horizontalResolution;
    }

    public void setVerticalResolution (long verticalResolution)
    {
        this.verticalResolution = verticalResolution;
    }

    public long getVerticalResolution ()
    {
        return verticalResolution;
    }

    public void setNumbOfUsedColors (long numbOfUsedColors)
    {
        this.numbOfUsedColors = numbOfUsedColors;
    }

    public long getNumbOfUsedColors ()
    {
        return numbOfUsedColors;
    }

    public void setNumbOfImportantColors (long numbOfImportantColors)
    {
        this.numbOfImportantColors = numbOfImportantColors;
    }

    public long getNumbOfImportantColors ()
    {
        return numbOfImportantColors;
    }

    public void setHalfOfWidth (long halfOfWidth)
    {
        this.halfOfWidth = halfOfWidth;
    }

    public long getHalfOfWidth ()
    {
        return halfOfWidth;
    }

    public void setValues (short type, long size, short resF1, short resF2, long
            offs,
                           long sHeader, long w, long h, short nColPan, short bCount, long compr,
                           long sComp,
                           long hRes, long vRes, long nUsCol, long nImpCol, long half )
    // метод для встановлення початкових значень полів класу HeaderBitmapImage
    {
        setType(type);
        setSize(size);
        setReserveField1(resF1);
        setReserveField2(resF2);
        setOffset(offs);
        setSizeOfHeader(sHeader);
        setWidth(w);
        setHeight(h);
        setNumberOfColorPlanes(nColPan);
        setBitsCount(bCount);
        setCompression(compr);
        setSizeOfCompImage(sComp);
        setHorizontalResolution(hRes);
        setVerticalResolution(vRes);
        setNumbOfUsedColors(nUsCol);
        setNumbOfImportantColors(nImpCol);
        setHalfOfWidth(half);
    }
}
