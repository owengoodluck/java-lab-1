package com.owen;

import com.owen.utils.RenameFile;
import org.junit.Test;

import java.io.File;


public class RenameFileTest {
    @Test
    public void test(){
        RenameFile rf = new RenameFile();
//        rf.renameFiles(new File("C:\\Movie\\a.已拷贝\\生化危机"));
        rf.renameFiles(new File("G:\\Movie"));
    }
}
