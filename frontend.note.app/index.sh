# #!/bin/bash

rm -r target/
mvn clean install package
rm -r /opt/wildfly-32.0.0.Final/standalone/deployments/*
rm -rf /opt/wildfly-32.0.0.Final/standalone/deployments/tmp/*
rm -rf /opt/wildfly-32.0.0.Final/standalone/data/content/*
mv target/appweb.war /opt/wildfly-32.0.0.Final/standalone/deployments/
/opt/wildfly-32.0.0.Final/bin/standalone.sh