#!/usr/bin/env bash

function note() {
    local GREEN NC
    GREEN='\033[0;32m'
    NC='\033[0m' # No Color
    printf "\n${GREEN}$@  ${NC}\n" >&2
}

#set -e
#. ./setup-env.sh

#cd util;                                              note "Building util...";            ./gradlew clean build -x test publishToMavenLocal; cd -

cd supporters/sup-discovery-server;            note "Building discovery server...";       ./gradlew clean build -x test; cd -
cd supporters/sup-config-server;               note "Building config server...";          ./gradlew clean build -x test; cd -
cd supporters/sup-edge-server;                 note "Building API gateway...";            ./gradlew clean build -x test; cd -
cd supporters/sup-auth;                        note "Building composite...";              ./gradlew clean build -x test; cd -

cd composite-services/core-composite-service;                 note "Building auth...";     ./gradlew clean build -x test; cd -

cd bus-services/core-product-service;               note "Building product-service...";    ./gradlew clean build -x test; cd -
cd bus-services/core-user-service;            note "Building user-service...";             ./gradlew clean build -x test; cd -

find . -name *SNAPSHOT.jar -exec du -h {} \;

#docker-compose build
