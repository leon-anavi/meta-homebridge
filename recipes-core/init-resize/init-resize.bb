SUMMARY = "Resize file system on first boot"
DESCRIPTION = "Resize file system on first boot"
HOMEPAGE = "https://github.com/RPi-Distro/raspi-config"
SECTION = "hello"
LICENSE = "MIT"
PR = "r1"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI += "\
	file://init-resize.sh \
	file://init-resize.service \
"

RDEPENDS_${PN} += " e2fsprogs-resize2fs parted"

do_install_append() {

	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then

		# Install the script for resizing partition
		install -d ${D}${systemd_unitdir}/scripts/
		install -m 777 ${WORKDIR}/init-resize.sh ${D}${systemd_unitdir}/scripts/

		# Install the systemd service
		install -d ${D}${systemd_unitdir}/system
		install -m 644 ${WORKDIR}/init-resize.service ${D}${systemd_unitdir}/system
        fi
}

FILES_${PN} += " \
	${systemd_unitdir}/scripts/ \
	${systemd_unitdir}/scripts/init-resize.sh \
"

SYSTEMD_SERVICE_${PN} = "init-resize.service"
