/**
 * 
 */
package com.example.demo.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author semon
 * @created 2018年10月30日
 * @since 
 * 
 */
@Configuration
public class InitConfig implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Path rootDir = Paths.get("external/");
		Path imgDir = rootDir.resolve("/img");
		try {
			Files.createDirectories(imgDir);
		} catch (IOException e) {
			throw new RuntimeException("必要文件夹创建失败", e);
		}
	}
	
}
