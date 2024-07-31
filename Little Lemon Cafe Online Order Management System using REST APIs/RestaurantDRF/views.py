from django.shortcuts import get_object_or_404
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes, throttle_classes
from rest_framework import generics, status
from decimal import Decimal
from django.core.paginator import Paginator, EmptyPage
from rest_framework.throttling import AnonRateThrottle, UserRateThrottle
from rest_framework.permissions import IsAuthenticated, IsAdminUser
from django.contrib.auth.models import User, Group
from django.http import HttpResponse, HttpResponseBadRequest, HttpResponseNotFound, JsonResponse
from .models import MenuItem, Cart, Order, OrderItem, Category
from .serializers import MenuItemSerializer, ManagerListSerializer, CartSerializer, OrderSerializer, CartAddSerializer, CartRemoveSerializer, SingleOrderSerializer, OrderPutSerializer, CategorySerializer
from .paginations import MenuItemListPagination
from .permissions import IsManager, IsDeliveryCrew
import math
from datetime import date


# Create your views here.
class MenuItemListView(generics.ListCreateAPIView):
    throttle_classes = [AnonRateThrottle, UserRateThrottle]
    queryset = MenuItem.objects.all()
    serializer_class = MenuItemSerializer
    search_fields = ['title', 'category__title']
    ordering_fields = ['price', 'category']
    pagination_class = MenuItemListPagination

    def get_permissions(self):
        if self.request.method != 'GET':
            return [IsAuthenticated(), IsAdminUser()]
        return []    
    
class CategoryView(generics.ListCreateAPIView):
    throttle_classes = [AnonRateThrottle, UserRateThrottle]
    serializer_class = CategorySerializer
    queryset = Category.objects.all()
    permission_classes = [IsAdminUser]

class MenuItemDetailView(generics.RetrieveUpdateDestroyAPIView):
    throttle_classes = [AnonRateThrottle, UserRateThrottle]
    queryset = MenuItem.objects.all()
    serializer_class = MenuItemSerializer

    def get_permissions(self):
        if self.request.method == 'PATCH':
            return [IsAuthenticated(), IsManager() | IsAdminUser()]
        if self.request.method == "DELETE":
            return [IsAuthenticated(), IsAdminUser()]
        return [IsAuthenticated()]

    def patch(self):
        menuitem = MenuItem.objects.get(pk=self.kwargs['pk'])
        menuitem.featured = not menuitem.featured
        menuitem.save()
        return JsonResponse({'message': f'Featured status of {menuitem.title} changed to {menuitem.featured}'}, status=status.HTTP_200_OK)

class ManagersListView(generics.ListCreateAPIView):
    throttle_classes = [AnonRateThrottle, UserRateThrottle]
    queryset = User.objects.filter(groups__name='Managers')
    serializer_class = ManagerListSerializer
    permission_classes = [IsAuthenticated, IsManager | IsAdminUser]

    def post(self, request, *args, **kwargs):
        username = request.data['username']
        if username:
            user = get_object_or_404(User, username=username)
            managers = Group.objects.get(name='Managers')
            managers.user_set.add(user)
            return JsonResponse({'message': f'User added to Managers group'}, status=status.HTTP_201_CREATED)
        
class ManagersRemoveView(generics.DestroyAPIView):
    throttle_classes = [AnonRateThrottle, UserRateThrottle]
    serializer_class = ManagerListSerializer
    permission_classes = [IsAuthenticated, IsManager | IsAdminUser]
    queryset = User.objects.filter(groups__name='Managers')

    def delete(self):
        pk = self.kwargs['pk']
        user = get_object_or_404(User, pk=pk)
        managers = Group.objects.get(name='Managers')
        managers.user_set.remove(user)
        return JsonResponse({'message': 'User removed from Managers group'}, status=status.HTTP_200_OK)    
    
class DeliveryCrewListView(generics.ListCreateAPIView):
    throttle_classes = [AnonRateThrottle, UserRateThrottle]
    queryset = User.objects.filter(groups__name='Delivery crew')
    serializer_class = ManagerListSerializer
    permission_classes = [IsAuthenticated, IsManager | IsAdminUser]

    def post(self, request, *args, **kwargs):
        username = request.data['username']
        if username:
            user = get_object_or_404(User, username=username)
            crew = Group.objects.get(name='Delivery crew')
            crew.user_set.add(user)
            return JsonResponse({'message': f'User added to Delivery Crew group'}, status=status.HTTP_201_CREATED)

class DeliveryCrewRemoveView(generics.DestroyAPIView):
    throttle_classes = [AnonRateThrottle, UserRateThrottle]
    serializer_class = ManagerListSerializer
    permission_classes = [IsAuthenticated, IsManager | IsAdminUser]
    queryset = User.objects.filter(groups__name='Delivery crew')

    def delete(self, request, *args, **kwargs):
        pk = self.kwargs['pk']
        user = get_object_or_404(User, pk=pk)
        crew = Group.objects.get(name='Delivery crew')
        crew.user_set.remove(user)
        return JsonResponse({'message': 'User removed from Delivery crew group'}, status=status.HTTP_200_OK)
    
