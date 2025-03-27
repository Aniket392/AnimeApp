# Anime Info App

## Overview

Anime Info App is an Android application that provides information about top anime. The app fetches data from the [Jikan API](https://api.jikan.moe/v4/top/anime) using Retrofit and displays a list of anime. When the user clicks on any anime row, detailed information about the selected anime is displayed. The details include a trailer video (if available) or an image of the anime, as well as a horizontal RecyclerView showing related genres.

The app also includes Lottie animations to indicate loading states, providing a smooth and user-friendly experience.

## Features

- Fetches anime list data from the [Jikan API](https://api.jikan.moe/v4/top/anime).
- Displays the anime list in a vertical list format.
- Shows detailed information when an anime is clicked, including:
  - Trailer video (if available).
  - Used Webview and iframe to load youtube embeded link
  - Anime image.
  - Genre list displayed horizontally in a RecyclerView.
- Uses Lottie for loading animations during data fetching.
- Dynamic display of anime genres fetched from the API.

## Technologies Used

- **Retrofit**: For making API requests.
- **Lottie**: For adding animations during loading states.
- **RecyclerView**: For displaying a horizontal list of genres.
- **ConstraintLayout**: For building responsive layouts.
- **MVVM Architecture**: For separation of concerns and data binding.
- **LiveData**: For observing data changes.
