from django.shortcuts import render
import json as simplejson
from django.http import HttpResponse
from django.shortcuts import render
from django.http import HttpResponseRedirect
from login.forms import LoginForm
from django.core.cache import caches
import datetime
from django.template.loader import get_template
from django.template import Context
from django.http import HttpResponse
from django.core.mail import EmailMessage
from django.shortcuts import redirect
from django.core.cache import caches
from django.template import RequestContext
from django.shortcuts import render_to_response,render
import random
from registerAwal.models.User import User
modelObj = User()
# Create your views here.
def load(request):
    return render(request,'Register.html',{"om":1,"page":"register"})
def storeRegistration(request):
    data = request.POST
    print "==============================>>>> \n",data
    check = modelObj.checkMobileAndEmail(request)
    url = "/register/load/"
    if check=="":
        print modelObj.insertNewUser(data)
        url = "/dashboard/load/"
    #file_cache.clear()
    jsonData = simplejson.dumps({'check':check,'url':url})
    return HttpResponse(jsonData, content_type="application/json")

def uploadAttachment(request):
    print request
    filename = request.FILES['uploadFile'].name
    filename = "".join([i if ord(i) < 128 else "" for i in filename])
    extension = filename.split('.')[-1]
    fileonlyname = filename.split('.')[0]
    import time
    from datetime import datetime
    curDate = datetime.now()
    extrafname=time.mktime(curDate.timetuple())
    filename = fileonlyname+str(int(extrafname))+"."+str(extension)
    extension = extension.lower()
    file = request.FILES['uploadFile']
    handle_uploaded_file(request.FILES['uploadFile'],filename)
    return HttpResponse(filename)
    #else:
    #return HttpResponse("RestrictedToUpload: "+str(ValidationMessage))
    
def handle_uploaded_file(upfile,filename):
    import os
    from django.conf import settings
    BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
    attUrl = BASE_DIR+'/../static/images/team/'
    print "attUrl-."+attUrl
    if not os.path.exists(attUrl):
        os.makedirs(attUrl)
        os.chmod(attUrl, 777)
    destination = open(attUrl+filename.encode('utf-8'), 'wb+')
    for chunk in upfile.chunks():
        destination.write(chunk)
    destination.close()