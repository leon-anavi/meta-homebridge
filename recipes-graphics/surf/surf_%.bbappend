FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://surf.service \
            file://isportbusy.sh \
"

inherit systemd

do_install_append() {

	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then

		# Install script to check if port is busy, aka if the web server is running
		install -d ${D}${systemd_unitdir}/scripts/
		install -m 777 ${WORKDIR}/isportbusy.sh ${D}${systemd_unitdir}/scripts/
		
		# Install systemd service for starting the web browser on boot
		install -d ${D}${systemd_unitdir}/system
		install -m 644 ${WORKDIR}/surf.service ${D}${systemd_unitdir}/system
	fi

}

FILES_${PN} += " \
	${systemd_unitdir}/scripts/ \
	${systemd_unitdir}/scripts/isportbusy.sh \
"

SYSTEMD_SERVICE_${PN} = "surf.service"
