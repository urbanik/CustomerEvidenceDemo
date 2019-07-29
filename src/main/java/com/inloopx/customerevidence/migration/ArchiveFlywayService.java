package com.inloopx.customerevidence.migration;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

@Singleton
@Startup
public class ArchiveFlywayService extends FlywayService {

//    inject datasource
    @Resource(lookup = "jdbc/evidence")
    private DataSource dataSource;


    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

}
