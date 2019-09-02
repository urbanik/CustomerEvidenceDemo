FROM payara/server-full:5.191

ENV  DOMAIN_NAME=production

ENV ADMIN_USER admin
ENV ADMIN_PASSWORD admin


USER root
# set credentials to admin/admin

RUN echo 'AS_ADMIN_PASSWORD=\n\
AS_ADMIN_NEWPASSWORD='$ADMIN_PASSWORD'\n\
EOF\n'\
>> /opt/tmpfile

RUN echo 'AS_ADMIN_PASSWORD='$ADMIN_PASSWORD'\n\
EOF\n'\
>> /opt/pwdfile

USER payara

RUN mkdir -p ${PAYARA_DIR}/resources
COPY glassfish-resources.xml ${PAYARA_DIR}/resources

COPY --chown=payara:payara /lib/mysql-connector-java-8.0.16.jar ${PAYARA_DIR}/glassfish/lib/

# create resources
RUN ${PAYARA_DIR}/bin/asadmin start-domain ${DOMAIN_NAME} && \
    ${PAYARA_DIR}/bin/asadmin --user ${ADMIN_USER} --passwordfile=/opt/tmpfile change-admin-password && \
    ${PAYARA_DIR}/bin/asadmin --user ${ADMIN_USER} --passwordfile=/opt/pwdfile enable-secure-admin && \
    ${PAYARA_DIR}/bin/asadmin restart-domain && \
    ${PAYARA_DIR}/bin/asadmin --user ${ADMIN_USER} --passwordfile=/opt/pwdfile add-resources ${PAYARA_DIR}/resources/glassfish-resources.xml && \
    ${PAYARA_DIR}/bin/asadmin --user ${ADMIN_USER} --passwordfile=/opt/pwdfile set-hazelcast-configuration --enabled true && \
    ${PAYARA_DIR}/bin/asadmin --user ${ADMIN_USER} --passwordfile=/opt/pwdfile create-system-properties FLYWAY_SCRIPTS_IGNORE_MISSING_MIGRATIONS=true && \
    ${PAYARA_DIR}/bin/asadmin stop-domain ${DOMAIN_NAME}

COPY --chown=payara:payara userservice.war ${DEPLOY_DIR}
