# QuizApp

QuizApp is a console-based quiz management application built in Core Java. It supports two roles:

- `ADMIN` users can manage questions, users, and score records.
- `USER` users can register, log in, take quizzes, and review their results.

The application stores its data locally using Java serialization in `.dat` files, so quiz content, users, and scores persist between runs.

## Features

- User registration and login
- Role-based access control with `ADMIN` and `USER`
- Category-based quizzes
- Multiple-choice questions with 4 options each
- Automatic score calculation and timestamped score history
- Leaderboard sorted by percentage
- Admin tools to:
  - add questions
  - view all questions
  - update questions
  - delete questions
  - view users
  - delete users
  - add admins
  - view all scores

## How It Works

### Application Flow

1. The app starts from [`src/app/Main.java`](src/app/Main.java).
2. `MenuService` asks whether the person is an existing user or a new user.
3. New users register as `USER`.
4. Existing users log in and are routed by role:
   - `ADMIN` users see admin actions
   - `USER` users see quiz/player actions

### User Flow

When a normal user logs in, they can:

- start a quiz in one of the available categories
- view previous scores
- view the leaderboard

During a quiz:

- the user selects a category
- questions from that category are loaded
- the list is shuffled
- up to 20 questions are asked
- answers are checked immediately
- the final score percentage is calculated and saved

### Admin Flow

When an admin logs in, they can manage the full quiz system:

- create questions
- list all questions
- update a question by ID
- delete a question by ID
- list all users
- find a user by ID
- view all scores
- view scores for a specific user
- register a new admin
- delete a user and their scores
- view the leaderboard

## Concepts Used

### Core Java

- `Scanner` for console input
- `List`, `ArrayList`, `ListIterator`
- `Stream` API for filtering and sorting
- `Comparator` for leaderboard ordering
- `Collections.shuffle()` for randomizing quiz order

### Object-Oriented Programming

- Encapsulation through model classes
- Service and repository separation
- Role-based behavior using `enum`

### Persistence

- Object serialization with `ObjectOutputStream`
- Deserialization with `ObjectInputStream`
- Local file storage for:
  - `users.dat`
  - `questions.dat`
  - `scores.dat`

### Data Modeling

- `User` stores login and role information
- `Question` stores the quiz prompt, options, correct option, and category
- `Score` stores user ID, category, total questions, correct answers, percentage, and timestamp

## Project Structure

```text
src/
  app/
    Main.java
  exception/
    InvalidCategoryException.java
  model/
    Category.java
    QCategory.java
    Question.java
    Role.java
    Score.java
    User.java
  repository/
    QuestionRepo.java
    ScoreRepo.java
    UserRepo.java
  service/
    AuthService.java
    MenuService.java
    QuestionService.java
    QuizService.java
    ScoreService.java
    UserService.java
questions.dat
scores.dat
users.dat
```

## How To Run

### Prerequisites

- Java JDK 14 or later
- Any IDE that supports Java projects, such as Eclipse or IntelliJ IDEA

### Run From An IDE

1. Import the project as a Java project.
2. Make sure `src` is on the classpath.
3. Run [`src/app/Main.java`](src/app/Main.java).

### Run From Terminal

If you want to compile and run manually from PowerShell:

```bash
New-Item -ItemType Directory -Force bin | Out-Null
$sources = Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName }
javac -d bin $sources
java -cp bin app.Main
```

## Data Files

The application reads and writes these files in the project root:

- `users.dat`
- `questions.dat`
- `scores.dat`

These files are part of the app’s local persistence layer. If you want to reset the app state, delete them and run the application again.

## Notes

- Question categories currently include:
  - Java
  - MySQL
  - HTML
  - CSS
  - JavaScript
- Scores are sorted by percentage for the leaderboard.
- Admin and user access are determined by the `Role` enum.

## Future Improvements

- Add password hashing instead of plain-text storage
- Add input validation and exception handling for invalid menu choices
- Replace file serialization with a database
- Add support for more question types
- Add pagination or filtering for score history
