# Bored API Spring Shell Application

This application provides suggestions for activities using the Bored API via a Spring Shell interface.

## Features

- Fetch random activities.
- Filter activities by type.
- Command completion for activity types.

## Requirements

- Java 17
- Docker (optional, for API and database setup)

## Setup and Running

### Run the Application

```bash
./gradlew bootRun --console=plain
```

### Using the Shell

```bash
shell:> get
shell:> get --type education
```

### Running Tests

```bash
./gradlew test
```

## Docker and Database Setup

For instructions on setting up the API with Docker and configuring the database, please refer to [SETUP.md](SETUP.md).
