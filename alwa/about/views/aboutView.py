from django.shortcuts import render

# Create your views here.
def load(request):
    return render(request,'about.html',{"om":1,"page":"aboutus"})
