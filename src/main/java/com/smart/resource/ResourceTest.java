package com.smart.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by XingWE on 2017/9/8.
 */
public class ResourceTest {
    public static void readAndWrite() {
        String path = "D:\\myIdeaSpace\\mySpring4.x\\chapter4\\file1.txt";
        WritableResource writableResource = new PathResource(path);

        Resource classPathResource = new ClassPathResource("config/file2.txt");

        try {
            OutputStream outputStream = writableResource.getOutputStream();
            outputStream.write("test....".getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream inputStream = writableResource.getInputStream();
//            InputStream inputStream1 = classPathResource.getInputStream();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            int i;
            while ((i = inputStream.read()) != -1) {
                outputStream.write(i);
            }

            System.out.println(outputStream.toString());

            System.out.println(writableResource.getFilename());
            System.out.println(classPathResource.getFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readAsString(){
        WritableResource resource = new PathResource("D:\\myIdeaSpace\\mySpring4.x\\chapter4\\chapter4\\src\\main\\resources\\config\\file2.txt");

        try {
            OutputStream outputStream = resource.getOutputStream();
            outputStream.write("testReadAsString........".getBytes());
            outputStream.close();

            Resource resource1 = new ClassPathResource("config/file2.txt");
            EncodedResource encodedResource = new EncodedResource(resource1,"utf-8");
            String content = FileCopyUtils.copyToString(encodedResource.getReader());
            System.out.println("resource.isOpen() = " + resource.isOpen());
            System.out.println("resource.isWritable() =" + resource.isWritable());
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readAndWrite();
        System.out.println("========================================");
        readAsString();
    }
}
