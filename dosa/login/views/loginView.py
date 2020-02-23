from django.shortcuts import render
from django.http import HttpResponseRedirect
from login.forms import LoginForm
from django.core.cache import caches
import datetime
from dosa.configfile import ConfigManager
from django.template.loader import get_template
from django.template import Context
from django.http import HttpResponse
from django.core.mail import EmailMessage
from django.shortcuts import redirect
from django.core.cache import caches
from django.template import RequestContext
from django.shortcuts import render_to_response,render
from login.models.DosaRegistration import DosaRegistration
import random
import json as simplejson

configobj = ConfigManager()
modelObj = DosaRegistration() 
file_cache = caches['filecache']

import os
from django.conf import settings
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

def checkCredentials(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        username = username.strip().lower()
        password = request.POST.get('password')
        finalRes = modelObj.validateAD(username, password, request)
        return redirect('/SendGrid/')
    else :
        form = LoginForm()
        if 'uName' in request.session:
            SHomeUrl = configobj.getConfigValue(configobj.SHomeUrl)
            return HttpResponseRedirect(SHomeUrl)
        curYear = datetime.datetime.now().year
        #release = comObj.getCurrentVersion()
        return render(request, 'login.html', {'form': form })


def logout(request):
    request.session.flush()
    return render(request,'navigation.html',{"om":"siva"})


def sendGrid(request):  
    users = modelObj.getUsers(request)
    result={}
    from collections import OrderedDict
    for user in users:
	print user,"=================>user",user["user_doj"]
        if user["user_doj"] and int(user["user_doj"]) in result.keys():
            result[int(user["user_doj"])].append(user)
        elif user["user_doj"]:
            result[int(user["user_doj"])]=[]
            result[int(user["user_doj"])].append(user)  
        #print user["user_doj"],"=================>user",user["user_doj"]
    print result
    result_order =   OrderedDict(sorted(result.items(), key=lambda t: t[0]))
    for key,val in result.iteritems():
        print key,"<===============>",val
    return render(request,'summary-grid.html', {"result":result_order})

def default(request):
    return render(request,'navigation.html',{"om":"siva"})
def registration(request):
    data = request.POST
    print "==============================>>>>",data
    check = modelObj.checkMobileAndEmail(request)
    url = "/registration/"
    if check=="":
        print modelObj.insertNewUser(data)
        url = "/SendGrid/"
    file_cache.clear()
    jsonData = simplejson.dumps({'check':check,'url':url})
    #return HttpResponse(jsonData, content_type="application/json")
    #jsonData = simplejson.dumps({'msps': partners, 'clients': clients})
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
    #ValidationMessage = ''
    configobj = ConfigManager()
    gallery = request.POST.get('gallery',None)
    if gallery:
    	handle_uploaded_file_gallery(request.FILES['uploadFile'],filename)
    else:
    	handle_uploaded_file(request.FILES['uploadFile'],filename)
    return HttpResponse(filename)
    #else:
    return HttpResponse("RestrictedToUpload: "+str(ValidationMessage))
    
def handle_uploaded_file(upfile,filename):
    import os
    from django.conf import settings
    BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
    attUrl = BASE_DIR+'/../static/images/team/'
    if not os.path.exists(attUrl):
        os.makedirs(attUrl)
        os.chmod(attUrl, 777)
    destination = open(attUrl+filename.encode('utf-8'), 'wb+')
    for chunk in upfile.chunks():
        destination.write(chunk)
    destination.close()
    
def handle_uploaded_file_gallery(upfile,filename):
    import os
    from django.conf import settings
    BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
    attUrl = BASE_DIR+'/../static/images/portfolio/'
    if not os.path.exists(attUrl):
        os.makedirs(attUrl)
        os.chmod(attUrl, 777)
    destination = open(attUrl+filename.encode('utf-8'), 'wb+')
    for chunk in upfile.chunks():
        destination.write(chunk)
    destination.close()
    
def userApprove(request,id):
    modelObj.updateUser(request,id)
    return HttpResponse("success")
def aboutUS(request):
    return render(request,'about.html',{'om':'siva',})
def gallery(request):
    attUrl = BASE_DIR+'/../static/images/portfolio/'
    images = os.listdir(attUrl)
    image_list=[]
    for image in images:
        image_list.append("/static/images/portfolio/"+image)
    print image_list
    return render(request,'gallery.html',{"images":image_list})
def login(request):
    return render(request,'login.html',{"om":"siva"})
def loadReg(request):
    return render(request,'registration.html',{"om":"siva"})
def contactus(request):
    return render(request,'contact.html',{"om":"siva"})
def messageBoard(request):
    posts=modelObj.getPosts()
    print "================>",posts
    return render(request,'message-board.html',{'posts':posts,"flag":1})
def createpost(request):
    result= modelObj.insertPost(request)
    if result == False:
        return redirect('/load_registration/')
    user_mobile_numbers = modelObj.getUsersPhoneNumbers()
    message = request.POST.get('post','')
    import requests
    url="http://smsstreet.in/websms/sendsms.aspx?userid=bidosa&password=abcd123&sender=BIDOSA&mobileno="+str(user_mobile_numbers)+"&msg="+str(message)
    apiResponceOrg = requests.get(url,headers={}, verify=False)
    return redirect('/message-board/')
def generateOTP(request):
    otp = random.randint(1000,9999)
    print "=================otp==========>",request.POST,otp
    phone = request.POST['phone']
    record = {}
    record["phone"]=phone
    record["otp"]=otp
    record[phone]=otp
    phone=request.POST.get("phone",None)
    uid=request.POST.get("uid",None)
    if phone and "uName" not in request.session:
        result=modelObj.getUserByPhone(phone)
        if len(result)>0:
	    import requests
            tokenHed = {}
            url="http://smsstreet.in/websms/sendsms.aspx?userid=bidosa&password=abcd123&sender=BIDOSA&mobileno="+str(phone)+"&msg=Your OTP is number:"+str(otp)
            apiResponceOrg = requests.get(url,headers=tokenHed, verify=False)
            print apiResponceOrg.content
            print result
        else:
	    return redirect('/load_registration/')
            #return HttpResponse(simplejson.dumps({"url":"/load_registration","record": {}}), content_type="application/json")
    #cache_phone = file_cache.get("cache_phone",None)
    if phone:
        file_cache.set("cache_phone",record,3600)
    jsonData = simplejson.dumps({'record': record,"url":""})
    return HttpResponse(jsonData, content_type="application/json")
    #return redirect('/SendGrid')
def verifyOTP(request):
    print "verifyOTP======================================>",request.POST
    phone = request.POST.get("otp_phone",None)
    otp = request.POST.get("otp",None)
    userid = request.POST.get("userid",None)
    flag = request.POST.get("flag",None)
    user=[{}]
    url=""
    print "frmdopost================",flag
    if flag == 0:
	""" send grid """
	#url='/SendGrid/'
        print "do post=============================="
        if "pwd" in request.session.keys():
            print "Admin=============userid",userid
	    user=modelObj.getUserById(userid)
        else:   
            cache_phone=file_cache.get("cache_phone",None)
            print otp,"cache_phone=>",cache_phone,"cache_phone.values()",cache_phone.values()
            if int(otp) in cache_phone.values():
                print "cache========",cache_phone
                user=modelObj.getUserById(userid)   
	    else:
                url='/SendGrid/' 
    else:
	""" message grid """
	#url='/message-board/'
        if "pwd" in request.session.keys() and userid:
            print "Admin=============userid",userid
            user=modelObj.getUserById(userid)
        else:   
            cache_phone=file_cache.get("cache_phone",None)
            print otp,"cache_phone=>",cache_phone,"cache_phone.values()",cache_phone.values()
            if int(otp) in cache_phone.values():
                print "cache========",cache_phone
                #user=modelObj.getUserById(userid)
		user==modelObj.getUserByPhone(phone)  
	    else:
                url='/message-board/' 
    if user:
	jsonData = simplejson.dumps({'user': user[0],'url':url,'flag':flag})
    else:
	jsonData = simplejson.dumps({'url':url,'flag':flag})
    print "user by id=>",jsonData
    return HttpResponse(jsonData, content_type="application/json")

def sendEmail(request):
    print "verifyOTP======================================>",request.POST
    name = request.POST.get("name",None)
    subject = request.POST.get("subject",None)
    message = request.POST.get("message",None)
    from django.core.mail import EmailMessage
    email = EmailMessage(subject,message, to=["info@bietdosa.com"])
    email.send()
    url='/SendGrid/'     
    import json as simplejson
    jsonData = simplejson.dumps({'url':url})
    print "user by id=>",jsonData
    return HttpResponse(jsonData, content_type="application/json")

def editAccount(request):
    data = request.POST
    print "==============================>>>>",data
    print modelObj.updateNewUser(data)
    url = "/SendGrid/"
    jsonData = simplejson.dumps({"url":url})
    return HttpResponse(jsonData, content_type="application/json")

def accounts(request):
    return render(request,'accounts.html',{'om':'siva',})
