# 🚀 Duplicate Application Finder (Java)

A Java-based GUI application that detects duplicate files using content-based hashing (MD5), allows safe deletion, and organizes files into categories automatically.

---

## 📌 Features

* 🔍 Detect duplicate files using MD5 hashing
* 🗑 Delete duplicate files safely with confirmation
* 📂 Organize files into categories:

  * Images
  * Documents
  * Music
  * Videos
* 🖥 User-friendly GUI built with Java Swing
* ⚡ Fast and efficient file processing

---

## 🛠 Technologies Used

* Java
* Swing (GUI)
* File Handling
* Hashing (MD5)
* Data Structures (HashMap, ArrayList)

---

## 📂 Project Structure

```
DuplicateFileFinder/
 ├── GUIApp.java
 ├── FileProcessorGUI.java
 ├── FileProcessor.java
 ├── FileOrganizer.java
 ├── HashUtil.java
```

---

## 🚀 How to Run

1. Clone the repository:

```
git clone https://github.com/Srijakandimalla/Duplicate-Application-Finder-Java.git
```

2. Open the project in VS Code or any Java IDE

3. Run:

```
GUIApp.java
```

4. Use the application:

* Click **Browse** → Select folder
* Click **Scan** → Detect duplicates
* Click **Delete** → Remove duplicates
* Click **Organize** → Arrange files

---

## 🎯 Example Output

```
Duplicate Found:
✔ Original: file1.txt
❌ Duplicate: copy_file1.txt

🗑 Deleted: copy_file1.txt

📂 Organizing files...
➡ Moved: file1.txt → Documents
```

---

## 💡 Key Concepts Used

* File hashing for duplicate detection
* File handling and directory traversal
* GUI-based interaction using Swing
* Rule-based file organization

---

## 👩‍💻 Author

**Srija Kandimalla**
