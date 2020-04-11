How to use this image
=====================
=====================


Running the daemon
==================
One of the important things to note about RabbitMQ is that it stores data based on what it calls the "Node Name", which defaults to the hostname. What this means for usage in Docker is that we should specify -h/--hostname explicitly for each daemon so that we don't get a random hostname and can keep track of our data:

    $ docker run -d --hostname my-rabbit --name some-rabbit rabbitmq:3

This will start a RabbitMQ container listening on the default port of 5672. If you give that a minute, then do docker logs some-rabbit, you'll see in the output a block similar to:

    =INFO REPORT==== 6-Jul-2015::20:47:02 ===
    node           : rabbit@my-rabbit
    home dir       : /var/lib/rabbitmq
    config file(s) : /etc/rabbitmq/rabbitmq.config
    cookie hash    : UoNOcDhfxW9uoZ92wh6BjA==
    log            : tty
    sasl log       : tty
    database dir   : /var/lib/rabbitmq/mnesia/rabbit@my-rabbit

Note the database dir there, especially that it has my "Node Name" appended to the end for the file storage. This image makes all of /var/lib/rabbitmq a volume by default.

Management Plugin
=================
There is a second set of tags provided with the management plugin installed and enabled by default, which is available on the standard management port of 15672, with the default username and password of guest / guest:

    $ docker run -d --hostname my-rabbit --name some-rabbit rabbitmq:3-management

You can access it by visiting http://container-ip:15672 in a browser or, if you need access outside the host, on port 8080:

    $ docker run -d --hostname my-rabbit --name some-rabbit -p 8080:15672 rabbitmq:3-management

You can then go to http://localhost:8080 or http://host-ip:8080 in a browser.

My Set Steps
============

Pull image

    gsskhan@gsskhan-Inspiron-3542:~$ docker pull rabbitmq:3-management
    3-management: Pulling from library/rabbitmq
    5bed26d33875: Pull complete 
    f11b29a9c730: Pull complete 
    930bda195c84: Pull complete 
    78bf9a5ad49e: Pull complete 
    0719cf990071: Pull complete 
    22082be2bc3e: Pull complete 
    c2323e1311ab: Pull complete 
    785decaedbf4: Pull complete 
    a6e11a8bffca: Pull complete 
    909784e4ac48: Pull complete 
    fc48d882c7db: Pull complete 
    c11b79102d15: Pull complete 
    Digest: sha256:714692897e914d3e6fcbac2232d98dec7758d8f8557601361781d7e504271142
    Status: Downloaded newer image for rabbitmq:3-management
    docker.io/library/rabbitmq:3-management
    gsskhan@gsskhan-Inspiron-3542:~$ 

Run image

    gsskhan@gsskhan-Inspiron-3542:~$ docker run -d --hostname local-rabbit-mq --name rabbitmq -p 8989:15672 rabbitmq:3-management
    1b552af260894aee070779371bffde0f98644449ba9263097f2ef343140f9e1a
    gsskhan@gsskhan-Inspiron-3542:~$

