# dhobi-seva-android
Laundry app for Vipassana centres (on Android)

## How to run

Download https://github.com/nilenso/dhobi-seva-android/archive/v1.0.zip and extract `dhobi-seva-android.apk`. Install APK on your phone.

## How to develop

1. Install the JDK
2. Install [Android Studio](https://developer.android.com/studio/index.html)
3. Set up ANDROID_HOME:

```
echo "ANDROID_HOME=/Users/$USERNAME/Library/Android/sdk" >> ~/.bash_rc
```

4. Fix the broken build-tools license:

```
mkdir "$ANDROID_HOME/licenses"
echo -e "\n8933bad161af4178b1185d1a37fbf41ea5269c55" > "$ANDROID_HOME/licenses/android-sdk-license"
```

5. Clone the repo:

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
