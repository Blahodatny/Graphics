package com.project;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadingHeaderFromBitmapImage {

	public PrintingImage pr; 
	
	public ReadingHeaderFromBitmapImage()
	{ }
	
	public HeaderBitmapImage Reading (BufferedInputStream reader1) throws IOException
	// �����, ���� ������ ���� ����� �������� �� ����� �� �����������
	// �� ����������� ���� ���� HeaderBitmapImage, � ����������� ��� ����������
	{
		HeaderBitmapImage hbi = new HeaderBitmapImage(); 
		
		int line;
		int i = 0;
		short type = 0;
		long size = 0;
		short res1 = 0;
		short res2 = 0;
		long offset = 0;
		long header = 0;
		long width = 0;
		long height = 0;
		short numbPanel = 0;
		short bitCount = 0;
		long compr = 0;
		long sCompIm = 0;
	    long hRes = 0;
	    long vRes = 0;
	    long numbUCol = 0;
	    long numbICol = 0;
		long half=0;

		long temp = 0;
		
		while ((line = reader1.read())!=-1) {  // ���� �� ����� �����
		  i++; // �������� �������� ������� ���� ��������� ����������
		  if (i==1) // ������� ��������� 
		  {
			     temp = 0;
				 temp=reader1.read();
				 type+=(temp*0x100)+line;
				 i++;
		  }
		  if (i==2) // ���� ������� ������� ������� ����� = 2, �� ������� ����� �����
		  {
				  size = readLong(reader1); // � ����� size �������� ��������� ������ ������ readLong
				  i = i+4; // ������ 4 �� ������� �������� ���� � �����, ��� �� ����� ���� size 4 �����
		  }
		  if (i==6) //������� ����������� ���� �1
		  {
				  res1 = readShort(reader1); // � ����� res1 �������� ��������� ������ ������ readShort
				  i = i+2; // ������ 2 �� ������� �������� ���� � �����, ��� �� ����� ���� res1 2 �����
		  }
		  if (i==8) //������� ����������� ���� �2
		  {
			      res2 = readShort(reader1);
			      i = i+2;
		  }
		  if (i==10) //������� �������
		  {
			      offset = readLong(reader1);
				  i = i+4;
		  }
		  
		  if (i==14) //������� ����� ���������
		  {
				  header = readLong(reader1);
				  i = i+4;   
		  }
		  // ������� � 18� �� 22� ������� ������ � ������� ����������
		  if (i==18)
		  {
				  width = readLong(reader1);
				  i = i+4;
				 
				  height = readLong(reader1);
				  i = i+4;
				  
				  half=width;         
				  if((half%2)!=0)     // �������� �� ������ ���������� ������ 2 � ���� �, �� �������� �� �������� �� 1
				  half++;             // ��� ��������� �������� �������� �� ������ ����������
				  half/=2;        
				  				   
				  if((half%4)!=0)      // ���� �� ������� �� 4
				  half=(half/4)*4+4;   // ���������� �������� �������� ������ ����������, ��� ���� ���� ������ 4
		  }
		  if (i==26) //������� ������� ������
		  {
			      numbPanel = readShort(reader1);
				  i = i+2;
		  }
		  if (i==28) //������� ������� ��
		  { 
				  bitCount = readShort(reader1);
				  i = i+2;
		  }
		  if (i==30) //������� ��� ����������
		  {
				  compr = readLong(reader1);
				  i = i+4;
		  } 
		  if (i==34) //������� ����� ����������� ����������
		  {
				  sCompIm = readLong(reader1);
				  i = i+4;
		  }
		  if (i==38) // ������������� �������� ��������
		  {
				  hRes = readLong(reader1);
				  i = i+4;
		  }
		  if (i==42) // ����������� �������� ��������
		  {
				  vRes = readLong(reader1);
				  i = i+4;
		  }
		  if (i==46) // ������� ������� ������
		  {
				  numbUCol = readLong(reader1);
				  i = i+4;
		  }
		  if (i==50) // ������� �������� �������
		  {
				  numbICol= readLong(reader1);
				  i = i+4;
		  }
		  
		  // �������� �� ������ �������� � ��'��� 
		  hbi.setValues(type, size, res1, res2, offset, header, width, 
				  height, numbPanel, bitCount, compr, sCompIm, hRes,
				  vRes, numbUCol, numbICol, half); 
		  		  
		  // �������� ���� � ������, �� ����������� �����
		  if (i==offset)
		  {
			  reader1.mark(1);
			  break;
		  }
		}
		// ��������� �� �� ���� ����� ����� ���������� �����
		reader1.reset();

		// �������� � ������� ���� ������� ����������, � ��� ����������� ������ �����
		BufferedOutputStream writer = new BufferedOutputStream (new FileOutputStream("pixels.txt")); 		while ((line = reader1.read())!=-1) {
			  writer.write(line);
		}
		writer.close();
		this.pr = new PrintingImage (hbi);
		
		return hbi;
	}

	// ����� ��� ���������� ������� ���� ������� 2 ����� � ������ ������ little-endian
	// �� �������� � ��������� ������� ��������
	private short readShort(BufferedInputStream reader1) throws IOException
	{
		 long temp = 0;
		 short valueToreturn = 0;
		 for(long j=0x1;j<=0x1000;j*=0x100) // ���� �� 1 �� 8 � ������ j*4 - �������� ������� �������� ����� = 2
		 {
		   	temp=reader1.read(); 
		   	valueToreturn+=(temp*j); // ������ �� ��������� ����� �������� ������ ������� ���������� � 10-�� ������ ��������  
		 }
		return valueToreturn;
	}
	
	// ����� ��� ���������� ������� ���� ������� 4 ����� � ������ ������ little-endian
	// �� �������� � ��������� ������� ��������
	private long readLong(BufferedInputStream reader1) throws IOException
	{
		 long temp = 0;
		 long valueToreturn = 0;
		 for(long j=0x1;j<=0x1000000;j*=0x100) // ���� �� 1 �� 64 � ������ j*4 - �������� ������� �������� ����� = 4
		 {
		   	temp=reader1.read();
		   	valueToreturn+=(temp*j); // ������ �� ��������� ����� �������� ������ ������� ���������� � 10-�� ������ ��������
		 }
		return valueToreturn;
	}
	
}
