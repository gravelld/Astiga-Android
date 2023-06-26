
### Install the necessary Android version for the emulator
If necessary, install the system-image for the version of Android required:

    $ sdkmanager "system-images;android-30;default;x86_64"

(Find the path using `sdkmanager --list`).

[sdkmanager](https://developer.android.com/studio/command-line/sdkmanager).

### Create the virtual device

    $ avdmanager create avd -n android30 -k "system-images;android-30;default;x86_64"


### Run the emulator

    $ /usr/lib/android-sdk/emulator/emulator -avd android30