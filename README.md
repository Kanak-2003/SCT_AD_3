# ⏱️ Stopwatch App

A basic **Stopwatch App** built as part of my Android Development Internship at **SkillCraft Technology**.

## 🚀 Features

- Displays **Minutes:Seconds:Milliseconds**
- **Start** the stopwatch
- **Pause** the timer
- **Reset** to zero
- Smooth real-time updates using `Handler` and `Runnable`

## 🛠️ Tech Stack

- **Java**
- **XML (Android Layout)**
- **Android Studio**
- `Handler` and `Runnable` for time updates

## 🧠 How it Works

- Uses `System.currentTimeMillis()` to calculate elapsed time
- Updates UI every 10 milliseconds using `Handler.postDelayed()`
- State maintained across start, pause, and reset actions
