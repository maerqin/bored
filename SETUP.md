# Bored API Setup Guide

This guide will walk you through setting up the Bored API using Docker and MongoDB.

## Prerequisites
Before proceeding, ensure you have the following installed on your machine:
- [Docker](https://www.docker.com/get-started)
- [MongoDB Database Tools](https://www.mongodb.com/try/download/database-tools) (for importing data into the MongoDB instance)

## Steps to Set Up and Run the API

### 1. Install and Start Docker
   - Follow [this guide](https://docs.docker.com/get-docker/) to install Docker if you haven’t already.
   - Ensure Docker is running before proceeding.

### 2. Install MongoDB Database Tools
   - Download the tools from the [MongoDB Database Tools page](https://www.mongodb.com/try/download/database-tools).
   - Install them following the instructions for your operating system.

### 3. Clone the Bored API Repository and navigate to the repository in the terminal
   ```bash
   git clone https://github.com/drewthoennes/Bored-API.git
   cd Bored-API
   ```

### 4. Set Up a MongoDB Container
- Pull and run a MongoDB container using Docker:
  ```bash
  docker run -d -p 27017:27017 --name mongodb mongo:latest
  ```

### 5. Import Data into MongoDB
- Navigate to the `db` directory within the cloned repository:
  ```bash
  cd db
  ```

- Import the data into MongoDB by running the following commands:
  ```bash
  mongoimport --db mydatabase --collection activities --file activities.json --uri mongodb://localhost:27017
  mongoimport --db mydatabase --collection facts --file facts.json --uri mongodb://localhost:27017
  mongoimport --db mydatabase --collection riddles --file riddles.json --uri mongodb://localhost:27017
  mongoimport --db mydatabase --collection websites --file websites.json --uri mongodb://localhost:27017
  ```

### 6. Create the Dockerfile for the API
- Navigate back to the root directory of the cloned repository:
  ```bash
  cd ..
  ```

- Create a file named `Dockerfile` in this directory and add the following content to it:

  ```Dockerfile
  # Use an older version of Node.js
  FROM node:12

  # Set the working directory inside the container
  WORKDIR /app

  # Copy package.json and package-lock.json to the container
  COPY package*.json ./

  # Install the dependencies
  RUN npm install

  # Copy the rest of the application files
  COPY . .

  # Expose the port the app runs on
  EXPOSE 8080

  # Command to run the app
  CMD ["npm", "start"]
  ```

### 7. Build and Run the API Container
- Build the Docker image for the API:
  ```bash
  docker build -t bored-api .
  ```

- Run the API container with access to the host network and connect it to MongoDB:
  ```bash
  docker run --network="host" -e MONGODB_URI="mongodb://localhost:27017/mydatabase" bored-api
  ```

## Running the API After Initial Setup

Once you have set up the API, you can easily start it again after a shutdown by following these steps:

### 1. Start Docker
- Ensure Docker is running on your system.

### 2. Start the MongoDB Instance
- If the MongoDB container is not running, start it:
  ```bash
  docker start mongodb
  ```

- You can check if the MongoDB instance is running by executing:
  ```bash
  docker ps -a
  ```

### 3. Run the API
- Navigate to the cloned Bored API folder:
  ```bash
  cd path/to/Bored-API
  ```

- Run the API container:
  ```bash
  docker run --network="host" -e MONGODB_URI="mongodb://localhost:27017/mydatabase" bored-api
  ```