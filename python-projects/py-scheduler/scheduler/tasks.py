from datetime import datetime

def example_task(job_name):
    print(f"[{datetime.now().isoformat()}] Running task: {job_name}")