# Trending Movies App

This Android application displays a list of trending movies fetched from the TMDB API and shows detailed information about each movie upon selection.

## Features

- Display a list of trending movies.
- Show detailed information about a selected movie.
- Smooth navigation between the list and the details screen.
- Shimmer effect while loading movies.
- Load movie images using Glide.

## Architecture

The app follows the MVVM (Model-View-ViewModel) pattern with Clean Architecture principles to ensure a scalable, maintainable, and testable codebase.

## Libraries and Tools

- **Dagger Hilt**: Dependency Injection.
- **Navigation Component**: For handling navigation between fragments.
- **Retrofit**: For API requests.
- **Glide**: For image loading.
- **Shimmer**: For the loading effect.

## Screenshots

<img src="https://github.com/ayoubhamouta05/TrandingMovies/assets/103429679/11d71ca0-bcce-4bd4-9973-4160e8e44703" width="300" />
<img src="https://github.com/ayoubhamouta05/TrandingMovies/assets/103429679/d1c77382-b08d-40e1-bdc8-6a681f60de11" width="300" />
<img src="https://github.com/ayoubhamouta05/TrandingMovies/assets/103429679/250da54b-906e-421a-aed7-36c393a52db7" width="300" />
<img src="https://github.com/ayoubhamouta05/TrandingMovies/assets/103429679/90492813-4b3d-4e78-90a5-9dddecc283db" width="300" />
<img src="https://github.com/ayoubhamouta05/TrandingMovies/assets/103429679/4af0c3bb-7f07-4a87-b8ea-ec0f0617fda7" width="300" />


## How to Run the Project

1. **Clone the repository**.
2. **Open the project in Android Studio.
3. **Build and run the project on an emulator or a physical device.
4. **Ensure you have an internet connection to fetch the data from the API.


## API Endpoints
- List of Trending Movies: https://api.themoviedb.org/3/discover/movie
- Movie Details: https://api.themoviedb.org/3/movie/{movie_id}
- API Documentation: TMDB API Documentation
- Image Configuration: TMDB Image Documentation

## Project Structure
- data: Contains the data sources and repositories.
- domain: Contains the use cases and model entities.
- presentation: Contains the ViewModels and UI components.
- di: Contains the Dagger Hilt modules for dependency injection.

## Contact
For any inquiries or further information, please contact:
- Name: Ayoub hamouta
- Email: ayoubbhamouta@gmail.com
- linkedin : https://www.linkedin.com/in/ayoub-hamouta-009183237