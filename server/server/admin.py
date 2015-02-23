from django.contrib import admin
from server.models import tempMessage
from django import forms


class messageAdmin(admin.ModelAdmin):
    list_display = ("text",'isactive','like','dislike','sending_user_link','sending_user_name','temperature','pub_date')
    
admin.site.register(tempMessage,messageAdmin)