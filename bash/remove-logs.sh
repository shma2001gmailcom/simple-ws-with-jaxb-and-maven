#!/bin/bash

## remove logs 
## logs location is defined in log4j.xml

if [ -e '../../logs/converter-ws.log' ]; then
 rm ../../converter-ws/lie.log
else echo 'No any logs to remove.'
fi
