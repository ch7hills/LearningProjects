import os
# Build paths inside the project like this: os.path.join(BASE_DIR, ...)
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
# Quick-start development settings - unsuitable for production
# See https://docs.djangoproject.com/en/1.9/howto/deployment/checklist/
# SECURITY WARNING: keep the secret key used in production secret!
SECRET_KEY = '3+wtwha*1acws2absq!^6f-=x07%g+a*7-zzx%&@_t!x+*h0-o'
# SECURITY WARNING: don't run with debug turned on in production!
DEBUG = True
ALLOWED_HOSTS = ['*']
# email configurations


EMAIL_USE_TLS = True
EMAIL_BACKEND = 'django_smtp_ssl.SSLEmailBackend'
MAILER_EMAIL_BACKEND = EMAIL_BACKEND
EMAIL_HOST = 'mail.bietdosa.com'
EMAIL_HOST_USER = 'info@bietdosa.com'
EMAIL_HOST_PASSWORD = 'dosa@123'
EMAIL_PORT = 465
DEFAULT_FROM_EMAIL = EMAIL_HOST_USER

# Application definition
INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    'login',
]
MIDDLEWARE_CLASSES = [
    'django.middleware.security.SecurityMiddleware',
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.common.CommonMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.auth.middleware.SessionAuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'django.middleware.clickjacking.XFrameOptionsMiddleware',
]
ROOT_URLCONF = 'dosa.urls'
TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [
            os.path.join(BASE_DIR, 'templates'),
            os.path.join(BASE_DIR, '/login/templates'),
        ],
        'APP_DIRS': True,
        'OPTIONS': {
            'context_processors': [
                'django.template.context_processors.debug',
                'django.template.context_processors.request',
                'django.contrib.auth.context_processors.auth',
                'django.contrib.messages.context_processors.messages',
            ],
        },
    },
]
WSGI_APPLICATION = 'dosa.wsgi.application'
# Database
# https://docs.djangoproject.com/en/1.9/ref/settings/#databases
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': 'bietdosa_dosa',
        'USER': 'root',
        'PASSWORD': 'mysql',
        'HOST': 'localhost',
        'PORT': '3306',
    },
    'users': {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': 'bietdosa_dosa',
        'USER': 'root',
        'PASSWORD': 'mysql',
        'HOST': 'localhost',
        'PORT': '3306',
    },
}
# Password validation
# https://docs.djangoproject.com/en/1.9/ref/settings/#auth-password-validators
AUTH_PASSWORD_VALIDATORS = [
    {
        'NAME': 'django.contrib.auth.password_validation.UserAttributeSimilarityValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.MinimumLengthValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.CommonPasswordValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.NumericPasswordValidator',
    },
]
# Internationalization
# https://docs.djangoproject.com/en/1.9/topics/i18n/
LANGUAGE_CODE = 'en-us'
TIME_ZONE = 'UTC'
USE_I18N = True
USE_L10N = True
USE_TZ = True
# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/1.9/howto/static-files/
STATIC_URL = '/static/'
if DEBUG: 
   #STATIC_ROOT = os.path.join(BASE_DIR, '/static')
   STATIC_ROOT = '/static/'
else:
   #STATIC_ROOT = os.path.join(BASE_DIR, 'static')
   STATIC_ROOT = '/static/'
STATIC_FILES_URL = '/static/files/'
STATICFILES_DIRS = (
    (os.path.join(BASE_DIR, 'static')),
    )
MEDIA_ROOT = BASE_DIR+STATIC_ROOT+"media/"
#MEDIA_ROOT = os.path.join(BASE_DIR,"media")
print "###################",MEDIA_ROOT
#MEDIA_ROOT = '/media'
CACHES = {
    
    'default': {
        'BACKEND': 'django.core.cache.backends.filebased.FileBasedCache',
        'LOCATION': MEDIA_ROOT+'/myCache',#'../logs/myCache',
        'OPTIONS': {
            'MAX_ENTRIES': 1000
        }
    },
    'filecache': {
        'BACKEND': 'django.core.cache.backends.filebased.FileBasedCache',
        'LOCATION': MEDIA_ROOT+'/myCache',#'../logs/myCache',
        'OPTIONS': {
            'MAX_ENTRIES': 1000
        }
    },
}
LOGGING = {
    'version': 1,
    'disable_existing_loggers': True,
    'formatters': {
        'simple': {
            'format': '[%(asctime)s] [%(levelname)s] [%(name)s]: %(message)s'
        },
    },
    'handlers': {
        'default': {
            'level': 'DEBUG',
            'class': 'logging.handlers.RotatingFileHandler',
            'filename': MEDIA_ROOT+'/logs/DOSA_request.log',
            'maxBytes': 1024*1024*5,
            'backupCount': 5,
            'formatter': 'simple'
        },
	'logfile': {
            'level': 'DEBUG',
            'class': 'logging.handlers.RotatingFileHandler',
            'filename': MEDIA_ROOT+'/logs/DOSA_console_request.log',
            'maxBytes': 1024*1024*5,
            'backupCount': 5,
            'formatter': 'simple'
        },
        'console': {
            'level': 'DEBUG',
            'class': 'logging.StreamHandler',
            #'stream': sys.stdout,
            'formatter': 'simple'
        },
        'request_handler': {
            'level': 'DEBUG',
            'class': 'logging.handlers.RotatingFileHandler',
            'filename': MEDIA_ROOT+'/logs/DOSA_request.log',
            'maxBytes': 1024*1024*5,
            'backupCount': 5,
            'formatter': 'simple'
        },
    },
 
}
APPEND_SLASH=False
