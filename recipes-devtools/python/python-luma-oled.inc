SUMMARY = "A small library to drive an OLED device"
DESCRIPTION = "\
A small library to drive an OLED device with either SSD1306 , SSD1309, SSD1322, \
SSD1325, SSD1327, SSD1331, SSD1351 or SH1106 chipset"
HOMEPAGE = "https://github.com/rm-hull/luma.oled"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=ce5fd4861bb9fba0391b38773ee34c53"

SRC_URI[md5sum] = "bcf056a04f3f6ffc940cda479ebb387e"
SRC_URI[sha256sum] = "4bc9f169c4342bc5b49b9a500fbc9e2d9f95de65e07fc2016e1631e08e0da694"

CLEANBROKEN = "1"

PYPI_PACKAGE = "luma.oled"

RDEPENDS_${PN} = "${PYTHON_PN}-luma-core ${PYTHON_PN}-smbus2"
