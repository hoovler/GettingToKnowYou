# Getting to Know You

_The API that's **in your face!**_

## Introduction

Starting a new job is hard _enough_ without having to go about the business of learning _everyone's_ name.  In a ground-breaking new study led by that guy whose name I can't remember that cost the taxpayers $42, grant-funded researchers have just discovered that nobody really bothers to force themselves to remember names when they meet new people.  It was reported in one case that one man (_who had been working in the same position for 42 years_) in a cube farm with just 32 other people, the newest of whom had begun work 16 years prior, could not name a single coworker.  

He was able to recall each persons' face, and could even converse with most of them about their stresses, families, joys, vacations, and other characteristics, both professional and personal; however, when asked how he addresses them, he replied, *"Well, I guess I just try to avoid situations requiring me to call out their name.  Like, when I need someone, I just say, 'Hey man! Can I get some help?' Or, 'Yes ma'am, I can have that right away!'"*

The research further revealed that almost _everyone_ use s this same tactic.  The study was so alarming that several representatives introduced new legislation that would force new employees to name every single co-worker within the first two weeks of employment, with the failure to do so resulting in up to one disaproving look, or several awkward conversations.    

_**Getting to Know You**_ is an API that presents a framework for anyone to build fun and engaging games designed to help new employees get to recognize their workmates.  Simply configure it with your own internal workforce's employee name and mugshot locations, and have fun!

*** 

## Building from Source

Building the **Getting to Know You** API has two required dependancies:

