FROM mysql/mysql-server:8.0.17

RUN mkdir -p /var/temp/sql
COPY init.sql /var/temp/sql
RUN cp /var/temp/sql/init.sql /docker-entrypoint-initdb.d/
RUN rm -r /var/temp/sql