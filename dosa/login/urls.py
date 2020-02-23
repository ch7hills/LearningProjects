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
from django.conf.urls import url
import login.views.loginView
urlpatterns = [
    url(r'^Login/$', login.views.loginView.checkCredentials),
    url(r'^SendGrid/$', login.views.loginView.sendGrid),
    url(r'^logout/$', login.views.loginView.logout),
    url(r'^registration/$', login.views.loginView.registration),
    url(r'^addattachment/$', login.views.loginView.uploadAttachment),
    url(r'^approve/(?P<id>\d+)$', login.views.loginView.userApprove),
    url(r'^load_aboutus/', login.views.loginView.aboutUS),
    url(r'^load_gallery/', login.views.loginView.gallery),
    url(r'^load_login/', login.views.loginView.login),
    url(r'^load_registration/', login.views.loginView.loadReg),
    url(r'^load_contactus/', login.views.loginView.contactus),
    url(r'^message-board/', login.views.loginView.messageBoard),
    url(r'^createpost/', login.views.loginView.createpost),
    url(r'^generateOTP/$', login.views.loginView.generateOTP),
    url(r'^verifyOTP/$', login.views.loginView.verifyOTP),
    url(r'^editAccount/$', login.views.loginView.editAccount),
    url(r'^sendMail/$', login.views.loginView.sendEmail),
   ]