1. [Java Development Kit (JDK) 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. [Maven 3.5.0+](https://maven.apache.org/download.cgi)

You may optionally elect to install any of the below IDEs if desired:

3. [Spring Tool Suite (STS) v4](https://spring.io/tools/) - _**AKA** Spring Tools 4 for Eclipse, the IDE I used to build the application_.
4. [Eclipse IDE for Java](https://www.eclipse.org/downloads/packages/release/2018-09/r/eclipse-ide-java-developers) or [Java EE](https://www.eclipse.org/downloads/packages/release/2018-09/r/eclipse-ide-java-ee-developers) developers, with the [Spring IDE plugin](https://marketplace.eclipse.org/content/spring-ide) - _Nearly identical to the STS platform._
5. [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) with [Spring Boot configuration](https://www.jetbrains.com/help/idea/spring-boot.html) - _the clunkiest approach, IMHO - yet still supported by the Spring Boot framework..._

I detail below how to set up the build environment with Java and Maven, letting reader decide which IDE, if any, to use.  Please see the links above for more information on IDE configuration.

#### 1 - Install Java JDK

Ensure you have Java Development Kit (JDK) 1.8 locally installed, and that the path to the Java 1.8 JDK folder is available in your system properties.

| _System_ | _Instructions_ |
| ------ | ------------ |
| **Windows:** | Ensure `{DRIVE}:\Path\To\Java\jdk1.8` is in _System Environment Variables -> path_ variable. |
| **Linux:** | Either install Java 1.8 using your system's package manager, or place the path to the binaries for JDK 1.8 in **.bashrc**, **.profile**, or wherever else you've set up your system init `export` variables. |
| **Mac:** | Mac's are a mystery to me, but Java is portable.  I presume it's similar to the Linux setup since that's what the Mac CLI is based on... |

Test your JVM setup by typing `java -version` into your command line console.  If you recieve a message other than one like the following, see the [Java installation instructions](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html) for your specific system:

```
$ java -version
    java version "1.8.0_181"
    Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
    Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
```

#### 2 - Install Maven

[Download](https://maven.apache.org/download.cgi) a version of Maven compatible with Java 1.8 (Java 1.7+ in the [compatibility chart](https://maven.apache.org/docs/history.html), and follow the [installation instructions](https://maven.apache.org/install.html) for your specific system.  The requirement for Maven is Java, which we've installed above. 

### 3 - Build & Run Executable JAR

**NOTE:** If using the **STS v4** IDE, you can skip this build process altogether (without creating the executable JAR) by right-clicking the top-level **getting-to-know-you** node in the IDE's Java Browsing perspective, and select **Run As** -> **Spring Boot App**.  This is the same as executing the following terminal command: `$ mvnw spring-boot:run`

1. Update the Maven project to ensure the required artifacts have been downloaded.
2. Execute the Maven build to build the executable JAR:
    * **IDE:** Run the _/getting-to-know-you/mvnw[.cmd]+_ file (regex matching _**mvnw**_ for *nix or _**mvnw.cmd**_ for Windows)
    * **Windows CMD Terminal:** `> mvnw.cmd clean package`
    * **Linux Bash/Sh Terminal:** `$ ./mvnw clean package`
3. Run the executable JAR from your terminal:
	* `java -jar /getting-to-know-you/target/getting-to-know-you-${version}.jar`

### 4 - Testing the Service

Open a new web browser instance, and connect to the following URI, either as written here, or using your own email address to replace _**test@email.com**_: _**http://localhost:8080/gtky/game?email=test@email.com**_

You should receive a response containing a JSON object in the body that represents an instance of the **Getting to Know You** Game object, on top of which you can build your own _amazing_ client!

*** 

## Endpoint Information

### Key

* `<variable>`
* `{possible_values}`
* `[optional]`

### Endpoints

#### /game/play

```
http://localhost:8080/game/play?
	email=<player@email.com>
	[&gameMode={1:4}]
```

* **email** = A valid email address, used as a unique (non PK) field in an internal, mem-based Hibernate DB.
* **gameMode** = A number from 1 to 4, inclusive.
    * 1: Normal Mode; 1 name, 6 image URLs
	* 2: Reversed Mode; 1 image URL, 6 names
	* 3: Normal MATT! Mode; Normal mode for Matts!
	* 4: Reversed MATT! Mode; Reversed mode for Matts!
	
Returns a `Game` object:

```
{
	"question": {
		"employee": {
			"id": "4HVV1s9W76GEiSUU2Aaocg",
			"value": "Williams, William"
		},
		"options": [{
				"id": "4HVV1s9W76GEiSUU2Aaocg",
				"value": "https:////images.ctfassets.net/woody.png"
			}, {
				"id": "4NCJTL13UkK0qEIAAcg4IQ",
				"value": "https:////images.ctfassets.net/joel.jpg"
			}, {
				"id": "3zGu1K4YfKYg0Us2qQgaQI",
				"value": "https:////images.ctfassets.net/luke.jpg"
			}, {
				"id": "14tyvyMcHuKOOsIGEWyyAG",
				"value": "https:////images.ctfassets.net/Jesse.jpg"
			}, {
				"id": "4lh5w7atvOgYsEggioUiCU",
				"value": "https:////images.ctfassets.net/angela.jpg"
			}, {
				"id": "19LrPt51IYuKq6qec8go68",
				"value": "https:////images.ctfassets.net/Connor.jpeg"
			}
		]
	}
}
```

Clearly, if the end user spots the JSON data, he or she could really game the game.  But if you have employees who can figure out how to F12-it to browse the DOM and grab the raw API data, you have a special employee.

#### /game/submit

```
http://localhost:8080/gtky/game/submit?
	email=<player@email.com>
	&optionId=<optionId_ofChosenQuestionObject>
```

* **email** = A valid email address, used as a unique (non PK) field in an internal, mem-based Hibernate DB.
* **optionId** = The string ''

#### /game/meta

```
http://localhost:8080/gtky/game/meta?
	[&leaderboardLength={1:N}]
	
```

***

## Technical Specifications 

|       -       | Item                        | Version | Build        |
|---------------|-----------------------------|---------|--------------|
| **Compiler**  | Java JDK                    | 1.8.0   | 181          |
| **Framework** | Spring Boot MVC             | 2.0.5   | RELEASE      |
| **IDE**       | Spring Tool Suite (Eclipse) | 4.0.0   | 201809220817 |