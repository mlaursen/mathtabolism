<h2>Mathtabolism</h2>
<h4>About</h2>
<p>This is a project for tracking your food intake; calories consumed, macro-nutrients consumed, etc. The goal is to eventually have a website where it calculates your TDEE/BMR and you can list the foods you have consumed to track your status. It is really mostly a project for learning new technologies.

<h4>Other Branches</h4>
<p>There is a tag for when this project was built using ant.<br />
<code>git checkout tags/ant_build</code> (if you are interested).<br />
The last working version of this project is on <code>feature/NEW-STYLE</code> which is unfortunately not 100% correct since I started trying to figure out how to use SASS.

<h4>Technologies Being Learned/Implemented</h4>
<p>Right now, my focus is on:
<ul>
<li>Separating the Front End from the Back End
<li>Web Services
<li>A front end framework (AngularJS right now)
<li>SASS - (Mostly trying to get good at CSS)
</ul>

<h4>Project Setup</h4>
<p>The project is built using maven and runs on a wildfly server. The database currently used is postgres. The database connection info is located at the top of the pom.xml. If you are too lazy to modfy it, the values are
<pre><code>
Database Server = localhost
Database Name   = mathtabolism
Username        = mathtabolism
Password        = welcome1
</code></pre>

<p>To run the application, start your wildfly node and run
<pre>
mvn wildfly:deploy
</pre>
Once it has deployed, you can navigate to <a href="http://localhost:8080/mathtabolism">http://localhost:8080/mathtabolism</a> to view the application.