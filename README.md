# Environment Variables
- `TEST_HOSTNAME`: The target hostname to open socket with.
- `TEST_PORT`: The target port to open socket with.
- `DEBUG`: Prints debug info for this test app. Right now, just prints the current date each time a new socket is created. Defaults to `false`. 
- `DELAY`: The number of MILLISECONDS to sleep between each socket creation. Defaults to 100ms.
- `EXIT_ON_FAIL`: Exit the application upon an Exception while creating a socket. Defaults to `true`. Set to `false` if you will leave this test running for a long time and need to capture data while it is executing.
# Running
## Basic Java
```
# javac Main.java
# TEST_HOSTNAME=redhat.com TEST_PORT=443 java Main
```

## Docker
```
# docker build -t java-exhaust-ephemeral .
# docker run -e TEST_HOSTNAME=redhat.com -e TEST_PORT=443 -e DEBUG=true --rm -it java-exhaust-ephemeral
```

## OpenShift 3.x
```
# oc new-app https://github.com/bostrt/java-exhaust-ephemeral.git
# oc set env dc/java-exhaust-ephemeral TEST_HOSTNAME=kubernetes.default.svc TEST_PORT=443 DELAY=1 DEBUG=true
```