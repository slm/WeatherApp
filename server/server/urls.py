from django.conf.urls import patterns, url , include
from django.contrib import admin
from server.views import *

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'server.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),
    url(r'^$', index),
    url(r'^admin/', include(admin.site.urls)),
    url(r'^addmessage/',addMessage),
    url(r'^getmessage/',getRandom),
    url(r'^addrandom/',addRandom),






)
