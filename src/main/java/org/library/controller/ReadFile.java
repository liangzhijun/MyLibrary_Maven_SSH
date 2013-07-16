package org.library.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class ReadFile
{
	public static void main(String[] args)
	{
		/** ��ȡ�ַ����ļ������ҰѴ��ļ����Ƶ����ش��� */
		/*String fromPath = "D://MyLibrary/upload/ÿ������.txt";
		String toPath = "D://1.txt";
		ReadFile.readAndWriteTxt(fromPath, toPath);
*/
		/** ��ȡ�ֽ����ļ������ҰѴ��ļ����Ƶ����ش��� */
		 String fromPath = "D://MyLibrary/upload/10001/photo.jpg";
		 String toPath = "D://1.jpg";
		 ReadFile.readAndWriteImg(fromPath, toPath);
	}

	/**
	 * FileInputStream ���ڶ�ȡ����ͼ�����֮���ԭʼ�ֽ�����Ҫ��ȡ�ַ������뿼��ʹ�� FileReader��
	 * 
	 * @param fromPath
	 * @param toPath
	 */
	public static void readAndWriteImg(String fromPath, String toPath)
	{

		File file = new File(fromPath);
		try
		{
			FileInputStream is = new FileInputStream(file);
			FileOutputStream os = new FileOutputStream(toPath);
			
			byte[] b = new byte[1024];
			while (is.read(b) != -1)	//ָ��ԭʼ�ֽ�����С
			{
				os.write(b);
			}
			os.close();
			is.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * FileReader ���ڶ�ȡ�ַ�����Ҫ��ȡԭʼ�ֽ������뿼��ʹ�� FileInputStream��
	 * 
	 * @param fromPath
	 * @param toPath
	 */
	public static void readAndWriteTxt(String fromPath, String toPath)
	{

		File file = new File(fromPath);
		try
		{
			FileReader ir = new FileReader(file);
//			BufferedReader br = new BufferedReader(ir);
			FileWriter ow = new FileWriter(toPath);
//			BufferedWriter bw = new BufferedWriter(ow);
			
			char[] b = new char[1024];
			while (ir.read(b) != -1)
			{
				ow.write(b);
			}
			ow.close();
			ir.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * ���ı��������������̨��ҳ�棬�������jspҳ���ϵ��õķ���
	 */
	public static String getTxt(HttpServletRequest request)
	{

		String fromPath = "D://MyLibrary/upload/ÿ������.txt";
		StringBuffer sb = new StringBuffer();
		File file = new File(fromPath);
		
		try
		{
			FileReader ir = new FileReader(file);
			
			BufferedReader br = new BufferedReader(ir);
			
			String output;
			while ((output = br.readLine()) != null)
			{
				sb.append(output + "\n");
				System.out.println(output);
			}
			ir.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return sb.toString();
	}

}