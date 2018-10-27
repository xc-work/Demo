package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//转换main函数参数
	public static Map<String, String> transformMainFunctionArgs(String[] args){
		Map<String, String> argsMap = new HashMap<>();
		for (int i = 0; i < args.length; i++) {
			String key = args[i];
			String value = (i + 1) >= args.length ? "" : args[i + 1];
			if (key.startsWith("-") && !value.startsWith("-")) {
				argsMap.put(key.substring(1).trim(), value);
				i++;
			} else if (key.startsWith("-")) {
				argsMap.put(key.substring(1).trim(), "");
			}
		}
		return argsMap;
	}

}
