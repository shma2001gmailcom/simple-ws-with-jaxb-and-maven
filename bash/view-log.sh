#!/bin/sh

## open logs in text editor

if [ -e '../../../logs/converter-ws.log' ]; then
 gedit ../../../logs/converter-ws.log
else echo "No any logs to view."
fi