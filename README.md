# TypeWriter
[![Release](https://jitpack.io/v/com.github.rajdeepvaghela/TypeWriter.svg)](https://jitpack.io/#com.github.rajdeepvaghela/TypeWriter)
[![Release](https://img.shields.io/github/v/release/rajdeepvaghela/TypeWriter)](https://github.com/rajdeepvaghela/MotionText/releases)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


If you use a simple TextView in MotionLayout for textSize transitions, the animation won't be smooth. Here MotionText will solve the issue, as internally 
it will convert text to Image and during transition only the image is resized. It also have a few out of the box design attributes.

## Installation
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
    	...
        maven { url 'https://jitpack.io' }
    }
} 
```
Add the dependency
```gradle
dependencies {
    implementation 'com.github.rajdeepvaghela:TypeWriter:1.0.3'
}
```
## Usage
```xml
    <com.rdapps.typewriter.TypeWriterTextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textColor="@color/textColor"
        android:textSize="12sp" />
```
For EditText you can use
```xml
    <com.rdapps.typewriter.TypeWriterEditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textColor="@color/textColor"
        android:textSize="14sp" />
```
to start animation
```kotlin
typeWriter.speed = 10 // to set the custom speed for animation
typeWriter.animateText("A long text which needs to be animated", startDelay = 500) {
    // do after complition
}
```
You can also animate animated three dots(...) at the end of the current text
```kotlin
typeWriter.animateLoadingDots()
// after the work is done
typeWriter.stopLoadingAnimation()
```

## License
```
Copyright 2023 Rajdeep Vaghela

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
