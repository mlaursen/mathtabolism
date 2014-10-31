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

<pre><code>&lt;?xml version="1.0" encoding="UTF-8"?&gt;
&lt;module xmlns="urn:jboss:module:1.1" name="com.(mysql | oracle)"&gt;
  &lt;resources&gt;
    &lt;resource-root path="JAR-NAME.jar"/&gt;
  &lt;/resources&gt;
  &lt;dependencies&gt;
    &lt;module name="javax.api"/&gt;
    &lt;module name="javax.transaction.api"/&gt;
  &lt;/dependencies&gt;
&lt;/module&gt;
</code></pre>

In your /your-wildfly-dir/standalone/configuration/standalone.xml file add the following as the last item in the security-domains section

<pre><code>
&lt;security-domain name="MathtabolismRealm" cache-type="default"&gt;
  &lt;authentication&gt;
    &lt;login-module code="Database" flag="required"&gt;
      &lt;module-option name="dsJndiName" value="java:jboss/datasources/MathtabolismDS"/&gt;
      &lt;module-option name="principalsQuery" value="select password from account where username=?"/&gt;
      &lt;module-option name="rolesQuery" value="select role, 'Roles' from account where username=?"/&gt;
      &lt;module-option name="hashAlgorithm" value="SHA-256"/&gt;
	    &lt;module-option name="hashEncoding" value="base64"/&gt;
	    &lt;module-option name="hashCharset" value="UTF-8"/&gt;
    &lt;/login-module&gt;
  &lt;/authentication&gt;
&lt;/security-domain&gt;
</code></pre>

In your datasources section, modify the datasource to be:

<pre><code>
&lt;datasource jndi-name="java:jboss/datasources/MathtabolismDS" pool-name="MathtabolismDS" enabled="true" use-java-context="true"&gt;
  &lt;connection-url&gt;jdbc:oracle:thin:@localhost:1521:orcl&lt;/connection-url&gt;
  &lt;connection-url&gt;jdbc:mysql://localhost:3306/wildfly&lt;/connection-url&gt;
  &lt;driver&gt;ojdbc6&lt;/driver&gt;
  &lt;driver&gt;mysql5&lt;/driver&gt;
  &lt;security&gt;
    &lt;user-name&gt;wildfly&lt;/user-name&gt;
    &lt;password&gt;abcd1234&lt;/password&gt;
  &lt;/security&gt;
&lt;/datasource&gt;
&lt;drivers&gt;
	&lt;driver name="ojdbc6" module="com.oracle"&gt;
	  &lt;xa-datasource-class&gt;com.jdbc.driver.OracleDriver&lt;/xa-datasource-class&gt;
	&lt;/driver&gt;
	&lt;driver name="mysql5" module="com.mysql"&gt;
	  &lt;driver-class&gt;com.mysql.jdbc.Driver&lt;/driver-class&gt;
	&lt;/driver&gt;
&lt;/drives&gt;
</code></pre>

Where you choose the connection url and driver that you need.

Finally, copy the build-user.properties.sample to build-user.properties and update the wildfly.dir to your location.
