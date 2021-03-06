package com.example.cloud.task.app.spark.client;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties to be used for cluster submission. These are in addition to the ones
 * defined in the common one.
 *
 * @author Thomas Risberg
 * 
 * just copy from Thomas Risberg
 */
@ConfigurationProperties("spark")
public class SparkClientTaskProperties {

    /**
     * The master setting to be used (local, local[N] or local[*]).
     */
    private String master = "local";

    /**
     * The name to use for the Spark application submission.
     */
    @Value("${spring.application.name:sparkapp-task}")
    private String appName;

    /**
     * The main class for the Spark application.
     */
    private String appClass;

    /**
     * The path to a bundled jar that includes your application and its dependencies, excluding any Spark dependencies.
     */
    private String appJar;

    /**
     * The arguments for the Spark application.
     */
    private String[] appArgs = new String[]{};

    /**
     * A comma separated list of files to be included with the application submission.
     */
    private String resourceFiles;

    /**
     * A comma separated list of archive files to be included with the app submission.
     */
    private String resourceArchives;

    /**
     * The memory setting to be used for each executor.
     */
    private String executorMemory = "1024M";

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @NotNull
    public String getAppClass() {
        return appClass;
    }

    public void setAppClass(String appClass) {
        this.appClass = appClass;
    }

    @NotNull
    public String getAppJar() {
        return appJar;
    }

    public void setAppJar(String appJar) {
        this.appJar = appJar;
    }

    public String[] getAppArgs() {
        return appArgs;
    }

    public void setAppArgs(String[] appArgs) {
        this.appArgs = appArgs;
    }

    public String getResourceFiles() {
        return resourceFiles;
    }

    public void setResourceFiles(String resourceFiles) {
        this.resourceFiles = resourceFiles;
    }

    public String getResourceArchives() {
        return resourceArchives;
    }

    public void setResourceArchives(String resourceArchives) {
        this.resourceArchives = resourceArchives;
    }

    public String getExecutorMemory() {
        return executorMemory;
    }

    public void setExecutorMemory(String executorMemory) {
        this.executorMemory = executorMemory;
    }
}