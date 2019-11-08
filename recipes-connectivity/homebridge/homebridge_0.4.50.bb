SUMMARY = "HomeKit support for the impatient"
DESCRIPTION = "Homebridge is a lightweight NodeJS server that emulates the iOS HomeKit API"
HOMEPAGE = "https://homebridge.io"
SECTION = "console/network"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34f8c1142fd6208a8be89399cb521df9"

inherit npm

SRC_URI = "npm://registry.npmjs.org;name=${BPN};version=${PV}"
NPM_SHRINKWRAP := "${THISDIR}/${PN}/npm-shrinkwrap.json"
NPM_LOCKDOWN := "${THISDIR}/${PN}/lockdown.json"

S = "${WORKDIR}/npmpkg"

# Ensure files are not owned by the the user running bitbake
# to avoid host contamination [host-user-contaminated]
do_install_append() {
	chown -R root:root ${D}
}