class CartOperationsView(generics.ListCreateAPIView):
    throttle_classes = [AnonRateThrottle, UserRateThrottle]
    serializer_class = CartSerializer
    permission_classes = [IsAuthenticated]

    def get_queryset(self, *args, **kwargs):
        cart = Cart.objects.filter(user=self.request.user)
        return cart

    def post(self, request, *arg, **kwargs):
        serialized_item = CartAddSerializer(data=request.data)
        serialized_item.is_valid(raise_exception=True)
        id = request.data['menuitem']
        quantity = request.data['quantity']
        item = get_object_or_404(MenuItem, id=id)
        price = int(quantity) * item.price
        try:
            Cart.objects.create(user=request.user, quantity=quantity, unit_price=item.price, price=price, menuitem_id=id)
        except:
            return JsonResponse({'message': 'Item already in cart'}, status=status.HTTP_409_CONFLICT)
        return JsonResponse({'message': 'Item added to cart!'}, status=status.HTTP_201_CREATED)
    
    def delete(self, request, *arg, **kwargs):
        if request.data['menuitem']:
            serialized_item = CartRemoveSerializer(data=request.data)
            serialized_item.is_valid(raise_exception=True)
            menuitem = request.data['menuitem']
            cart = get_object_or_404(Cart, user=request.user, menuitem=menuitem)
            cart.delete()
            return JsonResponse({'message': 'Item removed from cart'}, status=status.HTTP_200_OK)
        else:
            Cart.objects.filter(user=request.user).delete()
            return JsonResponse({'message': 'All Items removed from cart'}, status=status.HTTP_201_CREATED)
        
class OrderOperationsView(generics.ListCreateAPIView):
    throttle_classes = [AnonRateThrottle, UserRateThrottle]
    serializer_class = OrderSerializer

    def get_queryset(self, *args, **kwargs):
        if self.request.user.groups.filter(name='Managers').exists() or self.request.user.is_superuser:
            query = Order.objects.all()
        elif self.request.user.groups.filter(name='Delivery crew').exists():
            query = Order.objects.filter(delivery_crew=self.request.user)
        else:
            query = Order.objects.filter(user=self.request.user)
        return query

    def get_permissions(self):
        if self.request.method == 'GET' or self.request.method == 'POST':
            return [IsAuthenticated()]
        else:
            return [IsAuthenticated(), IsManager() | IsAdminUser()]

    def post(self, request, *args, **kwargs):
        cart = Cart.objects.filter(user=request.user)
        x = cart.values_list()
        if len(x) == 0:
            return HttpResponseBadRequest()
        total = math.fsum([float(x[-1]) for x in x])
        order = Order.objects.create(user=request.user, status=False, total=total, date=date.today())
        for i in cart.values():
            menuitem = get_object_or_404(MenuItem, id=i['menuitem_id'])
            orderitem = OrderItem.objects.create(order=order, menuitem=menuitem, quantity=i['quantity'])
            orderitem.save()
        cart.delete()
        return JsonResponse({'message': 'Your order has been placed! Your order number is {}'.format(str(order.id))}, status=status.HTTP_201_CREATED)

class SingleOrderView(generics.ListCreateAPIView):
    throttle_classes = [AnonRateThrottle, UserRateThrottle]
    serializer_class = SingleOrderSerializer

    def get_permissions(self):
        order = Order.objects.get(pk=self.kwargs['pk'])
        if self.request.user == order.user and self.request.method == 'GET':
            return [IsAuthenticated()]
        elif self.request.method == 'PUT' or self.request.method == 'DELETE':
            return [IsAuthenticated(), IsManager() | IsAdminUser()]
        else:
            return [IsAuthenticated(), IsDeliveryCrew() | IsManager() | IsAdminUser()]

    def get_queryset(self, *args, **kwargs):
        query = OrderItem.objects.filter(order_id=self.kwargs['pk'])
        return query

    def patch(self, request, *args, **kwargs):
        order = Order.objects.get(pk=self.kwargs['pk'])
        order.status = not order.status
        order.save()
        return JsonResponse({'message': f'Status of order #{order.id} changed to {order.status}'}, status=status.HTTP_200_OK)

    def put(self, request, *args, **kwargs):
        serialized_item = OrderPutSerializer(data=request.data)
        serialized_item.is_valid(raise_exception=True)
        order_pk = self.kwargs['pk']
        crew_pk = request.data['delivery_crew']
        order = get_object_or_404(Order, pk=order_pk)
        crew = get_object_or_404(User, pk=crew_pk)
        order.delivery_crew = crew
        order.save()
        return JsonResponse({'message': f'{crew.username} was assigned to order #{order.id}'}, status=status.HTTP_201_CREATED)

    def delete(self, request, *args, **kwargs):
        order = Order.objects.get(pk=self.kwargs['pk'])
        order_number = str(order.id)
        order.delete()
        return JsonResponse({'message': f'Order #{order_number} was deleted'}, status=status.HTTP_200_OK)


    




