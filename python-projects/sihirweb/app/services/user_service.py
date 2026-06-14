from fastapi import HTTPException, status
from sqlalchemy.orm import Session
from app.dao.user_dao import UserDAO
from app.schemas.users import UserCreate

class UserService:
    def __init__(self, db: Session):
        self.user_dao = UserDAO(db)

    def register_user(self, payload: UserCreate):
        # 1. Evaluate business constraints
        existing_user = self.user_dao.get_by_email(payload.email)
        if existing_user:
            raise HTTPException(
                status_code=status.HTTP_400_BAD_REQUEST, 
                detail="This email is already registered."
            )
        
        # 2. Mutate system data patterns (In production, replace with real pass hashing)
        fake_hashed_password = payload.password + "notsecurehash"
        
        # 3. Offload storage operations to the DAO
        return self.user_dao.create_user(payload, fake_hashed_password)

    def get_user_by_id(self, user_id: int):
        return self.user_dao.get_by_id(user_id)
