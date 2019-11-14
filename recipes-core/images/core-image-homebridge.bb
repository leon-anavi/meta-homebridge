require recipes-graphics/images/core-image-x11.bb

DESCRIPTION = "A small image with Homebridge"

IMAGE_INSTALL_append = " \
	nodejs nodejs-npm homebridge \
	nano vim \
	avahi-daemon \
	openbox xterm surf gedit \
"

# Add script and systemd service for extending the rootfs
# partition during the first boot for Raspberry Pi
IMAGE_INSTALL_append_rpi = " init-resize"

# Enable SSH access
CORE_IMAGE_EXTRA_INSTALL += "openssh"

# Extend the free space on the image
IMAGE_ROOTFS_EXTRA_SPACE ?= "524288"
