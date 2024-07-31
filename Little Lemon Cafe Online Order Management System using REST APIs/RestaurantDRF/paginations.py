from rest_framework import pagination

class MenuItemListPagination(pagination.PageNumberPagination):
    page_size = 10
    page_size_query_param = 'perpage'
    max_page_size = 30
    page_query_param = 'page'