FROM gradle:4.10.0-jdk8
USER root
ENV SDK_URL="https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip" \
    ANDROID_HOME="/usr/local/android-sdk" \
    ANDROID_VERSION=28 \
    ANDROID_BUILD_TOOLS_VERSION=27.0.3
# Download Android SDK
RUN mkdir "$ANDROID_HOME" .android
RUN cd "$ANDROID_HOME"
RUN curl -o sdk.zip $SDK_URL
RUN unzip sdk.zip
RUN mkdir -p licenses
RUN echo "24333f8a63b6825ea9c5514f83c2829b004d1fee" > licenses/android-sdk-license

# Install Android Build Tool and Libraries
RUN echo yes || tools/bin/sdkmanager --licenses
RUN tools/bin/sdkmanager --update
RUN tools/bin/sdkmanager "build-tools;${ANDROID_BUILD_TOOLS_VERSION}" "platforms;android-${ANDROID_VERSION}" "platform-tools"

## Install Build Essentials
RUN apt-get update && apt-get install build-essential -y && apt-get install file -y && apt-get install apt-utils -y
