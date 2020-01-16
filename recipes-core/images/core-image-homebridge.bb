require recipes-graphics/images/core-image-x11.bb

DESCRIPTION = "A small image with Homebridge"

IMAGE_INSTALL_append = " \
	kernel-modules \
	i2c-tools \
	nodejs nodejs-npm \
	homebridge homebridge-myhome-tng \
	nano vim \
	avahi-daemon \
	openbox pcmanfm xterm surf gedit \
	x11vnc \
	networkmanager networkmanager-nmtui \
	stalonetray network-manager-applet \
	udev-extraconf \
	mosquitto \
	python-sysinfodisplay \
"

# Add script and systemd service for extending the rootfs
# partition during the first boot for Raspberry Pi
IMAGE_INSTALL_append_rpi = " init-resize"

# Enable SSH access
CORE_IMAGE_EXTRA_INSTALL += "openssh"

# Extend the free space on the image
IMAGE_ROOTFS_EXTRA_SPACE ?= "524288"
