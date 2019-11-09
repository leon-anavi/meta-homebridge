require recipes-core/images/core-image-minimal.bb

DESCRIPTION = "A small image with Homebridge"

IMAGE_INSTALL_append = " \
        nodejs nodejs-npm homebridge \
        nano vim \
        avahi-daemon \
"

# Enable SSH access
CORE_IMAGE_EXTRA_INSTALL += "openssh"

# Extend the free space on the image
IMAGE_ROOTFS_EXTRA_SPACE ?= "524288"
