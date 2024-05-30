#!/bin/bash

docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker rmi $(docker images -a -q)
docker volume prune

docker build -t frontend.note.app .
docker run -p 8080:8080 --name frontend.note.app.container frontend.note.app