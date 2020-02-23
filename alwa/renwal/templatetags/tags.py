from django import template                                                                                                                                                        
from django.template.defaultfilters import stringfilter

register = template.Library()
@register.filter
def get_item(dictionary, key):
    if key in dictionary.keys():
        return "#"+str(dictionary.get(key))
    else:
        return ""
