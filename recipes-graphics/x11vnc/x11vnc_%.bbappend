FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://x11vnc.service"

inherit systemd

do_install_append() {

	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		# Install systemd service
		install -d ${D}${systemd_unitdir}/system
		install -m 644 ${WORKDIR}/x11vnc.service ${D}${systemd_unitdir}/system
	fi

}

SYSTEMD_SERVICE_${PN} = "x11vnc.service"
