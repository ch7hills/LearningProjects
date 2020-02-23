from django.shortcuts import render
from registerAwal.models.User import User
modelObj = User()
# Create your views here.
def load(request):
    batch = modelObj.getBatches()
    users = modelObj.getUsers();
    return render(request,'dashboard.html',{"om":1,"page":"dashboard","batch":batch[0],"users":users})