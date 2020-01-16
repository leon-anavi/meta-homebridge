SUMMARY = "Display system information"
DESCRIPTION = "\
Simple Python 3 application to display system information like \
IP and status of systemd services on mini I2C OLED display"
SECTION = "console/utils"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = "git://github.com/AnaviTechnology/sysinfodisplay"
SRCREV = "8cc88aa056fc49fe543cb740c8fdc039f419eb8f"
PV = "0.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += " python3-ctypes python3-luma-oled python3-pillow"

do_install_append () {
	install -d ${D}${datadir}/fonts/ttf/
	install -m 06444 pixelmix.ttf ${D}${datadir}/fonts/ttf/
	install -d ${D}${bindir}
	install -m 0755 sysinfodisplay.py ${D}${bindir}
}

FILES_${PN} += " \
	${datadir}/fonts/ttf/pixelmix.ttf \
"
