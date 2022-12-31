Downloading a MySQL Server Docker Image
=======================================
Command: docker pull mysql/mysql-server:tag

        gsskhan@gsskhan-Inspiron-3542:~$ docker pull mysql/mysql-server:latest
        latest: Pulling from mysql/mysql-server
        134439bbc243: Pull complete 
        24197d57c06a: Pull complete 
        a8ff14042390: Pull complete 
        209d472e303b: Pull complete 
        4158d94acc40: Pull complete 
        807107bf7d7a: Pull complete 
        5f5d5a703fe0: Pull complete 
        Digest: sha256:1b2005199e9dc12d88d5950cd738dfd12172b1224675294646ea9d6031c78408
        Status: Downloaded newer image for mysql/mysql-server:latest
        docker.io/mysql/mysql-server:latest

        gsskhan@gsskhan-Inspiron-3542:~$ docker images
        REPOSITORY           TAG       IMAGE ID       CREATED        SIZE
        mysql/mysql-server   latest    3f3946d5572f   2 months ago   517MB

Starting a MySQL Server Instance
================================
Command: docker run --name=container_name -p <outside port>:<inside container port> -e MYSQL_ROOT_PASSWORD=<mysql root user password> -d image_name:tag

The -d option used in the docker run command above makes the container run in the background.

We need to run the mysql image exposing ports. Refer below, 3406 port is exposed for classic connections. And 33406 has been exposed for MySql X connections.

Also we cant connect as root user outside docker container by default. However, there are few tricks. 

First, make sure to set root user password in docker run command. To do this, set the environment variable MYSQL_ROOT_PASSWORD to a password you want (As below). It will make mysql server on startup to set root user password provided by yourself.

    gsskhan@gsskhan-Inspiron-3542:~$ docker run --name mysql -p 3406:3306 -p 33406:33060 -e MYSQL_ROOT_PASSWORD=password -d mysql/mysql-server:latest
    925cf15c10c83e7f2fb03c71e8ed7d02c0f6df65d6eddb5a52fa7162a934cd5a

    gsskhan@gsskhan-Inspiron-3542:~$ docker ps -a
    CONTAINER ID   IMAGE                       COMMAND                  CREATED             STATUS                      PORTS                                                                                                 NAMES
    925cf15c10c8   mysql/mysql-server:latest   "/entrypoint.sh mysq…"   38 seconds ago      Up 36 seconds (healthy)     33061/tcp, 0.0.0.0:3406->3306/tcp, :::3406->3306/tcp, 0.0.0.0:33406->33060/tcp, :::33406->33060/tcp   mysql

