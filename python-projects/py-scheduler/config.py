DB_CONFIG = {
    "host": "localhost",
    "port": 5432,
    "dbname": "postgres",
    "user": "postgres",
    "password": "password"
}

SCHEDULER_CONFIG = {
    "job_defaults": {
        "coalesce": True,
        "max_instances": 1
    },
    "timezone": "UTC"
}