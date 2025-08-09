CREATE TABLE scheduled_jobs (
    job_id TEXT PRIMARY KEY,
    job_type TEXT NOT NULL CHECK (job_type IN ('fixed_rate', 'cron')),
    seconds INTEGER,
    cron_expr TEXT,
    job_name TEXT NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);

INSERT INTO scheduled_jobs (job_id, job_type, seconds, job_name, enabled)
VALUES ('job1', 'fixed_rate', 5, 'Hello Task', true);

INSERT INTO scheduled_jobs (job_id, job_type, cron_expr, job_name, enabled)
VALUES ('job2', 'cron', '*/5 * * * *', 'Test Cron Task', true);

SELECT * FROM scheduled_jobs;