Logs

    gsskhan@gsskhan-Inspiron-3542:~$ docker logs rabbitmq
    2020-04-11 10:47:59.394 [info] <0.9.0> Feature flags: list of feature flags found:
    2020-04-11 10:47:59.394 [info] <0.9.0> Feature flags:   [ ] drop_unroutable_metric
    2020-04-11 10:47:59.394 [info] <0.9.0> Feature flags:   [ ] empty_basic_get_metric
    2020-04-11 10:47:59.395 [info] <0.9.0> Feature flags:   [ ] implicit_default_bindings
    2020-04-11 10:47:59.395 [info] <0.9.0> Feature flags:   [ ] quorum_queue
    2020-04-11 10:47:59.395 [info] <0.9.0> Feature flags:   [ ] virtual_host_metadata
    2020-04-11 10:47:59.395 [info] <0.9.0> Feature flags: feature flag states written to disk: yes
    2020-04-11 10:47:59.494 [info] <0.269.0> ra: meta data store initialised. 0 record(s) recovered
    2020-04-11 10:47:59.496 [info] <0.274.0> WAL: recovering []
    2020-04-11 10:47:59.508 [info] <0.278.0> 
    Starting RabbitMQ 3.8.3 on Erlang 22.3.2
    Copyright (c) 2007-2020 Pivotal Software, Inc.
    Licensed under the MPL 1.1. Website: https://rabbitmq.com

    ##  ##      RabbitMQ 3.8.3
    ##  ##
    ##########  Copyright (c) 2007-2020 Pivotal Software, Inc.
    ######  ##
    ##########  Licensed under the MPL 1.1. Website: https://rabbitmq.com

    Doc guides: https://rabbitmq.com/documentation.html
    Support:    https://rabbitmq.com/contact.html
    Tutorials:  https://rabbitmq.com/getstarted.html
    Monitoring: https://rabbitmq.com/monitoring.html

    Logs: <stdout>

    Config file(s): /etc/rabbitmq/rabbitmq.conf

    Starting broker...2020-04-11 10:47:59.511 [info] <0.278.0> 
    node           : rabbit@local-rabbit-mq
    home dir       : /var/lib/rabbitmq
    config file(s) : /etc/rabbitmq/rabbitmq.conf
    cookie hash    : U6PbOTdkenR+vtdQJwRjmQ==
    log(s)         : <stdout>
    database dir   : /var/lib/rabbitmq/mnesia/rabbit@local-rabbit-mq
    2020-04-11 10:47:59.556 [info] <0.278.0> Running boot step pre_boot defined by app rabbit
    2020-04-11 10:47:59.556 [info] <0.278.0> Running boot step rabbit_core_metrics defined by app rabbit
    2020-04-11 10:47:59.560 [info] <0.278.0> Running boot step rabbit_alarm defined by app rabbit
    2020-04-11 10:47:59.571 [info] <0.284.0> Memory high watermark set to 3146 MiB (3299364044 bytes) of 7866 MiB (8248410112 bytes) total
    2020-04-11 10:47:59.582 [info] <0.286.0> Enabling free disk space monitoring
    2020-04-11 10:47:59.582 [info] <0.286.0> Disk free limit set to 50MB
    2020-04-11 10:47:59.590 [info] <0.278.0> Running boot step code_server_cache defined by app rabbit
    2020-04-11 10:47:59.590 [info] <0.278.0> Running boot step file_handle_cache defined by app rabbit
    2020-04-11 10:47:59.591 [info] <0.289.0> Limiting to approx 1048479 file handles (943629 sockets)
    2020-04-11 10:47:59.591 [info] <0.290.0> FHC read buffering:  OFF
    2020-04-11 10:47:59.591 [info] <0.290.0> FHC write buffering: ON
    2020-04-11 10:47:59.594 [info] <0.278.0> Running boot step worker_pool defined by app rabbit
    2020-04-11 10:47:59.595 [info] <0.279.0> Will use 4 processes for default worker pool
    2020-04-11 10:47:59.596 [info] <0.279.0> Starting worker pool 'worker_pool' with 4 processes in it
    2020-04-11 10:47:59.599 [info] <0.278.0> Running boot step database defined by app rabbit
    2020-04-11 10:47:59.601 [info] <0.278.0> Node database directory at /var/lib/rabbitmq/mnesia/rabbit@local-rabbit-mq is empty. Assuming we need to join an existing cluster or initialise from scratch...
    2020-04-11 10:47:59.602 [info] <0.278.0> Configured peer discovery backend: rabbit_peer_discovery_classic_config
    2020-04-11 10:47:59.602 [info] <0.278.0> Will try to lock with peer discovery backend rabbit_peer_discovery_classic_config
    2020-04-11 10:47:59.602 [info] <0.278.0> Peer discovery backend does not support locking, falling back to randomized delay
    2020-04-11 10:47:59.602 [info] <0.278.0> Peer discovery backend rabbit_peer_discovery_classic_config does not support registration, skipping randomized startup delay.
    2020-04-11 10:47:59.603 [info] <0.278.0> All discovered existing cluster peers: 
    2020-04-11 10:47:59.603 [info] <0.278.0> Discovered no peer nodes to cluster with. Some discovery backends can filter nodes out based on a readiness criteria. Enabling debug logging might help troubleshoot.
    2020-04-11 10:47:59.607 [info] <0.44.0> Application mnesia exited with reason: stopped
    2020-04-11 10:47:59.783 [info] <0.278.0> Waiting for Mnesia tables for 30000 ms, 9 retries left
    2020-04-11 10:47:59.867 [info] <0.278.0> Waiting for Mnesia tables for 30000 ms, 9 retries left
    2020-04-11 10:47:59.869 [info] <0.278.0> Feature flag `drop_unroutable_metric`: supported, attempt to enable...
    2020-04-11 10:47:59.870 [info] <0.278.0> Feature flag `drop_unroutable_metric`: mark as enabled=state_changing
    2020-04-11 10:47:59.912 [info] <0.278.0> Feature flags: list of feature flags found:
    2020-04-11 10:47:59.912 [info] <0.278.0> Feature flags:   [~] drop_unroutable_metric
    2020-04-11 10:47:59.912 [info] <0.278.0> Feature flags:   [ ] empty_basic_get_metric
    2020-04-11 10:47:59.912 [info] <0.278.0> Feature flags:   [ ] implicit_default_bindings
    2020-04-11 10:47:59.912 [info] <0.278.0> Feature flags:   [ ] quorum_queue
    2020-04-11 10:47:59.912 [info] <0.278.0> Feature flags:   [ ] virtual_host_metadata
    2020-04-11 10:47:59.912 [info] <0.278.0> Feature flags: feature flag states written to disk: yes
    2020-04-11 10:47:59.946 [info] <0.278.0> Feature flag `drop_unroutable_metric`: mark as enabled=true
    2020-04-11 10:47:59.976 [info] <0.278.0> Feature flags: list of feature flags found:
    2020-04-11 10:47:59.976 [info] <0.278.0> Feature flags:   [x] drop_unroutable_metric
    2020-04-11 10:47:59.976 [info] <0.278.0> Feature flags:   [ ] empty_basic_get_metric
    2020-04-11 10:47:59.976 [info] <0.278.0> Feature flags:   [ ] implicit_default_bindings
    2020-04-11 10:47:59.976 [info] <0.278.0> Feature flags:   [ ] quorum_queue
    2020-04-11 10:47:59.976 [info] <0.278.0> Feature flags:   [ ] virtual_host_metadata
    2020-04-11 10:47:59.976 [info] <0.278.0> Feature flags: feature flag states written to disk: yes
    2020-04-11 10:47:59.997 [info] <0.278.0> Feature flag `empty_basic_get_metric`: supported, attempt to enable...
    2020-04-11 10:47:59.997 [info] <0.278.0> Feature flag `empty_basic_get_metric`: mark as enabled=state_changing
    2020-04-11 10:48:00.040 [info] <0.278.0> Feature flags: list of feature flags found:
    2020-04-11 10:48:00.040 [info] <0.278.0> Feature flags:   [x] drop_unroutable_metric
    2020-04-11 10:48:00.040 [info] <0.278.0> Feature flags:   [~] empty_basic_get_metric
    2020-04-11 10:48:00.040 [info] <0.278.0> Feature flags:   [ ] implicit_default_bindings
    2020-04-11 10:48:00.041 [info] <0.278.0> Feature flags:   [ ] quorum_queue
    2020-04-11 10:48:00.041 [info] <0.278.0> Feature flags:   [ ] virtual_host_metadata
    2020-04-11 10:48:00.041 [info] <0.278.0> Feature flags: feature flag states written to disk: yes
    2020-04-11 10:48:00.068 [info] <0.278.0> Feature flag `empty_basic_get_metric`: mark as enabled=true
    2020-04-11 10:48:00.101 [info] <0.278.0> Feature flags: list of feature flags found:
    2020-04-11 10:48:00.101 [info] <0.278.0> Feature flags:   [x] drop_unroutable_metric
    2020-04-11 10:48:00.101 [info] <0.278.0> Feature flags:   [x] empty_basic_get_metric
    2020-04-11 10:48:00.101 [info] <0.278.0> Feature flags:   [ ] implicit_default_bindings
    2020-04-11 10:48:00.102 [info] <0.278.0> Feature flags:   [ ] quorum_queue
    2020-04-11 10:48:00.102 [info] <0.278.0> Feature flags:   [ ] virtual_host_metadata
    2020-04-11 10:48:00.102 [info] <0.278.0> Feature flags: feature flag states written to disk: yes
    2020-04-11 10:48:00.130 [info] <0.278.0> Feature flag `implicit_default_bindings`: supported, attempt to enable...
    2020-04-11 10:48:00.131 [info] <0.278.0> Feature flag `implicit_default_bindings`: mark as enabled=state_changing
    2020-04-11 10:48:00.172 [info] <0.278.0> Feature flags: list of feature flags found:
    2020-04-11 10:48:00.172 [info] <0.278.0> Feature flags:   [x] drop_unroutable_metric
    2020-04-11 10:48:00.172 [info] <0.278.0> Feature flags:   [x] empty_basic_get_metric
    2020-04-11 10:48:00.172 [info] <0.278.0> Feature flags:   [~] implicit_default_bindings
    2020-04-11 10:48:00.172 [info] <0.278.0> Feature flags:   [ ] quorum_queue
    2020-04-11 10:48:00.172 [info] <0.278.0> Feature flags:   [ ] virtual_host_metadata
    2020-04-11 10:48:00.173 [info] <0.278.0> Feature flags: feature flag states written to disk: yes
    2020-04-11 10:48:00.203 [info] <0.278.0> Waiting for Mnesia tables for 30000 ms, 0 retries left
    2020-04-11 10:48:00.203 [info] <0.278.0> Feature flag `implicit_default_bindings`: mark as enabled=true
    2020-04-11 10:48:00.249 [info] <0.278.0> Feature flags: list of feature flags found:
    2020-04-11 10:48:00.249 [info] <0.278.0> Feature flags:   [x] drop_unroutable_metric
    2020-04-11 10:48:00.249 [info] <0.278.0> Feature flags:   [x] empty_basic_get_metric
    2020-04-11 10:48:00.249 [info] <0.278.0> Feature flags:   [x] implicit_default_bindings
    2020-04-11 10:48:00.250 [info] <0.278.0> Feature flags:   [ ] quorum_queue
    2020-04-11 10:48:00.250 [info] <0.278.0> Feature flags:   [ ] virtual_host_metadata
    2020-04-11 10:48:00.250 [info] <0.278.0> Feature flags: feature flag states written to disk: yes
    2020-04-11 10:48:00.278 [info] <0.278.0> Feature flag `quorum_queue`: supported, attempt to enable...
    2020-04-11 10:48:00.278 [info] <0.278.0> Feature flag `quorum_queue`: mark as enabled=state_changing
    2020-04-11 10:48:00.309 [info] <0.278.0> Feature flags: list of feature flags found:
    2020-04-11 10:48:00.309 [info] <0.278.0> Feature flags:   [x] drop_unroutable_metric
    2020-04-11 10:48:00.309 [info] <0.278.0> Feature flags:   [x] empty_basic_get_metric
    2020-04-11 10:48:00.309 [info] <0.278.0> Feature flags:   [x] implicit_default_bindings
    2020-04-11 10:48:00.309 [info] <0.278.0> Feature flags:   [~] quorum_queue
    2020-04-11 10:48:00.309 [info] <0.278.0> Feature flags:   [ ] virtual_host_metadata
    2020-04-11 10:48:00.309 [info] <0.278.0> Feature flags: feature flag states written to disk: yes
    2020-04-11 10:48:00.335 [info] <0.278.0> Waiting for Mnesia tables for 30000 ms, 9 retries left
    2020-04-11 10:48:00.336 [info] <0.278.0> Feature flag `quorum_queue`:   migrating Mnesia table rabbit_queue...
    2020-04-11 10:48:00.357 [info] <0.278.0> Feature flag `quorum_queue`:   migrating Mnesia table rabbit_durable_queue...
    2020-04-11 10:48:00.382 [info] <0.278.0> Feature flag `quorum_queue`:   Mnesia tables migration done
    2020-04-11 10:48:00.383 [info] <0.278.0> Feature flag `quorum_queue`: mark as enabled=true
    2020-04-11 10:48:00.409 [info] <0.278.0> Feature flags: list of feature flags found:
    2020-04-11 10:48:00.409 [info] <0.278.0> Feature flags:   [x] drop_unroutable_metric
    2020-04-11 10:48:00.409 [info] <0.278.0> Feature flags:   [x] empty_basic_get_metric
    2020-04-11 10:48:00.409 [info] <0.278.0> Feature flags:   [x] implicit_default_bindings
    2020-04-11 10:48:00.409 [info] <0.278.0> Feature flags:   [x] quorum_queue
    2020-04-11 10:48:00.409 [info] <0.278.0> Feature flags:   [ ] virtual_host_metadata
    2020-04-11 10:48:00.409 [info] <0.278.0> Feature flags: feature flag states written to disk: yes
    2020-04-11 10:48:00.429 [info] <0.278.0> Feature flag `virtual_host_metadata`: supported, attempt to enable...
    2020-04-11 10:48:00.429 [info] <0.278.0> Feature flag `virtual_host_metadata`: mark as enabled=state_changing
    2020-04-11 10:48:00.460 [info] <0.278.0> Feature flags: list of feature flags found:
    2020-04-11 10:48:00.460 [info] <0.278.0> Feature flags:   [x] drop_unroutable_metric
    2020-04-11 10:48:00.460 [info] <0.278.0> Feature flags:   [x] empty_basic_get_metric
    2020-04-11 10:48:00.460 [info] <0.278.0> Feature flags:   [x] implicit_default_bindings
    2020-04-11 10:48:00.460 [info] <0.278.0> Feature flags:   [x] quorum_queue
    2020-04-11 10:48:00.460 [info] <0.278.0> Feature flags:   [~] virtual_host_metadata
    2020-04-11 10:48:00.460 [info] <0.278.0> Feature flags: feature flag states written to disk: yes
    2020-04-11 10:48:00.483 [info] <0.278.0> Waiting for Mnesia tables for 30000 ms, 9 retries left
    2020-04-11 10:48:00.502 [info] <0.278.0> Feature flag `virtual_host_metadata`: mark as enabled=true
    2020-04-11 10:48:00.547 [info] <0.278.0> Feature flags: list of feature flags found:
    2020-04-11 10:48:00.548 [info] <0.278.0> Feature flags:   [x] drop_unroutable_metric
    2020-04-11 10:48:00.548 [info] <0.278.0> Feature flags:   [x] empty_basic_get_metric
    2020-04-11 10:48:00.548 [info] <0.278.0> Feature flags:   [x] implicit_default_bindings
    2020-04-11 10:48:00.548 [info] <0.278.0> Feature flags:   [x] quorum_queue
    2020-04-11 10:48:00.548 [info] <0.278.0> Feature flags:   [x] virtual_host_metadata
    2020-04-11 10:48:00.549 [info] <0.278.0> Feature flags: feature flag states written to disk: yes
    2020-04-11 10:48:00.578 [info] <0.278.0> Waiting for Mnesia tables for 30000 ms, 9 retries left
    2020-04-11 10:48:00.647 [info] <0.278.0> Waiting for Mnesia tables for 30000 ms, 9 retries left
    2020-04-11 10:48:00.647 [info] <0.278.0> Peer discovery backend rabbit_peer_discovery_classic_config does not support registration, skipping registration.
    2020-04-11 10:48:00.647 [info] <0.278.0> Running boot step database_sync defined by app rabbit
    2020-04-11 10:48:00.648 [info] <0.278.0> Running boot step feature_flags defined by app rabbit
    2020-04-11 10:48:00.648 [info] <0.278.0> Running boot step codec_correctness_check defined by app rabbit
    2020-04-11 10:48:00.648 [info] <0.278.0> Running boot step external_infrastructure defined by app rabbit
    2020-04-11 10:48:00.648 [info] <0.278.0> Running boot step rabbit_registry defined by app rabbit
    2020-04-11 10:48:00.648 [info] <0.278.0> Running boot step rabbit_auth_mechanism_cr_demo defined by app rabbit
    2020-04-11 10:48:00.649 [info] <0.278.0> Running boot step rabbit_queue_location_random defined by app rabbit
    2020-04-11 10:48:00.649 [info] <0.278.0> Running boot step rabbit_event defined by app rabbit
    2020-04-11 10:48:00.649 [info] <0.278.0> Running boot step rabbit_auth_mechanism_amqplain defined by app rabbit
    2020-04-11 10:48:00.649 [info] <0.278.0> Running boot step rabbit_auth_mechanism_plain defined by app rabbit
    2020-04-11 10:48:00.650 [info] <0.278.0> Running boot step rabbit_exchange_type_direct defined by app rabbit
    2020-04-11 10:48:00.650 [info] <0.278.0> Running boot step rabbit_exchange_type_fanout defined by app rabbit
    2020-04-11 10:48:00.650 [info] <0.278.0> Running boot step rabbit_exchange_type_headers defined by app rabbit
    2020-04-11 10:48:00.650 [info] <0.278.0> Running boot step rabbit_exchange_type_topic defined by app rabbit
    2020-04-11 10:48:00.651 [info] <0.278.0> Running boot step rabbit_mirror_queue_mode_all defined by app rabbit
    2020-04-11 10:48:00.651 [info] <0.278.0> Running boot step rabbit_mirror_queue_mode_exactly defined by app rabbit
    2020-04-11 10:48:00.651 [info] <0.278.0> Running boot step rabbit_mirror_queue_mode_nodes defined by app rabbit
    2020-04-11 10:48:00.651 [info] <0.278.0> Running boot step rabbit_priority_queue defined by app rabbit
    2020-04-11 10:48:00.651 [info] <0.278.0> Priority queues enabled, real BQ is rabbit_variable_queue
    2020-04-11 10:48:00.652 [info] <0.278.0> Running boot step rabbit_queue_location_client_local defined by app rabbit
    2020-04-11 10:48:00.652 [info] <0.278.0> Running boot step rabbit_queue_location_min_masters defined by app rabbit
    2020-04-11 10:48:00.652 [info] <0.278.0> Running boot step kernel_ready defined by app rabbit
    2020-04-11 10:48:00.652 [info] <0.278.0> Running boot step rabbit_sysmon_minder defined by app rabbit
    2020-04-11 10:48:00.652 [info] <0.278.0> Running boot step rabbit_epmd_monitor defined by app rabbit
    2020-04-11 10:48:00.669 [info] <0.521.0> epmd monitor knows us, inter-node communication (distribution) port: 25672
    2020-04-11 10:48:00.669 [info] <0.278.0> Running boot step guid_generator defined by app rabbit
    2020-04-11 10:48:00.685 [info] <0.278.0> Running boot step rabbit_node_monitor defined by app rabbit
    2020-04-11 10:48:00.686 [info] <0.527.0> Starting rabbit_node_monitor
    2020-04-11 10:48:00.686 [info] <0.278.0> Running boot step delegate_sup defined by app rabbit
    2020-04-11 10:48:00.687 [info] <0.278.0> Running boot step rabbit_memory_monitor defined by app rabbit
    2020-04-11 10:48:00.688 [info] <0.278.0> Running boot step core_initialized defined by app rabbit
    2020-04-11 10:48:00.688 [info] <0.278.0> Running boot step upgrade_queues defined by app rabbit
    2020-04-11 10:48:00.813 [info] <0.278.0> message_store upgrades: 1 to apply
    2020-04-11 10:48:00.813 [info] <0.278.0> message_store upgrades: Applying rabbit_variable_queue:move_messages_to_vhost_store
    2020-04-11 10:48:00.814 [info] <0.278.0> message_store upgrades: No durable queues found. Skipping message store migration
    2020-04-11 10:48:00.814 [info] <0.278.0> message_store upgrades: Removing the old message store data
    2020-04-11 10:48:00.816 [info] <0.278.0> message_store upgrades: All upgrades applied successfully
    2020-04-11 10:48:00.942 [info] <0.278.0> Running boot step rabbit_connection_tracking defined by app rabbit
    2020-04-11 10:48:00.943 [info] <0.278.0> Running boot step rabbit_connection_tracking_handler defined by app rabbit
    2020-04-11 10:48:00.943 [info] <0.278.0> Running boot step rabbit_exchange_parameters defined by app rabbit
    2020-04-11 10:48:00.943 [info] <0.278.0> Running boot step rabbit_mirror_queue_misc defined by app rabbit
    2020-04-11 10:48:00.944 [info] <0.278.0> Running boot step rabbit_policies defined by app rabbit
    2020-04-11 10:48:00.946 [info] <0.278.0> Running boot step rabbit_policy defined by app rabbit
    2020-04-11 10:48:00.946 [info] <0.278.0> Running boot step rabbit_queue_location_validator defined by app rabbit
    2020-04-11 10:48:00.946 [info] <0.278.0> Running boot step rabbit_quorum_memory_manager defined by app rabbit
    2020-04-11 10:48:00.946 [info] <0.278.0> Running boot step rabbit_vhost_limit defined by app rabbit
    2020-04-11 10:48:00.946 [info] <0.278.0> Running boot step rabbit_mgmt_reset_handler defined by app rabbitmq_management
    2020-04-11 10:48:00.946 [info] <0.278.0> Running boot step rabbit_mgmt_db_handler defined by app rabbitmq_management_agent
    2020-04-11 10:48:00.947 [info] <0.278.0> Management plugin: using rates mode 'basic'
    2020-04-11 10:48:00.947 [info] <0.278.0> Running boot step recovery defined by app rabbit
    2020-04-11 10:48:00.949 [info] <0.278.0> Running boot step load_core_definitions defined by app rabbit
    2020-04-11 10:48:00.949 [info] <0.278.0> Running boot step empty_db_check defined by app rabbit
    2020-04-11 10:48:00.950 [info] <0.278.0> Adding vhost '/' (description: 'Default virtual host')
    2020-04-11 10:48:01.177 [info] <0.568.0> Making sure data directory '/var/lib/rabbitmq/mnesia/rabbit@local-rabbit-mq/msg_stores/vhosts/628WB79CIFDYO9LJI6DKMI09L' for vhost '/' exists
    2020-04-11 10:48:01.192 [info] <0.568.0> Starting message stores for vhost '/'
    2020-04-11 10:48:01.193 [info] <0.572.0> Message store "628WB79CIFDYO9LJI6DKMI09L/msg_store_transient": using rabbit_msg_store_ets_index to provide index
    2020-04-11 10:48:01.194 [info] <0.568.0> Started message store of type transient for vhost '/'
    2020-04-11 10:48:01.195 [info] <0.575.0> Message store "628WB79CIFDYO9LJI6DKMI09L/msg_store_persistent": using rabbit_msg_store_ets_index to provide index
    2020-04-11 10:48:01.196 [warning] <0.575.0> Message store "628WB79CIFDYO9LJI6DKMI09L/msg_store_persistent": rebuilding indices from scratch
    2020-04-11 10:48:01.198 [info] <0.568.0> Started message store of type persistent for vhost '/'
    2020-04-11 10:48:01.201 [info] <0.278.0> Creating user 'guest'
    2020-04-11 10:48:01.218 [info] <0.278.0> Setting user tags for user 'guest' to [administrator]
    2020-04-11 10:48:01.240 [info] <0.278.0> Setting permissions for 'guest' in '/' to '.*', '.*', '.*'
    2020-04-11 10:48:01.256 [info] <0.278.0> Running boot step rabbit_looking_glass defined by app rabbit
    2020-04-11 10:48:01.257 [info] <0.278.0> Running boot step rabbit_core_metrics_gc defined by app rabbit
    2020-04-11 10:48:01.257 [info] <0.278.0> Running boot step background_gc defined by app rabbit
    2020-04-11 10:48:01.257 [info] <0.278.0> Running boot step connection_tracking defined by app rabbit
    2020-04-11 10:48:01.262 [info] <0.278.0> Setting up a table for connection tracking on this node: 'tracked_connection_on_node_rabbit@local-rabbit-mq'
    2020-04-11 10:48:01.267 [info] <0.278.0> Setting up a table for per-vhost connection counting on this node: 'tracked_connection_per_vhost_on_node_rabbit@local-rabbit-mq'
    2020-04-11 10:48:01.267 [info] <0.278.0> Running boot step routing_ready defined by app rabbit
    2020-04-11 10:48:01.267 [info] <0.278.0> Running boot step pre_flight defined by app rabbit
    2020-04-11 10:48:01.267 [info] <0.278.0> Running boot step notify_cluster defined by app rabbit
    2020-04-11 10:48:01.267 [info] <0.278.0> Running boot step networking defined by app rabbit
    2020-04-11 10:48:01.272 [info] <0.621.0> started TCP listener on [::]:5672
    2020-04-11 10:48:01.272 [info] <0.278.0> Running boot step cluster_name defined by app rabbit
    2020-04-11 10:48:01.273 [info] <0.278.0> Initialising internal cluster ID to 'rabbitmq-cluster-id-o8GZ1VHl1lYML-RTOw2-OQ'
    2020-04-11 10:48:01.284 [info] <0.278.0> Running boot step direct_client defined by app rabbit
    2020-04-11 10:48:01.284 [info] <0.278.0> Running boot step os_signal_handler defined by app rabbit
    2020-04-11 10:48:01.284 [info] <0.624.0> Swapping OS signal event handler (erl_signal_server) for our own
    2020-04-11 10:48:01.357 [info] <0.674.0> Management plugin: HTTP (non-TLS) listener started on port 15672
    2020-04-11 10:48:01.357 [info] <0.780.0> Statistics database started.
    2020-04-11 10:48:01.357 [info] <0.779.0> Starting worker pool 'management_worker_pool' with 3 processes in it
    completed with 3 plugins.
    2020-04-11 10:48:02.275 [info] <0.9.0> Server startup complete; 3 plugins started.
    * rabbitmq_management
    * rabbitmq_web_dispatch
    * rabbitmq_management_agent
    gsskhan@gsskhan-Inspiron-3542:~$ 


Stop
    $ docker stop rabbitmq

Start
    $ docker start rabbitmq

Restart
    $ docker restart rabbitmq

Management UI available at: http://localhost:8989
Username/Password: guest/guest