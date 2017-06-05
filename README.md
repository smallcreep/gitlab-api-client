# What Is Gitlab Api Client?

Object Oriented Wrapper of [Gitlab API](https://gitlab.com/help/api/README.md)

## What Problems Does Gitlab Api Client Solve?

TBD... stakeholders and needs

## How Gitlab Api Client Works?

### Get Version
```
new RtGitlab(
    new URI(external_gitlab_url),
    PRIVATE_TOKEN
).version().json();
```

TBD... Actors and Features

## What Is Gitlab Api Client SLA?

TBD... quality requirements

## How to Contribute

Fork repository, make changes, send us a pull request. We will review
your changes and apply them to the `master` branch shortly, provided
they don't violate our quality standards. To avoid frustration, before
sending us your pull request please run full Gradle build:

```
$ gradle clean buildDashboard
```

To avoid build errors use gradle wrapper:

```
$ gradlew clean buildDashboard
```

## Got questions?

If you have questions or general suggestions, don't hesitate to submit
a new [Github issue](https://github.com/smallcreep/gitlab-api-client/issues/new).

## License

Cucumber Seeds is an open source project by Ilia Rogozhin ([smallcreep](https://github.com/smallcreep)) 
that is licensed under [MIT](http://opensource.org/licenses/MIT). Ilia Rogozhin
reserves the right to change the license of future releases.