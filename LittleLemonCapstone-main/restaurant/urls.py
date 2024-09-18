from django.urls import path
from . import views
from .views import MenuItemView, SingleItemView, UserViewSet
from rest_framework.authtoken.views import obtain_auth_token


urlpatterns = [
    
    path('', views.home, name='home'),
    path('menu-items/', MenuItemView.as_view()),
    path('menu-items/<int:pk>', SingleItemView.as_view()),
    path('users/', UserViewSet.as_view({'get':'list'})),
    #user registration
    path('api-token-auth/', obtain_auth_token),
    
    
]