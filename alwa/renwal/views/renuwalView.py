from django.shortcuts import render
from registerAwal.models.User import User
#from django.core.paginator import Paginator
from django.shortcuts import render
from django.core.paginator import Paginator, EmptyPage, PageNotAnInteger
modelObj = User()
# Create your views here.
def load(request):
    batch = modelObj.getBatches()
    userList = []
    useDict = {}
    users = modelObj.getUsers();
    for user in users:
        user[user["period"]]= user["voucherid"]
        if user["id"] in useDict.keys():
            user1 = useDict[user["id"]]
            user1[user["period"]]= user["voucherid"]
            user1["pay"]= False
            useDict[user["id"]]=user1
            
        else:
            user[user["period"]]= user["voucherid"]
            user["pay"]= True
            useDict[user["id"]]=user
    paginator = Paginator(useDict.values(), 10) # Show 25 contacts per page
    page = request.GET.get('page',1)
    try:
        pagelist = paginator.page(page)
    except PageNotAnInteger:
    # If page is not an integer, deliver first page.
        pagelist = paginator.page(1)
    except EmptyPage:
    # If page is out of range (e.g. 7777), deliver last page of results.
        pagelist = paginator.page(paginator.num_pages)

    print page,"----------->useDict->",pagelist.object_list
    return render(request,'Renuwal.html',{"om":1,"page":"renewal","batch":batch[0],"users":pagelist})
def payment(request):
    userid = request.POST.get('userid','')
    result = modelObj.getUser(userid)
    batch = modelObj.getBatches()
    result1 = modelObj.savePayments(request,result,batch)
    fromYear = batch[0]['period'].split("-")[0]
    toYear = batch[0]['period'].split("-")[1]
    amount = result1[0]['amount']
    return render(request,'PrintReceipt.html',{"result":result,"result1":result1[0],"amount":amount,"fromYear":fromYear,"toYear":toYear,"createdDate":result1[0]['created_date'].strftime("%m/%d/%Y %H:%M")})