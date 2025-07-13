# HomeSeek

## Overview

HomeSeek is a comprehensive home and property search application for Android. This mobile application helps users find, explore, and connect with property listings, providing features for property search, location services, messaging, and multimedia sharing.

## Features

- **Property Search & Discovery**: Browse and search through property listings
- **Interactive Maps**: Google Maps integration for location-based property search
- **Messaging System**: Built-in chat functionality with image and audio support
- **User Authentication**: Firebase-based authentication with multiple providers
- **Media Sharing**: Image capture, editing, and sharing capabilities
- **Push Notifications**: Real-time notifications via OneSignal
- **Social Integration**: Facebook and Google login support
- **Audio Recording**: Voice message capabilities
- **Location Services**: GPS-based location tracking and search

## Technology Stack

- **Platform**: Android (API 21+ / Android 5.0+)
- **Language**: Java 8
- **Build System**: Gradle 8.5
- **Android Gradle Plugin**: 8.1.4
- **Compile SDK**: Android 14 (API 34)
- **Target SDK**: Android 14 (API 34)
- **UI Framework**: AndroidX + Material Design Components

### Key Dependencies

- **AndroidX Libraries**: AppCompat, ConstraintLayout, Material Design
- **Firebase**: Authentication, Database, Storage, Messaging, Analytics
- **Google Play Services**: Maps, Location, Authentication, Ads
- **Image Processing**: Glide, Picasso, Universal Image Loader
- **Networking**: Volley, OkHttp (via httpclient module)
- **UI Components**: CircleImageView, RoundedImageView, Various Animation Libraries
- **Social**: Facebook SDK
- **Notifications**: OneSignal
- **Utilities**: JSoup for HTML parsing

## Prerequisites

Before building this project, ensure you have:

1. **Android Studio** (latest version recommended)
2. **Java Development Kit (JDK) 8 or higher**
3. **Android SDK** with API level 34 installed
4. **Google Play Services** (for Maps and other Google services)
5. **Firebase Project** configured (see Firebase Setup section)

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/Utshox/homeseek-new.git
cd homeseek-new
```

### 2. Firebase Configuration

1. Create a new project in [Firebase Console](https://console.firebase.google.com/)
2. Add an Android app to your Firebase project
3. Download the `google-services.json` file
4. Place it in the `app/` directory
5. Enable the following Firebase services:
   - Authentication (with desired providers)
   - Realtime Database
   - Storage
   - Cloud Messaging

### 3. API Keys Setup

Configure the following API keys in your project:

- **Google Maps API Key**: Add to `AndroidManifest.xml`
- **OneSignal App ID**: Update in `build.gradle` (defaultConfig section)
- **Facebook App ID**: Configure in `strings.xml` and `AndroidManifest.xml`

### 4. Build the Project

#### Using Android Studio

1. Open Android Studio
2. Choose "Open an existing Android Studio project"
3. Navigate to the cloned repository and select it
4. Wait for Gradle sync to complete
5. Build the project using `Build > Make Project`

#### Using Command Line

```bash
# Make gradlew executable (if not already)
chmod +x gradlew

# Clean the project
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease
```

### 5. Run the Application

- **Using Android Studio**: Click the "Run" button or use `Run > Run 'app'`
- **Using Command Line**: `./gradlew installDebug` (requires connected device/emulator)

## Project Structure

```
homeseek-new/
├── app/                          # Main application module
│   ├── src/main/java/            # Java source code
│   ├── src/main/res/             # Android resources
│   └── build.gradle              # App-level build configuration
├── httpclient/                   # HTTP client library module
│   ├── src/main/java/            # HTTP client source code
│   └── build.gradle              # Module build configuration
├── build.gradle                  # Project-level build configuration
├── settings.gradle               # Gradle settings
└── gradle.properties            # Gradle properties
```

## Build Configuration

### Gradle Wrapper
- **Version**: 8.5
- **Java Compatibility**: Java 8 language level
- **MultiDex**: Enabled for dependency management

### Android Configuration
- **Compile SDK**: 34 (Android 14)
- **Target SDK**: 34 (Android 14)
- **Min SDK**: 21 (Android 5.0)
- **Version Code**: 1
- **Version Name**: 1.0

## Development Notes

### Recent Modernization (2024)

This project has been updated from a 5-year-old codebase to modern Android development standards:

- **Gradle**: Upgraded from 4.6 to 8.5
- **Android Gradle Plugin**: Upgraded from 3.1.2 to 8.1.4
- **Dependencies**: Migrated from Support Libraries to AndroidX
- **Repositories**: Replaced deprecated jcenter with mavenCentral
- **SDK Versions**: Updated from API 27 to API 34
- **Java**: Ensured compatibility with Java 8+ features

### Known Dependencies

Some legacy dependencies are maintained for compatibility:
- Custom UI libraries for specific animations and components
- Third-party image processing libraries
- Specialized communication libraries

## Troubleshooting

### Common Build Issues

1. **Gradle Sync Issues**
   - Ensure stable internet connection
   - Try `./gradlew clean` and rebuild
   - Invalidate caches in Android Studio

2. **Missing API Keys**
   - Verify all API keys are properly configured
   - Check `google-services.json` is in the correct location

3. **Dependency Conflicts**
   - Review `build.gradle` for version conflicts
   - Use `./gradlew dependencies` to analyze dependency tree

### Support

For issues and support:
- Check the project's GitHub Issues page
- Ensure all prerequisites are properly installed
- Verify Firebase and API key configurations

## License

This project is part of a portfolio application. Please refer to the repository for licensing information.