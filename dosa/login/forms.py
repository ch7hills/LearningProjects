"""
 * This computer program is the confidential information and proprietary trade
 * secret of NetEnrich, Inc. Possessions and use of this program must
 * conform strictly to the license agreement between the user and
 * NetEnrich, Inc., and receipt or possession does not convey any rights
 * to divulge, reproduce, or allow others to use this program without specific
 * written authorization of NetEnrich, Inc.
 * 
 * Copyright  2016 NetEnrich, Inc. All Rights Reserved.
"""
from django import forms

class LoginForm(forms.Form):
    userName = forms.CharField(max_length=100)
    password = forms.CharField(max_length=100)