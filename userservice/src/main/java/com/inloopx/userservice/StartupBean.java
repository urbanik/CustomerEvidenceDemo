package com.inloopx.userservice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.logging.Logger;

@Singleton
@Startup
public class StartupBean {

    @Inject
    Logger LOG;

    @Resource(lookup = "java:app/AppName")
    private String moduleName;



    private final String STARTUP_MESSAGE = "\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="
            + "\n"
            + "\n"
            + "\n"

            + "{moduleName} was successfully deployed!"
            + "\n"
            + "\n"
            + "\n"
            + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";

    private final String SHUTDOWN_MESSAGE = "\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="
            + "\n"
            + "\n"
            + "\n"

            + "{moduleName} was successfully destroyed!"
            + "\n"
            + "\n"
            + "\n"
            + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";


    @PostConstruct
    private void startup() {
        LOG.info(STARTUP_MESSAGE.replace("{moduleName}", moduleName));
        LOG.info("Server timezone is: " + ZonedDateTime.now());
    }

    @PreDestroy
    private void shutdown() {
        LOG.info(SHUTDOWN_MESSAGE.replace("{moduleName}", moduleName));
    }

}