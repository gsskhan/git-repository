from flask import Blueprint, request, jsonify
from db.job_repository import upsert_job
from scheduler.job_service import schedule_job, scheduler

job_bp = Blueprint("jobs", __name__)

@job_bp.route("/jobs", methods=["POST"])
def add_job():
    data = request.json
    job_id = data["job_id"]

    try:
        # Save to DB
        upsert_job(data)

        existing_job = scheduler.get_job(job_id)

        if data.get("enabled", True):
            # Remove old job if it exists
            if existing_job:
                scheduler.remove_job(job_id)

            # Schedule new job
            schedule_job(
                job_id,
                data["job_type"],
                data.get("seconds"),
                data.get("cron_expr"),
                data["job_name"]
            )

            return jsonify({
                "status": "success",
                "message": "Job added/updated and scheduled"
            }), 201

        else:
            # Remove if running
            if existing_job:
                scheduler.remove_job(job_id)
                return jsonify({
                    "status": "success",
                    "message": "Job disabled and removed from scheduler"
                }), 200
            else:
                return jsonify({
                    "status": "success",
                    "message": "Job saved but not scheduled"
                }), 200

    except Exception as e:
        return jsonify({"status": "error", "message": str(e)}), 400

@job_bp.route("/jobs", methods=["GET"])
def list_jobs():
    jobs = scheduler.get_jobs()
    return jsonify([{
        "id": job.id,
        "next_run": str(job.next_run_time),
        "trigger": str(job.trigger)
    } for job in jobs]), 200