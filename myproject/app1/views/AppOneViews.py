from django.shortcuts import render
from app1.models.Table1 import TableModel
import logging
from django.template.defaultfilters import title
# Get an instance of a logger
logger = logging.getLogger(__name__)
# Create your views here.
def methodOneView(request):
    rec = TableModel.objects.all().order_by('-id')[0]
    print("##### count ##########")
    print(rec)
    print(rec.id+1)
    count = rec.id+1
    t=TableModel.objects.create(id=count,demodate='demo',name='Table1')
    t.save()
    logger.error("Hellow I am in method1")
    title ="Title: I am in APP1 methodOneView"
    metaDesc="MetaDescription: I am in APP1 methodOneView"
    url ="/app1/method1/"
    return render(request,'template1.html',{"title":title,"metaDesc":metaDesc,"url":url})
