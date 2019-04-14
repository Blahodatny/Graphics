package com.project;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ReadingImageFromFile {
    
	public static PrintingImage pr;
	
	// ����� ��� ������� ����� � ����������� �� ��������� ���������� ��� �������� ����, ���� 
	// ������ �� ���� ����� ����� � ����������� � ������ BMP
	public static void loadBitmapImage(String filename) throws IOException
	{
		int line;                                         
		BufferedInputStream reader = new BufferedInputStream (new FileInputStream(filename)); // ���� ��� �������
		BufferedOutputStream writer = new BufferedOutputStream (new FileOutputStream("primer_bmp.txt")); // ���� ��� ������ �������� ��������� ��� ����� � ASCII �����
		while ((line = reader.read())!=-1) {
		  writer.write(line);
		}
		reader.close();
		writer.close();
		
		BufferedInputStream reader1 = new BufferedInputStream (new FileInputStream("primer_bmp.txt")); // ���� 
		ReadingHeaderFromBitmapImage reading = new ReadingHeaderFromBitmapImage(); // ��������� ����� ���� ReadingHeaderFromBitmapImage
		HeaderBitmapImage hbi = new HeaderBitmapImage(); // �������� ��'���� ���� HeaderBitmapImage
		hbi = reading.Reading(reader1); // � ��'��� ���� HeaderBitmapImage �������� ��������� ������ ������ ������� ��������� �����
		pr = reading.pr;
		
	    // ���� ��������� ������� ���������� �� �������
		System.out.println("type = "+hbi.getType());
		System.out.println("size = "+hbi.getSize());
		System.out.println("reserve field 1 = "+hbi.getReserveField1());
		System.out.println("reserve field 2 = "+hbi.getReserveField2());
		System.out.println("offset = "+hbi.getOffset());
		System.out.println("size of header = "+hbi.getSizeOfHeader());
		System.out.println("width = "+hbi.getWidth());
		System.out.println("height = "+hbi.getHeight());
		System.out.println("number of planes = "+hbi.getNumberOfColorPlanes());
		System.out.println("number of bits = "+hbi.getBitsCount());
		System.out.println("type of compression = "+hbi.getCompression());
		System.out.println("size of image after compression = "+hbi.getSizeOfCompImage());
		System.out.println("horizontal resolution = "+hbi.getHorizontalResolution());
		System.out.println("vertical resolution = "+hbi.getVerticalResolution());
		System.out.println("number of used colors = "+hbi.getNumbOfUsedColors());
		System.out.println("number of important colors = "+hbi.getNumbOfImportantColors());
		
		System.out.println("half of width = "+hbi.getHalfOfWidth());
		
		reader1.close();
	}
	
	public static void main(String[] args) throws IOException {
		//loadBitmapImage("D:/Eclipse_workspace/JavaFX_Lab2_for_Comp_Graphics_Labs/sources/1.bmp");
	}

}
