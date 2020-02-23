from ConfigParser import ConfigParser
import os

class ConfigManager(object):
	#configuration file
	BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
	_conf_file = BASE_DIR+'/dosa/params.cfg'
	#get param
	VALUE='message'
	adminUser = "adminUser"
	adminPwd ="adminPwd"
	homeurl = "HOMEURL"
	loginurl = 'LOGINURL'
	SHomeUrl = 'SHomeUrl'
	
	def __init__(self):
		
		self.cfg = ConfigParser()
		self.cfg.read(self._conf_file)
		
	#get config parameters for login dictionary
	def getConfigValue(self, key):
		try:
			return self.cfg.get("Login", key)
		except:
			raise Exception('not found')
	#get config parameters for common dictionary
	def getCommConfigValue(self,key):
		try:
			return self.cfg.get("Common", key)
		except:
			raise Exception('not found')
	def getRepTypeConfigValue(self, key):
		try:
			return self.cfg.get("REPORT_TYPES",key)
		except:
			raise Exception('not found')
