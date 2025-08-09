from apscheduler.schedulers.background import BackgroundScheduler
from apscheduler.triggers.interval import IntervalTrigger
from apscheduler.triggers.cron import CronTrigger
from config import SCHEDULER_CONFIG
from scheduler.tasks import example_task
from db.job_repository import get_enabled_jobs
from datetime import datetime

scheduler = BackgroundScheduler(**SCHEDULER_CONFIG)

def schedule_job(job_id, job_type, seconds, cron_expr, job_name):
    if job_type == "fixed_rate" and seconds:
        scheduler.add_job(
            example_task,
            IntervalTrigger(seconds=seconds),
            args=[job_name],
            id=job_id,
            replace_existing=True
        )
    elif job_type == "cron" and cron_expr:
        parts = cron_expr.split()
        scheduler.add_job(
            example_task,
            CronTrigger(
                minute=parts[0],
                hour=parts[1],
                day=parts[2],
                month=parts[3],
                day_of_week=parts[4]
            ),
            args=[job_name],
            id=job_id,
            replace_existing=True
        )
    else:
        raise ValueError("Invalid job configuration")

def load_jobs_on_startup():
    print(f"[{datetime.now().isoformat()}] Loading jobs from DB...")

    rows = get_enabled_jobs()

    if not rows:
        print("[Scheduler] No enabled jobs found in DB.")
        return

    success_count = 0
    fail_count = 0

    for job in rows:
        job_id, job_type, seconds, cron_expr, job_name = job
        try:
            schedule_job(job_id, job_type, seconds, cron_expr, job_name)
            print(f"[Scheduler] ✅ Job loaded: ID={job_id}, Name={job_name}, Type={job_type}, Seconds={seconds}, Cron={cron_expr}")
            success_count += 1
        except Exception as exc:
            print(f"[Scheduler] ❌ Failed to schedule job ID={job_id}, Name={job_name}. Error: {exc}")
            fail_count += 1

    print(f"[Scheduler] --- Startup Summary ---")
    print(f"[Scheduler] ✅ Successful jobs: {success_count}")
    print(f"[Scheduler] ❌ Failed jobs: {fail_count}")
    print(f"[Scheduler] ------------------------")

def start_scheduler():
    load_jobs_on_startup()
    scheduler.start()
