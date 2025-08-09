from db.connection import get_db_connection

def get_enabled_jobs():
    query = """
    SELECT job_id, job_type, seconds, cron_expr, job_name
    FROM scheduled_jobs
    WHERE enabled = TRUE
    """
    with get_db_connection() as conn:
        with conn.cursor() as cur:
            cur.execute(query)
            return cur.fetchall()

def upsert_job(job_data):
    query = """
    INSERT INTO scheduled_jobs (job_id, job_type, seconds, cron_expr, job_name, enabled)
    VALUES (%(job_id)s, %(job_type)s, %(seconds)s, %(cron_expr)s, %(job_name)s, %(enabled)s)
    ON CONFLICT (job_id)
    DO UPDATE SET
        job_type = EXCLUDED.job_type,
        seconds = EXCLUDED.seconds,
        cron_expr = EXCLUDED.cron_expr,
        job_name = EXCLUDED.job_name,
        enabled = EXCLUDED.enabled
    """
    with get_db_connection() as conn:
        with conn.cursor() as cur:
            cur.execute(query, {
                "job_id": job_data["job_id"],
                "job_type": job_data["job_type"],
                "seconds": job_data.get("seconds"),
                "cron_expr": job_data.get("cron_expr"),
                "job_name": job_data["job_name"],
                "enabled": job_data.get("enabled", True)
            })
        conn.commit()
