package com.vip.own.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MapperByteBufferFileUtils {
	
	public static void main(String[] args) throws Exception {  
        ByteBuffer byteBuf = ByteBuffer.allocate(14*1024*1024);  
        byte[] buffer = new byte[14*1024*1024];
        FileInputStream fis = new FileInputStream("D:\\vipshop_finance_month_bills.csv");  
        FileOutputStream fos = new FileOutputStream("D:\\vipshop_finance_month_bills_0606.csv");  
        
        FileChannel fc = fis.getChannel();  
        long timeStar = System.currentTimeMillis();// 得到当前的时间  
//        fc.read(byteBuf);// 1 读取  
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());  
        
        System.out.println(fc.size()/1024); 
        
        long timeEnd = System.currentTimeMillis();// 得到当前的时间  
        
        System.out.println("Read time :" + (timeEnd - timeStar) + "ms");  
        timeStar = System.currentTimeMillis();  
//        fos.write(buffer);//2.写入  
        mbb.flip();  
        timeEnd = System.currentTimeMillis();  
        
        System.out.println("Write time :" + (timeEnd - timeStar) + "ms");  
        fos.flush();  
        fc.close();  
        fis.close();  
    }  

}
