# CoinCap

Best practice (Modularization) - built entirely with Jetpack Compose and cutting-edge libraries like
Coroutines, Flow, Hilt, Coil, etc.

This project is using Material 3 components, as well as the Compose navigation library. It also has
an integrated Static Analysis tool (Detekt) and a CI/CD pipeline (Github Actions).

### Tech Stack

- Jetpack Compose
- Hilt
- Retrofit (with a deserializer for another API)
- Coroutines
- Coil
- Detekt
- GitHub Actions
- Material 3
- Compose Navigation

### Screenshots

<p>
<img src="https://i.imgur.com/0f7TvSq.png" height="420" />
<img src="https://i.imgur.com/8mcrfij.png" height="420" />
<img src="https://i.imgur.com/59Kj14a.png" height="420" />
<img src="https://i.imgur.com/J7aMJr2.png" height="420" />
</p>

### Architecture

I've tried to follow the best practices from Google's official
repository [nowInAndroid](https://github.com/android/nowinandroid), and used Modularization to make
the app is more scalable and maintainable.

### Modules

<img src="https://i.imgur.com/21DcyNY.png" width="400">

### Features

1. The logic behind searching is to get the data only once and then filter it locally, so the user
   can search for any coin without any delay. This way, we won't make too many requests to the API,
   and the Firewall won't block us.
2. For the home screen, I am planning to get some data from the server for showing on the LineChart, but for
   now, I am using dummy data.
3. Unfortunately, the API doesn't provide the data for the LineChart, so I am using dummy data for
   now.
4. Iran (my home country) has been facing inflation for the last decade, and surprisingly, because
   of protests and the lack of management, the inflation rate is increasing day by day. So, I took
   the chance and put a logic for showing the rates in different currencies as **Local Currency**,
   with a refresh policy between Saturday to Thursday, and the rates will be updated every 5
   minutes. For Friday, the rates will be retrieved from the previous day.

### API

For this project, I am
using [CoinCap API](https://docs.coincap.io/#ee30bea9-bb6b-469d-958a-d3e35d442d7a) for rates, as
well as
[This API](https://bonbast-api.deta.dev/) for live Iranian exchange rates.

Also, I am using https://coinicons-api.vercel.app/api/icon/ for getting the icons of the coins.

### Continuous Integration

The project is using GitHub Actions to build and test the app on every push and pull request on two
devices. Also, it is using Renovate to keep the dependencies up to date.

### Static Code Analysis

The project is using Detekt for static code analysis, and it is using GitHub Actions to run the
analysis on every push and pull request.

I also used Detekt formatter to format the code automatically which is somehow a wrapper for ktLint
as well.

Please see: https://detekt.dev/docs/rules/formatting

### TODO

- Add Detekt and features only once into the project.
- Add Offline first support
- Fix the search issue with the price
- Tests
- Get data from Server for LineChart
- Add more features
