//package com.inloopx.userservice.repository;
//
//import com.querydsl.codegen.ClassPathUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.Singleton;
//import javax.ejb.Startup;
//import java.io.IOException;
//
//@Singleton
//@Startup
//public class QueryDslMultithreadingIssueWorkaround {
//    private static final Logger LOG = LoggerFactory.getLogger(QueryDslMultithreadingIssueWorkaround.class);
//
//
//    private static final String DEFAULT_PACKAGE = "com.inloopx.userservice";
//
//    @PostConstruct
//    public void init() {
//        try {
//            LOG.info("start scanning queryDsl entities in package: " + DEFAULT_PACKAGE);
//            ClassPathUtils.scanPackage(Thread.currentThread().getContextClassLoader(), DEFAULT_PACKAGE);
//            LOG.info("end scanning queryDsl entities in package: " + DEFAULT_PACKAGE);
//        } catch (IOException e) {
//            LOG.error(e.getMessage(), e);
//        }
//    }
//}
