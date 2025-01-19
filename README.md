# Location Tracker with Kafka

## Overview
This project simulates and tracks a person's location changes over time using Apache Kafka. It calculates the total distance traveled using the Haversine formula and provides a REST API to expose this information. The project is implemented using Java and Spring Boot, with Docker used for setting up Kafka and Zookeeper.

## Features
- Kafka-based location simulation with a producer and consumer.
- Real-time distance calculation using the Haversine formula.
- REST API to expose total distance traveled (`/distance` endpoint).
- Modular and extensible design.

## Prerequisites
- **Java**: Version 11 or higher.
- **Maven**: Version 3.8 or higher.
- **Docker**: Installed and running for Kafka and Zookeeper setup.

### Installing Java and Maven

#### **Windows**
1. **Install Java**:
   - Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or [AdoptOpenJDK](https://adoptopenjdk.net/).
   - Follow the installer instructions and set `JAVA_HOME` in your system environment variables.
   - Verify installation:
     ```bash
     java -version
     ```

2. **Install Maven**:
   - Download from [Maven's official site](https://maven.apache.org/download.cgi).
   - Extract the archive and add the `bin` folder to your system `PATH`.
   - Verify installation:
     ```bash
     mvn -version
     ```

#### **MacOS**
1. **Install Java**:
   - Use `brew` to install Java:
     ```bash
     brew install openjdk
     ```
   - Add Java to your PATH:
     ```bash
     export PATH="/usr/local/opt/openjdk/bin:$PATH"
     ```
   - Verify installation:
     ```bash
     java -version
     ```

2. **Install Maven**:
   - Install using Homebrew:
     ```bash
     brew install maven
     ```
   - Verify installation:
     ```bash
     mvn -version
     ```

#### **Linux**
1. **Install Java**:
   - For Debian/Ubuntu-based distributions:
     ```bash
     sudo apt update
     sudo apt install openjdk-11-jdk
     ```
   - For Fedora-based distributions:
     ```bash
     sudo dnf install java-11-openjdk
     ```
   - Verify installation:
     ```bash
     java -version
     ```

2. **Install Maven**:
   - For Debian/Ubuntu-based distributions:
     ```bash
     sudo apt install maven
     ```
   - For Fedora-based distributions:
     ```bash
     sudo dnf install maven
     ```
   - Verify installation:
     ```bash
     mvn -version
     ```

## Project Setup

### Step 1: Clone the Repository
```bash
git clone https://github.com/<your-username>/location-tracker.git
cd location-tracker
```

### Step 2: Start Kafka and Zookeeper
1. Ensure Docker is installed and running.
2. Start the services:
   ```bash
   docker-compose up -d
   ```

### Step 3: Run the Project

#### Option 1: Using IntelliJ IDEA
1. Open the project in IntelliJ.
2. Right-click `LocationProducer` and select **Run 'LocationProducer.main()'** to produce location data.
3. Right-click `LocationConsumer` and select **Run 'LocationConsumer.main()'** to consume and process data.
4. Right-click `LocationTrackerApplication` and select **Run 'LocationTrackerApplication.main()'** to start the REST API.

#### Option 2: Using Maven
1. Run the producer:
   ```bash
   mvn exec:java -Dexec.mainClass=com.example.locationtracker.producer.LocationProducer
   ```
2. Run the consumer:
   ```bash
   mvn exec:java -Dexec.mainClass=com.example.locationtracker.consumer.LocationConsumer
   ```
3. Start the REST API:
   ```bash
   mvn spring-boot:run
   ```

### Step 4: Access the REST API
Visit the following endpoint:
```bash
http://localhost:8080/distance
```
Expected Response:
```
Total Distance: <calculated-distance> km
```

## Project Structure
```
location-tracker/
├── src/
│   ├── main/
│   │   ├── java/com/example/locationtracker/
│   │   │   ├── LocationTrackerApplication.java
│   │   │   ├── producer/LocationProducer.java
│   │   │   ├── consumer/LocationConsumer.java
│   │   │   ├── api/LocationController.java
│   │   │   └── util/DistanceCalculator.java
│   └── resources/application.properties
├── pom.xml
└── docker-compose.yml
```

## Example Logs

### Producer Output
```
Sent: -34.603722,-58.381592
Sent: 51.5074,-0.1278
...
```

### Consumer Output
```
Received: -34.603722,-58.381592
Received: 51.5074,-0.1278
Total Distance: 11116.3 km
...
```

## Troubleshooting

1. **Docker Issues**:
   - Ensure Docker is running: `docker ps`
   - Restart services if necessary: `docker-compose up -d`

2. **Kafka Connectivity**:
   - Check that Kafka and Zookeeper containers are running.
   - Verify ports 9092 (Kafka) and 2181 (Zookeeper) are accessible.

3. **Maven Errors**:
   - Run `mvn clean install` to resolve dependency issues.
   - Ensure your environment variables for `JAVA_HOME` and `PATH` are correctly configured.



