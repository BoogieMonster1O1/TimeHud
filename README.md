# TimeHud

A server side mod for Fabric and Sponge that displays the current time in the `HH:MM` format in the actionbar title. 

The Fabric and Sponge implementations should only be used on dedicated servers.
The Liteloader implementation is client only.

### Building
- Fork and clone this repository.
- Run `./gradlew build`.
- The built jar will be in `<module_name>/build/libs`. The one without the classifier is the one you want to keep.

### Contributing
- Please create appropriate unit tests if adding code to the `timehud-common` module
- Run the `licenseFormatAll` gradle task to apply license headers to each file
