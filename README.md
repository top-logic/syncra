# Syncra, an Open Source Application based on TopLogic Open Source

The idea for Syncra emerged in parallel with the thought of making TopLogic available as open source in the future. 
In addition to the TopLogic platform, models should also be made available as open source. 
Syncra is intended to be the first application to be available as an open-source model. 
Syncra is completely based on the modeling tools integrated into TopLogic and on TopLogic's proprietary scripting language, 
TopLogic-Script.


## Regarding the professional background, features:

The core idea was crucial: to present TopLogic as a kind of data hub for various application scenarios.
It made sense to set up a scenario here, as it often occurs in the manufacturing and development industries:
 product master data, bills of materials, issues, risk management, goal management, measure and change management, 
 supplier management, and report management. All these sub-areas work with the same data.

Syncra is a self-contained application that covers all the mentioned sub-areas. Of course, 
it is not possible from a professional perspective to capture and implement all conceivable situations. 
Also, limitations were accepted at some points due to complexity reasons. However, at least basic functionality 
is provided for all sub-areas.

A second goal pursued with the Syncra model: It should also serve as a technical demo that does not operate at an 
abstract level but instead depicts concrete application scenarios that occur repeatedly in practice.


## Usage
You can import this library through a Maven dependency to your own project. Since it is not distributed to MavenCentral,
you have to add a custom repository section:</p>

<pre class="notranslate"><code>&lt;repositories&gt;
    &lt;repository&gt;
        &lt;id&gt;tl-snapshots&lt;/id&gt;
        &lt;name&gt;tl-synchra&lt;/name&gt;
        &lt;url&gt;https://dev.top-logic.com/nexus/repository/toplogic-snapshots&lt;/url&gt;
        &lt;releases&gt;
            &lt;enabled&gt;true&lt;/enabled&gt;
        &lt;/releases&gt;
        &lt;snapshots&gt;
            &lt;enabled&gt;false&lt;/enabled&gt;
        &lt;/snapshots&gt;
    &lt;/repository&gt;
&lt;/repositories&gt;
</code></pre>
<p dir="auto">Then you can import the modules with the following dependency:</p>
<p dir="auto">The client part (user-agent) to initiate calls:</p>
<code>&lt;dependency&gt;	
    &lt;groupId&gt;org.mjsip&lt;/groupId&gt;
	    &lt;artifactId&gt;mjsip-ua&lt;/artifactId&gt;
    &lt;version&gt;2.0.0&lt;/version&gt;
&lt;/dependency&gt;
</code>
<p dir="auto">The server part to implement VOIP servers:</p>
<code>&lt;dependency&gt;	
    &lt;groupId&gt;org.mjsip&lt;/groupId&gt;
	    &lt;artifactId&gt;mjsip-server&lt;/artifactId&gt;
    &lt;version&gt;2.0.0&lt;/version&gt;
&lt;/dependency&gt;
</code>
<p dir="auto">Note that the GitHub hosted repositories require authentication (no anonymous download as from Maven-Central).
Therefore, you need to provide your GitHub access token in the Maven settings to make the download work (in <code>~/.m2/settings.xml</code>):</p>
<code>&lt;servers&gt;
    &lt;server&gt;
        &lt;id&gt;github&lt;/id&gt;
            &lt;username&gt;YOUR_USERNAME&lt;/username&gt;
            &lt;password&gt;YOUR_AUTH_TOKEN&lt;/password&gt;
    &lt;/server&gt;
&lt;/servers&gt;
</code>
<p dir="auto">The library consists of the following modules, which can also be imported separately:</p>
<ul dir="auto">
<li>mjsip-examples</li>
<li>mjsip-net</li>
<li>mjsip-phone</li>
<li>mjsip-server</li>
<li>mjsip-sip</li>
<li>mjsip-sound</li>
<li>mjsip-ua</li>
<li>mjsip-util</li>
</ul>
<p dir="auto">To get an idea how to start, you can have a look into the <a href="https://github.com/haumacher/mjSIP/tree/master/mjsip-examples/src/main/java/org/mjsip/examples">examples module</a>.</p>









# Syncra Docker Image

## Requirements

- Installed [Docker](https://docs.docker.com/get-docker/)
- At least **2.6GB RAM** allocated for the container

## Downloading the Docker Image

The image can be pulled from our repository using the following command:

```sh
docker pull docker.top-logic.com/syncra
```

## Starting the Container

The following example starts the container with the recommended settings:

```sh
docker run -d \
  --name syncra \
  -p 8080:8080 \
  -m 2600m \
  -v $(pwd)/data:/tl \
  docker.top-logic.com/syncra
```

### Explanation:

- `-p 8080:8080` – Maps the internal Tomcat port 8080 to the host.
- `-m 2600m` – Allocates at least 2.6GB RAM to the container.
- `-v $(pwd)/data:/tl` – Mounts the local `data` folder to `/tl` inside the container to persist data.
- `-d` – Runs the container in detached mode.

## Persistent Storage and Database

The container includes an embedded **H2 database**, which is stored under the `/tl` directory. To ensure data persistence across container restarts, it is recommended to mount a local volume to `/tl`, as shown in the example above.

## Accessing the Application

After starting the container, the application can be accessed at:

```
http://localhost:8080
```

## Further Information

- [Docker Documentation](https://docs.docker.com/)

