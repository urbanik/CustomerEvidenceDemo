package com.inloopx.userservice.migration;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationVersion;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.logging.Logger;

@TransactionManagement(value = TransactionManagementType.BEAN)
public abstract class FlywayService {

    @Inject
    Logger LOG;

    private Flyway flyway;

    private static final String VERSION_UNKNOWN_MESSAGE = "Cannot resolve db version!";

    public static final String NO_DB_VERSION_MESSAGE = "No db version!";

    private String dbVersion;

    private static final String FLYWAY_SCRIPTS_OUT_OF_ORDER_KEY = "FLYWAY_SCRIPTS_OUT_OF_ORDER";
    private static final String FLYWAY_SCRIPTS_IGNORE_MISSING_MIGRATIONS_KEY = "FLYWAY_SCRIPTS_IGNORE_MISSING_MIGRATIONS";


    @PostConstruct
    private void onStartup() {

        try {
            if (getDataSource() == null) {
                LOG.severe("No datasource found to execute the db db!");
                throw new EJBException();
            }

            flyway = new Flyway();
//            Flyway.configure();

            flyway.setDataSource(getDataSource());
            MigrationInfo migrationInfo = flyway.info().current();

            if (migrationInfo == null) {
                LOG.info("No existing database at the actual datasource!");
            } else {
                LOG.info("Found a database with the version: " + migrationInfo.getVersion() + " : " + migrationInfo.getDescription());
            }

            flyway.setCleanDisabled(true);
            flyway.setBaselineOnMigrate(true);

            flyway.setPlaceholderReplacement(false);
            //empty script, used to init flyway
            flyway.setBaselineVersion(MigrationVersion.fromVersion("1.0_01"));
            flyway.setOutOfOrder(Boolean.getBoolean(FLYWAY_SCRIPTS_OUT_OF_ORDER_KEY));
            flyway.setIgnoreMissingMigrations(Boolean.getBoolean(FLYWAY_SCRIPTS_IGNORE_MISSING_MIGRATIONS_KEY));
            flyway.migrate();
            LOG.info("Successfully migrated to database version: " + getDbVersion());
        } catch (Exception e) {
            LOG.severe(e.getMessage() + e);
            throw e;
        }
    }

    public abstract DataSource getDataSource();

    @Lock(LockType.READ)
    public String getDbVersion() {
        if (dbVersion == null) {
            dbVersion = fetchDbVersion();
        }
        return dbVersion;
    }

    public String fetchDbVersion() {
        try {
            return flyway.info().current().getVersion().getVersion();
        } catch (Exception e) {
            LOG.severe(e.getMessage() + e);
            return VERSION_UNKNOWN_MESSAGE;
        }
    }
}
