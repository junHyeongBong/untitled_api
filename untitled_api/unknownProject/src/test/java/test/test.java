package test;

import org.junit.jupiter.api.Test;

import java.io.File;

public class test {

    @Test
    public void test234(){

        File file = new File("/Users/bongjunhyeong/Desktop/코딩관련/test");

        if(!file.exists()){
            file.mkdirs();
        }

        System.out.println("test");

    }





}
