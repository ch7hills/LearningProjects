from django.shortcuts import render
from django.http import HttpResponseRedirect
#from login.forms import LoginForm
from django.core.cache import caches
import datetime
from alwa.configfile import ConfigManager
from django.template.loader import get_template
from django.template import Context
from django.http import HttpResponse
from django.core.mail import EmailMessage
from django.shortcuts import redirect
from django.core.cache import caches
from django.template import RequestContext
from django.shortcuts import render_to_response,render
#from login.models.DosaRegistration import DosaRegistration
import random
import json as simplejson

configobj = ConfigManager()
#modelObj = DosaRegistration() 
file_cache = caches['filecache']

import os
from django.conf import settings
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

def loadContact(request):
    return render(request,'contact.html',{"om":1,"page":"contacts"})
# Create your views here.
def sendEmail(request):
    print "verifyOTP======================================>",request.POST
    name = request.POST.get("name",None)
    subject = request.POST.get("subject",None)
    message = request.POST.get("message",None)
    from django.core.mail import EmailMessage
    email = EmailMessage(subject,message, to=["biestdosa@gmail.com"])
    email.send()
    url='/SendGrid/'     
    import json as simplejson
    jsonData = simplejson.dumps({'url':url})
    print "user by id=>",jsonData
    return HttpResponse(jsonData, content_type="application/json")
