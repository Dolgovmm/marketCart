<a name="tech"></a>
<h2>Technology user</h2>
<ul class="discharged">
    <li>Servlet: Servlet API , JavaServer Pages</li>
    <li>Access to DB: JDBC, JPA</li>
    <li>Tests: JUnit;</li>
    <li>DB: MySQL</li>
    <li>Servlet container: Apache Tomcat</li>
</ul>


<a name="func"></a>
<h2>Functional</h2>

<ol class="discharged">
    <li>Cart
        <ul>
            <li>selecting products: adding, deleting, changing the quantity</li>
            <li>view the content of cart</li>
            <li>ordering</li>
        </ul>
    </li>
</ol>

<a name="db"></a>
<h2>Model DB</h2>

<p>Application DB  consists in 4 linked tables given by means jdbc in 4 classes.</p>

<br>
<p>The data access layer is represented by DAO classes.</p>

<a name="deploy"></a>
<h2>Deploy</h2>
<ul class = "discharged">
    <li>Create DB MySQL. Creating with scropt - script.sql</li>
    <li>Make war file with command: maven package</li>
    <li>War file put in a servlet container Apache TomCat. Path for war file: <tomcat_folder>/webapps/</li>
    <li>Start TomCat with command startup.sh (startup.bat) and go to page in browser: http//:localhost:8080/market</li>
</ul>
