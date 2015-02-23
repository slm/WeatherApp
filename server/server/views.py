from django.http import HttpResponse
from models import tempMessage
from django.core import serializers
from django.views.decorators.csrf import csrf_exempt
import datetime
from faker import Factory
import random
import json

fake = Factory.create()



@csrf_exempt
def addMessage(request):

    text = request.POST['text']
    userlink = request.POST['userlink']
    username = request.POST['username']
    temperature = request.POST['temperature']

    tm = tempMessage()
    tm.text = text
    tm.dislike = 0
    tm.like = 0
    tm.sending_user_link = userlink
    tm.sending_user_name = username
    tm.temperature = temperature
    tm.pub_date = datetime.datetime.now()
    tm.isactive = False
    b = tm.save()

    return HttpResponse("added")



@csrf_exempt
def getRandom(request):

    temp = int(request.GET['t'])
    data = ""
    if temp < 0:
        ranged = tempMessage.objects.filter(temperature__lt=0, temperature__gt=-2500)
        data = serializers.serialize('json', ranged)

    elif -15 <= temp < -20:
        ranged = tempMessage.objects.filter(temperature__lt=-20, temperature__gt=-15)
        data = serializers.serialize('json', ranged)

    elif -10 <= temp < -15:
        ranged = tempMessage.objects.filter(temperature__lt=-10, temperature__gt=-15)
        data = serializers.serialize('json', ranged)

    elif -10 <= temp < -5:
        ranged = tempMessage.objects.filter(temperature__lt=-5, temperature__gt=-10)
        data = serializers.serialize('json', ranged)


    elif -5 <= temp < 0:
        ranged = tempMessage.objects.filter(temperature__lt=0, temperature__gt=-5)
        data = serializers.serialize('json', ranged)

    elif 0 <= temp < 5:
        ranged = tempMessage.objects.filter(temperature__lt=5, temperature__gt=0)
        data = serializers.serialize('json', ranged)

    elif 5 <= temp < 10:
        ranged = tempMessage.objects.filter(temperature__lt=10, temperature__gt=5)
        data = serializers.serialize('json', ranged)

    elif 10 <= temp < 15:
        ranged = tempMessage.objects.filter(temperature__lt=15, temperature__gt=10)
        data = serializers.serialize('json', ranged)

    elif 15 <= temp < 20:
        ranged = tempMessage.objects.filter(temperature__lt=20, temperature__gt=15)
        data = serializers.serialize('json', ranged)

    elif 20 <= temp < 25:
        ranged = tempMessage.objects.filter(temperature__lt=25, temperature__gt=20)
        data = serializers.serialize('json', ranged)

    elif 25 <= temp < 30:
        ranged = tempMessage.objects.filter(temperature__lt=30, temperature__gt=25)
        data = serializers.serialize('json', ranged)

    elif 30 <= temp < 35:
        ranged = tempMessage.objects.filter(temperature__lt=35, temperature__gt=30)
        data = serializers.serialize('json', ranged)

    elif 35 <= temp < 40:
        ranged = tempMessage.objects.filter(temperature__lt=40, temperature__gt=35)
        data = serializers.serialize('json', ranged)

    elif 40 <= temp < 45:
        ranged = tempMessage.objects.filter(temperature__lt=45, temperature__gt=40)
        data = serializers.serialize('json', ranged)

    elif 45 <= temp < 25000:
        ranged = tempMessage.objects.filter(temperature__lt=25000, temperature__gt=45)
        data = serializers.serialize('json', ranged)

    if len(data) == 0:
        ranged = tempMessage.objects.filter(temperature__lt=temp+5, temperature__gt=temp-5)
        data = ranged[random.randint(0,len(ranged))]
        json = serializers.serialize("json",data)
        return json

    json = serializers.serialize("json",ranged)

    return HttpResponse(json)

def index(request):
    return HttpResponse("Index")


@csrf_exempt
def addRandom(request):
    tm = tempMessage()
    tm.text = fake.sentence()
    tm.dislike = random.randint(0, 50)
    tm.like = random.randint(0, 50)
    tm.sending_user_link = fake.url()
    tm.sending_user_name = fake.user_name()
    tm.temperature = random.randint(-20, 50)
    tm.pub_date = datetime.datetime.now()
    tm.isactive = True
    b = tm.save()
    return HttpResponse("added")


''' if temp < 0:
        ranged = tempMessage.objects.filter(temperature__lt=0, temperature__gt=-2500)
        data = serializers.serialize('json', ranged)

    elif -15 <= temp < -20:
        ranged = tempMessage.objects.filter(temperature__lt=-20, temperature__gt=-15)
        data = serializers.serialize('json', ranged)

    elif -10 <= temp < -15:
        ranged = tempMessage.objects.filter(temperature__lt=-10, temperature__gt=-15)
        data = serializers.serialize('json', ranged)

    elif -10 <= temp < -5:
        ranged = tempMessage.objects.filter(temperature__lt=-5, temperature__gt=-10)
        data = serializers.serialize('json', ranged)


    elif -5 <= temp < 0:
        ranged = tempMessage.objects.filter(temperature__lt=0, temperature__gt=-5)
        data = serializers.serialize('json', ranged)

    elif 0 <= temp < 5:
        ranged = tempMessage.objects.filter(temperature__lt=5, temperature__gt=0)
        data = serializers.serialize('json', ranged)

    elif 5 <= temp < 10:
        ranged = tempMessage.objects.filter(temperature__lt=10, temperature__gt=5)
        data = serializers.serialize('json', ranged)

    elif 10 <= temp < 15:
        ranged = tempMessage.objects.filter(temperature__lt=15, temperature__gt=10)
        data = serializers.serialize('json', ranged)

    elif 15 <= temp < 20:
        ranged = tempMessage.objects.filter(temperature__lt=20, temperature__gt=15)
        data = serializers.serialize('json', ranged)

    elif 20 <= temp < 25:
        ranged = tempMessage.objects.filter(temperature__lt=25, temperature__gt=20)
        data = serializers.serialize('json', ranged)

    elif 25 <= temp < 30:
        ranged = tempMessage.objects.filter(temperature__lt=30, temperature__gt=25)
        data = serializers.serialize('json', ranged)

    elif 30 <= temp < 35:
        ranged = tempMessage.objects.filter(temperature__lt=35, temperature__gt=30)
        data = serializers.serialize('json', ranged)

    elif 35 <= temp < 40:
        ranged = tempMessage.objects.filter(temperature__lt=40, temperature__gt=35)
        data = serializers.serialize('json', ranged)

    elif 40 <= temp < 45:
        ranged = tempMessage.objects.filter(temperature__lt=45, temperature__gt=40)
        data = serializers.serialize('json', ranged)

    elif 45 <= temp < 25000:
        ranged = tempMessage.objects.filter(temperature__lt=25000, temperature__gt=45)
        data = serializers.serialize('json', ranged)
'''
