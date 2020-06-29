from django.urls import path
from . import views

urlpatterns = [
    path ('' , views.welcome ),
    path ('index' , views.welcome ),
    path ('home' , views.welcome ),
]