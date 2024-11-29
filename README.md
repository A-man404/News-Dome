📱 News Dome

A sleek news app built with Kotlin, Jetpack Compose, and Retrofit to browse Indian news across categories like Technology and Sports. Users can read articles and navigate to their original sources for more details.
🌟 Features

    Browse Indian News: News categorized by topics like Tech, Sports, and more.
    Original Articles: Open the full article in your browser.
    Modern UI: Built with Jetpack Compose for a clean, user-friendly interface.
    Planned Enhancements:
        Offline support using Room Database.
        Search functionality for specific topics.

🛠️ Tech Stack

    Kotlin: For development.
    Jetpack Compose: Declarative UI.
    Retrofit: API integration.
    Coroutines: Asynchronous programming.
    Room: (Planned) Offline data storage.
    Navigation Component: In-app navigation.

🚀 Getting Started
Prerequisites

    Install Android Studio.
    Obtain an API key from NewsAPI.
    Add the API key to your local.properties:

    NEWS_API_KEY="your_api_key_here"  

Installation

    Clone the repository:

    git clone https://github.com/yourusername/news-dome.git  

    Open the project in Android Studio.
    Sync the Gradle files.
    Run the app on an emulator or a physical device.

📂 Project Structure

├── data
│   ├── model          # Data models for API responses
│   ├── network        # Retrofit API interfaces
│   └── repository     # Business logic and data handling
├── ui
│   ├── screens        # Compose screens for UI
│   ├── components     # Reusable UI components
│   └── navigation     # Navigation logic
├── utils              # Utility classes and constants
└── MainActivity.kt    # Entry point of the app

📝 To-Do List
Backend Features

Fetch articles using Retrofit.
Add Room Database for offline caching.

    Implement pagination for endless scrolling.

UI Features

Categorized news display.
Search functionality for articles.

    Favorites section for bookmarked articles.

Optimizations

Shared ViewModel for better state management.

    Enhanced error handling for API failures.

📸 Screenshots

(Include app screenshots here to showcase the UI and functionality.)
📜 License

This project is licensed under the MIT License.
🤝 Contributing

Contributions, issues, and feature requests are welcome!

    Fork the project.
    Create your feature branch:

git checkout -b feature/AmazingFeature  

Commit your changes:

git commit -m "Add some AmazingFeature"  

Push to the branch:

    git push origin feature/AmazingFeature  

    Open a pull request.

