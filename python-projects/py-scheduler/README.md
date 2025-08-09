### Setup

    Step 1. Create Tables listed in postgres.sql

    Step 2. Create venv
        python -m venv .venv

    Step 3. Activate venv
        source .venv/bin/activate

    Step 4. Install dependencies from requirements.txt
        pip install -r requirements.txt

    Step 5. Verify the installation
        pip list

    Step 6. Run the application
        python app.py

    Step 7. (optional) Deactivate the virtual environment
        deactivate

### REST Endopints

    GET /api/jobs — list scheduled jobs

    POST /api/jobs — add/update a job
    
        Example request body for POST:
        {
            "job_id": "job1",
            "job_type": "fixed_rate",
            "seconds": 5,
            "job_name": "Hello Task",
            "enabled": true
        }

        Example request body for POST:
        {
            "job_id": "job2",
            "job_type": "cron",
            "cron_expr": "*/5 * * * *",
            "job_name": "Test Cron Task",
            "enabled": true
        }

