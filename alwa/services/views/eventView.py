from django.shortcuts import render

# Create your views here.
from django.shortcuts import render
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
from login.forms import LoginForm
configobj = ConfigManager()
#modelObj = DosaRegistration() 
file_cache = caches['filecache']

import os
from django.conf import settings
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
# Create your views here.
def LoadEvents(request):
    return render(request,'services.html',{"om":1,"page":"events"})