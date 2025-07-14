# HomeSeek - Real Estate Property Search App

## Overview

HomeSeek is a comprehensive Android application designed for real estate property search and management. The app provides users with an intuitive platform to discover, search, and manage property listings with integrated mapping and social features.

## Key Features

### 🏡 Property Management
- **Property Browsing**: Browse through property listings with detailed information
- **Advanced Search**: Search properties by location, category, price range, and other filters
- **Property Categories**: Explore properties by different types (residential, commercial, etc.)
- **Property Details**: View comprehensive property information with image galleries
- **Add Properties**: Users can list their own properties for sale or rent

### 🗺️ Location Services
- **Google Maps Integration**: Interactive map view showing property locations
- **Location Picker**: Precise location selection when adding properties
- **Nearby Properties**: Find properties in specific cities or areas
- **GPS Location Support**: Current location-based property recommendations

### 👤 User Management
- **User Authentication**: Secure login and registration system
- **Firebase Authentication**: Email and social media login options
- **Facebook Integration**: Social login capabilities
- **User Profiles**: Personalized user accounts with preferences
- **Favorites**: Save and manage favorite properties

### 📱 Communication & Social
- **Messaging System**: In-app messaging between users
- **Push Notifications**: Real-time notifications for important updates
- **OneSignal Integration**: Advanced notification management
- **Property Sharing**: Share property listings with others

### 🔍 Advanced Features
- **Image Management**: Photo upload and gallery functionality
- **Price Range Filters**: Customizable price range selection
- **Voice Recording**: Audio message capabilities
- **Property Reviews**: Rating and review system
- **Offline Support**: Basic functionality available offline

## Technology Stack

### Core Framework
- **Platform**: Android (Native Java)
- **Minimum SDK**: API 21 (Android 5.0)
- **Target SDK**: API 34 (Android 14)
- **Build System**: Gradle 8.5 with Android Gradle Plugin 8.1.4

### Backend Services
- **Firebase Realtime Database**: Real-time data synchronization
- **Firebase Authentication**: User authentication and authorization
- **Firebase Storage**: Image and file storage
- **Firebase Analytics**: App usage analytics
- **Firebase Messaging**: Push notifications

### Google Services
- **Google Maps API**: Interactive maps and location services
- **Google Places API**: Location search and autocomplete
- **Google Play Services**: Core Google functionality
- **Google Ads**: Monetization through advertisements

### UI/UX Libraries
- **AndroidX**: Modern Android support libraries
- **Material Design Components**: Google's design system
- **Constraint Layout**: Flexible UI layouts
- **RecyclerView**: Efficient list displays
- **ViewPager**: Swipeable page layouts

### Image Processing
- **Glide**: Image loading and caching
- **Picasso**: Alternative image loading library
- **Universal Image Loader**: Legacy image management
- **Image Cropping**: Built-in image editing capabilities

### Third-Party Integrations
- **OneSignal**: Advanced push notification service
- **Facebook SDK**: Social media integration
- **Volley**: HTTP networking library
- **JSoup**: HTML parsing for web scraping
- **Giphy SDK**: GIF support and integration

### Development Tools
- **Multidex**: Support for large applications
- **ProGuard**: Code obfuscation and optimization
- **Android Test Framework**: Unit and integration testing

## Building and Running the Project

### Prerequisites
- **Android Studio**: Latest stable version (recommended)
- **Java Development Kit**: JDK 8 or higher
- **Android SDK**: API Level 34 (Android 14)
- **Google Play Services**: Latest version

### Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Utshox/homeseek-new.git
   cd homeseek-new
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the cloned directory and select it

3. **Configure Firebase**
   - Create a new Firebase project at [Firebase Console](https://console.firebase.google.com/)
   - Download the `google-services.json` file
   - Place it in the `app/` directory
   - Enable Firebase Authentication, Realtime Database, and Storage

4. **Configure Google Maps**
   - Get a Google Maps API key from [Google Cloud Console](https://console.cloud.google.com/)
   - Add the API key to `app/src/main/res/values/strings.xml`:
     ```xml
     <string name="google_maps_key">YOUR_API_KEY_HERE</string>
     ```

5. **Configure OneSignal**
   - Create an OneSignal account and app
   - Update the OneSignal App ID in `app/build.gradle`

6. **Configure Facebook SDK**
   - Create a Facebook App at [Facebook Developers](https://developers.facebook.com/)
   - Add the Facebook App ID to `app/src/main/res/values/strings.xml`:
     ```xml
     <string name="facebook_app_id">YOUR_FACEBOOK_APP_ID</string>
     ```

### Build Commands

1. **Clean Build**
   ```bash
   ./gradlew clean
   ```

2. **Build Debug APK**
   ```bash
   ./gradlew assembleDebug
   ```

3. **Build Release APK**
   ```bash
   ./gradlew assembleRelease
   ```

4. **Run Tests**
   ```bash
   ./gradlew test
   ```

5. **Install on Connected Device**
   ```bash
   ./gradlew installDebug
   ```

### Development Notes

- The project uses AndroidX libraries for modern Android development
- Firebase configuration is required for authentication and data storage
- Google Play Services are essential for map functionality
- The app includes both Firebase and OneSignal for push notifications
- Image upload functionality requires proper storage permissions

### Recent Updates (2025)

- **Modernized Build System**: Updated to Gradle 8.5 and latest Android Gradle Plugin
- **AndroidX Migration**: Migrated from deprecated Android Support Library to AndroidX
- **Updated Dependencies**: All Firebase, Google Play Services, and third-party libraries updated to latest stable versions
- **Java 17 Support**: Build system now supports modern Java versions
- **Improved Security**: Updated SDK targets and security configurations

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For questions, issues, or contributions, please contact the development team or create an issue in the GitHub repository.

---

**HomeSeek** - Making property search simple and efficient! 🏠✨