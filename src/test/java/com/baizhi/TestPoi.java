package com.baizhi;

import com.baizhi.entity.Student;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TestPoi
 * @Discription
 * @Author
 * @Date 2019/12/24 0024 9:54
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPoi {
    @Test
    public void test1() throws IOException {
        //1.先创建Excle表格
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2.创建sheet工作表
        HSSFSheet sheet = workbook.createSheet("aaa");
        //3.创建row行
        HSSFRow row = sheet.createRow(0);
        //4.创建cell单元格
        HSSFCell cell = row.createCell(0);
        //5.向单元当中添加数据
        cell.setCellValue("hehe");
        //6.poi进行导出
        workbook.write(new File("D:/a.xls"));
    }
    @Test
    public void test2() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建时间格式
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        //为格式规定样式
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        //创建单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //将时间格式赋值给单元格样式
        cellStyle.setDataFormat(format);
        HSSFSheet sheet = workbook.createSheet("666");
        //注意进行宽度格式转换，必须给宽度乘上256
        sheet.setColumnWidth(0,20*256);
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);
        workbook.write(new File("D:/a.xls"));
    }
    @Test
    public void test3() throws IOException {
        List<Student> list=new ArrayList<>();
        list.add(new Student("1","小叶",13,new Date()));
        list.add(new Student("2","老李",81,new Date()));
        list.add(new Student("3","小王",18,new Date()));
        String[] s={"编号","姓名","年龄","生日"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置单元格的字体格式
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(Font.COLOR_RED);
        font.setFontName("楷体");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置单元格的时间格式
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);

        HSSFSheet sheet = workbook.createSheet("student");
        sheet.setColumnWidth(3,20*256);
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<s.length;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(s[i]);
        }
        for(int i=0;i<list.size();i++){
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(list.get(i).getId());
            row1.createCell(1).setCellValue(list.get(i).getName());
            row1.createCell(2).setCellValue(list.get(i).getAge());
            HSSFCell cell = row1.createCell(3);
            cell.setCellStyle(cellStyle1);
            cell.setCellValue(list.get(i).getBirthday());
        }
        workbook.write(new File("D:/学生.xls"));
    }
}
