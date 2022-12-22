# CoinCap

Best practice (Modularization) - built entirely with Jetpack Compose.

### Tech Stack

- Jetpack Compose
- MVVM
- Hilt
- Retrofit
- Coroutines
- Navigation

### Architecture

I've tried to follow the best practices from the Google's official
repository [nowInAndroid](https://github.com/android/nowinandroid), and used Modularization to make
the app more scalable and maintainable.

### Screenshots

<p>
<img src="https://i.imgur.com/2O20IGa.png" height="400" />
<img src="https://i.imgur.com/8mcrfij.png" height="400" />
<img src="https://i.imgur.com/1hK0pFC.png" height="400" />
<img src="https://i.imgur.com/HJsrNNT.png" height="400" />
</p>

### Features

1. The logic behind searching is to get the data only once and then filter it locally, so the user
   can search for any coin without any delay. This way, we won't make too many requests to the API,
   and the Firewall won't block us.
2. For home screen, I am planning to get some data from server for showing on the LineChart, but for
   now, I am using dummy data.


### API

For this project, I am using [CoinCap API](https://docs.coincap.io/#ee30bea9-bb6b-469d-958a-d3e35d442d7a) for now. I will probably add more APIs in the future.

### Continuous Integration

The project is using Github Actions to build and test the app on every push and pull request on two
devices. Also, it is using Renovate to keep the dependencies up to date.

### TODO

- Add Offline first support
- Tests
- Get data from Server for LineChart
- Add more features
