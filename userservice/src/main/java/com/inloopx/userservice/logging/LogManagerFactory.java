package com.inloopx.userservice.logging;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

public class LogManagerFactory {

    @Produces
    public Logger getLogManager(InjectionPoint injectionPoint){
       return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
