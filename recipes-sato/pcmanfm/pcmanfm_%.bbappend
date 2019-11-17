FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
	file://browser.desktop \
	file://gedit.desktop \
	file://pcmanfm.desktop \
	file://terminal.desktop \
"

do_install_append() {
	install -d ${D}/home/root/Desktop
	install -m 644 ${WORKDIR}/browser.desktop ${D}/home/root/Desktop/
	install -m 644 ${WORKDIR}/gedit.desktop ${D}/home/root/Desktop/
	install -m 644 ${WORKDIR}/pcmanfm.desktop ${D}/home/root/Desktop/
	install -m 644 ${WORKDIR}/terminal.desktop ${D}/home/root/Desktop/
}

FILES_${PN} += " \
	/home/root/Desktop/browser.desktop \
	/home/root/Desktop/gedit.desktop \
	/home/root/Desktop/pcmanfm.desktop \
	/home/root/Desktop/terminal.desktop \
"
