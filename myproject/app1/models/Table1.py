from django.db import models


# Create your models here.
class TableModel(models.Model):
    id = models.AutoField(primary_key=True)
    demodate = models.CharField(max_length=50)
    name = models.CharField(max_length=250)
    
    class Meta:
        managed=True
        db_table="demo_table"
        
    def __str__(self):
        """A string representation of the model."""
        return "Instance of TableModel()"
    
    def __unicode__(self):
        return self.type