package com.example.paoche;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OtherTest {

    @Test
    public void test() throws  Exception{
        String path = System.getProperty("user.dir");
        File file =  new File(path+"\\src\\main\\resources\\static\\images\\403.png");
        File file2 = new File(path+"\\src\\main\\resources\\static\\images\\555.png");
        InputStream is = new FileInputStream(file);
        OutputStream os = new FileOutputStream(file2);
        byte[] bytes = new byte[1024];
        int read = is.read(bytes);
        while(read != -1){
            os.write(bytes,0,read);
            read = is.read(bytes);
        }
    }
}
