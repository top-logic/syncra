# Syncra

Overview of the functions
- Parts Management: Create parts catalogs, add individual parts, assign individual parts to a catalog, display catalogs with various classifications.
- Action Management: Perform a 360-degree assessment of an action regarding its impact on the prices of an assembly/individual part, the probability of occurrence and expected severity of a risk, as well as the increase or reduction of the carbon footprint.
- Risks and Issues: Basic risk management with configurable risk categorization and graphical evaluation, link risks to actions through a 360-degree assessment of the actions in terms of their impact on risk parameters (probability of occurrence and expected severity), list all issues captured via the process cockpit along with links to affected components and actions for problem resolution.
- Bill of Materials Management: Product creation, building assemblies, versioned bill of materials.
- Reporting: Price development between two freely selectable dates, price trend curves for up to 500 days.
- Supplier Management: List of all suppliers with available parts and contacts.
- Carbon Monitoring: List of all carbon emitters, list of actions affecting the carbon footprint, trend curve.


# Usage
There are two ways to get access to syncra. You can download the java project and import it in an IDE (as Eclipse) or you can use a docker image. 

## Download java project
To run the **Syncra Java application** from the repository and import it into Eclipse, follow these steps:

### 1. **Clone the Git Repository**
   - Open a terminal or command prompt.
   - Execute the following command to clone the repository:
     ``` git clone https://github.com/top-logic/syncra.git```
   - This will download the repository into a local directory named `syncra`.
   (If you have not installed git, look at https://git-scm.com.)

### 2. **Open Eclipse and Enable Git Integration**
   - Make sure the **Git integration (EGit)** is installed in Eclipse. If not, install it through the Eclipse Marketplace.
   - Open Eclipse, and go to **Window > Perspective > Open Perspective > Git** to enable the Git view.

### 3. **Import the Project into Eclipse**
   - Navigate to **File > Import > Git > Projects from Git**, then click **Next**.
   - Choose **Existing local repository** and click **Next**.
   - Select the cloned repository (e.g., the `syncra` folder) and proceed.
   - Select **Import existing Eclipse projects**. 

### 4. **Configure and start the Project**
   - If the project uses **Maven**, ensure the appropriate plugins are installed in Eclipse.
   - Run a **Maven build** by right-clicking on `pom.xml` and selecting **Run As > Maven Build**.
   - A browser opens with the login page. For the user "root" the initial passwort is written in the console (may be you have to scroll up some lines)

### 5. **First steps**
   - In the application you can import some initial data. Select "Parts" on the left side. Now, in the burger menue of "Kataloge" contains an entry "Import (data and pictures)".
   - Select that entry. a dialog occusr which allows you to upload a zip file.
   - An appropriate zip-file is provided with the repository in the path "synchra/src/test/data".

## Syncra Docker Image

### 1. Requirements

- Installed [Docker](https://docs.docker.com/get-docker/)
- At least **2.6GB RAM** allocated for the container

### 2. Downloading the Docker Image

The image can be pulled from our repository using the following command:

```sh
docker pull docker.top-logic.com/syncra
```

### 3. Starting the Container

The following example starts the container with the recommended settings:

```sh
docker run -d \
  --name syncra \
  -p 8080:8080 \
  -m 2600m \
  -v $(pwd)/data:/tl \
  docker.top-logic.com/syncra
```

#### Explanation:

- `-p 8080:8080` – Maps the internal Tomcat port 8080 to the host.
- `-m 2600m` – Allocates at least 2.6GB RAM to the container.
- `-v $(pwd)/data:/tl` – Mounts the local `data` folder to `/tl` inside the container to persist data.
- `-d` – Runs the container in detached mode.

#### Persistent Storage and Database

The container includes an embedded **H2 database**, which is stored under the `/tl` directory. To ensure data persistence across container restarts, it is recommended to mount a local volume to `/tl`, as shown in the example above.

#### Accessing the Application

After starting the container, the application can be accessed at:

```
http://localhost:8080
```

### 4. Further Information

- [Docker Documentation](https://docs.docker.com/)

