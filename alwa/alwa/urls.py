"""alwa URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.9/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.contrib import admin
from django.conf.urls import url
from django.contrib import admin
from django.conf.urls import patterns, url, include
from django.views.generic import TemplateView
import login.views.loginView
import services.views.eventView
from django.conf import settings

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^$', login.views.loginView.default),
    url(r'^login/', include('login.urls')),
    url(r'^contact/', include('contact.urls')),
    url(r'^events/', include('services.urls')),
    url(r'^about/', include('about.urls')),
    url(r'^dashboard/', include('dashboard.urls')),
    url(r'^register/', include('registerAwal.urls')),
    url(r'^renewal/', include('renwal.urls')),
]
