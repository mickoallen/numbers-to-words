[![Build Status](https://travis-ci.org/mickoallen/numbers-to-words.svg?branch=master)](https://travis-ci.org/mickoallen/numbers-to-words) [![Coverage Status](https://coveralls.io/repos/github/mickoallen/numbers-to-words/badge.svg?branch=master)](https://coveralls.io/github/mickoallen/numbers-to-words?branch=master) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/759486ef33664533b49c692c6d0f47aa)](https://www.codacy.com/manual/mickoallen/numbers-to-words?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mickoallen/numbers-to-words&amp;utm_campaign=Badge_Grade)
# Numbers-to-words
> Converting numbers to English words is easy, and fun.

This project converts numbers to their English word equivalents. It comes with a handy CLI application for local testing.

## Installing / Getting started

Requires Java 8+ (recommend using [SDKMAN](https://sdkman.io/) for managing java installations).

## Usage
_One day it might exist in maven central, if it did:_

Add to your project:
```xml
<dependency>
    <groupId>com.mick</groupId>
    <artifactId>numbers-to-words-core</artifactId>
    <version>0.1</version>
</dependency>
```
Using NumbersToWordsConverter:
```java
import com.mick.ntw.NumbersToWordsConverter;
import com.mick.ntw.NumbersToWordsConverterFactory;

NumbersToWordsConverter convert = NumbersToWordsConverterFactory.create();
String numberAsWords = convert.convert(77); 
assert numberAsWords.equals("seventy seven");
```
Alternatively you can hyphenate composites:
```java
import com.mick.ntw.NumbersToWordsConverter;
import com.mick.ntw.NumbersToWordsConverterFactory;

NumbersToWordsConverter convert = NumbersToWordsConverterFactory.create(true);
String numberAsWords = convert.convert(77);
assert numberAsWords.equals("seventy-seven");
```

### Using the test CLI application
Build:
```shell
./mvnw clean install 
```
Start CLI:
```shell
java -jar cli/target/numbers-to-words-cli-jar-with-dependencies.jar
```
or
```shell
java -jar cli/target/numbers-to-words-cli-jar-with-dependencies.jar hyphenate-composites
```

## Testing
Uses Spock for unit testing, they can be run easily using:
```shell
./mvnw clean test
```

## Features

Converts numbers to words.
* Handles all numbers between Integer.MIN_VALUE and Integer.MAX_VALUE inclusively
* Adds 'and' in the correct places
* Optional hyphenations

## How it works

The input number is broken up into groups of hundreds.

example: ```1234567 -> [1, 234, 567]```

Each of these groups of hundreds is then mapped sections, containing it's English equivalent and assigned a multiplier value (thousand, million, billion).

The final step is to combine all of the sections into a full string, adding appropriate joining words where required.

## CI
* Built using [Travis CI](https://travis-ci.com/mickoallen/numbers-to-words)
* Code coverage report by Jacoco to [Coveralls](https://coveralls.io/github/mickoallen/numbers-to-words)
* Code analysis by [PMD](https://pmd.github.io/) and [Codacy](https://app.codacy.com/manual/mickoallen/numbers-to-words/dashboard)

## Contributing

If you'd like to contribute, please fork the repository and use a feature
branch. Pull requests are sweet!

## Licensing

[See licensing](LICENSE)