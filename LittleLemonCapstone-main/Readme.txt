Test the following: 

Project Level URLS:

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/', include('restaurant.urls')),
    path('restaurant/booking/', include(router.urls)),
    #view booking go (restaurant/booking/tables/)
    path('auth/', include('djoser.urls')),
    path('auth/', include('djoser.urls.authtoken')),
   #user registration ('auth/users/)
]



App Level URLS:


urlpatterns = [
    
    path('', views.home, name='home'),
    path('menu-items/', MenuItemView.as_view()),
    path('menu-items/<int:pk>', SingleItemView.as_view()),
    #user registration, POST request using credentials to get a token
    path('api-token-auth/', obtain_auth_token),
    
    
]



superuser:
admin || root@123 || ada5529e39375f4542a24adfbc8c38778a77cfb7

users:
customer1 || 123qweC1 || 1c0930dc46fb5b503404cd5063065e10c660368e
