# Graphics &middot; [![Github license](https://img.shields.io/badge/license-Apache%202.0-purple.svg)](https://opensource.org/licenses/Apache-2.0) [![Build Status](https://travis-ci.org/Blahodatny/Graphics.svg?branch=master)](https://travis-ci.org/Blahodatny/Graphics) [![Known Vulnerabilities](https://snyk.io/test/github/Blahodatny/Graphics/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/Blahodatny/Graphics?targetFile=pom.xml)

To execute JavaFX Application run:
``````
mvn compile exec:java -pl {name of module} -Dexec.mainClass={name of Main Class}
``````

If you see the following error when running *mvn compile*:
```text
[ERROR] Failed to execute goal org.codehaus.mojo:exec-maven-plugin:1.6.0:java (default-cli) on project lab1: An exception occured while executing the Java class. The specified mainClass doesn't contain a main method with appropriate signature.: com.project.Variant2.main([Ljava.lang.String;) -> [Help 1]
```

Insert that method in one of JavaFX running classes:
```java
public static void main(String... args) {
    launch();
}
```
After executing you can get rid of it &#128522;
___
Read some beneficial articles before exploring third lab:

* [*What is Bitmap Picture?* (на русском)](https://ru.wikipedia.org/wiki/BMP)

* [More detailed exploration of BMP File Format](https://en.wikipedia.org/wiki/BMP_file_format)

<img src ="https://upload.wikimedia.org/wikipedia/commons/c/c4/BMPfileFormat.png" alt="Bitmap" height="500" width="320"></img>