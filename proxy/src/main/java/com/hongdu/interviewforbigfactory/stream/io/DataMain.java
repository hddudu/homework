package com.hongdu.interviewforbigfactory.stream.io;

import java.io.*;

/**
 * @ClassName DataMain
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/17 14:51
 * @Version 1.0
 */
public class DataMain {

    public static void main(String[] args) throws IOException {
        int cityIdA = 1;
        String cityNameA = "Green Lake City";
        int cityPopulationA = 500000;
        float cityTempA = 15.50f;

        int cityIdB = 2;
        String cityNameB = "Salt Lake City";
        int cityPopulationB = 250000;
        float cityTempB = 10.45f;
        File dir = new File("G:/Test");
        dir.mkdirs();
        // Create FileOutputStream write to file.
        FileOutputStream fos = new FileOutputStream("G:/Test/cities.txt");
        // Create DataOutputStream object wrap 'fos'.
        // The data write to 'dos' will be pushed to 'fos'.
        DataOutputStream dos = new DataOutputStream(fos);
        DataInputStream dis = new DataInputStream(new FileInputStream("G:/Test/cities.txt"));
//        System.out.println(dis.readUTF());//一般不用，一般会出现EOFException
        System.out.println(dis.readUnsignedShort());
        //
        // Write data.
        //原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java/java-io-tutorial-binary-streams.html

//        dos.writeInt(cityIdA);
//        dos.writeUTF(cityNameA);
//        dos.writeInt(cityPopulationA);
//        dos.writeFloat(cityTempA);
//
//        dos.writeInt(cityIdB);
//        dos.writeUTF(cityNameB);
//        dos.writeInt(cityPopulationB);
//        dos.writeFloat(cityTempB);
//
        dos.flush();
        dos.close();
    }
}
