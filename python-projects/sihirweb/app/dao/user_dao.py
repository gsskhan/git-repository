from sqlalchemy.orm import Session
from app.models.user import UserModel
from app.schemas.users import UserCreate

class UserDAO:
    def __init__(self, db: Session):
        self.db = db

    def get_by_id(self, user_id: int) -> UserModel:
        return self.db.query(UserModel).filter(UserModel.id == user_id).first()

    def get_by_email(self, email: str) -> UserModel:
        return self.db.query(UserModel).filter(UserModel.email == email).first()

    def create_user(self, schema: UserCreate, hashed_password: str) -> UserModel:
        db_user = UserModel(
            email=schema.email,
            full_name=schema.full_name,
            hashed_password=hashed_password
        )
        self.db.add(db_user)
        self.db.commit()
        self.db.refresh(db_user)
        return db_user
