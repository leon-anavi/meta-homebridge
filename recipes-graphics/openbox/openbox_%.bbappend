FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# List applications which are included in the custom openbox
# interface as runtime dependencies
RDEPENDS_${PN} += " xterm surf gedit stalonetray network-manager-applet"

# Custom configurations to automatically launche a simple web browser
# in full screen without any window decoration
SRC_URI_append = " \
	file://autostart \
	file://rc.xml \
	file://menu.xml \
"

do_install_append() {
	install -m 0755 ${WORKDIR}/autostart ${D}/${sysconfdir}/xdg/openbox/
	install -m 0755 ${WORKDIR}/rc.xml ${D}/${sysconfdir}/xdg/openbox/
	install -m 0755 ${WORKDIR}/menu.xml ${D}/${sysconfdir}/xdg/openbox/
}

ALTERNATIVE_${PN}-core = "x-window-manager x-session-manager"
ALTERNATIVE_TARGET[x-session-manager] = "${bindir}/openbox-session"
ALTERNATIVE_PRIORITY[x-session-manager] = "100"

FILES_${PN}-core = "${bindir}/openbox ${bindir}/openbox-session ${libdir}/*${SOLIBS}"

FILES_${PN}-gnome += " \
	${bindir}/openbox-gnome-session \
"
