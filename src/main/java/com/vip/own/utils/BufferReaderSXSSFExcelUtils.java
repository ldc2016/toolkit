package com.vip.own.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vip.own.entity.MothBillsEntity;

/**
 * Created by dacheng.liu on 2017/5/26.
 */
public class BufferReaderSXSSFExcelUtils {
    public static void main(String[] args) throws IOException {
        String srcFile = "D:\\新客账单数据0606.xlsx";
        String dstFile = "D:\\老库新客账单数据0626_records.xlsx";
        String txtFilePath = "D:\\vipshop_finance_month_bills06.csv";
        
        InputStream is = new FileInputStream(srcFile);
        OutputStream os = new FileOutputStream(dstFile);
       
        final BufferedReader bufferedReader = FileReaderUtils.initFileReader(txtFilePath);
        final MothBillsEntity mothBillsEntity = new MothBillsEntity();
        
        //定义内存中存储的行数
        int memoryRowAccess = 100;//内存中缓存记录行数
        //生成3个SHEET
        int sheetNum=3;

        XSSFWorkbook dstWorkBook = new XSSFWorkbook(is);
        SXSSFWorkbook workBook = new SXSSFWorkbook(dstWorkBook,memoryRowAccess);
        
        for(int i=0;i <sheetNum;i++){
        	SXSSFSheet sh = workBook.createSheet();
        	CellStyle cellStyle = workBook.createCellStyle();
        	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        	
            //每个SHEET有60000行
            for(int rownum = 0; rownum < 60000; rownum++) {
            	SXSSFRow row = sh.createRow(rownum);
                
                if(!getMonthBillsEntity(bufferedReader,mothBillsEntity)){
                	break;
                }
                makeExcelSheet(mothBillsEntity,sh,cellStyle,row,false);

                //每当行数达到设置的值就刷新数据到硬盘,以清理内存
                if(rownum % memoryRowAccess == 0){
                   sh.flushRows();
                }
            }
         }
        
        /*写数据到文件中*/
        workBook.write(os);
        os.close();
        
        System.out.println("导出excel文件完毕！");
        
    }

