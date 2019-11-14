#!/bin/sh

if [ "$1" != "" ]; then
	PORT=$1
else
	PORT=80
fi

CHECK=`netstat -lnt | grep ":${PORT}"`
echo $CHECK
if [ -z "$CHECK" ]; then
	echo "Port $PORT is available."
	exit 1
fi
echo "Port $PORT is busy."
exit 0
