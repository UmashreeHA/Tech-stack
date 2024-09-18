from django.test import TestCase
from restaurant.models import Menu, Booking

class MenuTest(TestCase):
    def test_get_item(self):
        item = Menu.objects.create(Title = 'Fish', Price=16.99, Inventory=10)
        itemstr = item.get_item()
        
        self.assertEqual(itemstr, 'Fish : 16.99')
        
        