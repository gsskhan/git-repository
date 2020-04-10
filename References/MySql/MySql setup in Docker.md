Downloading a MySQL Server Docker Image
=======================================
Command: docker pull mysql/mysql-server:tag

    gsskhan@gsskhan-Inspiron-3542:~$ docker pull mysql/mysql-server
    Using default tag: latest
    latest: Pulling from mysql/mysql-server
    c7127dfa6d78: Pull complete 
    530b30ab10d9: Pull complete 
    59c6388c2493: Pull complete 
    cca3f8362bb0: Pull complete 
    Digest: sha256:7cd104d6ff11f7e6a16087f88b1ce538bcb0126c048a60cd28632e7cf3dbe1b7
    Status: Downloaded newer image for mysql/mysql-server:latest
    docker.io/mysql/mysql-server:latest

    gsskhan@gsskhan-Inspiron-3542:~$ docker images
    REPOSITORY           TAG                 IMAGE ID            CREATED             SIZE
    mysql/mysql-server   latest              a7a39f15d42d        2 months ago        381MB

Starting a MySQL Server Instance
================================
Command: docker run --name=container_name -d image_name:tag

(The -d option used in the docker run command above makes the container run in the background. )

    gsskhan@gsskhan-Inspiron-3542:~$ docker run --name=mysql -d mysql/mysql-server:latest
    04e6d9f9e16b7cbfd3e3f5534a80e2fe8576b36df02eef42aeff72789ef965b9

    gsskhan@gsskhan-Inspiron-3542:~$ docker ps -a
    CONTAINER ID        IMAGE                       COMMAND                  CREATED             STATUS                            PORTS                 NAMES
    04e6d9f9e16b        mysql/mysql-server:latest   "/entrypoint.sh mysq…"   10 seconds ago      Up 9 seconds (health: starting)   3306/tcp, 33060/tcp   mysql

