from flask import Flask
from routes.job_routes import job_bp
from scheduler.job_service import start_scheduler

app = Flask(__name__)
app.register_blueprint(job_bp)

if __name__ == "__main__":
    start_scheduler()
    app.run(host="0.0.0.0", port=5000)
