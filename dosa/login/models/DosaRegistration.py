from __future__ import unicode_literals
from django.db import connections    
from django.db import models
from dosa.configfile import ConfigManager

class DosaRegistration(models.Model):
    user_name = models.CharField(max_length=15, blank=True, null=True)
    father_name = models.CharField(max_length=15, blank=True, null=True)
    user_email = models.CharField(max_length=15, blank=True, null=True)
    user_dob = models.CharField(max_length=15, blank=True, null=True)
    user_doj = models.CharField(max_length=15, blank=True, null=True)
    address = models.TextField(blank=True, null=True)
    most_liked = models.CharField(max_length=15, blank=True, null=True)
    frequency_get_together = models.CharField(max_length=15, blank=True, null=True)
    approved = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'dosa_registration'
        
    def validateAD(self,username, password, request):
	print "###############################",request.POST
        configobj = ConfigManager()
        adminUser = configobj.getConfigValue(configobj.adminUser)
        adminPwd = configobj.getConfigValue(configobj.adminPwd)
        print "====================> ip address",self.get_client_ip(request)
        if(username == adminUser and password == adminPwd):
            request.session['uName'] = adminUser
            request.session['pwd'] = adminPwd
            return True
        else:
            return False
    def getUsers(self,request):
	from django.core.cache import caches
	file_cache = caches['filecache']
	result = file_cache.get("getUsers",None)
	if result in [None,"","None"]:
		sql = "SELECT * FROM dosa_registration Order by user_name"
		cursor = connections['users'].cursor()
		print sql
		cursor.execute(sql);
		result = self.dictfetchall(cursor)
		cursor.close()
		file_cache.set("getUsers",result,3600*4)
       	#print result
        return result
    #convert cursor date into dictionary
    def dictfetchall(self,cursor): 
        "Returns all rows from a cursor as a dict" 
        desc = cursor.description 
        return [
                dict(zip([col[0] for col in desc], row)) 
                for row in cursor.fetchall() 
        ]
    
    def insertNewUser(self,data):
        sql = "INSERT INTO `dosa_registration`(user_name, father_name, user_email, user_dob, user_doj, address, most_liked, frequency_get_together,phone_number,other_frequency,profile_pic)" 
        sql += " VALUES ('"+data.getlist('Name',[""])[0]+"','"+data.getlist('Father Name',[""])[0]+"','"+data.getlist('User Name',[""])[0]+"','"+data.getlist('Date of Birth',[""])[0]+"','"+data.getlist('Year of Joining',[""])[0]+"','"+data.getlist('Address',[""])[0]+"','"+data.getlist('The Professor',[""])[0]+"','"+data.getlist('frequency_get_together',[""])[0]+"','"+data.getlist('Phone NO',[""])[0]+"','"+data.getlist('Others',[""])[0]+"','"+data.getlist('pic_name',[""])[0]+"')"
        print sql
        cursor = connections['users'].cursor()
        cursor.execute(sql);
        result = self.dictfetchall(cursor)
        cursor.close()
        print result
        return result
    
    def get_client_ip(self,request):
        x_forwarded_for = request.META.get('HTTP_X_FORWARDED_FOR')
        if x_forwarded_for:
            ip = x_forwarded_for.split(',')[0]
        else:
            ip = request.META.get('REMOTE_ADDR')

        print ip
        return ip
    
    def updateUser(self,request,id):
        if("uName" in request.session):
            print request.session['uName']
            sql = "UPDATE dosa_registration set approved=1 WHERE id="+id
        cursor = connections['users'].cursor()
        print sql
        cursor.execute(sql);
        result = self.dictfetchall(cursor)
        cursor.close()
        print result
        return result
    
    def insertPost(self,request):
        print "=======================>",request.POST
        message = request.POST.get('post','')
        if "uName" in request.session:
            username = request.session["uName"]
        else: 
            username = request.POST.get('username','')

        mobile = request.POST.get('mobile','')
        result=[]
        if mobile and "uName" not in request.session:
            result=self.getUserByPhone(mobile)
            if len(result)>0:
                print result
                username = result[0]["user_name"]
            else:
                return False
        print message,"##############",username
        sql = "INSERT INTO dosa_posts(message,user_name,mobile,updated_time) VALUES('"+str(message)+"','"+str(username)+"','"+str(mobile)+"',NOW())"
        cursor = connections['users'].cursor()
        print sql
        cursor.execute(sql);
        result = self.dictfetchall(cursor)
        cursor.close()
        print result
        return result
        
    def getPosts(self):
        sql ="SELECT * FROM dosa_posts ORDER BY id DESC "
        cursor = connections['users'].cursor()
        print sql
        cursor.execute(sql);
        result = self.dictfetchall(cursor)
        cursor.close()
        print "getPosts<====>",result
        return result
    
    def getUserByPhone(self,phone):
        sql ="SELECT * FROM dosa_registration WHERE phone_number="+str(phone)
        cursor = connections['users'].cursor()
        print sql
        cursor.execute(sql);
        result = self.dictfetchall(cursor)
        cursor.close()
        print result
        return result
    def getUserById(self,id):
        if id:
		sql ="SELECT * FROM dosa_registration WHERE id="+str(id)
        	cursor = connections['users'].cursor()
        	#print sql
        	cursor.execute(sql);
        	result = self.dictfetchall(cursor)
       		cursor.close()
        	print result
        	return result
    
    def checkMobileAndEmail(self,request):
        result =""
        if("Phone NO" in request.POST):
            print request.POST['Phone NO']
            sql = "select count(*) as count from dosa_registration WHERE phone_number='"+request.POST['Phone NO']+"'"
            cursor = connections['users'].cursor()
            print sql
            cursor.execute(sql);
            mresult = self.dictfetchall(cursor)
            cursor.close()
            print mresult
            if mresult[0]["count"]>0:
                result = "mobile"
        if("User Name" in request.POST):
            print request.POST['Phone NO']
            sql = "select count(*) as count from dosa_registration WHERE user_email='"+request.POST['User Name']+"'"
            cursor = connections['users'].cursor()
            print sql
            cursor.execute(sql);
            uresult = self.dictfetchall(cursor)
            cursor.close()
            print uresult
            if uresult[0]["count"]>0:
                result = "email"
        return result
    
    def updateNewUser(self,data):
        sql = "UPDATE `dosa_registration` SET user_name='"+data.getlist('user_name',[""])[0]+"', father_name='"+data.getlist('father_name',[""])[0]+"', user_email='"+data.getlist('user_email',[""])[0]+"', user_dob='"+data.getlist('user_dob',[""])[0]+"', user_doj='"+data.getlist('user_doj',[""])[0]+"', address='"+data.getlist('address',[""])[0]+"', most_liked='"+data.getlist('most_liked',[""])[0]+"', frequency_get_together='"+data.getlist('frequency_get_together',[""])[0]+"',phone_number='"+data.getlist('phone_number',[""])[0]+"',other_frequency='"+data.getlist('other_frequency',[""])[0]+"',profile_pic='"+data.getlist('pic_name',[""])[0]+"' " 
        sql += " WHERE id='"+data.getlist('id',[""])[0]+"'"
        print sql
        cursor = connections['users'].cursor()
        cursor.execute(sql);
        result = self.dictfetchall(cursor)
        cursor.close()
        print result
        return result
     
    def getUsersPhoneNumbers(self):
        sql ="SELECT GROUP_CONCAT(phone_number) AS numbers FROM `dosa_registration` WHERE `approved`=1 and LENGTH(phone_number)=10"
        cursor = connections['users'].cursor()
        print sql
        cursor.execute(sql);
        result = self.dictfetchall(cursor)
        cursor.close()
        return result[0]["numbers"]
