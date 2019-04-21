package com.owen.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 1. 读取配置文件
 * 2. 遍历文件夹，获取文件名
 * 3. 如果文件名包含在配置文件中，则进行重命名
 */
@Slf4j
public class RenameFile {

    private String configFilePath = "file_name_pattern.txt";
    private List<String> namePatters = new ArrayList<String>();

    public RenameFile(){
        this.initConfig();
    }

    public void renameFiles(File targetDir){
        if(targetDir.isFile()){
            String fileName = targetDir.getName();
            String targetName = fileName;
            for(String pt : this.namePatters){
                int index = targetName.indexOf(pt);
                if(index<0){
                    continue;
                }
                if(0==index ){
                    targetName = targetName.substring(pt.length());
                }else{
                    targetName = targetName.substring(0,index)+targetName.substring(index+pt.length());
                }
            }

            //remote the . which is at the beginning of filename
            while(targetName.startsWith(".")){
                targetName = targetName.substring(1);
            }
            //replace duplicate .. with .
            targetName = targetName.replaceAll("\\.\\.",".");

            rename(targetDir,targetName);
        }else{
            File[] subFiles = targetDir.listFiles();
            for(File f: subFiles){
                renameFiles(f);
            }
        }
    }

    private void rename(File sourceFile,String newName){
        String parent = sourceFile.getParent();
        String newFileFuleName = parent + File.separator + newName;
        boolean result = sourceFile.renameTo(new File(newFileFuleName));
        if(!result){
            log.error("File rename fail: from ["+sourceFile+"] to ["+newFileFuleName+"]");
        }
    }

    private synchronized void  initConfig(){
        File config =  new File( getClass().getClassLoader().getResource(configFilePath).getFile() );
        try {
            LineIterator iterator = FileUtils.lineIterator(config);
            while(iterator.hasNext()){
                String line = iterator.nextLine().trim();
                if(line.length()>0){
                    namePatters.add(line);
                }
            }
            log.info(namePatters.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
