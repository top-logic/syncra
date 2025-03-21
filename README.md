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

# Syncra Docker Image

This document describes how to download and start the Syncra Docker image from our repository.

## Requirements

- Installed [Docker](https://docs.docker.com/get-docker/)
- At least **2548MB RAM** allocated for the container

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
  -m 3g \
  -v $(pwd)/data:/tl \
  docker.top-logic.com/syncra
```

### Explanation:

- `-p 8080:8080` – Maps the internal Tomcat port 8080 to the host.
- `-m 3g` – Allocates at least 3GB RAM to the container.
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

