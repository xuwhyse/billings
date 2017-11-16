JAVA_OPTS="-server -XX:+HeapDumpOnOutOfMemoryError -XX:+UseG1GC  -Xms876M -Xmx876M"
JAVA_OPTS=${JAVA_OPTS} -Dcom.sun.management.jmxremote -Djava.rmi.server.hostname=192.168.4.78 -Dcom.sun.management.jmxremote.port=9009 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false
java ${JAVA_OPTS} -Duser.timezone=GMT+8 -cp .:lib/*   com.cyanspring.adaptor.future.wind.gateway.Gateway4j > gateway4j.log 2>&1 &
