# jwp-subway-path

---

# API 명세서

### 노선 추가
POST http://localhost:8080/lines
Content-Type: application/json

```json
{
"name": "4호선",
"lineNumber": 4,
"color": "하늘색"
}
```

### 노선 전체 조회
GET http://localhost:8080/lines

### 노선 조회
GET http://localhost:8080/lines/2

### 노선 삭제
DELETE http://localhost:8080/lines/1

### 역 구간 정보 추가
POST http://localhost:8080/sections
Content-Type: application/json

```json
{
"lineNumber": 2,
"upStation": "잠실역",
"downStation": "매봉역",
"distance": 1
}
```

### 역 구간 정보 삭제
DELETE http://localhost:8080/sections
Content-Type: application/json

```json
{
"lineNumber": 2,
"station": "매봉역"
}
```

### 역 추가
POST http://localhost:8080/stations
Content-Type: application/json

```json
{
"name": "아현역"
}
```

### 역 전체 조회
GET http://localhost:8080/stations

### 역 조회
GET http://localhost:8080/stations/1

### 역 삭제
DELETE http://localhost:8080/stations/1