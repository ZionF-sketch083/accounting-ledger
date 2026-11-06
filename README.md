# Bread Bank

A console app of the bank that keep track of all of your transactions you made in a csv file 
and allow you to report on transactions and have it be structure by either the month or year.

<!-- HOW TO USE THIS TEMPLATE
     Save it as README.md at the root of your project, next to pom.xml. Replace every comment like this one
     with your own text; comments do not show on GitHub, so what you leave untouched shows as an empty heading.
     Two sections are optional and say so: Class Diagram, and Data and configuration. When your project does not
     need one, delete the whole section, heading included. Every other section stays. -->

<!-- One sentence under the title: what the app is, for whom.
     Example: A console app that keeps a store's inventory in a csv file and lets a clerk search it. -->

## Description of the Project

This version of Bread Bank is a Java console application for someone who want to keep track of their transactions 
that they made. Each transaction has a date, time, description, vendor of the transaction, and the amount. The app will 
read from a file when booted up, let the user add deposits and payments, and allow them to look at the list of either 
if they want to only look the deposits, payments, or everything. They are also allow to give out reports to
the list of transactions to have them structure to only show the transaction of the month or year or the previous month 
or previous year.

<!-- Three to five sentences. What the application does, who uses it, what problem it solves.
     Write it for someone who has never seen the workbook: a recruiter, a classmate, you in six months. -->

## Features

<!-- One bullet per thing the app can do, in the order of your menu. Mark bonuses.
     For an API (Capstone 3), one line per endpoint instead: method, path, what it does.
     Be honest: list what works. If something is half done, say so under Future Work instead.
     Example:
     - Shows every product in the store
     - Searches products by name (bonus) -->

- Have it where it shows the dates, times, descriptions, sellers and prices of said transactions
- Make sure to have the deposits and payments show by date
- Have the transactions be filtered by the beginning of the year to the current date or transactions from 
the previous year, or from the beginning of the month to current date or transactions from the previous month.

## User Stories

<!-- The stories you worked from, one per bullet, in the format you learned in Workbook 3b:
     As a [type of user], I want [some goal] so that [some reason].
     Same text as the cards on your GitHub project board. -->

- As a user, I want to see all my transactions with the date, time, description, seller and price, 
so that I will be notified by a purchase that I didn't authorize.
- As a user, I want to locate transactions from either this year or the previous year or this month, or the previous, 
so that I could find the transactions easier.

## Setup

### Prerequisites

- Java 17 (Amazon Corretto), the version used in class.
- IntelliJ IDEA Community Edition: <https://www.jetbrains.com/idea/download/>

### Running the application

1. Clone this repository, or download it as a zip and unzip it.
2. In IntelliJ: File > Open, pick the project folder (the one that holds `pom.xml`), press Open.
3. Wait for IntelliJ to finish indexing (progress bar at the bottom).
4. Open `src/main/java/com/pluralsight/capstone/accounting-ledger.java`.
5. Right-click inside the file and choose Run 'FinancialTracker.main()'.

### Data and configuration

- 'transactions.csv' is rooted on the top bar. The program needs this file so it could put all the transactions at and 
read off of with prompt to list it out.

<!-- OPTIONAL. Anything the program needs that is not code: data files and where they must sit, files the
     program creates, a database script to run first, a settings file to edit (connection, port).
     If there is nothing, delete this subsection, heading included.
     Example:
     - `inventory.csv` sits at the project root, next to `pom.xml`. The program reads it at start.
     - `logs.txt` is created by the program at the project root on the first run. -->

-

## Technologies Used

- Java 17
- IntelliJ IDEA
<!-- Add anything else you used: a library, Maven plugins, an external tool. For a console app this list is short. That is fine. -->

## Demo

<!-- Pictures of the app running: the console for a console app, Insomnia or Swagger for an API.
     Screenshots are enough; a GIF is a bonus.
     1. Create a folder named `images` at the project root, next to `pom.xml`.
     2. Take screenshots of the app in the IntelliJ console (the menu, one feature, one error message):
          Windows: Win + Shift + S, drag a rectangle, then paste into Paint and save as PNG, or open the
                   notification and save. Save into the `images` folder.
          Mac:     Shift + Cmd + 4, drag a rectangle. The file lands on the Desktop; move it into `images`.
     3. Name the files by what they show: `images/menu.png`, `images/search.png`.
     4. Reference each one below with a relative path. No spaces in file names.
     5. Commit the images with the rest of your work (Git > Commit shows them as unversioned files: tick them).
     Optional GIF: LICEcap (free, Windows and Mac, <https://www.cockos.com/licecap/>) records a region of the
     screen straight to a .gif. Keep it under 10 seconds and under 10 MB, save it into `images`. -->

![Main menu](images/menu.png)

![One feature in action](images/feature.png)

## A piece of code I am proud of

         

<!-- Paste one short block of your own code (5 to 15 lines) between the three backticks, then write two
     or three sentences: what it does and why you chose to write it that way. This is what you show on
     demo day and what you talk about in an interview. -->

      if (amount < 0) {
         System.out.println("Your payment must be a positive number.");
         return;
         }
           amount = -amount;
            LocalDate date = LocalDate.parse(dateTime.split(" ")[0]);
            LocalTime time = LocalTime.parse(dateTime.split(" ")[1]);
            transactions.add(new Transaction(date, time, description, vendor, amount));
            bw.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount);
            bw.newLine();
            bw.close();         

This piece of code is where, depending on the user input, the program will separate the transaction in 5 parts
it would then put "|" in between the parts. I use the parse to split the datetime into two separate variables. 


## Future Work

<!-- What you would add or fix next, one bullet each. Unfinished bonuses belong here. -->

- Sort the search result by the user choice

## Resources

<!-- What helped you: the workbook, the week's hub pages, a tutorial, a documentation page. Real links. -->

- Java YearUp Fall 2026 hub: <https://raymaroun.github.io/yearup-java-fall2026/>
- Java Visual Hub, Capstone 1, Build Guide and the Commit Message: https://raymaroun.github.io/yearup-java-fall2026/week-04/index.html
- Microsoft Copilot AI agent: https://copilot.cloud.microsoft/chat?fromcode=cmm5vstpudj&es=UnauthClick&redirfrom=ccAadReentry&redirfrom=userTypeCookie&redirfrom=cosmicRingCookie

## Author

<!-- Your name and, if you like, your GitHub profile link. For a team workshop, one line per person with what each one built. -->

- **Zion C. Freeman**

## Thanks

<!-- Optional. The people who helped: the instructor, a classmate who debugged with you, a mentor. -->

- Thank you to Raymond for providing a skeleton of the code(or a layout of the code) that made programing a bit easier.
- Thank you to Adrian and Maalik for checking my code and giving some advises for my code.