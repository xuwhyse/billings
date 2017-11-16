#!/usr/bin/env bash
if [ -s GATEWAY_PID ]; then
PID=`cat GATEWAY_PID`
kill -9 $PID
fi

sleep 2

JAVA_OPTS="-server -XX:+HeapDumpOnOutOfMemoryError -XX:+UseG1GC  -Xms1500M -Xmx1500M"
java ${JAVA_OPTS} -cp .:lib/*   com.cyanspring.adaptor.future.wind.gateway.Gateway4j > log.log 2>&1 &
echo $! > GATEWAY_PID