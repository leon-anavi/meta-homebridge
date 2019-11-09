#!/bin/sh

DISK="/dev/mmcblk0"
PART1="${DISK}p1"
PART2="${DISK}p2"

if [ ! -e "$DISK" ] || [ ! -e "$PART1" ] || [ ! -e "$PART2" ] ; then
	echo "ERROR: Device doesn't exist, unable to resize partition."
else
	# Calculate max size for the partition with rootfs
	SIZEPART1=$(parted -s -m $PART1 unit mb print | grep ^/dev | cut -f2 -d ":" | sed 's/.\{2\}$//' | cut -d "." -f 1)
	SIZETOTAL=$(parted -s -m $DISK unit mb print | grep ^/dev | cut -f2 -d ":" | sed 's/.\{2\}$//' | cut -d "." -f 1)
	(( SIZEPART2 = SIZETOTAL - SIZEPART1 - 1 ))

	echo "Resizing partitions. Please wait..."

	# Resize and extend the partition
	parted $DISK resizepart 2 $SIZEPART2
	# Notify the kernel about the table
	partprobe
	# Extend the file system on-line
	resize2fs $PART2
	# Do NOT run the systemd service on next boot
	systemctl disable init-resize.service
fi
