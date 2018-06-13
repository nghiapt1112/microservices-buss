#!/usr/bin/env bash
function note() {
    local GREEN NC
    GREEN='\033[0;32m'
    NC='\033[0m' # No Color
    printf "\n${GREEN}$@  ${NC}\n" >&2
}

#set -e
#. ./setup-env.sh

#note "Building Libraries...";       ./gradlew clean build -x test; cd -
note "Building ...";       ./gradlew clean build -x test; cd -

find . -name *1.0.jar -exec du -h {} \;

#docker-compose build
