# CareFLi
<div align="center">
선물을 고르고 축하 문구를 고민하는 데 들이는 시간을 줄이고 싶으신가요?

**케어플리**는 **LLM을 활용한 MBTI별 맞춤형 선물 추천 서비스**로,  
정성과 마음이 담긴 선물과 문구를 손쉽게 준비할 수 있도록 도와드립니다! 🎁
</div>

<div align="center">

<img width="50" alt="케어플리_1차로고_최종 1@2x" src="https://github.com/user-attachments/assets/d991ace5-3abd-4c3e-b098-0b9101eb617f" /><br><br>**케어플리**와 함께라면, 선물을 고르는 고민은 줄어들고,<br>더 많은 시간을 상대방을 위해 쓸 수 있습니다.<br>
지금 바로 케어플리를 이용해보세요!
</div>
<p align="center">
  <a href="#How to Build">How to Build</a> •
  <a href="#How to Install">How to Install</a> •
  <a href="#How to Test">How to Test</a>
</p>

## 1️⃣ <a id="How to Build">How to Build</a>

### 1. Git Clone
원하는 곳에 프로젝트를 클론하세요.
```bash
git clone https://github.com/uuuueong/carefli-back.git 
```

### 2. Repository로 이동
```bash
cd carefli-back
```

### 3. Gradle Build
```bash
./gradlew clean build
```
### 4. 빌드 결과 확인
```bash
ls build/libs
# 출력 예시: carefli-back-0.0.1-SNAPSHOT.jar
```

## 2️⃣ <a id="How to Install">How to Install</a>

### 1. Java 17 설치
#### For macOS
```bash
brew install openjdk@17
```
#### For Ubuntu/Linux
```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

### 2. MySQL 설치
#### For macOS
```bash
brew install mysql
brew services start mysql
```

### 3. 서버 실행
#### 1. 환경 설정
application.yml 파일에서 데이터베이스 및 API 키를 설정하세요.
application.yml 파일은 따로 이메일을 주시면 알려드립니다.

#### 2. 빌드된 `.jar` 파일 실행
```bash
java -jar build/libs/carefli-back-0.0.1-SNAPSHOT.jar
```

## 3️⃣ <a id="How to Test">How to Test</a>


---
추가 문의 사항 : [이메일](smileji86@gmail.com)
