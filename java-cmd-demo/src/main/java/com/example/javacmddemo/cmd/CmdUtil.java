package com.example.javacmddemo.cmd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * @Author: LiXinchao
 * @Date: 18-11-16 下午1:34
 */
@Component
public class CmdUtil implements CommandLineRunner {

    private  static String execCmd(String cmd){
        try {
            String[] cmdA = { "/bin/sh", "-c", cmd };
            Process process = Runtime.getRuntime().exec(cmdA);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Start Test Execute System Cmd ! \n");

        String s = execCmd("jps");

        System.out.println("Test End , The Result is >>> \n \n" + s);

    }
}
