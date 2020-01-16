SUMMARY = "Display system information"
DESCRIPTION = "\
Simple Python 3 application to display system information like \
IP and status of systemd services on mini I2C OLED display"
SECTION = "console/utils"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = "git://github.com/AnaviTechnology/sysinfodisplay"
SRCREV = "3c77315eec76165cca41a2ea7b90b19c49f7c695"
PV = "0.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3 systemd

RDEPENDS_${PN} += " python3-ctypes python3-luma-oled python3-pillow"

do_install_append () {
	install -d ${D}${datadir}/fonts/ttf/
	install -m 06444 pixelmix.ttf ${D}${datadir}/fonts/ttf/
	install -d ${D}${bindir}
	install -m 0755 sysinfodisplay.py ${D}${bindir}
        if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		install -d ${D}${systemd_unitdir}/system
		install -m 644 ${S}/sysinfodisplay.service ${D}${systemd_unitdir}/system
        fi
}

FILES_${PN} += " \
	${datadir}/fonts/ttf/pixelmix.ttf \
"

SYSTEMD_SERVICE_${PN} = "sysinfodisplay.service"
