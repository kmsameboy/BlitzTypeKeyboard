#!/bin/sh
ANDROID_HOME=/home/vanous/android_sdk/ gradle assembleRelease
cd app/build/outputs/apk
/home/vanous/android_sdk/build-tools/23.0.2/zipalign -v -p 4 app-release-unsigned.apk app-release-unsigned-unaligned.apk
apksigner sign --ks ../../../../../BlitzType-signing-key/my-release-key.jks --out BlitzTypeKeyboard.apk app-release-unsigned-unaligned.apk 