Monitor output of container
===========================
Command: docker logs container_name

    gsskhan@gsskhan-Inspiron-3542:~$ docker logs mysql
    [Entrypoint] MySQL Docker Image 8.0.19-1.1.15
    [Entrypoint] No password option specified for new database.
    [Entrypoint]   A random onetime password will be generated.
    [Entrypoint] Initializing database
    2020-04-10T11:52:39.503389Z 0 [System] [MY-013169] [Server] /usr/sbin/mysqld (mysqld 8.0.19) initializing of server in progress as process 20
    2020-04-10T11:52:44.498576Z 5 [Warning] [MY-010453] [Server] root@localhost is created with an empty password ! Please consider switching off the --initialize-insecure option.
    [Entrypoint] Database initialized
    2020-04-10T11:52:49.025005Z 0 [System] [MY-010116] [Server] /usr/sbin/mysqld (mysqld 8.0.19) starting as process 68
    2020-04-10T11:52:50.303315Z 0 [Warning] [MY-010068] [Server] CA certificate ca.pem is self signed.
    2020-04-10T11:52:50.351552Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.19'  socket: '/var/lib/mysql/mysql.sock'  port: 0  MySQL Community Server - GPL.
    2020-04-10T11:52:50.477798Z 0 [System] [MY-011323] [Server] X Plugin ready for connections. Socket: '/var/run/mysqld/mysqlx.sock'
    Warning: Unable to load '/usr/share/zoneinfo/iso3166.tab' as time zone. Skipping it.
    Warning: Unable to load '/usr/share/zoneinfo/leapseconds' as time zone. Skipping it.
    Warning: Unable to load '/usr/share/zoneinfo/tzdata.zi' as time zone. Skipping it.
    Warning: Unable to load '/usr/share/zoneinfo/zone.tab' as time zone. Skipping it.
    Warning: Unable to load '/usr/share/zoneinfo/zone1970.tab' as time zone. Skipping it.
    [Entrypoint] GENERATED ROOT PASSWORD: nIDgErGON9yfgAcuRYwH3fR@gq

    [Entrypoint] ignoring /docker-entrypoint-initdb.d/*

    2020-04-10T11:52:54.287962Z 10 [System] [MY-013172] [Server] Received SHUTDOWN from user root. Shutting down mysqld (Version: 8.0.19).
    2020-04-10T11:52:55.661864Z 0 [System] [MY-010910] [Server] /usr/sbin/mysqld: Shutdown complete (mysqld 8.0.19)  MySQL Community Server - GPL.
    [Entrypoint] Server shut down
    [Entrypoint] Setting root user as expired. Password will need to be changed before database can be used.

    [Entrypoint] MySQL init process done. Ready for start up.

    [Entrypoint] Starting MySQL 8.0.19-1.1.15
    2020-04-10T11:52:56.882367Z 0 [System] [MY-010116] [Server] /usr/sbin/mysqld (mysqld 8.0.19) starting as process 1
    2020-04-10T11:52:57.826285Z 0 [Warning] [MY-010068] [Server] CA certificate ca.pem is self signed.
    2020-04-10T11:52:57.903867Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.19'  socket: '/var/lib/mysql/mysql.sock'  port: 3306  MySQL Community Server - GPL.
    2020-04-10T11:52:57.947661Z 0 [System] [MY-011323] [Server] X Plugin ready for connections. Socket: '/var/run/mysqld/mysqlx.sock' bind-address: '::' port: 33060
    gsskhan@gsskhan-Inspiron-3542:~$ 

Get the random password generated for the root user
===================================================
Once initialization is finished, the command's output is going to contain the random password generated for the root user. 
Check the password with, for example, with following: 

Command: docker logs container_name 2>&1 | grep GENERATED

    gsskhan@gsskhan-Inspiron-3542:~$ docker logs mysql 2>&1 | grep GENERATED
    [Entrypoint] GENERATED ROOT PASSWORD: nIDgErGON9yfgAcuRYwH3fR@gq

Connecting to MySQL Server from within the Container
====================================================
Once the server is ready, you can run the mysql client within the MySQL Server container you just started, and connect it to the MySQL Server. 
Use the "docker exec -it" command to start a mysql client inside the Docker container you have started, like the following:

Command: docker exec -it container_name mysql -uroot -p

When asked, enter the GENERATED ROOT PASSWORD (From the above section). 
Because the MYSQL_ONETIME_PASSWORD option is true by default, after you have connected a mysql client to the server, 
you must reset the server root password by issuing this statement:

    mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'password';

Substitute password with the password of your choice. Once the password is reset, the server is ready for use.

    gsskhan@gsskhan-Inspiron-3542:~$ docker exec -it mysql mysql -uroot -p
    Enter password: 
    Welcome to the MySQL monitor.  Commands end with ; or \g.
    Your MySQL connection id is 32
    Server version: 8.0.19

    Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

    Oracle is a registered trademark of Oracle Corporation and/or its
    affiliates. Other names may be trademarks of their respective
    owners.

    Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

    mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'password';
    Query OK, 0 rows affected (0.01 sec)

    mysql> exit
    Bye
    gsskhan@gsskhan-Inspiron-3542:~$ 

    (Note: I have here for simplicity reset password as string password only)

Container Shell Access
======================
To have shell access to your MySQL Server container, use the docker exec -it command to start a bash shell inside the container:

Command: docker exec -it container_name bash 

    gsskhan@gsskhan-Inspiron-3542:~$ docker exec -it mysql bash
    bash-4.2# ls /var/lib/mysql
    #innodb_temp   binlog.000002  ca.pem	       ib_buffer_pool  ibdata1	mysql.ibd	 performance_schema  server-cert.pem  undo_001
    auto.cnf       binlog.index   client-cert.pem  ib_logfile0     ibtmp1	mysql.sock	 private_key.pem     server-key.pem   undo_002
    binlog.000001  ca-key.pem     client-key.pem   ib_logfile1     mysql	mysql.sock.lock  public_key.pem      sys
    bash-4.2# exit
    exit


Stopping and Deleting a MySQL Container
=======================================
To stop the MySQL Server container we have created, use this 

Command: docker stop container_name

    gsskhan@gsskhan-Inspiron-3542:~$ docker stop mysql
    mysql

To start the MySQL Server container again:
==========================================
docker start mysql

To stop and start again the MySQL Server container with a single command:
========================================================================
docker restart mysql

To delete the MySQL container, stop it first, and then use the docker rm command:
=================================================================================
docker stop mysql
docker rm mysql


# Connecting with Mysql Workbench
=================================
=================================

Download and install same version of Workbench from MySql Oracle website, as same/compatible to mysql version you are running.

Then, we need to run the mysql image exposing ports. 

Also we cant connect as root user outside docker container. 

    gsskhan@gsskhan-Inspiron-3542:~$ docker run --name mysql -p 3406:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql/mysql-server:latest
    e8249e5afbdcb938853d3bb641ae0ee1095469495888a4fd34275c96543ebb18

There, is trick. To set root user password, Set the environment variable MYSQL_ROOT_PASSWORD to a password you want (As above).

    gsskhan@gsskhan-Inspiron-3542:~$ docker ps -a
    CONTAINER ID        IMAGE                       COMMAND                  CREATED             STATUS                    PORTS                               NAMES
    e8249e5afbdc        mysql/mysql-server:latest   "/entrypoint.sh mysq…"   39 seconds ago      Up 38 seconds (healthy)   33060/tcp, 0.0.0.0:3406->3306/tcp   mysql

Login to container, then login to mysql with user root; and password. Create a new user, which can be accessed from outside container.

    gsskhan@gsskhan-Inspiron-3542:~$ docker exec -it mysql bash
    bash-4.2# mysql -uroot -p
    Enter password: 
    Welcome to the MySQL monitor.  Commands end with ; or \g.
    Your MySQL connection id is 10
    Server version: 8.0.19 MySQL Community Server - GPL

    Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

    Oracle is a registered trademark of Oracle Corporation and/or its
    affiliates. Other names may be trademarks of their respective
    owners.

    Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

    mysql>

I create a user named "dev", grant all privileges, and quit.

Important:
=========
This step is required to log into MySQL from outside the container. The root user will not be able to log in from the host OS (Linux). Use % instead of localhost in dev@localhost.


    mysql> CREATE USER 'dev'@'%' IDENTIFIED BY 'password';
    Query OK, 0 rows affected (0.02 sec)

    mysql> GRANT ALL PRIVILEGES ON * . * TO 'dev'@'%';
    Query OK, 0 rows affected (0.00 sec)

    mysql> exit
    Bye

Test your connection of new user "dev"

    bash-4.2# mysql -udev -ppassword
    mysql: [Warning] Using a password on the command line interface can be insecure.
    Welcome to the MySQL monitor.  Commands end with ; or \g.
    Your MySQL connection id is 14
    Server version: 8.0.19 MySQL Community Server - GPL

    Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

    Oracle is a registered trademark of Oracle Corporation and/or its
    affiliates. Other names may be trademarks of their respective
    owners.

    Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

    mysql> SELECT User, Host FROM mysql.user;
    +------------------+-----------+
    | User             | Host      |
    +------------------+-----------+
    | dev              | %         |
    | healthchecker    | localhost |
    | mysql.infoschema | localhost |
    | mysql.session    | localhost |
    | mysql.sys        | localhost |
    | root             | localhost |
    +------------------+-----------+
    6 rows in set (0.00 sec)

    mysql> quit
    Bye
    bash-4.2# exit
    exit
    gsskhan@gsskhan-Inspiron-3542:~$

Open Workbench and use below as connection details
==================================================
    hostname: localhost
    port: 3406
    username: dev
    password: password