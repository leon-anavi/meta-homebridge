SUMMARY = "Homebridge Web UI plugin"
DESCRIPTION = "Homebridge Web UI plugin to monitor, manage and control Homebridge from a browser."
HOMEPAGE = "https://github.com/oznu/homebridge-config-ui-x"
SECTION = "Applications/Internet"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a68ee9390150ed7048fc68a6d15b17cf"

inherit npm

SRC_URI = "npm://registry.npmjs.org;name=${BPN};version=${PV}"
NPM_SHRINKWRAP := "${THISDIR}/${PN}/npm-shrinkwrap.json"
NPM_LOCKDOWN := "${THISDIR}/${PN}/lockdown.json"

S = "${WORKDIR}/npmpkg"
