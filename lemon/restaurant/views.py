# from django.http import HttpResponse
from django.shortcuts import render
from django.views.decorators.csrf import csrf_exempt

from .models import Menu
from django.core import serializers
from .models import Booking
from datetime import datetime
import json
from django.http import JsonResponse  # Add this import

# from .forms import BookingForm

def home(request):
    return render(request, 'index.html')

def about(request):
    return render(request, 'about.html')

# Uncomment the book function if needed
# def book(request):
#     form = BookingForm()
#     if request.method == 'POST':
#         form = BookingForm(request.POST)
#         if form.is_valid():
#             form.save()
#     context = {'form':form}
#     return render(request, 'book.html', context)

@csrf_exempt
def bookings(request):
    if request.method == "POST":
        data = json.loads(request.body)
        exist = Booking.objects.filter(reservation_date=data['reservation_date']).filter(
            reservation_slot=data['reservation_slot']).exists()
        
        if not exist:
            booking = Booking(
                first_name=data['first_name'],
                reservation_date=data['reservation_date'],
                reservation_slot=data['reservation_slot'],
            )
            booking.save()
        else:
            return JsonResponse({"error": 1}, content_type='application/json')

    date = request.GET.get('date', datetime.today().date())
    bookings = Booking.objects.filter(reservation_date=date)
    booking_json = serializers.serialize('json', bookings)

    return JsonResponse(booking_json, safe=False, content_type='application/json')


def menu(request):
    menu_data = Menu.objects.all()
    main_data = {"menu": menu_data}
    return render(request, 'menu.html', {"menu": main_data})


def display_menu_item(request, pk=None): 
    if pk: 
        menu_item = Menu.objects.get(pk=pk) 
    else: 
        menu_item = "" 
    return render(request, 'menu_item.html', {"menu_item": menu_item})