Monitor output of container
===========================
Command: docker logs container_name

    gsskhan@gsskhan-Inspiron-3542:~$ docker logs mysql
    [Entrypoint] MySQL Docker Image 8.0.31-1.2.10-server
    [Entrypoint] Initializing database
    2022-12-31T10:09:43.480391Z 0 [Warning] [MY-011068] [Server] The syntax '--skip-host-cache' is deprecated and will be removed in a future release. Please use SET GLOBAL host_cache_size=0 instead.
    2022-12-31T10:09:43.480626Z 0 [System] [MY-013169] [Server] /usr/sbin/mysqld (mysqld 8.0.31) initializing of server in progress as process 17
    2022-12-31T10:09:43.500505Z 1 [System] [MY-013576] [InnoDB] InnoDB initialization has started.
    2022-12-31T10:09:44.892012Z 1 [System] [MY-013577] [InnoDB] InnoDB initialization has ended.
    2022-12-31T10:09:47.545793Z 6 [Warning] [MY-010453] [Server] root@localhost is created with an empty password ! Please consider switching off the --initialize-insecure option.
    [Entrypoint] Database initialized
    2022-12-31T10:09:54.701951Z 0 [Warning] [MY-011068] [Server] The syntax '--skip-host-cache' is deprecated and will be removed in a future release. Please use SET GLOBAL host_cache_size=0 instead.
    2022-12-31T10:09:54.704942Z 0 [System] [MY-010116] [Server] /usr/sbin/mysqld (mysqld 8.0.31) starting as process 66
    2022-12-31T10:09:54.783946Z 1 [System] [MY-013576] [InnoDB] InnoDB initialization has started.
    2022-12-31T10:09:55.325975Z 1 [System] [MY-013577] [InnoDB] InnoDB initialization has ended.
    2022-12-31T10:09:56.705829Z 0 [Warning] [MY-010068] [Server] CA certificate ca.pem is self signed.
    2022-12-31T10:09:56.705884Z 0 [System] [MY-013602] [Server] Channel mysql_main configured to support TLS. Encrypted connections are now supported for this channel.
    2022-12-31T10:09:56.760050Z 0 [System] [MY-011323] [Server] X Plugin ready for connections. Socket: /var/run/mysqld/mysqlx.sock
    2022-12-31T10:09:56.760312Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.31'  socket: '/var/lib/mysql/mysql.sock'  port: 0  MySQL Community Server - GPL.
    Warning: Unable to load '/usr/share/zoneinfo/iso3166.tab' as time zone. Skipping it.
    Warning: Unable to load '/usr/share/zoneinfo/leapseconds' as time zone. Skipping it.
    Warning: Unable to load '/usr/share/zoneinfo/tzdata.zi' as time zone. Skipping it.
    Warning: Unable to load '/usr/share/zoneinfo/zone.tab' as time zone. Skipping it.
    Warning: Unable to load '/usr/share/zoneinfo/zone1970.tab' as time zone. Skipping it.

    [Entrypoint] ignoring /docker-entrypoint-initdb.d/*

    2022-12-31T10:10:02.278662Z 11 [System] [MY-013172] [Server] Received SHUTDOWN from user root. Shutting down mysqld (Version: 8.0.31).
    2022-12-31T10:10:06.419907Z 0 [System] [MY-010910] [Server] /usr/sbin/mysqld: Shutdown complete (mysqld 8.0.31)  MySQL Community Server - GPL.
    [Entrypoint] Server shut down

    [Entrypoint] MySQL init process done. Ready for start up.

    [Entrypoint] Starting MySQL 8.0.31-1.2.10-server
    2022-12-31T10:10:07.742119Z 0 [Warning] [MY-011068] [Server] The syntax '--skip-host-cache' is deprecated and will be removed in a future release. Please use SET GLOBAL host_cache_size=0 instead.
    2022-12-31T10:10:07.747396Z 0 [System] [MY-010116] [Server] /usr/sbin/mysqld (mysqld 8.0.31) starting as process 1
    2022-12-31T10:10:07.785916Z 1 [System] [MY-013576] [InnoDB] InnoDB initialization has started.
    2022-12-31T10:10:08.821646Z 1 [System] [MY-013577] [InnoDB] InnoDB initialization has ended.
    2022-12-31T10:10:09.469950Z 0 [Warning] [MY-010068] [Server] CA certificate ca.pem is self signed.
    2022-12-31T10:10:09.470002Z 0 [System] [MY-013602] [Server] Channel mysql_main configured to support TLS. Encrypted connections are now supported for this channel.
    2022-12-31T10:10:09.525841Z 0 [System] [MY-011323] [Server] X Plugin ready for connections. Bind-address: '::' port: 33060, socket: /var/run/mysqld/mysqlx.sock
    2022-12-31T10:10:09.526133Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.31'  socket: '/var/lib/mysql/mysql.sock'  port: 3306  MySQL Community Server - GPL.
    gsskhan@gsskhan-Inspiron-3542:~$ 

Stopping and Restarting MySQL Database
========================================

User docker start/stop to start MySQL server container.

    Here our container name is: mysql. Hence execute following:-

    $ docker stop mysql
    $ docker ps
    $ docker start mysql
    $ docker ps

Connect to MySQL database
=========================

From inside container
---------------------

Use user as root and password as that value which we set to MYSQL_ROOT_PASSWORD in docker run command.

    gsskhan@gsskhan-Inspiron-3542:~$ docker exec -it mysql bash
    bash-4.4# 
    bash-4.4# mysql -u root -p

    Enter password:
    Welcome to the MySQL monitor.  Commands end with ; or \g.
    Your MySQL connection id is 28
    Server version: 8.0.31 MySQL Community Server - GPL

    Copyright (c) 2000, 2022, Oracle and/or its affiliates.

    Oracle is a registered trademark of Oracle Corporation and/or its
    affiliates. Other names may be trademarks of their respective
    owners.

    Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

    mysql> quit
    Bye     
    bash-4.4# exit
    exit
    gsskhan@gsskhan-Inspiron-3542:~$ 


From outside container. Eg; SQL workbench, DB visualizer, etc
-------------------------------------------------------------

The root user will not be able to log in from the host OS (Linux) by default. These steps are required to login into MySQL from outside the container. 

Login to container, next then login to mysql with user root; and password. Then, Create a new user, which can be accessed from outside container. Remember to use % in hostname.

    gsskhan@gsskhan-Inspiron-3542:~$ docker exec -it mysql bash
    bash-4.4# 
    bash-4.4# mysql -u root -p
    Enter password: 
    Welcome to the MySQL monitor.  Commands end with ; or \g.
    Your MySQL connection id is 58
    Server version: 8.0.31 MySQL Community Server - GPL

    Copyright (c) 2000, 2022, Oracle and/or its affiliates.

    Oracle is a registered trademark of Oracle Corporation and/or its
    affiliates. Other names may be trademarks of their respective
    owners.

    Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

    mysql> 

    mysql> CREATE USER 'dev'@'%' IDENTIFIED BY 'password';
    Query OK, 0 rows affected (0.04 sec)

    mysql> GRANT ALL PRIVILEGES ON * . * TO 'dev'@'%';
    Query OK, 0 rows affected (0.00 sec)

    mysql> FLUSH PRIVILEGES;
    Query OK, 0 rows affected (0.01 sec)

    mysql> quit
    Bye
    bash-4.4# exit
    exit
    gsskhan@gsskhan-Inspiron-3542:~$ 

Above, I created a user named "dev", granted all privileges, and quit. Note, also that I used % instead of localhost in dev@localhost. Else "dev" user would not be accessible outside from container.

Test your connection of new user "dev"

    gsskhan@gsskhan-Inspiron-3542:~$ docker exec -it mysql bash
    bash-4.4# 
    bash-4.4# mysql -udev -ppassword
    mysql: [Warning] Using a password on the command line interface can be insecure.
    Welcome to the MySQL monitor.  Commands end with ; or \g.
    Your MySQL connection id is 74
    Server version: 8.0.31 MySQL Community Server - GPL

    Copyright (c) 2000, 2022, Oracle and/or its affiliates.

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
    bash-4.4# 
    gsskhan@gsskhan-Inspiron-3542:~$

Open Workbench and use below as connection details

    hostname: localhost
    port: 3406
    username: dev
    password: password

IMPORTANT! To enable external access to the database, you need to change localhost to % for any user.
=====================================================================================================
For root user: You can do via Workbench - by logging as user "dev", since you granted all previledges to it.

    In workbench, navigate to "Administration" > "Users And Priveledges" > Change "Limits to host matching" for root; user to % from localhost.

Alternatively, From docker container (Not verified -- Approach)

    gsskhan@gsskhan-Inspiron-3542:~$ docker exec -it mysql bash
    bash-4.4# mysql -u root -p
    Enter password: 
    Welcome to the MySQL monitor.  Commands end with ; or \g.
    Your MySQL connection id is 102
    Server version: 8.0.31 MySQL Community Server - GPL

    Copyright (c) 2000, 2022, Oracle and/or its affiliates.

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

    mysql> UPDATE mysql.user SET Host='%' WHERE Host='localhost' AND User='root';
    Query OK, 1 row affected (0.01 sec)
    Rows matched: 1  Changed: 1  Warnings: 0

    mysql> UPDATE mysql.db SET Host='%' WHERE Host='localhost' AND User='root';
    Query OK, 0 rows affected (0.00 sec)
    Rows matched: 0  Changed: 0  Warnings: 0

    mysql> SELECT User, Host FROM mysql.user;
    +------------------+-----------+
    | User             | Host      |
    +------------------+-----------+
    | dev              | %         |
    | root             | %         |
    | healthchecker    | localhost |
    | mysql.infoschema | localhost |
    | mysql.session    | localhost |
    | mysql.sys        | localhost |
    +------------------+-----------+
    6 rows in set (0.00 sec)

    mysql> FLUSH PRIVILEGES;
    Query OK, 0 rows affected (0.01 sec)

    mysql> exit;
    Bye
    bash-4.4# exit
    exit
    gsskhan@gsskhan-Inspiron-3542:~$ 

Open Workbench and use below as connection details

    hostname: localhost
    port: 3406
    username: root
    password: password
