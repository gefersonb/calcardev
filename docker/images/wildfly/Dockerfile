FROM jboss/wildfly:10.1.0.Final

RUN /opt/jboss/wildfly/bin/add-user.sh -u gefe -p 1 --silent

ADD modules /opt/jboss/wildfly/modules/

#ADD customization /opt/jboss/wildfly/customization/
#COPY customization/standalone2.xml /opt/jboss/wildfly/standalone/configuration/standalone2.xml

ADD customization/standalone2.xml /opt/jboss/wildfly/standalone/configuration/
ADD customization/ear.ear /opt/jboss/wildfly/standalone/deployments/

#RUN /opt/jboss/wildfly/customization/execute.sh standalone

EXPOSE 8080 9990

CMD /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0 -c standalone2.xml
