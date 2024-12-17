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
  <a href="#how-to-build">How to Build</a> •
  <a href="#how-to-install">How to Install</a> •
  <a href="#how-to-test">How to Test</a> •
  <a href="#sample-data">Sample Data</a>
</p>

## 1️⃣ <a id="how-to-build">How to Build</a>

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

## 2️⃣ <a id="how-to-install">How to Install</a>

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

## 3️⃣ <a id="how-to-test">How to Test</a>
배포된 URL을 통해 테스트할 수 있습니다.
Base URL : `https://api.carefli.p-e.kr`

### **Postman 다운로드**  

API를 테스트하려면 Postman이 필요합니다. 아래 링크에서 Postman을 다운로드하세요:  

🔗 [Postman 다운로드](https://www.postman.com/downloads/)

### 🎁 **선물 추천 목록 조회**

#### **요청 생성**  
- **Method**: `GET`  
- **URL**: `https://api.carefli.p-e.kr/gifts/recommendations/3`  

#### **요청 보내기**  
1. Postman을 실행합니다.  
2. **Method**를 `GET`으로 설정하고, **URL**에 `https://api.carefli.p-e.kr/gifts/recommendations/3`를 입력합니다.  
3. **Send** 버튼을 클릭합니다.

#### **응답 예시**  
```json
{
    "recommendationSetId": 3,
    "userId": 17,
    "connectionId": 8,
    "occasionType": "생일",
    "recommendedGifts": [
        {
            "giftId": 11,
            "category": "뷰티",
            "subCategory": "화장품",
            "giftName": "멜릭서 비건 립 버터 듀오 (12종 중 택2)",
            "price": 24000,
            "giftUrl": "https://gift.kakao.com/product/3712419?tab=review&sortProperty=SCORE",
            "giftImageUrl": "https://carefli-github-actions-s3-bucket.s3.ap-northeast-2.amazonaws.com/giftImages/%5B%E1%84%83%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A9%E1%86%A8%3A%E1%84%89%E1%85%A5%E1%86%AB%E1%84%86%E1%85%AE%E1%86%AF%E1%84%91%E1%85%A9%E1%84%8C%E1%85%A1%E1%86%BC%5D+%E1%84%87%E1%85%B5%E1%84%80%E1%85%A5%E1%86%AB+%E1%84%85%E1%85%B5%E1%86%B8+%E1%84%87%E1%85%A5%E1%84%90%E1%85%A5+%E1%84%83%E1%85%B2%E1%84%8B%E1%85%A9+(12%E1%84%8C%E1%85%A9%E1%86%BC+%E1%84%8C%E1%85%AE%E1%86%BC+%E1%84%90%E1%85%A2%E1%86%A82).jpeg",
            "createdAt": null
        },
        {
            "giftId": 25,
            "category": "디저트",
            "subCategory": "디저트",
            "giftName": "스타벅스 부드러운 티라미수 롤케이크",
            "price": 22900,
            "giftUrl": "https://gift.kakao.com/product/6508069",
            "giftImageUrl": "https://carefli-github-actions-s3-bucket.s3.ap-northeast-2.amazonaws.com/giftImages/%22%E1%84%83%E1%85%A1%E1%86%AF%E1%84%8F%E1%85%A9%E1%86%B7%E1%84%92%E1%85%A1%E1%86%AB+%E1%84%8E%E1%85%A9%E1%84%8F%E1%85%A9+%E1%84%89%E1%85%B5%E1%84%90%E1%85%B3%22+%E1%84%89%E1%85%B3%E1%84%90%E1%85%A1%E1%84%87%E1%85%A5%E1%86%A8%E1%84%89%E1%85%B3+%E1%84%87%E1%85%AE%E1%84%83%E1%85%B3%E1%84%85%E1%85%A5%E1%84%8B%E1%85%AE%E1%86%AB+%E1%84%90%E1%85%B5%E1%84%85%E1%85%A1%E1%84%86%E1%85%B5%E1%84%89%E1%85%AE+%E1%84%85%E1%85%A9%E1%86%AF%E1%84%8F%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%8F%E1%85%B3.jpeg",
            "createdAt": null
        },
        {
            "giftId": 77,
            "category": "디지털/가전",
            "subCategory": "가전",
            "giftName": "오아 감성 블루투스 무선스피커 아이브릭 미니 스피커(OABTSPEAKER2)",
            "price": 22900,
            "giftUrl": "https://gift.kakao.com/product/507374",
            "giftImageUrl": "https://carefli-github-actions-s3-bucket.s3.ap-northeast-2.amazonaws.com/giftImages/%5B%E1%84%8C%E1%85%A1%E1%84%8E%E1%85%B1%3A%E1%84%8C%E1%85%B5%E1%86%B8%E1%84%83%E1%85%B3%E1%86%AF%E1%84%8B%E1%85%B5%E1%84%89%E1%85%A5%E1%86%AB%E1%84%86%E1%85%AE%E1%86%AF%5D+%E1%84%80%E1%85%A1%E1%86%B7%E1%84%89%E1%85%A5%E1%86%BC+%E1%84%87%E1%85%B3%E1%86%AF%E1%84%85%E1%85%AE%E1%84%90%E1%85%AE%E1%84%89%E1%85%B3+%E1%84%86%E1%85%AE%E1%84%89%E1%85%A5%E1%86%AB%E1%84%89%E1%85%B3%E1%84%91%E1%85%B5%E1%84%8F%E1%85%A5+%E1%84%8B%E1%85%A9%E1%84%8B%E1%85%A1+%E1%84%8B%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%87%E1%85%B3%E1%84%85%E1%85%B5%E1%86%A8+%E1%84%86%E1%85%B5%E1%84%82%E1%85%B5+%E1%84%89%E1%85%B3%E1%84%91%E1%85%B5%E1%84%8F%E1%85%A5.jpeg",
            "createdAt": null
        }
    ]
}
```
<img width="928" alt="스크린샷 2024-12-17 오후 3 26 08" src="https://github.com/user-attachments/assets/90127e71-01fc-4f09-b21e-760ac5d0f280" />

## 4️⃣ <a id="sample-data">Sample Data</a>

### 1. 선물 CSV 파일 다운로드
로컬에서 실행하는 경우, 아래 CSV 파일을 MySQL에 직접 Import 후 선물 데이터베이스를 구축할 수 있습니다. <br>
배포된 주소에서는 이미 선물 데이터베이스가 구축되어 있습니다. <br>
[gift.csv 다운로드](./gift_data.csv)

### 2. MySQL 테이블 생성
CSV 파일 구조에 맞는 MySQL 테이블을 먼저 생성해야 합니다.

```sql
CREATE TABLE gift (
    gift_id INT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(50) NOT NULL,
    sub_category VARCHAR(50),
    gift_name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    occasion_type VARCHAR(50),
    gift_url VARCHAR(500),
    gift_image_url VARCHAR(500)
);
```

### 3. CSV 파일 Import

```sql
LOAD DATA LOCAL INFILE '/경로/to/gift.csv'
INTO TABLE gift
FIELDS TERMINATED BY '\t' -- CSV가 탭으로 구분된 경우
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(category, sub_category, gift_name, price, occasion_type, gift_url, gift_image_url);
```

### 추가 내용 (로컬 실행)
만약 로컬 환경에서 직접 실행하고 싶다면 아래의 단계를 따르세요.

#### 1. Gradle로 로컬 서버 실행
```bash
./gradlew bootRun
```

#### 2. 로컬 접속
브라우저에서 `http://localhost:8080`으로 접근합니다.

---
추가 문의 사항 : [이메일](smileji86@gmail.com)
