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
(Work in progress)

Clone the repository: (assuming you have installed GitHub)

gh repo clone top-logic/syncra

Import the repository in the IDE of your choice.
Start the application (for eclipse a launch configuration "Start Synchra.launch" is delivered with the repository).


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

