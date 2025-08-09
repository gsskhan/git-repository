CREATE TABLE scheduled_jobs (
    job_id TEXT PRIMARY KEY,
    job_type TEXT NOT NULL CHECK (job_type IN ('fixed_rate', 'cron')),
    seconds INTEGER,
    cron_expr TEXT,
    job_name TEXT NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);