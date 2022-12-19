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
<img src="https://i.imgur.com/8PCPlF5.png" height="340" />
<img src="https://i.imgur.com/uAhmcqg.png" height="340" />
</p>

### Continuous Integration

The project is using Github Actions to build and test the app on every push and pull request on two
devices. Also, it is using Renovate to keep the dependencies up to date.

### TODO

- Add Offline first support 
- Tests
- Fix the navigation back icon issue while navigating
- Get data from Server for LineChart
- Add more features
