from __future__ import unicode_literals

from django.db import models
from django.db import connections    
#from django.db import models

class User(models.Model):
    # Create your models here.
    def validateAD(self,username, password, request):
        adminUser = configobj.getConfigValue(configobj.adminUser)
        adminPwd = configobj.getConfigValue(configobj.adminPwd)
        print "====================> ip address",self.get_client_ip(request)
        if(username == adminUser and password == adminPwd):
            request.session['uName'] = adminUser
            request.session['pwd'] = adminPwd
            return True
        else:
            return False
    def getUsers(self):
        from django.core.cache import caches
        file_cache = caches['filecache']
        result = file_cache.get("getUsers",None)
        if result in [None,"","None"]:
            batch = self.getBatches()
            #sql = 'select usr.id as id,usr.full_name,usr.mobile,pm.period,pm.amount from users usr join payments pm on usr.id=pm.user_id where period in ("'+batch[0]['new_batch']+'","'+batch[0]['old_batch']+'")'
            sql = 'select usr.id as id,usr.full_name,usr.mobile,usr.voucher_id,pm.period,pm.id as voucherid from users usr left join payments pm on usr.id=pm.user_id '
            
            cursor = connections['users'].cursor()
            print sql
            cursor.execute(sql);
            result = self.dictfetchall(cursor)
            cursor.close()
        return result
    
    def getUser(self,id):
        sql = "SELECT * FROM users where id="+id
        cursor = connections['users'].cursor()
        print sql
        cursor.execute(sql);
        result = self.dictfetchall(cursor)
        cursor.close()
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
        intFieldList = ["uid","application_type","relation_type","martial_status","blood_group","voucher_id"]
        stringFieldList=["full_name","relation_name","email","dob","gender","address","introducer","relation_name","pic_name","occupation","alternate_mobile","mobile","teliphone_number"]
        
        glue=""
        sql1 = "INSERT INTO users("
        sql2 = ""
        columns=0
        matched=0
        for key,value in data.iteritems():
            print key,value
            if key in intFieldList:
                sql1 += glue+ key
                matched +=1
                if value not in ["None",None,"","NULL"]:
                    sql2 += glue+value
                else:
                    sql2 += glue+"0"
            elif key in stringFieldList:
                sql1 += glue+ key
                matched +=1
                sql2 += glue+"'"+value+"'"
            else:
                print key,"*********",value
            columns +=1
            glue=","
        sql=sql1+") VALUES("+sql2+")"
        print "SQL->",sql
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
    
    def deleteUser(self,request,id):
        if("uName" in request.session):
            print request.session['uName']
            sql = "DELETE FROM dosa_registration WHERE id="+id
            cursor = connections['users'].cursor()
            print sql
            cursor.execute(sql);
            result = self.dictfetchall(cursor)
            cursor.close()
            print result
            return result
    
    def savePayments(self,request,result,batch):
        print "=======================>",request.POST
        post = request.POST
        res = self.getPaymentsByUserId(result[0]['id'])
        type  = "renewal" if len(res)>0 else "new"
        desc = "renewal" if len(res)>0 else "new user registration payment"
        if type == "renewal":
            amount = 500
        else:
            amount = 1500 if result[0]['application_type'] ==1 else 1000
               
        sql ="insert into payments(type,created_date,updated_date,donar_name,receipt_header,mobile,amount,description,user_id,period) values("
        sql  += "'"+type+"',now(),now(),'"+result[0]['full_name']+"','registered_user','"+result[0]['mobile']+"',"+str(amount)+",'"+desc+"',"+str(result[0]['id'])+",'"+batch[0]["new_batch"]+"')"
        cursor = connections['users'].cursor()
        print sql
        cursor.execute(sql);
        result1 = self.dictfetchall(cursor)
        cursor.close()
        res = self.getPaymentsByUserId(result[0]['id'])
        if type == "new":
            self.updateNewUserPayment(res)
        return res
    def updateNewUserPayment(self,res):
        sql ="update users set voucher_id="+str(res[0]["id"])+" WHERE id ="+str(res[0]["user_id"])
        cursor = connections['users'].cursor()
        print sql
        cursor.execute(sql);
        result = self.dictfetchall(cursor)
        cursor.close()
        return result
    
    def getPaymentsByUserId(self,userId):
        sql ="SELECT * FROM payments WHERE user_id ="+str(userId)+" order by id desc limit 1"
        cursor = connections['users'].cursor()
        print sql
        cursor.execute(sql);
        result = self.dictfetchall(cursor)
        cursor.close()
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
    def getBatches(self):
        if id:
            sql ='select concat(DATE_FORMAT(now()- INTERVAL 1 YEAR,"%y"),"-",DATE_FORMAT(now(),"%y")) as old_batch,concat(DATE_FORMAT(now(),"%y"),"-",DATE_FORMAT(now() + INTERVAL 1 YEAR,"%y")) as new_batch,concat(DATE_FORMAT(now(),"%Y"),"-",DATE_FORMAT(now() + INTERVAL 1 YEAR,"%Y")) as period;'
            cursor = connections['users'].cursor()
            print sql
            cursor.execute(sql);
            result = self.dictfetchall(cursor)
            cursor.close()
            print result
            return result
    
    def checkMobileAndEmail(self,request):
        result =""
        if("mobile" in request.POST):
            print request.POST['mobile']
            sql = "select count(*) as count from users WHERE mobile='"+request.POST['mobile']+"'"
            cursor = connections['users'].cursor()
            print sql
            cursor.execute(sql);
            mresult = self.dictfetchall(cursor)
            cursor.close()
            print mresult
            if mresult[0]["count"]>0:
                result = "mobile"
        if("email" in request.POST):
            print request.POST['email']
            sql = "select count(*) as count from users WHERE email='"+request.POST['email']+"'"
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