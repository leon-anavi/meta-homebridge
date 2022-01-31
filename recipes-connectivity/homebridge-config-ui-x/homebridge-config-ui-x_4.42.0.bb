SUMMARY = "A web based management, configuration and control platform for Homebridge"
DESCRIPTION = "Homebridge Web UI plugin to monitor, manage and control Homebridge from a browser."
HOMEPAGE = "https://github.com/oznu/homebridge-config-ui-x"
SECTION = "Applications/Internet"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=376404cca9e4ef504ea08f648aacde23"

SRC_URI = " \
    npm://registry.npmjs.org/;package=homebridge-config-ui-x;version=${PV} \
    npmsw://${THISDIR}/${BPN}/npm-shrinkwrap.json \
    "

S = "${WORKDIR}/npm"

inherit npm

RDEPENDS_${PN} += " bash"

python do_unpack_append() {
    import shutil
    import glob
    import os
    prebuildBinDir = 'node_modules/node-pty-prebuilt-multiarch/prebuilds'

    # Remove non arm node_modules prebuilt binaries
    shutil.rmtree(oe.path.join(d.getVar('S'), '%s/darwin-arm64' % prebuildBinDir))
    shutil.rmtree(oe.path.join(d.getVar('S'), '%s/darwin-x64' % prebuildBinDir))
    shutil.rmtree(oe.path.join(d.getVar('S'), '%s/linux-ia32' % prebuildBinDir))
    shutil.rmtree(oe.path.join(d.getVar('S'), '%s/linux-x64' % prebuildBinDir))

    # Remove unused libc prebuilt binaries
    if d.getVar('TCLIBC') == 'glibc':
        fileList = glob.glob(oe.path.join(d.getVar('S'), '%s/**/node.abi??.musl.node' % prebuildBinDir), recursive=True)
    elif d.getVar('TCLIBC') == 'musl':
        fileList = glob.glob(oe.path.join(d.getVar('S'), '%s/**/node.abi??.node' % prebuildBinDir), recursive=True)
    else:
        raise bb.parse.SkipRecipe("incompatible with %s C library" % d.getVar("TCLIBC"))

    for filePath in fileList:
        try:
            os.remove(filePath)
        except:
            raise bb.error("Unable to delete prebuilt binary: %s " % filePath)
}

# Remove arm64 node_modules prebuilt binaries for arm target archtectures
python do_unpack_append_arm() {
    import shutil
    shutil.rmtree(oe.path.join(d.getVar('S'), 'node_modules/node-pty-prebuilt-multiarch/prebuilds/linux-arm64'))
}

# Remove arm node_modules prebuilt binaries for arm64 target architectures
python do_unpack_append_aarch64() {
    import shutil
    shutil.rmtree(oe.path.join(d.getVar('S'), 'node_modules/node-pty-prebuilt-multiarch/prebuilds/linux-arm'))
}
