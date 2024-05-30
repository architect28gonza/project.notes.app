# #!/bin/bash

cd module.note.app
mvn clean install compile package -e -X -U
cd ..
cd backend.note.validation
mvn clean install compile package -e -X -U
cd ..
cd backend.note.app
mvn clean install compile package -e -X -U
cd ..
cd backend.note.app
mvn clean install compile package -e -X -U