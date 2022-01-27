SUMMARY = "HomeKit support for the impatient"
DESCRIPTION = "Homebridge is a lightweight NodeJS server that emulates the iOS HomeKit API"
HOMEPAGE = "https://homebridge.io"
SECTION = "console/network"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34f8c1142fd6208a8be89399cb521df9"

inherit npm systemd

SRC_URI = " \
	npm://registry.npmjs.org/;package=homebridge;version=${PV} \
	npmsw://${THISDIR}/${BPN}/npm-shrinkwrap.json \
	file://config.json \
	file://homebridge \
	file://homebridge.service \
	"

S = "${WORKDIR}/npm"

RDEPENDS_${PN} += " homebridge-config-ui-x"

# Remove test binaries from build (compiled in x86)
python do_unpack_append() {
    import shutil
    shutil.rmtree(oe.path.join(d.getVar('S'), 'node_modules/put/test'))
}

do_install_append() {

	install -d ${D}/home/root/.homebridge/
	install -m 644 ${WORKDIR}/config.json ${D}/home/root/.homebridge/

	install -d ${D}${sysconfdir}/default/
	install -m 644 ${WORKDIR}/homebridge ${D}${sysconfdir}/default/

	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		install -d ${D}${systemd_unitdir}/system
		install -m 644 ${WORKDIR}/homebridge.service ${D}${systemd_unitdir}/system
	fi

	# Ensure files are not owned by the the user running bitbake
	# to avoid host contamination [host-user-contaminated]
	chown -R root:root ${D}

}

FILES_${PN} += " \
	/home/root/.homebridge/config.json \
	${sysconfdir}/default/homebridge \
"

SYSTEMD_SERVICE_${PN} = "homebridge.service"
