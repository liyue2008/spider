package com.github.liyue2008.spider.core.service;



import com.github.liyue2008.spider.core.utils.AlphanumComparator;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * Created by liyue on 2017/3/30.
 */
@Component


public class CsvFileService {
    private final Logger logger = LoggerFactory.getLogger(CsvFileService.class);
    public int appendToCsv(List<String[]> resultList,String csvFilePath,String [] columnTitles) throws IOException{
        boolean isWriteColumnHeader = false;
        logger.info("Save {} medias to file [{}]...",resultList.size(),csvFilePath);
        File csvFile = new File(csvFilePath);
        if(!csvFile.exists()){
            isWriteColumnHeader = true;
        }

//        FileWriter fw = new FileWriter(csvFile, true);
//        BufferedWriter bw = new BufferedWriter(fw);


        PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(csvFile,true),"GBK"),true);

        if(isWriteColumnHeader){
            out.println("\"" + String.join("\",\"",columnTitles) + "\"");
        }
        for(String[] m:resultList){
            out.println("\"" + String.join("\",\"",m) + "\"");
        }
        out.close();
        logger.info("Save {} medias to file [{}] success!",resultList.size(),csvFilePath);
        return 0;
    }


    public void sortCsv(String csvFilePath,int sortCol) throws IOException{
//        Scanner s = new Scanner(new File(csvFilePath)).useDelimiter("\n");
        List<List<String>> list = new ArrayList<>();

        List<String> lineList = FileUtils.readLines(new File(csvFilePath),"GBK");
        for(String line:lineList){
            List<String> listOfLine = Arrays.asList(line.split("\\s*,\\s*"));
            list.add(listOfLine);
        }


        if(list.size() > 0){
            List<String> titleRow = list.remove(0);

            Comparator<List<String>> comp = new AlphanumComparator(sortCol);
            Collections.sort(list, comp);
            list.add(0,titleRow);
        }
        File csvFile = new File(csvFilePath);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(csvFile,false),"GBK"),true);

        for(List<String> m:list){
            out.println(String.join(",",m));
        }
        out.close();
    }

    public static  void main(String [] args) throws IOException {
        new CsvFileService().sortCsv("/Users/liyue/Workspace/dangdang/spider/results/job_10/2017_05_11_16_43_02.csv",0);
    }
}