    private static int makeExcelSheet(MothBillsEntity mothBillsEntity, SXSSFSheet sheet, CellStyle cellStyle,Row row,boolean isFenUnit) {

        if (mothBillsEntity == null) {
            return 0;
        }

        if (cellStyle != null) {
            row.setRowStyle(cellStyle);
        }

        SXSSFCell cell0 = (SXSSFCell) row.createCell(0);
        if (mothBillsEntity.getCreateUser()!= null) {
            cell0.setCellValue(mothBillsEntity.getCreateUser());
            cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell1 = (SXSSFCell) row.createCell(1);
        if (mothBillsEntity.getCreateTime() != null) {
            cell1.setCellValue(mothBillsEntity.getCreateTime());
            cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell2 = (SXSSFCell) row.createCell(2);
        if (mothBillsEntity.getUpdateUser() != null) {
            cell2.setCellValue(mothBillsEntity.getUpdateUser());
            cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell3 = (SXSSFCell) row.createCell(3);
        if (mothBillsEntity.getUpdateTime() != null) {
            cell3.setCellValue(mothBillsEntity.getUpdateTime());
            cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell4 = (SXSSFCell) row.createCell(4);
        if (mothBillsEntity.getCheckId() != null) {
            cell4.setCellValue(mothBillsEntity.getCheckId());
            cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell5 = (SXSSFCell) row.createCell(5);
        if (mothBillsEntity.getDateTime() != null) {
            cell5.setCellValue(mothBillsEntity.getDateTime());
            cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell6 = (SXSSFCell) row.createCell(6);
        if (mothBillsEntity.getMonthBillNo() != null) {
            cell6.setCellValue(mothBillsEntity.getMonthBillNo());
            cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell7 = (SXSSFCell) row.createCell(7);
        if (mothBillsEntity.getCurrFee() != null) {
        	if(isFenUnit){
        		cell7.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrFee())).toString());
        	}else{
        		cell7.setCellValue(mothBillsEntity.getCurrFee());
        	}
            cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell8 = (SXSSFCell) row.createCell(8);
        if (mothBillsEntity.getCurrNeedPayFee() != null) {
        	if(isFenUnit){
        		cell8.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrNeedPayFee())).toString());
        	}else{
        		cell8.setCellValue(mothBillsEntity.getCurrNeedPayFee());
        	}
            cell8.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell9 = (SXSSFCell) row.createCell(9);
        if (mothBillsEntity.getCurrNeedPayInterest() != null) {
        	if(isFenUnit){
        		cell9.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrNeedPayInterest()),5).toString());
        	}else{
        		cell9.setCellValue(mothBillsEntity.getCurrNeedPayInterest());
        	}
            cell9.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell10 = (SXSSFCell) row.createCell(10);
        if (mothBillsEntity.getCurrNeedPayCapital() != null) {
            if(isFenUnit){
        		cell10.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrNeedPayCapital())).toString());
        	}else{
        		cell10.setCellValue(mothBillsEntity.getCurrNeedPayCapital());
        	}
            cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell11 = (SXSSFCell) row.createCell(11);
        if (mothBillsEntity.getCurrNeedPayTotal() != null) {
        	if(isFenUnit){
        		cell11.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrNeedPayTotal())).toString());
          	}else{
          		cell11.setCellValue(mothBillsEntity.getCurrNeedPayTotal());
          	}
            cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell12 = (SXSSFCell) row.createCell(12);
        if (mothBillsEntity.getCurrInterest() != null) {
            if(isFenUnit){
            	cell12.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrInterest()),5).toString());
          	}else{
          		cell12.setCellValue(mothBillsEntity.getCurrInterest());
          	}
            cell12.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell13 = (SXSSFCell) row.createCell(13);
        if (mothBillsEntity.getCurrCapital() != null) {
        	if(isFenUnit){
        		cell13.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrCapital())).toString());
          	}else{
          		cell13.setCellValue(mothBillsEntity.getCurrCapital());
          	}
            cell13.setCellType(HSSFCell.CELL_TYPE_STRING);
        }
        
        SXSSFCell cell14 = (SXSSFCell) row.createCell(14);
        if (mothBillsEntity.getCurrYesFee() != null) {
            if(isFenUnit){
            	cell14.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrYesFee())).toString());
          	}else{
          		cell14.setCellValue(mothBillsEntity.getCurrYesFee());
          	}
            cell14.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell15 = (SXSSFCell) row.createCell(15);
        if (mothBillsEntity.getCurrYesInterest() != null) {
        	 if(isFenUnit){
        		cell15.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrYesInterest()),5).toString());
           	}else{
           		cell15.setCellValue(mothBillsEntity.getCurrYesInterest());
           	}
            cell15.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell16 = (SXSSFCell) row.createCell(16);
        if (mothBillsEntity.getCurrYesCapital() != null) {
        	    if(isFenUnit){
        		    cell16.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrYesCapital())).toString());
            	}else{
            		cell16.setCellValue(mothBillsEntity.getCurrYesCapital());
            	}
            cell16.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell17 = (SXSSFCell) row.createCell(17);
        if (mothBillsEntity.getCurrYesTotal() != null) {
        	if(isFenUnit){
        		cell17.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrYesTotal())).toString());
        	}else{
        		cell17.setCellValue(mothBillsEntity.getCurrYesTotal());
        	}
            cell17.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell18 = (SXSSFCell) row.createCell(18);
        if (mothBillsEntity.getLastRepayTime() != null) {
            cell18.setCellValue(mothBillsEntity.getLastRepayTime());
            cell18.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell19 = (SXSSFCell) row.createCell(19);
        if (mothBillsEntity.getLateDays() != null) {
            cell19.setCellValue(mothBillsEntity.getLateDays());
            cell19.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell20 = (SXSSFCell) row.createCell(20);
        if (mothBillsEntity.getOverDueStatus() != null) {
            cell20.setCellValue(mothBillsEntity.getOverDueStatus());
            cell20.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell21 = (SXSSFCell) row.createCell(21);
        if (mothBillsEntity.getPayOffStatus() != null) {
            cell21.setCellValue(mothBillsEntity.getPayOffStatus());
            cell21.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell22 = (SXSSFCell) row.createCell(22);
        if (mothBillsEntity.getCurrYesStagingAmount() != null) {
        	if(isFenUnit){
        		cell22.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrYesStagingAmount())).toString());
        	}else{
        		cell22.setCellValue(mothBillsEntity.getCurrYesStagingAmount());
        	}
            cell22.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell23 = (SXSSFCell) row.createCell(23);
        if (mothBillsEntity.getCurrCanStagingAmount() != null) {
        	if(isFenUnit){
        		cell23.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrCanStagingAmount())).toString());
        	}else{
        		cell23.setCellValue(mothBillsEntity.getCurrCanStagingAmount());
        	}
            cell23.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell24 = (SXSSFCell) row.createCell(24);
        if (mothBillsEntity.getCurrNoStagingAmount() != null) {
        	if(isFenUnit){
        		cell24.setCellValue(MoneyUtils.convertIntToBigDecimal(Integer.parseInt(mothBillsEntity.getCurrNoStagingAmount())).toString());
        	}else{
        		cell24.setCellValue(mothBillsEntity.getCurrNoStagingAmount());
        	}
            cell24.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        SXSSFCell cell25 = (SXSSFCell) row.createCell(25);
        if (mothBillsEntity.getUid() != null) {
            cell25.setCellValue(mothBillsEntity.getUid());
            cell25.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        return 1;

    }

    private static boolean getMonthBillsEntity(final BufferedReader bufferedReader,final MothBillsEntity mothBillsEntity) {
        String metaData = FileReaderUtils.getMetaData(bufferedReader);
        if(StringUtils.isNotBlank(metaData)){
            String[] monthBillMetaDatas = metaData.split(",");
            mothBillsEntity.setCreateUser(monthBillMetaDatas[0].replace("\"", ""));
            mothBillsEntity.setCreateTime(monthBillMetaDatas[1].replace("\"", ""));
            mothBillsEntity.setUpdateUser(monthBillMetaDatas[2].replace("\"", ""));
            mothBillsEntity.setUpdateTime(monthBillMetaDatas[3].replace("\"", ""));
            mothBillsEntity.setCheckId(monthBillMetaDatas[4].replace("\"", ""));
            mothBillsEntity.setDateTime(monthBillMetaDatas[5].replace("\"", ""));
            mothBillsEntity.setMonthBillNo(monthBillMetaDatas[6].replace("\"", ""));
            mothBillsEntity.setCurrFee(monthBillMetaDatas[7].replace("\"", ""));
            mothBillsEntity.setCurrNeedPayFee(monthBillMetaDatas[8].replace("\"", ""));
            mothBillsEntity.setCurrNeedPayInterest(monthBillMetaDatas[9].replace("\"", ""));
            mothBillsEntity.setCurrNeedPayCapital(monthBillMetaDatas[10].replace("\"", ""));
            mothBillsEntity.setCurrNeedPayTotal(monthBillMetaDatas[11].replace("\"", ""));
            mothBillsEntity.setCurrInterest(monthBillMetaDatas[12].replace("\"", ""));
            mothBillsEntity.setCurrCapital(monthBillMetaDatas[13].replace("\"", ""));
            mothBillsEntity.setCurrYesFee(monthBillMetaDatas[14].replace("\"", ""));
            mothBillsEntity.setCurrYesInterest(monthBillMetaDatas[15].replace("\"", ""));
            mothBillsEntity.setCurrYesCapital(monthBillMetaDatas[16].replace("\"", ""));
            mothBillsEntity.setCurrYesTotal(monthBillMetaDatas[17].replace("\"", ""));
            mothBillsEntity.setLastRepayTime(monthBillMetaDatas[18].replace("\"", ""));
            mothBillsEntity.setLateDays(monthBillMetaDatas[19].replace("\"", ""));
            mothBillsEntity.setOverDueStatus(monthBillMetaDatas[20].replace("\"", ""));
            mothBillsEntity.setPayOffStatus(monthBillMetaDatas[21].replace("\"", ""));
            mothBillsEntity.setCurrYesStagingAmount(monthBillMetaDatas[22].replace("\"", ""));
            mothBillsEntity.setCurrCanStagingAmount(monthBillMetaDatas[23].replace("\"", ""));
            mothBillsEntity.setCurrNoStagingAmount(monthBillMetaDatas[24].replace("\"", ""));
            mothBillsEntity.setUid(monthBillMetaDatas[25].replace("\"", ""));
            
            metaData = null;                // 方便GC回收
            monthBillMetaDatas = null;      // 方便GC回收
        }else{
        	return false;
        }
        return true;
    }

}

