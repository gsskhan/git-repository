from fastapi import FastAPI, Request
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import JSONResponse

from app.api.v1.api import api_v1_router
from app.core.config import settings
from app.core.database import engine, Base

# Standard SQLAlchemy init (Use Alembic for real production migrations)
Base.metadata.create_all(bind=engine)

def create_application() -> FastAPI:
    application = FastAPI(
        title=settings.PROJECT_NAME,
        version=settings.PROJECT_VERSION,
        openapi_url=f"{settings.API_V1_STR}/openapi.json",
    )

    if settings.BACKEND_CORS_ORIGINS:
        application.add_middleware(
            CORSMiddleware,
            allow_origins=[str(origin) for origin in settings.BACKEND_CORS_ORIGINS],
            allow_credentials=True,
            allow_methods=["*"],
            allow_headers=["*"],
        )

    application.include_router(api_v1_router, prefix=settings.API_V1_STR)

    @application.exception_handler(Exception)
    async def global_exception_handler(request: Request, exc: Exception):
        return JSONResponse(
            status_code=500,
            content={"detail": "An unexpected internal server error occurred."},
        )

    return application

app = create_application()

@app.get("/health", tags=["Health Check"])
def health_check():
    return {"status": "healthy", "version": settings.PROJECT_VERSION}