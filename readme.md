# Telnet socket chat

This is a test task for a position of Java-developer somewhere :)

## Task

Implement a simple socket-server for an online chat.

`telnet` program is used as a client.

You can use anything you want for persistence.

### Features:
* add new user
* the room
* save 30 last messages in history
* private messages

## Solution

### Build

Use Maven 2.

`mvn package`

### Launch

`mvn exec:java` launches the server on 60000 port.

The port can be changed in `pom.xml` in config for plugin with artifactId = exec-maven-plugin.

### Connect to the server (Linux)

`telnet localhost 60000`

### Chat commands:

* `/login [<name>]` - change your name
* `/tellAll <text>` - send to the room
* `/tell <name> <text>` - send private message
* `/getHistory [<count>]` - show chat history
* `/userList` - get list of users in the room
* `/quit` - leave the room

If the server doesn't recognize your message as a command, this message gets sent to the room (so `/tellAll` is the default command).
