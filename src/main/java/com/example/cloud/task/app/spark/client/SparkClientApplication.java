package com.example.cloud.task.app.spark.client;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.spark.deploy.SparkSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

@SpringBootApplication
@EnableTask
@EnableConfigurationProperties(SparkClientTaskProperties.class)
public class SparkClientApplication {

    @Autowired
    private SparkClientTaskProperties config;

	public static void main(String[] args) {
		SpringApplication.run(SparkClientApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commdlineRunner() {
		return args -> {
//			System.out.println("Hello");
//			String[] ss = {"--class", "org.apache.spark.examples.SparkPi",
//					"--master", "local[8]",
//					"/opt/spark/examples/jars/spark-examples_2.11-2.1.1.jar",
//					"10"};
//			System.out.println("=====");
//			SparkSubmit.main(ss);
//			System.out.println("=====");
//			System.out.println(config);
            ArrayList<String> argList = new ArrayList<>();
            if (StringUtils.hasText(config.getAppName())) {
                argList.add("--name");
                argList.add(config.getAppName());
            }
            argList.add("--class");
            argList.add(config.getAppClass());
            argList.add("--master");
            argList.add(config.getMaster());
            argList.add("--deploy-mode");
            argList.add("client");

            argList.add(config.getAppJar());

            if (StringUtils.hasText(config.getResourceFiles())) {
                argList.add("--files");
                argList.add(config.getResourceFiles());
            }

            if (StringUtils.hasText(config.getResourceArchives())) {
                argList.add("--jars");
                argList.add(config.getResourceArchives());
            }

            argList.addAll(Arrays.asList(config.getAppArgs()));

            try {
                SparkSubmit.main(argList.toArray(new String[argList.size()]));
            } catch (Throwable t) {
//               logger.error("Spark Application failed: " + t.getMessage(), t);
                throw new RuntimeException("Spark Application failed", t);
            }

		};
	}
}
