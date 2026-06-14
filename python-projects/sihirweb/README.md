## Create VENV

    $ source /opt/anaconda/anaconda3/bin/activate 

    $ python -m venv .venv

    $ exit

## Activate VENV

    $ source .venv/bin/activate

## Copy dependencies into a text file named as requirement.txt and install it via pip.

    $ pip install --upgrade pip

    $ pip install -r requirements.txt 

    $ pip list

## Run the Project

    $ uvicorn app.main:app --reload

## Swagger URL can be found at /docs
    
    http://127.0.0.1:8000/docs

## Project structure (Router-Service-Repository Pattern)

    sihirweb/
    ├── app/
    │   ├── __init__.py
    │   ├── main.py                  # App initialization & middleware
    │   ├── core/                    # Global configurations
    │   │   ├── config.py            # Pydantic environment settings
    │   │   └── database.py          # Database engine and session setup
    │   ├── api/                     # Controllers / Router Layer
    │   │   ├── v1/
    │   │   │   ├── endpoints/
    │   │   │   │   ├── auth.py
    │   │   │   │   └── users.py     # Handles HTTP requests & responses
    │   │   │   └── api.py           # Includes and aggregates all v1 routers
    │   ├── schemas/                 # Data validation (DTOs)
    │   │   ├── auth.py
    │   │   └── users.py             # Pydantic models for input/output
    │   ├── services/                # Business Logic Layer
    │   │   ├── auth_service.py
    │   │   └── user_service.py      # Core rules, external APIs, computations
    │   ├── dao/                     # Data Access Object / Repository Layer
    │   │   ├── base.py              # Generic CRUD helpers
    │   │   └── user_dao.py          # Direct database queries (SQLAlchemy/SQLModel)
    │   └── models/                  # Database Domain Models
    │       └── user.py              # SQLAlchemy/ORM table definitions
    ├── .env                         # Local environment variables
    ├── .venv                        # Python venv environment
    ├── .gitignore
    └── requirements.txt
