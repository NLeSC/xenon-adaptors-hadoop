# xenon-adaptors-hadoop

[Hadoop](https://hadoop.apache.org) related adaptors for Xenon

Implemented adaptors:
* hdfs, Xenon filesystem adaptor for Hadoop Distributed File System (HDFS)

## Usage

The library can be added as a Gradle dependency to your own project with
```groovy
repositories {
    // ... others
    jcenter()
}
dependencies {
    // ... others
    implementation group: 'nl.esciencecenter.xenon.adaptors', name: 'xenon-adaptors-hadoop', version: '3.0.0'
}
```

## New release

Chapter is for xenon developers.

The major version should be the same as the used xenon library.

1. Bump version in `README.md`, `build.gradle` and `CITATION.cff`, update CHANGELOG.md and commit/push
1. Publish to bintray with `BINTRAY_USER=*** BINTRAY_KEY=**** ./gradlew bintrayUpload`
1. Create GitHub release
1. Announce release so users of library like xenon-cli can use new version.
