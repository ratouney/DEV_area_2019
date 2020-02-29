#!/bin/sh

./gradlew clean
./gradlew assembleRelease
cp ./app/build/outputs/apk/release/app-release-unsigned.apk build/Area.apk
