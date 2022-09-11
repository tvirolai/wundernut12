# wundernut12

A solution for the [Wundernut puzzle vol 12](https://github.com/wunderdogsw/wundernut-vol12).

## Requirements

- Java 8 or later
- [Leiningen](https://leiningen.org/)
- You also need an API key for [OCR Space's Free OCR API](https://ocr.space/OCRAPI)

## Usage

- Set the API key to an environment variable `API_KEY`:

``` shell
EXPORT API_KEY=myapikey
```

- Run the application:

``` shell
lein run
```

If the API key is missing, the application will fall back to using pre-OCR'd text under the `resources` folder. The black and white version of the input image will also be created there.

## License

Copyright © 2022 Tuomo Virolainen

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
