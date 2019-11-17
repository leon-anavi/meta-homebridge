#!/bin/sh

if [ "$1" != "" ]; then
	PORT=$1
else
	PORT=80
fi

for (( ; ; ))
do
	CHECK=`netstat -lnt | grep ":${PORT}"`
	echo $CHECK
	if [ -z "$CHECK" ]; then
		echo "Port $PORT is available."
		/bin/sleep 1
	else
		echo "Port $PORT is busy."
		exit 0
	fi
done
