from flask import Blueprint, request, jsonify
from db.job_repository import upsert_job
from scheduler.job_service import schedule_job, scheduler

job_bp = Blueprint("jobs", __name__)

@job_bp.route("/jobs", methods=["POST"])
def add_job():
    data = request.json
    try:
        upsert_job(data)
        schedule_job(
            data["job_id"], 
            data["job_type"], 
            data.get("seconds"), 
            data.get("cron_expr"), 
            data["job_name"]
        )
        return jsonify({"status": "success", "message": "Job scheduled"}), 201
    except Exception as e:
        return jsonify({"status": "error", "message": str(e)}), 400

@job_bp.route("/jobs", methods=["GET"])
def list_jobs():
    jobs = scheduler.get_jobs()
    return jsonify([{
        "id": job.id,
        "next_run": str(job.next_run_time),
        "trigger": str(job.trigger)
    } for job in jobs])