# dhobi-seva-android
Laundry app for Vipassana centres (on Android)

## How to run

Download https://github.com/nilenso/dhobi-seva-android/archive/v1.0.zip and extract `dhobi-seva-android.apk`. Install APK on your phone.

## How to develop

1. Install the JDK
2. Install [Android Studio](https://developer.android.com/studio/index.html)
3. Clone the repo:

### Building

```
git clone git@github.com:nilenso/dhobi-seva-android.git
cd dhobi-seva-android
./gradlew clean
./gradlew assemble
```

### Running

```
cd <android_sdk>/tools/
emulator -list-avds
emulator -avd <avd_name>
adb install app/build/outputs/apk/vipassana.apk
```
