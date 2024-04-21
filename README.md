[![MIUN DT179G][course_badge]](https://www.miun.se/utbildning/kursplaner-och-utbildningsplaner/Sok-kursplan/kursplan/?kursplanid=32023)

![Version](https://img.shields.io/static/v1?label=version&message=1.1&color=b34700)
[![MIT license](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE.md)
[![JetBrains][jetbrains_badge]](https://www.jetbrains.com/student/)
[![java][java_badge]](https://docs.oracle.com/en/java/javase/17/)
[![vcs][git_badge]](https://git-scm.com/)
[![Bitbucket][bitbucket_badge]](https://git-scm.com/)
[![made-with-Markdown][md_badge]](http://commonmark.org)

[course_badge]: _RepoResources/img/badge_miun.png
[java_badge]: https://img.shields.io/badge/-17-blue&style=flat?logo=java&logoWidth=20&labelColor=black&logoColor=white&color=blueviolet
[git_badge]: https://img.shields.io/badge/-Git-blue&style=flat?logo=git&logoWidth=20&labelColor=black&logoColor=white&color=blueviolet
[bitbucket_badge]: https://img.shields.io/badge/-Bitbucket-blue&style=flat?logo=Bitbucket&logoWidth=20&labelColor=black&logoColor=white&color=blueviolet
[md_badge]: https://img.shields.io/badge/-Markdown-blue&style=flat?logo=markdown&logoWidth=20&labelColor=black&logoColor=white&color=blueviolet
[jetbrains_badge]: https://img.shields.io/badge/-IntelliJ-blue&style=flat?logo=jetbrains&logoWidth=20&labelColor=black&logoColor=white&color=blue


-----------

# Student Assignment Solutions
This is your personal student repository which will be used to submit solutions for the course's three lab assignments. 
This repo is completely private, meaning none other than you and the teachers have access to it. As you've completed a 
particular assignment and wish to have its solution evaluated, you'll need to perform a formal hand-in using the 
dedicated submission box in Moodle. Nothing should be attached to this submission in Moodle, as the current solution 
state will be fetched from this repository. So make sure to synchronize any local changes with **remote origin** before 
submission.

Just as in DT179G you're encouraged to isolate development to dedicated branches, and only merge with **master** upon
submitting your solution for evaluation. This will enable greater degree of experimentation since the stable states are
preserved, and readily available at all times. Do note that evaluations will only be performed on the solutions
available in this **master** branch, which for our purposes is regarded as the _**release branch**_.

> _Please write a short presentation in the dedicated section at the end of this document!_

-------

## Structure of Repository

````
studid_solutions/   (1)
  _playground/      (2)
  _RepoResources/   (3)
  Laboration_X/     (4)
  Project/          (5)
  .gitignore        (6)
  pom.xml           (7)
  README.md         (8)
````

````
laboration_X/       (9)
  src/main/         (10)
  src/test/         (11)
  pom.xml           (12)
  README.md         (13)
````

1. Root of repository.
2. All contents in this folder will be ignored by version control, as long as the ``.gitignore``
      remains untouched. This is a good place to just play around with scripts, which are not meant to be viewed by others.
3. General resources for the repository, not to be confused with resources for tests and / or Java. 
   An example of usage can be seen at the top of this document, where an image is pulled from **``/img/badge_miun.png``**.
4. Directory for laboration content, further explained in points ``9 - 13``.
5. Directory for final project. Further details surrounding the structure will be explained once the assignment has been published.
6. Patterns to be ignored from version control, such as project files generated by the IDE. 
   Existing contents of this file should be general enough to cover most circumstances, but feel free to add more if 
   the need arises.
7. The parent Maven script. Note that we use a distributed system, where we have subscripts in each module.
8. The document you're currently reading.
9. Main directory for laboration content, where 'X' indicate a sequence number. There are a total of three lab 
   assignments in the course and each one represents a nested Maven module within the parent project.
10. This is where you store java files and resources needed for the solutions. Note that source documents 
   (**``.java``**) needs to be placed under folder **``java``**, which is explicitly marked as **Sources Root**, and 
   its package name. Ex: **``java/com/dt180g/laboration_1/Lab1.java``**.
11. This is where we place unit tests, which will be relevant for laboration 2 and 3. The first lab assignment does not 
    have this structure, and is excluded from tests as stated in its **``pom.xml``**.
12. Maven script for the lab module, deriving from parent **``pom.xml``** in project root.
13. **README** which needs to contain a written report, in conformity to requirements which are stated in study guidance. 


--------------

## Markdown in Reports
Bitbucket offers very limited support for HTML-tags so you need to avoid them, and instead fully rely on ordinary 
[**Markdown syntax**](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet). For example, if you would 
like to present some code in your lab reports you should not enclose it within **``<pre>``**. The same goes for tags
used to structure content, such as **``<center>``**. If you really want more control over the presentation, you should 
instead utilize stylesheets (**``.css``**) as we did in DT179G, but it's not needed as plain Markdown will suffice.

In general, you shouldn't include much code in your reports as it represents implementation details which would not 
scale well with a larger project. It will also add redundancies since we already have access to the solution, and you 
can easily imagine how bloated the report becomes if all code is repeated. However, in some situations it can be 
justified to include some snippets to illustrate an approach or to strengthen a case. 
But be sure to use proper Markdown:

````java
public class ShowCase {
  public static void main(String[] args) {
    System.out.println("Hello World!");
  }
}
````

--------------

## Maven
We will rely heavily on Maven throughout both this and the upcoming course **DT181G**, but won't dive into the 
specifics until the later **DT042G**. The Moodle page for the course's initial learning module links to some 
material which provides an introduction to what Maven is and what it can do, but in order to fully appreciate 
its value one would also need to understand its build processes in terms of **phase stages** together with 
targeting of specific **goals**. 

Build tools such as Maven is a rather vast subject to cover, and since it's strictly governed by a large set of 
rules one would also need to reach some proficiency with its declarative syntax. In order to learn any new 
technology it's always important to strip away complexities and start from the very fundamentals. This repo uses 
a modularized implementation, of one base project and multiple sub-projects, which makes its structure unsuitable
as learning material. 

The modularized structure you find in this repo is in a _**somewhat**_ stable state, and will have improved 
once we reuse it for **DT181G**. We have a parent **``pom.xml``** in the main root which declares dependencies, 
build process, and report generations to be used in our sub modules. These sub modules will have their own 
**``pom.xml``** and inherits the configurations from the parent script. There shouldn't be any reason for you to 
change anything in these documents, and the current structure has been verified to work in both Windows 10 and 
macOS Monterey running **``openjdk 17.0.1``** and **``Apache Maven 3.8.6``**.

Maven builds follow specific life cycles, which in turn are constituted out of multiple phases which are exectuted
in sequence. There are three built-in life cycles:

- **default**: the main life cycle as it's responsible for project deployment. Consists of 23 phases in total.
- **clean**: to clean the project and remove all files generated by the previous build. Consists of 3 phases in total.
- **site**: to create the project's site documentation. Consists of 4 phases in total.

### Phases
A Maven phase represents a stage in the Maven build lifecycle. Each phase is responsible for a specific task. Here are 
some of the most important phases in the default build lifecycle:

- **validate**: check if all information necessary for the build is available
- **compile**: compile the source code
- **test-compile**: compile the test source code
- **test**: run unit tests
- **package**: package compiled source code into the distributable format (jar, war, …)
- **integration-test**: process and deploy the package if needed to run integration tests
- **install**: install the package to a local repository
- **deploy**: copy the package to the remote repository

> _For the full list of each lifecycle's phases, check out the [**Maven Reference**][Maven_Ref]._

Phases are executed in a specific order. This means that if we run a specific phase using the command 
**``mvn <PHASE>``** it won't only execute the specified phase but all the preceding phases as well. For example, 
if we run the deploy phase **``mvn deploy``**, which is the last phase in the default build lifecycle, that will 
execute all phases before the deploy phase as well, which is the entire default lifecycle.

[Maven_Ref]: https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Lifecycle_Reference

### Goals
Each phase is a sequence of goals, and each goal is responsible for a specific task. When we run a phase, all 
goals bound to this phase are executed in order. Here are some of the phases and default goals bound to them:

- **compiler:compile** the compile goal from the compiler plugin is bound to the compile phase
- **compiler:testCompile** is bound to the test-compile phase
- **surefire:test** is bound to test phase
- **install:install** is bound to install phase
- **jar:jar** and **war:war** is bound to package phase

We can list all goals bound to a specific phase and their plugins using the command 
**``mvn help:describe -Dcmd=PHASENAME``**. For example, to list all goals bound to the **site** phase, we can 
run **``mvn help:describe -Dcmd=site``** which would output info such as:

````
'site' is a phase within the 'site' lifecycle, which has the following phases: 
* pre-site: Not defined
* site: org.apache.maven.plugins:maven-site-plugin:3.3:site
* post-site: Not defined
* site-deploy: org.apache.maven.plugins:maven-site-plugin:3.3:deploy
````

### Maven Plugins
A Maven plugin is a group of goals. However, these goals aren't necessarily all bound to the same phase. We can 
infact control these details to a high degree by encapsulating plugins within specific tags in our **``pom.xml``**.
The tag **``<build>``** is used for plugins which are involved in our compilation of **``.jar``** files, while 
those declared within **``<reporting>``** will be tied to our site phase.

- [**maven-site-plugin**][site_plugin]: The Site Plugin is used to generate a site for the project. The generated site 
  also includes the project's reports that were configured in the POM.
- [**maven-project-info-reports-plugin**][project_info_plugin]: The Maven Project Info Reports plugin is used to 
  generate reports information about the project.
- [**maven-javadoc-plugin**][javadoc_plugin]: The Javadoc Plugin uses the Javadoc tool to generate javadocs for the 
  specified project. For more information about the standard Javadoc tool, please refer to [**Reference Guide**][javadoc_ref].
- [**maven-checkstyle-plugin**][checkstyle_plugin]: The Checkstyle Plugin generates a report regarding the code style 
  used by the developers. For more information about Checkstyle, see [**its official homepage**][checkstyle_ref].
- [**maven-surefire-plugin**][surefire_plugin]: The Surefire Plugin is used during the test phase of the build
  lifecycle to execute the unit tests of an application. It generates reports in two different file formats: **``.txt``** and **``.xml``**.
- [**maven-surefire-report-plugin**][surefire_report_plugin]: The Surefire Report Plugin parses the generated 
  ``TEST-*.xml` files under **``${basedir}/target/surefire-reports``** and renders them using DOXIA, which creates the 
  web interface version of the test results.
- [**maven-jar-plugin**][jar_plugin]: This plugin provides the capability to build jars.
- [**maven-compiler-plugin**][compiler_plugin]: The Compiler Plugin is used to compile the sources of your project.
- [**maven-project-info-reports-plugin**][project_info_reports_plugin]: The Maven Project Info Reports plugin is used 
  to generate reports information about the project.


[site_plugin]: https://maven.apache.org/plugins/maven-site-plugin/
[project_info_plugin]: https://maven.apache.org/plugins/maven-project-info-reports-plugin/
[javadoc_plugin]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[javadoc_ref]: http://docs.oracle.com/javase/7/docs/technotes/tools/windows/javadoc.html
[checkstyle_plugin]: https://maven.apache.org/plugins/maven-checkstyle-plugin/
[checkstyle_ref]: https://checkstyle.org/
[surefire_report_plugin]: https://maven.apache.org/surefire/maven-surefire-report-plugin/
[jar_plugin]: https://maven.apache.org/plugins/maven-jar-plugin/
[compiler_plugin]: http://maven.apache.org/plugins/maven-compiler-plugin/
[project_info_reports_plugin]: https://maven.apache.org/plugins/maven-project-info-reports-plugin/
[surefire_plugin]: https://maven.apache.org/surefire/maven-surefire-plugin/

### The Commands We Will Use
For our purposes we'll mostly rely on the phases **``clean``**, **``verify``**, and **``site``**. All output 
generated from these phases, and their inherent goals, will be placed under directory **``target/``**, for which 
there are one for each module. Since their contents will be generated on local systems, they are excluded from 
version control. It will be a good idea to include **``clean``** for commands you run, since it will 
remove lingering files from previous output. Simply running **``mvn clean``** from repo's root will effectively 
delete all **``target/``** in the project hierarchy.

#### mvn site
The aim has been to minimize the amount of Maven commands you need to learn and in terms of generating reports 
we've consolidated **JavaDoc**, **Checkstyle**, and **Surefire** under the shared **``site``** goal. If you run 
**``mvn clean site``** from the repo's root this will traverse all modules and generate reports for these plugins,
and link necessary pages to one another. You can open the generated site pages from the root's **``target/``**:

````
/target/site/index.html
````

When you open this page in a browser you'll find some general information about the project and its modules,
but the important stuff will be under **Project Reports**, accessed from the navigation menu to the left.
Under the **Overview** of this page you'll find the generated reports but you may notice that only **Surefire Report** 
is available here, while you can only see reports for JavaDoc and Checkstyle if you navigate to the report section of 
respective module. The reason for this lie in how we have configured propagation in the build, where some settings are 
inherited while others are aggregated.

Because of how we have it right now you should always run **``mvn site``* from module directories, such as from the 
folder for **Laboration_2/**. When you do so, all reports will be generated properly and you can access everything by
opening **``Laboration_2/target/site/index.html``* in any browser.

> _When generating JavaDoc, Maven may throw an **``ERROR``** stating broken link to **``javadoc-bundle-options``**.  
> You may ignore this error since it regards creation of JavaDoc **``jar``** for publishing to Maven repositories._
 
> _Note that we use **``_RepoResources/checkstyle.xml``** for our linting purposes. This is a custom ruleset we use in 
> the course, and it's based on the official **``sun_checks.xml``**_

#### mvn verify
In order to build our solutions to executables (**``.jar``**) we can use the Maven command **``mvn clean verify``**.
If ran from the repo's root this will generate a **``.jar``** for each sub module which can be found under 
respective **``target/``**, but you can of course also run the command from modules also. The path for **Laboration 1** 
would thus be:

````
Laboration_1/target/Laboration_1-1.0-SNAPSHOT.jar
````

The **maven-jar-plugin** will make sure we have included a manifest attribute stating which class should be the main
execution. You can execute this **``.jar``**:

````
java -jar path_to_jar.jar
````

So, you would use the following commands with Maven:

| **Command**               | **Description**   |
|:---|:---|
|**``$ mvn clean``**        | _deletes all contents under 'target/'_ |
|**``$ mvn clean verify``** | _runs tests, builds and compiles source files into 'jar'._ |
|**``$ mvn clean site``**   | _runs tests, generates JavaDoc and reports for Checkstyle and Surefire._ |

You can also make more fine grained executions by stating specific goals:

````
$ mvn javadoc:javadoc         # only generate JavaDocs
$ mvn checkstyle:checkstyle   # only run code linting
$ mvn surefire:test           # only run unit tests
````

--------------

## About the Author
- My name is Frank and although I was born in Sweden (to a Swedish mother and British father), the whole family moved to England when I was quite young and thus, I went to school and grew up in England (considering myself, and always feeling, English). I am from a big family (we are 9 children), and we were quite poor growing up in Fleetwood. I am studying and am also a stay at home Dad. My wife and I have 2 boys together and we currently live in Spain. Spain is my ninth country of residence in the past 20 years. I am 43 years old.
- I have no previous programming experience. However, from early on as a child, I played around with code. My earliest experience coding was when my dad bought us an Amstrad CPC464. He did this, not because he wanted us to code, but because it was one of the cheaper options for a video game system at that time (the games ran on audio casettes). However, being curious, my brothers and I ended up coding a little on the machine, one of the advantages of not being able to afford a Nintendo Entertainment System (NES). Again, because we were poor, our next computer was an Amiga 500 (instead of a Sega Mega Drive or a SNES or a Sony Playstation (which were brand new at the time)). Again, the Amiga was bought for games but we ended up coding a little as well. After that, once an adult and being able to buy my own computers, I have dabbled with a little web design and used Linux on and off for the past 20 years. But all said and done, real programming is an eye opener. All that amateur experience has helped me grasp the ideas very quickly however.
- To be quite honest, I don't have big expectations from the course, I just want the degree. To be a coder is something that can be learned by yourself. One of my younger brothers was head of IT at a Swedish company (Sakra Larm), and is now working for SopraSteria, and he learned everything himself online. That isn't to say I don't expect to learn anything here. I will be trying to learn as much as possible, but if the quality is good or bad doesn't really matter as I know that I will be learning lots once I finish here too. The most important thing for me is the degree, just to give me a boost when searching for work, my afformentioned brother was just in the right place at the right time when he landed his head of IT job. I want to work in IT, either coding or Web development.
