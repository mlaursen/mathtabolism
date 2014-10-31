Setting Up Your Development Environment:<br>
<strong>Requirements:</strong>
<ol>
<li>Database - Currently Support Oracle or MySQL</li>
<li>Wildfly 8.1</li>
</ol>

<strong>Setting Up Wildfly:</strong><br>
Create a new module for your database connection. In <strong>/your-wildfly-dir/modules/system/layers/base/com</strong> 
create a folder (mysql | oracle)/main. Inside of the main folder, drop the jdbc jar you want to use. Create a module.xml 
file: replacing the JAR-NAME and the name="com."

`
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="com.(mysql | oracle)">
  <resources>
    <resource-root path="JAR-NAME.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>
`

In your /your-wildfly-dir/standalone/configuration/standalone.xml file add the following as the last item in the security-domains section

`
<security-domain name="MathtabolismRealm" cache-type="default">
  <authentication>
    <login-module code="Database" flag="required">
      <module-option name="dsJndiName" value="java:jboss/datasources/MathtabolismDS"/>
      <module-option name="principalsQuery" value="select password from account where username=?"/>
      <module-option name="rolesQuery" value="select role, 'Roles' from account where username=?"/>
      <module-option name="hashAlgorithm" value="SHA-256"/>
	    <module-option name="hashEncoding" value="base64"/>
	    <module-option name="hashCharset" value="UTF-8"/>
    </login-module>
  </authentication>
</security-domain>
`

In your datasources section, modify the datasource to be:

`
<datasource jndi-name="java:jboss/datasources/MathtabolismDS" pool-name="MathtabolismDS" enabled="true" use-java-context="true">
  <connection-url>jdbc:oracle:thin:@localhost:1521:orcl</connection-url>
  <connection-url>jdbc:mysql://localhost:3306/wildfly</connection-url>
  <driver>ojdbc6</driver>
  <driver>mysql5</driver>
  <security>
    <user-name>wildfly</user-name>
    <password>abcd1234</password>
  </security>
</datasource>
<drivers>
	<driver name="ojdbc6" module="com.oracle">
	  <xa-datasource-class>com.jdbc.driver.OracleDriver</xa-datasource-class>
	</driver>
	<driver name="mysql5" module="com.mysql">
	  <driver-class>com.mysql.jdbc.Driver</driver-class>
	</driver>
</drives>
`

Where you choose the connection url and driver that you need.

Finally, copy the build-user.properties.sample to build-user.properties and update the wildfly.dir to your location.
