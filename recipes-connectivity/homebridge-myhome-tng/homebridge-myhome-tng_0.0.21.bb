SUMMARY = "Legrand (BTicino) MyHome plugin for homebridge"
DESCRIPTION = "Legrand (BTicino) MyHome plugin for homebridge, compatible with Legrand MyHome home automation solution"
HOMEPAGE = "https://github.com/angeloxx/homebridge-myhome"
SECTION = "Applications/Internet"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c48871b9a5796b2db0663fa5cfe5d8e0"

inherit npm

SRC_URI = " \
    npm://registry.npmjs.org/;package=homebridge-myhome-tng;version=${PV} \
    npmsw://${THISDIR}/${BPN}/npm-shrinkwrap.json \
    "

S = "${WORKDIR}/npm"
