package com.project;

public class HeaderBitmapImage {

    private short type;           // ��� ���������� ��� ���������
    private long size;            // ����� �����
    private short reserveField1;  // ����������� ���� �1
    private short reserveField2;  // ����������� ���� �2
    private long offset;          // �������
    private long sizeOfHeader;    // ����� ���������
    private long width;           // ������
    private long height;          // ������
    private short numberOfColorPlanes; // �������
    private short bitsCount;           // ������� ��
    private long compression;          // ��� ����������
    private long sizeOfCompImage;      // ����� ����������� ����������
    private long horizontalResolution; // ������������� �������� ��������
    private long verticalResolution;   // ����������� �������� ��������
    private long numbOfUsedColors;     // ������� ������� ������
    private long numbOfImportantColors; // ������� �������� �������
    
    private long halfOfWidth;  // �������� �� ������ ���������� (�� �������� � ���������)
    

    public HeaderBitmapImage () {}  // �����������
		 
    public void setType (short type) // ����� "������" ��� ���������� ������������ �������� ���� type
    {
       this.type = type; 
    }

    public short getType () // ����� "������" ��� ���������� ��������� ��������, �� ���������� � ��� type
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
        
    public void setValues (short type, long size, short resF1, short resF2, long offs, 
    		long sHeader, long w, long h, short nColPan, short bCount, long compr, long sComp, 
    		long hRes, long vRes, long nUsCol, long nImpCol, long half )
    // ����� ��� ������������ ���������� ������� ���� ����� HeaderBitmapImage 
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
